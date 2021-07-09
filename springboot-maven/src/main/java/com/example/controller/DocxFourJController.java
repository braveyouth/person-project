package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.util.DocImageHandler;
import com.example.util.Docx4jUtil;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangy
 * @Time 2021-07-07 15:20
 * @Description:
 */
@RestController
@RequestMapping("/docx4j")
public class DocxFourJController {

    private static Logger logger = LoggerFactory.getLogger(DocxFourJController.class);

    @Value("${docxPath}")
    private String docxPath;
    @Value("${picPath}")
    private String picPath;

    /**
     * 基础创建:创建一个新的docx文档
     * 获取文档可操作对象
     */
    @GetMapping("/createDocx")
    public String createDocx(){
        WordprocessingMLPackage wordMLPackage = null;
        if(!StringUtils.isEmpty(docxPath)) {
            try {
                wordMLPackage =  WordprocessingMLPackage.createPackage();
                wordMLPackage.save(new File(docxPath));
//                Docx4J.save(wordMLPackage, new File(docxPath));
            } catch (InvalidFormatException e) {
                logger.error(e.getMessage());
            }catch (Docx4JException e){
                logger.error(e.getMessage());
            }
        }
        return "基础创建成功";
    }

    /**
     * 追加文档内容
     * 向文档中追加内容(默认支持中文)
     * 先清空,再生成,防重复
     */
    @GetMapping("/addParagraph")
    public String addParagraph() {
        WordprocessingMLPackage wordprocessingMLPackage = null;
        try {
            //先加载word文档
            wordprocessingMLPackage = WordprocessingMLPackage.load(new File(docxPath));
//            wordprocessingMLPackage = Docx4J.load(new File(docxPath));

            //增加内容
            wordprocessingMLPackage.getMainDocumentPart().addParagraphOfText("你好!");
            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "这是标题!");
            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle", " 这是二级标题!");

            wordprocessingMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subject", "试一试");
            //保存文档
            wordprocessingMLPackage.save(new File(docxPath));
        } catch (Docx4JException e) {
            logger.error("addParagraph to docx error: Docx4JException", e);
        }
        return "追加文档内容成功";
    }

    /**
     * 插入图片
     */
    @GetMapping("/wordInsertImage")
    public String wordInsertImage() {
        WordprocessingMLPackage wordprocessingMLPackage = null;
        File file = new File(picPath);
        try {
            wordprocessingMLPackage = WordprocessingMLPackage.load(new File(docxPath));
            byte[] bytes = DocImageHandler.convertImageToByteArray(file);
            DocImageHandler.addImageToPackage(wordprocessingMLPackage, bytes);
            wordprocessingMLPackage.save(new File(docxPath));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "插入图片成功";
    }

    /**
     * 读取docx文件(这里不支持doc文件)
     * 读取word文件，这里没有区分 word中的样式格式
     */
    @GetMapping("readParagraph")
    public String readParagraph() {
        List<Object> list = new ArrayList<>();
        WordprocessingMLPackage wordprocessingMLPackage = null;
        try {
            wordprocessingMLPackage = WordprocessingMLPackage.load(new File(docxPath));

            String contentType = wordprocessingMLPackage.getContentType();
            logger.info("contentType:"+contentType);
            MainDocumentPart part = wordprocessingMLPackage.getMainDocumentPart();
            logger.info("content -> body -> "+part.getContents().getBody().toString());
            list = part.getContent();
            for(Object o :list) {
                logger.info("info:"+o);
            }
        }catch(Exception e) {
            logger.error(e.getMessage());
        }
        String jsonString = JSON.toJSONString(list);
        return jsonString;
    }

    /**
     * docx转html文件
     * 样式表可以自行修改
     * 转xls,再转html
     */
    @GetMapping("/wordToHtml")
    public String wordToHtml() {
        boolean nestLists = true;
        boolean save = true;
        WordprocessingMLPackage wordprocessingMLPackage = null;
        try {
            wordprocessingMLPackage = WordprocessingMLPackage.load(new File(docxPath));
        } catch (Docx4JException e) {
           logger.error(e.getMessage());
        }

        HTMLSettings html = Docx4J.createHTMLSettings();
        //设置图片的目录地址
        html.setImageDirPath(docxPath + "_files");
        html.setImageTargetUri(docxPath.substring(docxPath.lastIndexOf("/") + 1 ) + "_files");
        html.setWmlPackage(wordprocessingMLPackage);
        String userCSS = null;
        if (nestLists) {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  table, caption, tbody, tfoot, thead, tr, th, td "
                    + "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";
        } else {
            userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td "
                    + "{ margin: 0; padding: 0; border: 0;}" + "body {line-height: 1;} ";
        }
        html.setUserCSS(userCSS);
        OutputStream os = null;
        try {
            if (save) {
                os = new FileOutputStream(docxPath + ".html");
            } else {
                os = new ByteArrayOutputStream();
            }
            //设置输出
            Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

            Docx4J.toHTML(html, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        }catch(Exception e) {

        }
        if (save) {
            System.out.println("Saved: " + docxPath + ".html ");
        } else {
            System.out.println(((ByteArrayOutputStream) os).toString());
        }
        if (wordprocessingMLPackage.getMainDocumentPart().getFontTablePart() != null) {
            wordprocessingMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
        }
        return "docx转html文件成功";
    }

    /**
     * docx转换为pdf
     */
    @GetMapping("/wordToPdf")
    public String wordToPdf() throws FileNotFoundException {
        WordprocessingMLPackage wordMLPackage = null;
        try {
            wordMLPackage = WordprocessingMLPackage
                    .load(new File(docxPath));
            Docx4J.toPDF(wordMLPackage, new FileOutputStream(new File(docxPath + ".pdf")));
        } catch (Docx4JException e) {
            logger.error(e.getMessage());
        }
        return "docx转换为pdf成功";
    }




    @GetMapping("downloadWord")
    public void downloadWord(HttpServletResponse response) throws Docx4JException, JAXBException, FileNotFoundException {
        //模板文件路径
//        String path = this.getClass().getClassLoader().getResource("D://template//test.docx").getPath();
        String path = docxPath;
        //模板中要生成表格的数据
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String, String> m = new HashMap<>();
            m.put("name", "姓名"+i);
            m.put("sex", "性别"+i);
            m.put("age", "年龄"+i);
            m.put("bz", "备注"+i);
            m.put("xx", "详细"+i);
            list.add(m);
        }
        list.stream();

        //模板中要插入图片的数据
//        byte[] img = null;
//        try (InputStream input = new FileInputStream(this.getClass().getClassLoader().getResource("template/timg.jpg").getPath())){
//            img = new byte[input.available()];
//            input.read(img);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //要插入的map数据
        Map<String, String> m = new HashMap<>();
        m.put("today", LocalDate.now().toString());
        m.put("active", "游泳");

        //处理好数据后就是超级简单的调用
        byte[] bytes = Docx4jUtil.of(path)
                .addParam("title", "测试文档标题")
                .addParam("user", "测试人")
                .addParams(m)
                .addTable("name", 2, list)
//                .addImg("img", img)
                .get();

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.flush();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}

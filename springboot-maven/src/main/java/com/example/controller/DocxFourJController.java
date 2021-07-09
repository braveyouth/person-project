package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.util.DocImageHandler;
import com.example.util.Docx4jUtil;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.finders.ClassFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

/**
 * @author zhangy
 * @Time 2021-07-07 15:20
 * @Description:
 */
@RestController
@RequestMapping("/docx4j")
public class DocxFourJController {

    private static Logger logger = LoggerFactory.getLogger(DocxFourJController.class);

    private static WordprocessingMLPackage wordMLPackage;
    private static ObjectFactory  factory;

    @Value("${docxPath}")
    private String docxPath;
    @Value("${picPath}")
    private String picPath;
    @PostConstruct
    public void init(){
        logger.info("docx4j服务,{}", true);
    }

    /**
     * 基础创建:创建一个新的docx文档
     * 获取文档可操作对象
     */
    @GetMapping("/createDocx")
    public String createDocx(){
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
        try {
            //先加载word文档
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));
//            wordMLPackage = Docx4J.load(new File(docxPath));

            //增加内容
            wordMLPackage.getMainDocumentPart().addParagraphOfText("你好!");
            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "这是标题!");
            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle", " 这是二级标题!");

            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subject", "试一试");
            //保存文档
            wordMLPackage.save(new File(docxPath));
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
        File file = null;
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));
            file = new File(picPath);
            byte[] bytes = DocImageHandler.convertImageToByteArray(file);
            DocImageHandler.addImageToPackage(wordMLPackage, bytes);
            wordMLPackage.save(new File(docxPath));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "插入图片成功";
    }

    /**
     * 创建表格
     */
    @GetMapping("addTable")
    public String addTable() {
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));
            MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();

            factory = Context.getWmlObjectFactory();
            // 1 创建表格元素
            Tbl table = factory.createTbl();
            //2 显示表格的边框
            addBorders(table);

            //3 添加表格内容(创建行和列)
            for (int i = 0; i < 3; i++) {
                Tr tr = factory.createTr();
                for (int j = 0; j < 3; j++) {
                    Tc tc = factory.createTc();

                    //3.1
//                    P p = mainDocumentPart.createParagraphOfText("---row" + i + "---column" + j + "---");

                    //3.2 第二种创建P并设置样式的方法
                    P p = factory.createP();
                    R r = factory.createR();
                    Text text = factory.createText();
                    text.setValue("---row" + i + "---column" + j + "---");
                    r.getContent().add(text);
                    p.getContent().add(r);
                    //3.2.1 通过R设置字体加粗等属性
                    setRStyle(r);
                    //3.2.2 设置列宽
                    if (j == 1) {
                        setCellWidth(tc, 1250);
                    } else {
                        setCellWidth(tc, 2500);
                    }

                    tc.getContent().add(p);
                    tr.getContent().add(tc);
                }
                table.getContent().add(tr);
            }

            //4 将新增表格加到主要内容中
            mainDocumentPart.addObject(table);
            wordMLPackage.save(new File(docxPath));
        } catch (Docx4JException e) {
            logger.error("createDocx error: Docx4JException", e);
        }
        return "创建表格成功";
    }

    /**
     * 读取表格内容
     */
    @GetMapping("readTable")
    public String readTable(){
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

            // 1. ClassFinder构造类型查询器获取指定元素
            ClassFinder find = new ClassFinder(Tbl.class);
            new TraversalUtil(documentPart.getContent(), find);

            // 获取到第一个表格元素
            Tbl table = (Tbl) find.results.get(0);
            List<Object> trs = table.getContent();
            logger.info("{}", trs);

            System.out.println("=====================");

            for (Object obj : trs) {
                Tr tr = (Tr) obj;// 获取到tr
                List<Object> content = tr.getContent();
                logger.info("{}", content);
                List<Object> objList = getAllElementFromObject(tr, Tc.class);// 获取所有的Tc元素
                for (Object obj1 : objList) {
                    Tc tc = (Tc) obj1;
                    logger.info("{}", tc.getContent());
                }
                System.out.println("===============");
            }
        } catch (Docx4JException e) {
            logger.error(e.getMessage());
        }
        return "读取表格内容";
    }


    /**
     * 读取docx文件(这里不支持doc文件)
     * 读取word文件，这里没有区分 word中的样式格式
     */
    @GetMapping("readParagraph")
    public String readParagraph() {
        List<Object> list = new ArrayList<>();
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));

            String contentType = wordMLPackage.getContentType();
            logger.info("contentType:"+contentType);
            MainDocumentPart part = wordMLPackage.getMainDocumentPart();
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
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));
        } catch (Docx4JException e) {
           logger.error(e.getMessage());
        }

        HTMLSettings html = Docx4J.createHTMLSettings();
        //设置图片的目录地址
        html.setImageDirPath(docxPath + "_files");
        html.setImageTargetUri(docxPath.substring(docxPath.lastIndexOf("/") + 1 ) + "_files");
        html.setWmlPackage(wordMLPackage);
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
        if (wordMLPackage.getMainDocumentPart().getFontTablePart() != null) {
            wordMLPackage.getMainDocumentPart().getFontTablePart().deleteEmbeddedFontTempFiles();
        }
        return "docx转html文件成功";
    }

    /**
     * docx转换为pdf
     */
    @GetMapping("/wordToPdf")
    public String wordToPdf() throws FileNotFoundException {
        try {
            wordMLPackage = WordprocessingMLPackage
                    .load(new File(docxPath));
            Docx4J.toPDF(wordMLPackage, new FileOutputStream(new File(docxPath + ".pdf")));
        } catch (Docx4JException e) {
            logger.error(e.getMessage());
        }
        return "docx转换为pdf成功";
    }

    /**
     * 按指定变量替换docx中的内容  ${var}替换
     */
    @GetMapping("replaceTableByVariable")
    public String replaceTableByVariable(){
        factory = Context.getWmlObjectFactory();
        boolean save = true;
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(docxPath));
            VariablePrepare.prepare(wordMLPackage);
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

            //需要替换的map
            HashMap<String, String> mappings = new HashMap<String, String>();
            mappings.put("name", "张三");
            mappings.put("age", "25");
            mappings.put("sex", "男");

            long start = System.currentTimeMillis();
            documentPart.variableReplace(mappings);
//            // unmarshallFromTemplate requires string input
//            String xml = XmlUtils.marshaltoString(documentPart.getJaxbElement(), true);
//            // Do it...
//            Object obj = XmlUtils.unmarshallFromTemplate(xml, mappings);
//            // Inject result into docx
//            documentPart.setJaxbElement((Document) obj);
            long end = System.currentTimeMillis();
            long total = end - start;
            System.out.println("Time: " + total);

            // Save it
            if (save) {
                SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
                saver.save("/home/person-project/helloworld_1.docx");
            } else {
                System.out.println(XmlUtils.marshaltoString(documentPart.getJaxbElement(), true, true));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "按指定变量替换docx中的内容成功";
    }


    /**
     * 下载
     * @param response
     */
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

    /**
     * 设置边框样式
     * 需要设置表格边框的单元格
     * @param table
     */
    private static void addBorders(Tbl table) {
        // 必须设置一个TblPr，否则最后会报空指针异常
        table.setTblPr(new TblPr());

        // 创建一个默认颜色（黑色）、粗细尺寸为4、间距为0的单线边框的边框组件（Border component）
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);

        // 边框组件被应用到表格的四周以及表格内部水平和垂直的边框
        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);

        // 获取其内部的TblPr属性设置属性,边框应用到表格
        table.getTblPr().setTblBorders(borders);
    }

    /**
     * 通过设置R设置表格中属性字体加粗，大小为25
     * @param
     */
    private static void setRStyle(R r) {
        // 1.创建一个RPr
        RPr rpr = new RPr();

        // 2.设置RPr
        // 2.1设置字体大小
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger("25"));
        rpr.setSz(size);
        // 2.2设置加粗
        BooleanDefaultTrue bold = new BooleanDefaultTrue();
        bold.setVal(true);
        rpr.setB(bold);

        // 3.将RPr设置为R的属性
        r.setRPr(rpr);
    }

    /**
     * 设置列宽
     * @param tc
     * @param width
     */
    private static void setCellWidth(Tc tc, int width) {
        TcPr tableCellProperties = new TcPr();
        TblWidth tableWidth = new TblWidth();
        tableWidth.setW(BigInteger.valueOf(width));
        tableCellProperties.setTcW(tableWidth);

        tc.setTcPr(tableCellProperties);
    }

    private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement)
            obj = ((JAXBElement<?>) obj).getValue();
        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }
        }
        return result;
    }



}

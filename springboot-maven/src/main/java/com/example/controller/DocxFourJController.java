package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.dto.CompositeDocxReq;
import com.example.util.DocImageHandler;
import com.example.util.Docx4jUtil;
import com.example.util.Docx4jUtilBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.dml.CTNonVisualDrawingProps;
import org.docx4j.dml.wordprocessingDrawing.Anchor;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.ClassFinder;
import org.docx4j.finders.RangeFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.wml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
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

    @Value("${picPath}")
    private String picPath;
    @Value("${template01Path}")
    private String template01Path;
    @Value("${template01OutPath}")
    private String template01OutPath;
    @Value("${template02Path}")
    private String template02Path;
    @Value("${template02outPath}")
    private String template02outPath;

    @PostConstruct
    public void init(){
        logger.info("docx4j服务,{}", true);
    }

    /**
     * 基础创建:创建一个新的docx文档
     * 获取文档可操作对象
     */
    @GetMapping("/createDocx")
    public String createDocx(HttpServletResponse response){
        if(!StringUtils.isEmpty(template01Path)) {
            OutputStream outs = null;
            try {
                wordMLPackage =  WordprocessingMLPackage.createPackage();
//                wordMLPackage.save(new File(template01Path));
//                Docx4J.save(wordMLPackage, new File(docxPath));
                String fileName = URLEncoder.encode("模板表", "UTF-8");
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".docx");
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

                outs = response.getOutputStream();
                wordMLPackage.save(outs);
                outs.flush();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }else {
            return "路径不存在";
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
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
//            wordMLPackage = Docx4J.load(new File(docxPath));

            //增加内容
            wordMLPackage.getMainDocumentPart().addParagraphOfText("你好!");
            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "这是标题!");
            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle", " 这是二级标题!");

            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subject", "试一试");
            //保存文档
            wordMLPackage.save(new File(template01OutPath));
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
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
            byte[] bytes = DocImageHandler.convertImageToByteArray(new File(picPath));
            DocImageHandler.addImageToPackage(wordMLPackage, bytes);
            wordMLPackage.save(new File(template02outPath));
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
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
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
            wordMLPackage.save(new File(template01Path));
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
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
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
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));

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
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
        } catch (Docx4JException e) {
           logger.error(e.getMessage());
        }

        HTMLSettings html = Docx4J.createHTMLSettings();
        //设置图片的目录地址
        html.setImageDirPath(template01Path + "_files");
        html.setImageTargetUri(template01Path.substring(template01Path.lastIndexOf("/") + 1 ) + "_files");
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
                os = new FileOutputStream(template01Path + ".html");
            } else {
                os = new ByteArrayOutputStream();
            }
            //设置输出
            Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

            Docx4J.toHTML(html, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
        }catch(Exception e) {

        }
        if (save) {
            System.out.println("Saved: " + template01Path + ".html ");
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
                    .load(new File(template01Path));
            Docx4J.toPDF(wordMLPackage, new FileOutputStream(new File(template01Path + ".pdf")));
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
        boolean save = true;
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
            VariablePrepare.prepare(wordMLPackage);
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
            Docx4jUtil.cleanDocumentPart(documentPart);

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
            logger.info("Time: " + total);

            // Save it
            if (save) {
                // 输出word文件

                //1
//                SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
//                saver.save("/home/person-project/helloworld_1.docx");

                //2
//                OutputStream outputStream = new FileOutputStream(new File(docxOutPath));
//                wordMLPackage.save(outputStream);
//                outputStream.flush();

                //3
//                wordMLPackage.save(new File(docxOutPath));

                //4
                Docx4J.save(wordMLPackage, new File(template01OutPath));
            } else {
                logger.info(XmlUtils.marshaltoString(documentPart.getJaxbElement(), true, true));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "按指定变量替换docx中的内容成功";
    }

    /**
     * 替换模板里面的表格(循环替换标签)
     */
    @GetMapping("replaceTableByLoop")
    public String replaceTableByLoop(){
        factory = Context.getWmlObjectFactory();
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(template01Path));
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
            Docx4jUtil.cleanDocumentPart(documentPart);

            // 构造循环列表的数据
            ClassFinder find = new ClassFinder(Tbl.class);
            new TraversalUtil(documentPart.getContent(), find);
            // 获取到第一个表格元素
            Tbl table = (Tbl) find.results.get(0);
            // 第一行约定为模板，获取到第一行内容
            Tr dynamicTr = (Tr) table.getContent().get(0);
            // 获取模板行的xml数据
            String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);

            List<Map<String, Object>> dataList = getDataList();
            for (Map<String, Object> dataMap : dataList) {
                Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);// 填充模板行数据
                table.getContent().add(newTr);
            }

            // 删除模板行的占位行
            table.getContent().remove(0);

            Docx4J.save(wordMLPackage, new File(template01OutPath));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "替换模板里面的表格成功";
    }

    /**
     * 按书签替换内容(替换变量、表格、图片等格式数据)
     */
    @GetMapping("/booknameReplaceVar")
    public String booknameReplaceVar(){
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(template02Path));
            MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
            factory = Context.getWmlObjectFactory();

            Document wmlDoc = (Document) mainDocumentPart.getJaxbElement();
            Body body = wmlDoc.getBody();
            // 提取正文中所有段落
            List<Object> paragraphs = body.getContent();
            // 提取书签并创建书签的游标
            RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
            new TraversalUtil(paragraphs, rt);
            // 遍历书签
            for (CTBookmark bm : rt.getStarts()) {
                logger.info("标签名称:" + bm.getName());
                // 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
//                List<Map<String, Object>> dataList = getDataList();
//                for (Map<String, Object> map : dataList) {
//                    replaceText(bm, map);
//                }
                if (bm.getName().equals("name0")) {
                    replaceText(bm, "zhangsan");
                }
                if (bm.getName().equals("pic01")) {
                    addImage(wordMLPackage, bm, picPath);
                }
            }
            Docx4J.save(wordMLPackage, new File(template02outPath));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "按书签替换内容成功";
    }

    /**
     * 按占位符替换内容(替换变量、表格、图片等格式数据)
     * 注意：1 占位符在word转换为xml被分离问题：
     * 1.1 原因：
     * 1.1.1 打开word模板，单词底下有红线标注，这个就是word文档的单词校验，一般组装的标识符不符合单词校验规则，在转换的过程中，会单独分开（因为底下有标注），所以就会产生占位符被分开的情况。
     * 1.2 解决方案：
     * 1.2.1 docx中先不写变量，将docx另存为xml，然后用docx打开这个xml，这时候加变量就好了，${variable}就不会被分离了，之后再另存为docx即可
     * 1.2.2 先建一个txt文本，将${variable}编辑到文本，然后复制到docx即可(推荐)
     */
    @GetMapping("/placeholderTable")
    public String placeholderTable(){
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File(template02Path));
            Docx4jUtil.cleanDocumentPart(wordMLPackage.getMainDocumentPart());
            Map<String, String> mappings = new HashMap<String, String>();
            //构造非循环格子的表格数据
            mappings.put("name", "马参军");
            mappings.put("sex", "男");
            mappings.put("skill", "散谣：三人成虎事多有");

            //构造循环列表的数据
            ClassFinder find = new ClassFinder(Tbl.class);
            new TraversalUtil(wordMLPackage.getMainDocumentPart().getContent(), find);
            Tbl table = (Tbl) find.results.get(1);
            //第二行约定为模板
            Tr dynamicTr = (Tr) table.getContent().get(1);
            //获取模板行的xml数据
            String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);
            List<Map<String , Object>> dataList = dataList();
            for (Map<String, Object> dataMap : dataList) {
                //填充模板行数据
                Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);
                table.getContent().add(newTr);
            }
            //删除模板行的占位行
            table.getContent().remove(1);
            wordMLPackage.getMainDocumentPart().variableReplace(mappings);//设置全局的变量替换
            Docx4J.save(wordMLPackage, new File(template02outPath));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "按占位符替换内容成功";
    }

    /**
     * 替换图片
     * @return
     */
    @GetMapping("placeholderPicTable")
    public String placeholderPicTable(){
        try {
            wordMLPackage = WordprocessingMLPackage.load(new File("/home/person-project/1539525754960040802.docx"));
            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
            String xPath = "//w:drawing";
            List<Object> list = documentPart.getJAXBNodesViaXPath(xPath, true);
            @SuppressWarnings("unchecked")
            JAXBElement<Drawing> element = (JAXBElement<Drawing>) list.get(0);
            Drawing drawing = element.getValue();
            //获取原图的相关信息，再取创建一个新的图片，用户替换原图
            Anchor anchor = (Anchor) drawing.getAnchorOrInline().get(0);//当前的图片
            Integer posH = anchor.getPositionH().getPosOffset();//原占位图的坐标位置
            Integer posV = anchor.getPositionV().getPosOffset();
            CTNonVisualDrawingProps docPr = anchor.getDocPr();
            int xId = (int) docPr.getId();
            String filenameHint = docPr.getName();
            String altText = docPr.getDescr();
            int yId = (int) anchor.getGraphic().getGraphicData().getPic().getNvPicPr().getCNvPr().getId();
            byte bytes[] = FileUtils.readFileToByteArray(new File(picPath));
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
//            Anchor inline = imagePart.createImageAnchor(filenameHint, altText, xId, yId, false, posH, posV);
            Inline inline = imagePart.createImageInline(filenameHint, altText, xId, yId, posV, posH, false);
            drawing.getAnchorOrInline().set(0, inline);
            wordMLPackage.save(new File("/home/person-project/1539525754960040802_out.docx"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "图片已经替换";
    }



    //构造循环数据
    private static List<Map<String , Object>> dataList() {
        List<Map<String , Object>> dataList = new ArrayList<Map<String , Object>>();
        Map<String , Object> m1 = new HashMap<String , Object>();
        m1.put("number", "1");m1.put("company", "阿里巴巴");
        m1.put("slogan", "让天下没有难做的生意");
        dataList.add(m1);
        Map<String , Object> m2 = new HashMap<String , Object>();
        m2.put("number", "2");m2.put("company", "腾讯");
        m2.put("slogan", "连接你我 共生未来");
        dataList.add(m2);
        Map<String , Object> m3 = new HashMap<String , Object>();
        m3.put("number", "3");m3.put("company", "字节跳动");
        m3.put("slogan", "激发创造 丰富生活");
        dataList.add(m3);
        return dataList;
    }


    /**
     * 下载
     * @param response
     */
    @GetMapping("downloadWord")
    public void downloadWord(HttpServletResponse response) throws Docx4JException, JAXBException, FileNotFoundException {
        //模板文件路径
//        String path = this.getClass().getClassLoader().getResource("D://template//test.docx").getPath();
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
        byte[] img = null;
//        try (InputStream input = new FileInputStream(this.getClass().getClassLoader().getResource(picPath).getPath())){
        try (InputStream input = new FileInputStream(new File(picPath))){
            img = new byte[input.available()];
            input.read(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //要插入的map数据
        Map<String, String> m = new HashMap<>();
        m.put("today", LocalDate.now().toString());
        m.put("active", "游泳");

        //处理好数据后就是超级简单的调用
        byte[] bytes = Docx4jUtilBuilder.of(template02Path)
//                .addParam("title", "测试文档标题")
//                .addParam("user", "测试人")
//                .addParams(m)
//                .addTable("name", 2, list)
                .addImg("pic02", img)
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
     * 综合使用
     * @return
     */
    @PostMapping("/composite")
    public String composite(@RequestBody CompositeDocxReq compositeDocxReq, HttpServletResponse response){
        MainDocumentPart mainDocumentPart = null;
        try {
//            wordMLPackage = WordprocessingMLPackage.load(new File(template02Path));
//            VariablePrepare.prepare(wordMLPackage);
//            mainDocumentPart = wordMLPackage.getMainDocumentPart();
//            factory = Context.getWmlObjectFactory();
//            //清扫docx4j模板变量字符
//            Docx4jUtil.cleanDocumentPart(mainDocumentPart);

            // //构造非循环列表的变量数据
//            Map<String, String> mappings = new HashMap<String, String>();
//            mappings.put("name", "张三");
//            mappings.put("sex", "男");
//            mappings.put("skill", "游泳");


//            //构造循环列表的变量数据
//            ClassFinder find = new ClassFinder(Tbl.class);
//            new TraversalUtil(mainDocumentPart.getContent(), find);
//            Tbl table = (Tbl) find.results.get(1);
//            //第二行约定为模板
//            Tr dynamicTr = (Tr) table.getContent().get(1);
//            //获取模板行的xml数据
//            String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);
//            List<Map<String , Object>> dataList = dataList();
//            for (Map<String, Object> dataMap : dataList) {
//                //填充模板行数据
//                Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);
//                table.getContent().add(newTr);
//            }
//            //删除模板行的占位行
//            table.getContent().remove(1);
//
//            List<Map<String, Object>> picList = new ArrayList<Map<String , Object>>();
//            Map<String, Object> map = new HashMap<>();
//            map.put("pic01", picPath);
//            picList.add(map);
//            //插入图片
//            //书签
//            Document wmlDoc = (Document) mainDocumentPart.getJaxbElement();
//            Body body = wmlDoc.getBody();
//            // 提取正文中所有段落
//            List<Object> paragraphs = body.getContent();
//            // 提取书签并创建书签的游标
//            RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
//            new TraversalUtil(paragraphs, rt);
//            // 遍历书签
//            for (CTBookmark bm : rt.getStarts()) {
//                logger.info("标签名称:" + bm.getName());
//                if (bm.getName().equals("pic01")) {
//                    addImage(wordMLPackage, bm, picPath);
//                }
//            }
//
//            //设置全局的变量替换
//            mainDocumentPart.variableReplace(mappings);
//            //保存新生成的docx文件
//            Docx4J.save(wordMLPackage, new File(template02outPath));

            //工具类下载
//            Docx4jUtil.downloadDocxUseDoc4j(new FileInputStream(new File(template02Path)),
//                    mappings,
//                    dataList,
//                    picList,
//                    response,
//                    "export");
            Docx4jUtil.downloadDocxUseDoc4j(new FileInputStream(new File(template02Path)),
                    compositeDocxReq.getMappings(),
                    compositeDocxReq.getDataList(),
                    compositeDocxReq.getPicList(),
                    compositeDocxReq.getFileName(),
                    response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "综合使用调用成功";
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

    private static List<Map<String, Object>> getDataList() {
        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Map map = new HashMap();
            map.put("name", "name" + i);
            map.put("sex", "sex" + i);
            map.put("age", "age" + i);
            list.add(map);
        }
        return list;
    }

    /**
     * 在标签处插入内容
     *
     * @param bm
     * @param object
     * @throws Exception
     */
    public static void replaceText(CTBookmark bm, Object object) throws Exception {
        if (object == null) {
            return;
        }
        // do we have data for this one?
        if (bm.getName() == null)
            return;
        String value = object.toString();
        try {
            // Can't just remove the object from the parent,
            // since in the parent, it may be wrapped in a JAXBElement
            List<Object> theList = null;
            ParaRPr rpr = null;
            if (bm.getParent() instanceof P) {
                PPr pprTemp = ((P) (bm.getParent())).getPPr();
                if (pprTemp == null) {
                    rpr = null;
                } else {
                    rpr = ((P) (bm.getParent())).getPPr().getRPr();
                }
                theList = ((ContentAccessor) (bm.getParent())).getContent();
            } else {
                return;
            }
            int rangeStart = -1;
            int rangeEnd = -1;
            int i = 0;
            for (Object ox : theList) {
                Object listEntry = XmlUtils.unwrap(ox);
                if (listEntry.equals(bm)) {

                    if (((CTBookmark) listEntry).getName() != null) {

                        rangeStart = i + 1;

                    }
                } else if (listEntry instanceof CTMarkupRange) {
                    if (((CTMarkupRange) listEntry).getId().equals(bm.getId())) {
                        rangeEnd = i - 1;

                        break;
                    }
                }
                i++;
            }
            int x = i - 1;
            // if (rangeStart > 0 && x >= rangeStart) {
            // Delete the bookmark range
            for (int j = x; j >= rangeStart; j--) {
                theList.remove(j);
            }
            // now add a run
            R run = factory.createR();
            Text t = factory.createText();
            // if (rpr != null)
            // run.setRPr(paraRPr2RPr(rpr));
            t.setValue(value);
            run.getContent().add(t);
            // t.setValue(value);

            theList.add(rangeStart, run);
            // }
        } catch (ClassCastException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 插入图片
     *
     * @param wPackage
     * @param bm
     * @param file
     */
    public static void addImage(WordprocessingMLPackage wPackage, CTBookmark bm, String file) {
        logger.info("addImage :->{},{},{}", wPackage, bm,file);
        try {
            // 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
            // 获取该书签的父级段落
            P p = (P) (bm.getParent());
            // R对象是匿名的复杂类型，然而我并不知道具体啥意思，估计这个要好好去看看ooxml才知道
            R run = factory.createR();
            // 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
            byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));

            // InputStream is = new FileInputStream;
            // byte[] bytes = IOUtils.toByteArray(inputStream);
            // byte[] bytes = FileUtil.getByteFormBase64DataByImage("");
            // 开始创建一个行内图片
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wPackage, bytes);
            // createImageInline函数的前四个参数我都没有找到具体啥意思，，，，
            // 最有一个是限制图片的宽度，缩放的依据
            Inline inline = imagePart.createImageInline(null, null, 0, 1, false, 0);
            // 获取该书签的父级段落
            // drawing理解为画布？
            Drawing drawing = factory.createDrawing();
            drawing.getAnchorOrInline().add(inline);
            run.getContent().add(drawing);
            p.getContent().add(run);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}

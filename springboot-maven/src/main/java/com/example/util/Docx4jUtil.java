package com.example.util;

import com.example.dto.CompositeDocxReq;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.ClassFinder;
import org.docx4j.finders.RangeFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.wml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author zhangy
 * @Time 2021-07-12 16:08
 * @Description: 关于文件操作的工具类
 */
public class Docx4jUtil {

    private static final Logger logger = LoggerFactory.getLogger(Docx4jUtil.class);

    private static WordprocessingMLPackage wordMLPackage;
    private static ObjectFactory factory;

    /**
     * 替换变量并下载word文档
     * @param inputStream
     * @param map
     * @param dataList
     * @param fileName
     * @param response
     */
    public static void downloadDocxUseDoc4j(InputStream inputStream,
                                            Map<String, String> map,
                                            List<CompositeDocxReq.DataList> dataList,
                                            List<Map<String, Object>> picList,
                                            String fileName,
                                            HttpServletResponse response) {

        OutputStream outs = null;
        try {
            // 设置响应头
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".docx");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            outs = response.getOutputStream();
            Docx4jUtil.replaceDocUseDoc4j(inputStream,map,dataList,picList,outs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 替换变量并输出word文档
     * @param inputStream
     * @param map
     * @param dataList
     * @param picList
     * @param outputStream
     */
    public static void replaceDocUseDoc4j(InputStream inputStream,
                                          Map<String, String> map,
                                          List<CompositeDocxReq.DataList> dataList,
                                          List<Map<String, Object>> picList,
                                          OutputStream outputStream) {
        MainDocumentPart mainDocumentPart = null;
        try {
            wordMLPackage = WordprocessingMLPackage.load(inputStream);
            VariablePrepare.prepare(wordMLPackage);
            mainDocumentPart = wordMLPackage.getMainDocumentPart();
            factory = Context.getWmlObjectFactory();

            if (!CollectionUtils.isEmpty(map) || !CollectionUtils.isEmpty(dataList) || !CollectionUtils.isEmpty(picList)) {
                // 将${}里的内容结构层次替换为一层,清扫docx4j模板变量字符
                Docx4jUtil.cleanDocumentPart(mainDocumentPart);

                //构造循环列表的变量数据
                for (int i = 0; i < dataList.size(); i++) {
                    ClassFinder find = new ClassFinder(Tbl.class);
                    new TraversalUtil(mainDocumentPart.getContent(), find);
                    Tbl table = (Tbl) find.results.get(dataList.get(i).getNum());
                    //第二行约定为模板
                    Tr dynamicTr = (Tr) table.getContent().get(1);
                    //获取模板行的xml数据
                    String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);
                    for (Map<String, Object> dataMap : dataList.get(i).getDataInnerList()) {
                        //填充模板行数据
                        Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);
                        table.getContent().add(newTr);
                    }
                    //删除模板行的占位行
                    table.getContent().remove(1);
                }

                //插入图片
                //书签方式
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
                    for (int i = 0; i < picList.size(); i++) {
                        Map<String, Object> stringObjectMap = picList.get(i);
                        Set<String> keys = stringObjectMap.keySet();
                        for (String key : keys) {
                            if(bm.getName().equals(key)){
                                addImage(wordMLPackage, bm, (String) stringObjectMap.get(key));
                            }
                        }
                    }
                }

                // 设置全局的变量替换
                mainDocumentPart.variableReplace(map);
            }

            // 输出word文件
            wordMLPackage.save(outputStream);
            outputStream.flush();
            //输出文件到保存地点
//            wordMLPackage.save(new File("/home/person-project/template02_out.docx"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally {
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }


    /**
     * cleanDocumentPart
     *
     * @param documentPart
     */
    public static boolean cleanDocumentPart(MainDocumentPart documentPart) throws Exception {
        if (documentPart == null) {
            return false;
        }
        Document document = documentPart.getContents();
        String wmlTemplate =
                XmlUtils.marshaltoString(document, true, false, Context.jc);
        document = (Document) XmlUtils.unwrap(DocxVariableClearUtils.doCleanDocumentPart(wmlTemplate, Context.jc));
        documentPart.setContents(document);
        return true;
    }

    /**
     * 清扫 docx4j 模板变量字符,通常以${variable}形式
     * <p>
     * XXX: 主要在上传模板时处理一下, 后续
     *
     * @author zhangy
     * @Time 2021-07-12 16:08
     */
    private static class DocxVariableClearUtils {

        /**
         * 去任意XML标签
         */
        private static final Pattern XML_PATTERN = Pattern.compile("<[^>]*>");

        private DocxVariableClearUtils() {
        }

        /**
         * start符号
         */
        private static final char PREFIX = '$';

        /**
         * 中包含
         */
        private static final char LEFT_BRACE = '{';

        /**
         * 结尾
         */
        private static final char RIGHT_BRACE = '}';

        /**
         * 未开始
         */
        private static final int NONE_START = -1;

        /**
         * 未开始
         */
        private static final int NONE_START_INDEX = -1;

        /**
         * 开始
         */
        private static final int PREFIX_STATUS = 1;

        /**
         * 左括号
         */
        private static final int LEFT_BRACE_STATUS = 2;

        /**
         * 右括号
         */
        private static final int RIGHT_BRACE_STATUS = 3;


        /**
         * doCleanDocumentPart
         *
         * @param wmlTemplate
         * @param jc
         * @return
         * @throws JAXBException
         */
        private static Object doCleanDocumentPart(String wmlTemplate, JAXBContext jc) throws JAXBException {
            // 进入变量块位置
            int curStatus = NONE_START;
            // 开始位置
            int keyStartIndex = NONE_START_INDEX;
            // 当前位置
            int curIndex = 0;
            char[] textCharacters = wmlTemplate.toCharArray();
            StringBuilder documentBuilder = new StringBuilder(textCharacters.length);
            documentBuilder.append(textCharacters);
            // 新文档
            StringBuilder newDocumentBuilder = new StringBuilder(textCharacters.length);
            // 最后一次写位置
            int lastWriteIndex = 0;
            for (char c : textCharacters) {
                switch (c) {
                    case PREFIX:
                        // TODO 不管其何状态直接修改指针,这也意味着变量名称里面不能有PREFIX
                        keyStartIndex = curIndex;
                        curStatus = PREFIX_STATUS;
                        break;
                    case LEFT_BRACE:
                        if (curStatus == PREFIX_STATUS) {
                            curStatus = LEFT_BRACE_STATUS;
                        }
                        break;
                    case RIGHT_BRACE:
                        if (curStatus == LEFT_BRACE_STATUS) {
                            // 接上之前的字符
                            newDocumentBuilder.append(documentBuilder.substring(lastWriteIndex, keyStartIndex));
                            // 结束位置
                            int keyEndIndex = curIndex + 1;
                            // 替换
                            String rawKey = documentBuilder.substring(keyStartIndex, keyEndIndex);
                            // 干掉多余标签
                            String mappingKey = XML_PATTERN.matcher(rawKey).replaceAll("");
                            if (!mappingKey.equals(rawKey)) {
                                char[] rawKeyChars = rawKey.toCharArray();
                                // 保留原格式
                                StringBuilder rawStringBuilder = new StringBuilder(rawKey.length());
                                // 去掉变量引用字符
                                for (char rawChar : rawKeyChars) {
                                    if (rawChar == PREFIX || rawChar == LEFT_BRACE || rawChar == RIGHT_BRACE) {
                                        continue;
                                    }
                                    rawStringBuilder.append(rawChar);
                                }
                                // FIXME 要求变量连在一起
                                String variable = mappingKey.substring(2, mappingKey.length() - 1);
                                int variableStart = rawStringBuilder.indexOf(variable);
                                if (variableStart > 0) {
                                    rawStringBuilder = rawStringBuilder.replace(variableStart, variableStart + variable.length(), mappingKey);
                                }
                                newDocumentBuilder.append(rawStringBuilder.toString());
                            } else {
                                newDocumentBuilder.append(mappingKey);
                            }
                            lastWriteIndex = keyEndIndex;

                            curStatus = NONE_START;
                            keyStartIndex = NONE_START_INDEX;
                        }
                    default:
                        break;
                }
                curIndex++;
            }
            // 余部
            if (lastWriteIndex < documentBuilder.length()) {
                newDocumentBuilder.append(documentBuilder.substring(lastWriteIndex));
            }
            return XmlUtils.unmarshalString(newDocumentBuilder.toString(), jc);
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

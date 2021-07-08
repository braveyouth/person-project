package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.docx4j.wml.ObjectFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

/**
 * @author zhangy
 * @Time 2021-07-07 16:55
 * @Description: 插入图片工具类
 */
public class DocImageHandler {
    /**
     * 将图片加入到包中
     * @param wordMLPackage
     * @param bytes
     * @throws Exception
     */
    public static void addImageToPackage(WordprocessingMLPackage wordMLPackage, byte[] bytes) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;
        org.docx4j.dml.wordprocessingDrawing.Inline inline = imagePart.createImageInline("Filename hint",
                "Alternative text", docPrId, cNvPrId, false);

        P paragraph = addInlineImageToParagraph(inline);

        wordMLPackage.getMainDocumentPart().addObject(paragraph);
    }

    /**
     * 将图片加入到 段落中去
     *
     * @param inline
     * @return
     */
    public static P addInlineImageToParagraph(org.docx4j.dml.wordprocessingDrawing.Inline inline) {
        // 添加内联对象到一个段落中
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

    /**
     * 将图片转换为字节数组
     *
     * @param file 文件流
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static byte[] convertImageToByteArray(File file) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        // 不能使用long类型创建数组, 需要用int类型.
        if (length > Integer.MAX_VALUE) {
            System.out.println("File too large!!");
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确认所有的字节都没读取
        if (offset < bytes.length) {
            System.out.println("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }
}

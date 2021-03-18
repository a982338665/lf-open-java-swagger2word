package com.github.lfopenjavaswagger2word.util;

/**
 * @author : Mr huangye
 *  CSDN 皇夜_
 *  2020/7/18 19:39
 *  word 转 pdf
 */
public class WordToPDFUtils {

    /*public static void main(String[] args) {
        doc2pdf("InterfaceFile.docx", "InterfaceFile.pdf", "皇夜_");
    }

    *//**
     * word 转 pdf
     * @param inPath    word文档路径
     * @param outPath   pdf输出路径
     * @param watermarkText 水印内容，为空则不添加水印
     *//*
    public static void doc2pdf(String inPath, String outPath, String watermarkText) {
        FileOutputStream os = null;
        try {
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            if (!TextUtil.isBlank(watermarkText)) {
                insertWatermarkText(doc, watermarkText);
            }
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    *//**
     * word 转 pdf
     * @param inPath    word输入流
     * @param os   pdf输出流
     * @param watermarkText 水印内容，为空则不添加水印
     *//*
    public static void doc2pdfByStream(InputStream inPath, OutputStream os, String watermarkText) {
        try {
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            if (!TextUtil.isBlank(watermarkText)) {
                insertWatermarkText(doc, watermarkText);
            }
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    *//**
     * @param doc
     * @param watermarkText
     * @throws Exception
     * @throws
     * @Title: insertWatermarkText
     * @Description: PDF生成水印
     * @author mzl
     *//*
    private static void insertWatermarkText(Document doc, String watermarkText) throws Exception {
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
        //水印内容
        watermark.getTextPath().setText(watermarkText);
        //水印字体
        watermark.getTextPath().setFontFamily("宋体");
        //水印宽度
        watermark.setWidth(500);
        //水印高度
        watermark.setHeight(100);
        //旋转水印
        watermark.setRotation(-40);
        //水印颜色
        watermark.getFill().setColor(Color.lightGray);
        watermark.setStrokeColor(Color.lightGray);
        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        watermark.setWrapType(WrapType.NONE);
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);
        for (Section sect : doc.getSections()) {
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
    }

    private static void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);
        if (header == null) {
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }
        header.appendChild(watermarkPara.deepClone(true));
    }*/


}

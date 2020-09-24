package com.github.lfopenjavaswagger2word.util;


/**
 * 设置文件路径的单例模式
 */

public class SetDocxConf {

    private String name = "皇夜";
    private String url = "http://wwww.pingpingpang.cn";
    private String email = "982338665@qq.com";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 默认的文件导出路径
     */
    private String filePath = "InterfaceFile";


    /**
     * 首页介绍的 key （一级标题：固定名称）
     */
    private String firstName = "系统简介";
    private String docxDesc = "【文档描述】：";
    private String docxVersion = "【文档版本】：";
    private String swaggVersion = "【Swagger版本】：";
    private String contactName = "【联系人】：";
    private String contactUrl = "【联系地址】：";
    private String contactEmail = "【联系邮件】：";
    private String docxTime = "【创建时间】：";

    /**
     * 文档的创建时间可以自己定义，不定义的时候取当前时间为准
     */
    private String docxTimeValue = null;


    /**
     * 避免标题重复的 标题分隔符
     */
    private String splitTitle = "@@@WE-";


    /**
     * 接口文档前缀
     */
    private String interDesc = "【协议描述】：";
    private String interUrl = "【接口地址】：";
    private String interMethod = "【请求方式】：";
    private String interType = "【请求类型】：";
    private String interTypeRes = "【响应类型】：";
    private String interReq = "【请求参数】：";
    private String interRes = "【响应参数】：";
    private String interExample = "【请求示例】：";

    /**
     * 示例中表格的列内容
     */
    private String interReqExample = "请求参数";
    private String interResExample = "响应参数";



    /**
     * 字体设置；==========================================
     */
    /**
     * 行间距
     */
    private int textPosition = 2;
    /**
     * 标题字体
     */
    private String textTitleFont = "宋体";
    /**
     * 标题颜色
     */
    private String textTitleColor = "000000";
    /**
     * 内容字体
     */
    private String textContentFont = "宋体";
    /**
     * 文档主题目大小
     */
    private int textMainTitleFontSize = 26;
    /**
     * 文档1级标题大小
     */
    private int textFirstTitleFontSize = 22;
    /**
     * 文档2级标题大小
     */
    private int textSecondTitleFontSize = 18;
    /**
     * 文档3级标题大小
     */
    private int textThreeTitleFontSize = 14;
    /**
     * 文档4级标题大小
     */
    private int textFourTitleFontSize = 10;
    /**
     * 文本大小
     */
    private int textFontSize = 12;




    /**
     * 请求参数列表行头颜色
     */
    private String reqRowColor = "2EC6BD";
    /**
     * 请求参数列表行体颜色
     */
    private String reqBodyColor = "CCCCCC";
    /**
     * 响应参数列表行头颜色
     */
    private String resRowColor = "C2C3FD";
    /**
     * 响应参数列表行体颜色
     */
    private String resBodyColor = "CCCCCC";
    /**
     * 请求示例 的第一列颜色
     */
    private String resExampleFirstCellColor = "B4ACC6";
    /**
     * 请求示例 的其他列颜色
     */
    private String resExampleOtherCellColor = "CCCCCC";

    public String getInterTypeRes() {
        return interTypeRes;
    }

    public void setInterTypeRes(String interTypeRes) {
        this.interTypeRes = interTypeRes;
    }

    public String getDocxTimeValue() {
        return docxTimeValue;
    }

    public void setDocxTimeValue(String docxTimeValue) {
        this.docxTimeValue = docxTimeValue;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDocxDesc() {
        return docxDesc;
    }

    public void setDocxDesc(String docxDesc) {
        this.docxDesc = docxDesc;
    }

    public String getDocxVersion() {
        return docxVersion;
    }

    public void setDocxVersion(String docxVersion) {
        this.docxVersion = docxVersion;
    }

    public String getSwaggVersion() {
        return swaggVersion;
    }

    public void setSwaggVersion(String swaggVersion) {
        this.swaggVersion = swaggVersion;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDocxTime() {
        return docxTime;
    }

    public void setDocxTime(String docxTime) {
        this.docxTime = docxTime;
    }

    public String getSplitTitle() {
        return splitTitle;
    }

    public void setSplitTitle(String splitTitle) {
        this.splitTitle = splitTitle;
    }

    public String getInterDesc() {
        return interDesc;
    }

    public void setInterDesc(String interDesc) {
        this.interDesc = interDesc;
    }

    public String getInterUrl() {
        return interUrl;
    }

    public void setInterUrl(String interUrl) {
        this.interUrl = interUrl;
    }

    public String getInterMethod() {
        return interMethod;
    }

    public void setInterMethod(String interMethod) {
        this.interMethod = interMethod;
    }

    public String getInterType() {
        return interType;
    }

    public void setInterType(String interType) {
        this.interType = interType;
    }

    public String getInterReq() {
        return interReq;
    }

    public void setInterReq(String interReq) {
        this.interReq = interReq;
    }

    public String getInterRes() {
        return interRes;
    }

    public void setInterRes(String interRes) {
        this.interRes = interRes;
    }

    public String getInterExample() {
        return interExample;
    }

    public void setInterExample(String interExample) {
        this.interExample = interExample;
    }

    public String getInterReqExample() {
        return interReqExample;
    }

    public void setInterReqExample(String interReqExample) {
        this.interReqExample = interReqExample;
    }

    public String getInterResExample() {
        return interResExample;
    }

    public void setInterResExample(String interResExample) {
        this.interResExample = interResExample;
    }

    public int getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(int textPosition) {
        this.textPosition = textPosition;
    }

    public String getTextTitleFont() {
        return textTitleFont;
    }

    public void setTextTitleFont(String textTitleFont) {
        this.textTitleFont = textTitleFont;
    }

    public String getTextTitleColor() {
        return textTitleColor;
    }

    public void setTextTitleColor(String textTitleColor) {
        this.textTitleColor = textTitleColor;
    }

    public String getTextContentFont() {
        return textContentFont;
    }

    public void setTextContentFont(String textContentFont) {
        this.textContentFont = textContentFont;
    }

    public int getTextMainTitleFontSize() {
        return textMainTitleFontSize;
    }

    public void setTextMainTitleFontSize(int textMainTitleFontSize) {
        this.textMainTitleFontSize = textMainTitleFontSize;
    }

    public int getTextFirstTitleFontSize() {
        return textFirstTitleFontSize;
    }

    public void setTextFirstTitleFontSize(int textFirstTitleFontSize) {
        this.textFirstTitleFontSize = textFirstTitleFontSize;
    }

    public int getTextSecondTitleFontSize() {
        return textSecondTitleFontSize;
    }

    public void setTextSecondTitleFontSize(int textSecondTitleFontSize) {
        this.textSecondTitleFontSize = textSecondTitleFontSize;
    }

    public int getTextThreeTitleFontSize() {
        return textThreeTitleFontSize;
    }

    public void setTextThreeTitleFontSize(int textThreeTitleFontSize) {
        this.textThreeTitleFontSize = textThreeTitleFontSize;
    }

    public int getTextFourTitleFontSize() {
        return textFourTitleFontSize;
    }

    public void setTextFourTitleFontSize(int textFourTitleFontSize) {
        this.textFourTitleFontSize = textFourTitleFontSize;
    }

    public int getTextFontSize() {
        return textFontSize;
    }

    public void setTextFontSize(int textFontSize) {
        this.textFontSize = textFontSize;
    }

    public String getReqRowColor() {
        return reqRowColor;
    }

    public void setReqRowColor(String reqRowColor) {
        this.reqRowColor = reqRowColor;
    }

    public String getReqBodyColor() {
        return reqBodyColor;
    }

    public void setReqBodyColor(String reqBodyColor) {
        this.reqBodyColor = reqBodyColor;
    }

    public String getResRowColor() {
        return resRowColor;
    }

    public void setResRowColor(String resRowColor) {
        this.resRowColor = resRowColor;
    }

    public String getResBodyColor() {
        return resBodyColor;
    }

    public void setResBodyColor(String resBodyColor) {
        this.resBodyColor = resBodyColor;
    }

    public String getResExampleFirstCellColor() {
        return resExampleFirstCellColor;
    }

    public void setResExampleFirstCellColor(String resExampleFirstCellColor) {
        this.resExampleFirstCellColor = resExampleFirstCellColor;
    }

    public String getResExampleOtherCellColor() {
        return resExampleOtherCellColor;
    }

    public void setResExampleOtherCellColor(String resExampleOtherCellColor) {
        this.resExampleOtherCellColor = resExampleOtherCellColor;
    }

    public static SetDocxConf getSetDocxConf() {
        return setDocxConf;
    }

    public static void setSetDocxConf(SetDocxConf setDocxConf) {
        SetDocxConf.setDocxConf = setDocxConf;
    }

    private SetDocxConf() {

    }

    private static SetDocxConf setDocxConf = new SetDocxConf();

    public static SetDocxConf getInstance() {
        return setDocxConf;
    }


}

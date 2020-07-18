# lf-open-java-swagger2word 简介
>swagger2转word文档，pdf文档等
> 1.JSON解析部分部分借鉴 https://github.com/JMCuixy/swagger2word.git
> 2.pdf文档生成暂未开发
> 3.基础属性可配置


## 1.引入依赖

    	<dependency>
            <groupId>com.github.a982338665</groupId>
            <artifactId>lf-open-java-swagger2word</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
## 2.使用的依赖包
### 2.1 pom.xml

    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.6</version>
    </dependency>
    <!--docx处理：表格，图片等，可用于预设模板处理，如准考证打印-->
    <!-- 也用于表格处理 xls/xlsx-->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>3.14</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>ooxml-schemas</artifactId>
        <version>1.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml-schemas</artifactId>
        <version>3.14</version>
    </dependency>
    <dependency>
        <groupId>org.apache.xmlbeans</groupId>
        <artifactId>xmlbeans</artifactId>
        <version>2.6.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-examples</artifactId>
        <version>3.14</version>
    </dependency>

### 2.2 非pom管理的jar包
> 主要用来将 word文档 转为 pdf文档
> 位置：/resources/lib

    aspose-words-14.9.0-jdk16.jar

## 3 使用
### 3.1 简单使用
    
    1.生成接口文档到指定位置：
        //设置即将生成的文档的基本属性
        SetDocxConf instance = SetDocxConf.getInstance();
        instance.setFilePath("xxx.docx");
        //swagger导出的json文件的文档
        String file = "./xxx.json";
        boolean b = GenerateDocxUtils.generateDocxByFile(file);
        System.err.println(b);
    2.方法介绍：
        boolean generateFile(String file) ：
            参数：file-读取的本地json文件路径
            返回：true or false
        boolean generateFileByJSON(String reader) :
            参数：reader-JSON文件的内容
            返回：true or false
        XWPFDocument generateXWPFDocumentByJSON(String reader):
            参数：reader-JSON文件的内容
            返回：XWPFDocument
        XWPFDocument generateXWPFDocument(String file)：
            参数：file-读取的本地json文件路径
            返回：XWPFDocument
    
### 3.2 可选择设置属性介绍 SetDocxConf.class
    
    默认的文件导出路径，项目根路径下 filePath = "InterfaceFile.docx"
    首页介绍的 key （一级标题：固定名称）
        firstName = "系统简介";
        docxDesc = "【文档描述】：";
        docxVersion = "【文档版本】：";
        swaggVersion = "【Swagger版本】：";
        contactName = "【联系人】：";
        contactUrl = "【联系地址】：";
        contactEmail = "【联系邮件】：";
        docxTime = "【创建时间】：";
    文档的创建时间可以自己定义，不定义的时候取当前时间为准
    docxTimeValue = null;
    避免标题重复的 标题分隔符
    private String splitTitle = "@@@WE-";
    接口文档前缀
        interDesc = "【协议描述】：";
        interUrl = "【接口地址】：";
        interMethod = "【请求方式】：";
        interType = "【请求类型】：";
        interTypeRes = "【响应类型】：";
        interReq = "【请求参数】：";
        interRes = "【响应参数】：";
        interExample = "【请求示例】：";
    示例中表格的首列内容
        interReqExample = "请求参数";
        interResExample = "响应参数";
    字体相关设置；
        行间距
        textPosition = 2;
        标题字体
        textTitleFont = "宋体";
        标题颜色
        textTitleColor = "000000";
        内容字体
        textContentFont = "宋体";
        文档主题目大小
        textMainTitleFontSize = 26;
        文档1级标题大小
        textFirstTitleFontSize = 22;
        文档2级标题大小
        textSecondTitleFontSize = 18;
        文档3级标题大小
        textThreeTitleFontSize = 14;
        文档4级标题大小
        textFourTitleFontSize = 10;
        文本大小
        textFontSize = 12;
    颜色相关设置：
        请求参数列表行头颜色
        reqRowColor = "2EC6BD";
        请求参数列表行体颜色
        reqBodyColor = "CCCCCC";
        响应参数列表行头颜色
        resRowColor = "C2C3FD";
        响应参数列表行体颜色
        resBodyColor = "CCCCCC";
        请求示例 的第一列颜色
        resExampleFirstCellColor = "B4ACC6";
        请求示例 的其他列颜色
        resExampleOtherCellColor = "CCCCCC";

## 4.分支介绍：
> pdf-aspose 分支是 包含有 aspose方式，将wprd文档完美转换为pdf的项目  
> 由于该包是付费版，为去除水印，使用破解版jar包  
> 使用方式 - 参见类：GenerateDocxUtils中的方法介绍  

    
## 5.tag介绍：
> V1.0.0: 仅支持Swagger转word文档，添加依赖直接使用  
> V2.0.0：添加支持Swagger转PDF文档，包含word文档转PDF，但是不能直接引用中央仓库依赖使用，需要下载该项目，mvn install使用  
> V3.0.0：持续...寻找其他方式进行pdf文档生成或转换  

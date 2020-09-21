# 1.使用介绍
>__V1.0.0版本仅可以导出docx__
>__V2.0.0版本可以导出docx，可以将docx转为PDF（可加水印）__
>__V2.0.0版本暂未上传中央仓库，只能取源码打包使用，详情查看README文件，或者提Issues__
>__本项目Git地址__：[https://github.com/a982338665/lf-open-java-swagger2word.git](https://github.com/a982338665/lf-open-java-swagger2word.git)
>__借鉴代码Git地址__：[https://github.com/JMCuixy/swagger2word.git](https://github.com/JMCuixy/swagger2word.git)
>__测试项目的Git地址__：[https://github.com/a982338665/lf-mk-mybatis-plus.git](https://github.com/a982338665/lf-mk-mybatis-plus.git)

>__使用场景：一些传统项目需要将接口文档导出作为交付文档使用__
>__使用详情查看：git地址下的README.md文件__
>__由于输出的docx文档不满足甲方需求，做此更改__
>__尾部有文档截图，按需取用！__

# 2.使用：
## 2.1添加依赖
	<dependency>
        <groupId>com.github.a982338665</groupId>
        <artifactId>lf-open-java-swagger2word</artifactId>
        <version>1.0.0-RELEASE</version>
    </dependency>

## 2.2 V1.0.0版使用介绍：
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

## 2.3 实际使用
>项目在启动的时候，初始化加载数据，进行文档生成

	package com.lf.mp.conf;
	
	import com.github.lfopenjavaswagger2word.util.GenerateDocxUtils;
	import lombok.extern.slf4j.Slf4j;
	import org.apache.http.HttpEntity;
	import org.apache.http.client.methods.CloseableHttpResponse;
	import org.apache.http.client.methods.HttpGet;
	import org.apache.http.impl.client.CloseableHttpClient;
	import org.apache.http.impl.client.HttpClients;
	import org.apache.http.util.EntityUtils;
	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.boot.ApplicationArguments;
	import org.springframework.boot.ApplicationRunner;
	import org.springframework.stereotype.Component;
	
	import java.io.IOException;
	
	/**
	 * 项目初始化后：调用Swagger2 接口获取JSON文档
	 */
	@Component
	@Slf4j
	public class SpringInit implements ApplicationRunner {
	
	    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	
	    @Value("${server.port}")
	    private String port;
	
	    @Override
	    public void run(ApplicationArguments args) throws Exception {
	        String uri = "http://127.0.0.1:" + port + "/v2/api-docs";
	        log.info("访问：{}", uri);
	        HttpGet httpget = new HttpGet(uri);
	        CloseableHttpResponse response = null;
	        try {
	            response = httpclient.execute(httpget);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        String result = null;
	        try {
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                result = EntityUtils.toString(entity);
	                log.info("结果：{}", result != null ? "success" : "fail!");
	//                TextUtil.write("./swagger/file.json", result);
	                boolean b = GenerateDocxUtils.generateFileByJSON(result);
	                System.err.println(b);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                response.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
# 3.生成文档截图
## 3.1 本项目截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200718184736490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTc4NDY0Mg==,size_16,color_FFFFFF,t_70)

## 3.2 借鉴项目的截图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200718184829487.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTc4NDY0Mg==,size_16,color_FFFFFF,t_70)
## 3.3 PDF
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200720092142278.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NTc4NDY0Mg==,size_16,color_FFFFFF,t_70)

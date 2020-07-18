package com.github.lfopenjavaswagger2word.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lfopenjavaswagger2word.model.ModelAttr;
import com.github.lfopenjavaswagger2word.model.Request;
import com.github.lfopenjavaswagger2word.model.Response;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.util.*;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/16 17:04
 * @Description :
 */
public class GenerateDocxUtils {

    /**
     * 通过读取本地JSON 生成本地接口文档
     *
     * @param file
     * @throws IOException
     */
    public static boolean generateFile(String file) {
        try {
            //1.读取json
            String reader = TextUtil.reader(file);
            //2.解析赋值
            ObjectMapper om = new ObjectMapper();
            //3.导出
            SetDocxConf instance = SetDocxConf.getInstance();
            //所有的一级标题集合
            Map<String, List<Map<String, Object>>> mappAll = new HashMap<>();
            // 转map
            Map<String, Object> map = om.readValue(reader, HashMap.class);
            String host = map.get("host").toString();
            //解析文档介绍信息
            Map<String, String> mapIndex = parseWordIndex(map);
            generateDocContent(mappAll, map, host);
            DocxUtils.generateDoc(instance, mappAll, mapIndex);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成本地接口文档
     * @param reader swagger导出的json文件内容
     * @throws IOException
     */
    public static boolean generateFileByJSON( String reader) {
        try {
            if(TextUtil.isBlank(reader)){
                throw new Exception("jsonstr cannot null！");
            }
            //2.解析赋值
            ObjectMapper om = new ObjectMapper();
            //3.导出
            SetDocxConf instance = SetDocxConf.getInstance();
            //所有的一级标题集合
            Map<String, List<Map<String, Object>>> mappAll = new HashMap<>();
            // 转map
            Map<String, Object> map = om.readValue(reader, HashMap.class);
            String host = map.get("host").toString();
            //解析文档介绍信息
            Map<String, String> mapIndex = parseWordIndex(map);
            generateDocContent(mappAll, map, host);
            DocxUtils.generateDoc(instance, mappAll, mapIndex);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回文档对象 XWPFDocument doc
     * 可通过以下方式写出文档：或者通过response返回前端
     * FileOutputStream out = new FileOutputStream(instance.getFilePath());
     * doc.write(out);
     * @param reader swagger导出的json文件内容
     * @throws IOException
     */
    public static XWPFDocument generateXWPFDocumentByJSON(String reader) {
        try {
            if(TextUtil.isBlank(reader)){
                throw new Exception("jsonstr cannot null！");
            }
            //2.解析赋值
            ObjectMapper om = new ObjectMapper();
            //3.导出
            SetDocxConf instance = SetDocxConf.getInstance();
            //所有的一级标题集合
            Map<String, List<Map<String, Object>>> mappAll = new HashMap<>();
            // 转map
            Map<String, Object> map = om.readValue(reader, HashMap.class);
            String host = map.get("host").toString();
            //解析文档介绍信息
            Map<String, String> mapIndex = parseWordIndex(map);
            generateDocContent(mappAll, map, host);
            XWPFDocument xwpfDocument = DocxUtils.getXWPFDocument(instance, mappAll, mapIndex);
            return xwpfDocument;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过读取本地JSON 返回文档对象 XWPFDocument doc
     * 可通过以下方式写出文档：或者通过response返回前端
     * FileOutputStream out = new FileOutputStream(instance.getFilePath());
     * doc.write(out);
     * @param file
     * @throws IOException
     */
    public static XWPFDocument generateXWPFDocument(String file){
        try {
            //1.读取json
            String reader = TextUtil.reader(file);
            //2.解析赋值
            ObjectMapper om = new ObjectMapper();
            //3.导出
            SetDocxConf instance = SetDocxConf.getInstance();
            //所有的一级标题集合
            Map<String, List<Map<String, Object>>> mappAll = new HashMap<>();
            // 转map
            Map<String, Object> map = om.readValue(reader, HashMap.class);
            String host = map.get("host").toString();
            //解析文档介绍信息
            Map<String, String> mapIndex = parseWordIndex(map);
            generateDocContent(mappAll, map, host);
            XWPFDocument xwpfDocument = DocxUtils.getXWPFDocument(instance, mappAll, mapIndex);
            return xwpfDocument;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void generateDocContent(Map<String, List<Map<String, Object>>> mappAll, Map<String, Object> map, String host) throws IOException {
        //解析model
        Map<String, ModelAttr> definitinMap = parseDefinitions(map);
        //单个一级标题下的所有内容，每个一级标题下都有一个List，装的是二级及以下的接口内容
//        List<Map<String, Object>> list = new ArrayList<>();
        //解析paths
        Map<String, Map<String, Object>> paths = (Map<String, Map<String, Object>>) map.get("paths");
        if (paths != null) {
            Iterator<Map.Entry<String, Map<String, Object>>> it = paths.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Map<String, Object>> path = it.next();
                //请求参数列表
                List<Map<String, Object>> reqList = new ArrayList<>();
                //响应参数列表
                List<Map<String, Object>> resList = new ArrayList<>();
                Iterator<Map.Entry<String, Object>> it2 = path.getValue().entrySet().iterator();
                // 1.请求路径
                String url = path.getKey();
                // 2.请求方式，类似为 get,post,delete,put 这样
                String requestType = TextUtil.join(path.getValue().keySet(), ",");
                // 3. 不管有几种请求方式，都只解析第一种
                Map.Entry<String, Object> firstRequest = it2.next();
                Map<String, Object> content = (Map<String, Object>) firstRequest.getValue();
                // 5.小标题 （方法说明）
                String tag = String.valueOf(content.get("summary"));
                // 6.接口描述
                String description = String.valueOf(content.get("description"));
                // 7.请求参数格式，类似于 multipart/form-data
                String requestForm = "";
                List<String> consumes = (List) content.get("consumes");
                if (consumes != null && consumes.size() > 0) {
                    requestForm = TextUtil.join(consumes, ",");
                }
                // 8.返回参数格式，类似于 application/json
                String responseForm = "";
                List<String> produces = (List) content.get("produces");
                if (produces != null && produces.size() > 0) {
                    responseForm = TextUtil.join(produces, ",");
                }
                // 4. 大标题（类说明）
                String title = String.valueOf(((List) content.get("tags")).get(0));
                //TODO 存放每一个二级接口的list
                List<Map<String, Object>> list1;
                //如果没有大标题放在 其他接口
                if (title == null || title.equals("")) {
                    list1 = mappAll.get("其他接口：无类说明");
                    if (list1 == null) {
                        list1 = new ArrayList<>();
                        mappAll.put("其他接口：无类说明", list1);
                    }
                } else {
                    //若有大标题
                    list1 = mappAll.get(title);
                    if (list1 == null) {
                        list1 = new ArrayList<>();
                        //一级标题 ：二级接口内容
                        mappAll.put(title, list1);
                    }
                }
                // 9. 请求体
                List<LinkedHashMap> parameters = (ArrayList) content.get("parameters");
                List<Request> requestList = new ArrayList<>();
                //请求体解析
                if (parameters != null && parameters.size() > 0) {
                    for (int i = 0; i < parameters.size(); i++) {
                        String firstSuffix = i + ".";
                        Map<String, Object> param = parameters.get(i);
                        Object in = param.get("in");
                        Map<String, Object> mreqMap = new HashMap<>();
                        mreqMap.put(GetDocxConf.REQ_PARAM_TYPE, String.valueOf(in));
                        mreqMap.put(GetDocxConf.REQ_NAME, firstSuffix + String.valueOf(param.get("name")));
                        mreqMap.put(GetDocxConf.REQ_DESC, String.valueOf(param.get("description")));
                        String type = param.get("type") == null ? "object" : param.get("type").toString();
                        if (param.get("format") != null) {
                            type = TextUtil.concat(type, "(", param.get("format").toString(), ")");
                        }
                        mreqMap.put(GetDocxConf.REQ_DATA_TYPE, type);
                        mreqMap.put(GetDocxConf.REQ_ISFILL, param.get("required") != null ? param.get("required").toString().concat("") : "false");
                        // 考虑对象参数类型
                        if (in != null && "body".equals(in)) {
                            Map<String, Object> schema = (Map) param.get("schema");
                            Object ref = schema.get("$ref");
                            // 数组情况另外处理
                            if (schema.get("type") != null && "array".equals(schema.get("type"))) {
                                ref = ((Map) schema.get("items")).get("$ref");
                                mreqMap.put(GetDocxConf.REQ_DATA_TYPE, "array");
                            }
                            if (ref != null) {
                                mreqMap.put(GetDocxConf.REQ_DATA_TYPE, mreqMap.get(GetDocxConf.REQ_DATA_TYPE).toString().concat(":").concat(ref.toString().replaceAll("#/definitions/", "")));
                                //获取实体类属性
                                ModelAttr modelAttr = definitinMap.get(ref);
//                                System.err.println(om.writeValueAsString(modelAttr));
                                List<ModelAttr> properties = modelAttr.getProperties();
                                //添加入参
                                addReqParam(reqList, firstSuffix, in, properties);
                            }
                        }
                        reqList.add(mreqMap);

                        //request对象
                        Request request = new Request();
                        request.setName(String.valueOf(param.get("name")));
                        request.setType(param.get("type") == null ? "object" : param.get("type").toString());
                        if (param.get("format") != null) {
                            request.setType(request.getType() + "(" + param.get("format") + ")");
                        }
                        request.setParamType(String.valueOf(in));
                        // 考虑对象参数类型
                        if (in != null && "body".equals(in)) {
                            Map<String, Object> schema = (Map) param.get("schema");
                            Object ref = schema.get("$ref");
                            // 数组情况另外处理
                            if (schema.get("type") != null && "array".equals(schema.get("type"))) {
                                ref = ((Map) schema.get("items")).get("$ref");
                                request.setType("array");
                            }
                            if (ref != null) {
                                request.setType(request.getType() + ":" + ref.toString().replaceAll("#/definitions/", ""));
                                request.setModelAttr(definitinMap.get(ref));
                            }
                        }
                        // 是否必填
                        request.setRequire(false);
                        if (param.get("required") != null) {
                            request.setRequire((Boolean) param.get("required"));
                        }
                        // 参数说明
                        request.setRemark(String.valueOf(param.get("description")));
                        requestList.add(request);
                    }
                }

                // 10.返回体
                Map<String, Object> responses = (LinkedHashMap) content.get("responses");
                processResponseCodeList(responses);
                // 取出来状态是200时的返回值
                Map<String, Object> obj = (Map<String, Object>) responses.get("200");
                if (obj != null && obj.get("schema") != null) {
                    ModelAttr attr = processResponseModelAttrs(obj, definitinMap);
//                    System.err.println(om.writeValueAsString(attr));
                    List<ModelAttr> properties = attr.getProperties();
                    //反参为空时，作为基础数据类型存入
                    if (properties == null || properties.size() == 0) {
                        Map<String, Object> mresMap = new HashMap<>();
                        mresMap.put(GetDocxConf.REQ_DESC, attr.getDescription() == null ? "" : attr.getDescription());
                        mresMap.put(GetDocxConf.REQ_DATA_TYPE, attr.getType());
                        mresMap.put(GetDocxConf.REQ_NAME, "[无名称]");
                        resList.add(mresMap);
                    } else {
//                      添加出参
                        addResParam(resList, null, properties);
                    }
                }

                //示例
                String reqExam = processRequestParam(requestList);
                String resExam = processResponseParam(obj, definitinMap);
                DocxUtils.addList(list1, reqList, resList, tag, description, host.concat(url), requestType, requestForm, reqExam, resExam, responseForm);
            }
        }
    }

    /**
     * 处理返回值
     *
     * @param responseObj
     * @return
     */
    private static String processResponseParam(Map<String, Object> responseObj, Map<String, ModelAttr> definitinMap) throws JsonProcessingException {
        if (responseObj != null && responseObj.get("schema") != null) {
            Map<String, Object> schema = (Map<String, Object>) responseObj.get("schema");
            String type = (String) schema.get("type");
            String ref = null;
            // 数组
            if ("array".equals(type)) {
                Map<String, Object> items = (Map<String, Object>) schema.get("items");
                if (items != null && items.get("$ref") != null) {
                    ref = (String) items.get("$ref");
                }
            }
            // 对象
            if (schema.get("$ref") != null) {
                ref = (String) schema.get("$ref");
            }
            if (TextUtil.isNotEmpty(ref)) {
                ModelAttr modelAttr = definitinMap.get(ref);
                if (modelAttr != null && !CollectionUtils.isEmpty(modelAttr.getProperties())) {
                    Map<String, Object> responseMap = new HashMap<>(8);
                    for (ModelAttr subModelAttr : modelAttr.getProperties()) {
                        responseMap.put(subModelAttr.getName(), getValue(subModelAttr.getType(), subModelAttr));
                    }
                    return JsonUtils.writeJsonStr(responseMap);
                }
            }
        }
        return TextUtil.EMPTY;
    }

    /**
     * 处理返回属性列表
     *
     * @param responseObj
     * @param definitinMap
     * @return
     */
    private static ModelAttr processResponseModelAttrs(Map<String, Object> responseObj, Map<String, ModelAttr> definitinMap) {
        Map<String, Object> schema = (Map<String, Object>) responseObj.get("schema");
        String type = (String) schema.get("type");
        String ref = null;
        //数组
        if ("array".equals(type)) {
            Map<String, Object> items = (Map<String, Object>) schema.get("items");
            if (items != null && items.get("$ref") != null) {
                ref = (String) items.get("$ref");
            }
        }
        //对象
        if (schema.get("$ref") != null) {
            ref = (String) schema.get("$ref");
        }

        //其他类型
        ModelAttr modelAttr = new ModelAttr();
        modelAttr.setType(TextUtil.defaultIfBlank(type, ""));
        if (!TextUtil.isBlank(ref) && definitinMap.get(ref) != null) {
            modelAttr = definitinMap.get(ref);
        }
        return modelAttr;
    }

    /**
     * 封装请求体
     *
     * @param list
     * @return
     */
    private static String processRequestParam(List<Request> list) throws IOException {
        Map<String, Object> headerMap = new LinkedHashMap<>();
        Map<String, Object> queryMap = new LinkedHashMap<>();
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        if (list != null && list.size() > 0) {
            for (Request request : list) {
                String name = request.getName();
                String paramType = request.getParamType();
                Object value = getValue(request.getType(), request.getModelAttr());
                switch (paramType) {
                    case "header":
                        headerMap.put(name, value);
                        break;
                    case "query":
                        queryMap.put(name, value);
                        break;
                    case "body":
                        //TODO 根据content-type序列化成不同格式，目前只用了json
                        jsonMap.put(name, value);
                        break;
                    default:
                        break;

                }
            }
        }
        String res = "";
        if (!queryMap.isEmpty()) {
            res += getUrlParamsByMap(queryMap);
        }
        if (!headerMap.isEmpty()) {
            res += " " + getHeaderByMap(headerMap);
        }
        if (!jsonMap.isEmpty()) {
            if (jsonMap.size() == 1) {
                for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
                    res += " -d '" + JsonUtils.writeJsonStr(entry.getValue()) + "'";
                }
            } else {
                res += " -d '" + JsonUtils.writeJsonStr(jsonMap) + "'";
            }
        }
        return res;
    }

    /**
     * 将map转换成url
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = TextUtil.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
     * 将map转换成header
     */
    public static String getHeaderByMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("--header '");
            sb.append(entry.getKey() + ":" + entry.getValue());
            sb.append("'");
        }
        return sb.toString();
    }

    /**
     * 例子中，字段的默认值
     *
     * @param type      类型
     * @param modelAttr 引用的类型
     * @return
     */
    private static Object getValue(String type, ModelAttr modelAttr) {
        int pos;
        if ((pos = type.indexOf(":")) != -1) {
            type = type.substring(0, pos);
        }
        switch (type) {
            case "string":
                return "string";
            case "string(date-time)":
                return "2020/01/01 00:00:00";
            case "integer":
            case "integer(int64)":
            case "integer(int32)":
                return 0;
            case "number":
                return 0.0;
            case "boolean":
                return true;
            case "file":
                return "(binary)";
            case "array":
                List list = new ArrayList();
                Map<String, Object> map = new LinkedHashMap<>();
                if (modelAttr != null && !CollectionUtils.isEmpty(modelAttr.getProperties())) {
                    for (ModelAttr subModelAttr : modelAttr.getProperties()) {
                        map.put(subModelAttr.getName(), getValue(subModelAttr.getType(), subModelAttr));
                    }
                }
                list.add(map);
                return list;
            case "object":
                map = new LinkedHashMap<>();
                if (modelAttr != null && !CollectionUtils.isEmpty(modelAttr.getProperties())) {
                    for (ModelAttr subModelAttr : modelAttr.getProperties()) {
                        map.put(subModelAttr.getName(), getValue(subModelAttr.getType(), subModelAttr));
                    }
                }
                return map;
            default:
                return null;
        }
    }

    /**
     * 处理返回码列表
     *
     * @param responses 全部状态码返回对象
     * @return
     */
    private static List<Response> processResponseCodeList(Map<String, Object> responses) {
        List<Response> responseList = new ArrayList<>();
        Iterator<Map.Entry<String, Object>> resIt = responses.entrySet().iterator();
        while (resIt.hasNext()) {
            Map.Entry<String, Object> entry = resIt.next();
            Response response = new Response();
            // 状态码 200 201 401 403 404 这样
            response.setName(entry.getKey());
            LinkedHashMap<String, Object> statusCodeInfo = (LinkedHashMap) entry.getValue();
            response.setDescription(String.valueOf(statusCodeInfo.get("description")));
            Object schema = statusCodeInfo.get("schema");
            if (schema != null) {
                Object originalRef = ((LinkedHashMap) schema).get("originalRef");
                response.setRemark(originalRef == null ? "" : originalRef.toString());
            }
            responseList.add(response);
        }
        return responseList;
    }

    private static void addReqParam(List<Map<String, Object>> reqList, String firstSuffix, Object in, List<ModelAttr> properties) {
        if (properties != null && properties.size() > 0) {
            for (int j = 0; j < properties.size(); j++) {
                ModelAttr attr = properties.get(j);
                Map<String, Object> mreqChild = new HashMap<>();
                String secondSuffix = TextUtil.addBlank(TextUtil.concat(firstSuffix, j + "."), 1);
                mreqChild.put(GetDocxConf.REQ_NAME, TextUtil.concat(secondSuffix, attr.getName()));
                mreqChild.put(GetDocxConf.REQ_PARAM_TYPE, String.valueOf(in));
                mreqChild.put(GetDocxConf.REQ_DATA_TYPE, attr.getType());
                mreqChild.put(GetDocxConf.REQ_ISFILL, attr.getRequire());
                mreqChild.put(GetDocxConf.REQ_DESC, attr.getDescription() == null ? "" : attr.getDescription());
                reqList.add(mreqChild);
                List<ModelAttr> properties1 = attr.getProperties();
                addReqParam(reqList, secondSuffix, in, properties1);
            }
        }
    }

    private static void addResParam(List<Map<String, Object>> resList, String firstSuffix, List<ModelAttr> properties) {
        if (properties != null && properties.size() > 0) {
            for (int j = 0; j < properties.size(); j++) {
                ModelAttr attr = properties.get(j);
                Map<String, Object> mreqChild = new HashMap<>();
                String secondSuffix;
                if (firstSuffix == null) {
                    secondSuffix = TextUtil.concat(j + ".");
                } else {
                    secondSuffix = TextUtil.addBlank(TextUtil.concat(firstSuffix, j + "."), 1);
                }
                mreqChild.put(GetDocxConf.REQ_NAME, TextUtil.concat(secondSuffix, attr.getName()));
                mreqChild.put(GetDocxConf.REQ_DATA_TYPE, attr.getType());
                mreqChild.put(GetDocxConf.REQ_DESC, attr.getDescription() == null ? "" : attr.getDescription());
                resList.add(mreqChild);
                List<ModelAttr> properties1 = attr.getProperties();
                addResParam(resList, secondSuffix, properties1);
            }
        }
    }

    private static Map<String, String> parseWordIndex(Map<String, Object> map) {
        Map<String, String> mapIndex = new HashMap<>();
        String swagger = map.get("swagger").toString();
        Map<String, Object> info = (Map<String, Object>) map.get("info");
        String description1 = (String) info.get("description");
        String version = (String) info.get("version");
        String title1 = (String) info.get("title");
        Map<String, String> map1 = (Map<String, String>) info.get("contact");
        String name = map1.get("name");
        String url1 = map1.get("url");
        String email = map1.get("email");
        mapIndex.put(GetDocxConf.INDEX_TITLE, title1);
        mapIndex.put(GetDocxConf.INDEX_DESC, description1);
        mapIndex.put(GetDocxConf.INDEX_VERSIONSWAGGER, swagger);
        mapIndex.put(GetDocxConf.INDEX_VERSIONDOCX, version);
        mapIndex.put(GetDocxConf.INDEX_NAME, name);
        mapIndex.put(GetDocxConf.INDEX_URL, url1);
        mapIndex.put(GetDocxConf.INDEX_EMAIL, email);
        mapIndex.put(GetDocxConf.INDEX_TIME, SetDocxConf.getInstance().getDocxTimeValue() == null ? DateUtils.now(null) : SetDocxConf.getInstance().getDocxTimeValue());
//        mapIndex.keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + mapIndex.get(key)));
        return mapIndex;
    }

    /**
     * 解析Definition
     *
     * @param map
     * @return
     */
    private static Map<String, ModelAttr> parseDefinitions(Map<String, Object> map) {
        Map<String, Map<String, Object>> definitions = (Map<String, Map<String, Object>>) map.get("definitions");
        Map<String, ModelAttr> definitinMap = new HashMap<>(256);
        if (definitions != null) {
            Iterator<String> modelNameIt = definitions.keySet().iterator();
            while (modelNameIt.hasNext()) {
                String modeName = modelNameIt.next();
                getAndPutModelAttr(definitions, definitinMap, modeName);
            }
        }
        return definitinMap;
    }


    /**
     * 递归生成ModelAttr
     * 对$ref类型设置具体属性
     */
    private static ModelAttr getAndPutModelAttr(Map<String, Map<String, Object>> swaggerMap, Map<String, ModelAttr> resMap, String modeName) {
        ModelAttr modeAttr;
        if ((modeAttr = resMap.get("#/definitions/" + modeName)) == null) {
            modeAttr = new ModelAttr();
            resMap.put("#/definitions/" + modeName, modeAttr);
        } else if (modeAttr.isCompleted()) {
            return resMap.get("#/definitions/" + modeName);
        }
        Map<String, Object> modeProperties = (Map<String, Object>) swaggerMap.get(modeName).get("properties");
        if (modeProperties == null) {
            return null;
        }
        Iterator<Map.Entry<String, Object>> mIt = modeProperties.entrySet().iterator();

        List<ModelAttr> attrList = new ArrayList<>();
        //解析属性
        while (mIt.hasNext()) {
            Map.Entry<String, Object> mEntry = mIt.next();
            Map<String, Object> attrInfoMap = (Map<String, Object>) mEntry.getValue();
            ModelAttr child = new ModelAttr();
            child.setName(mEntry.getKey());
            child.setType((String) attrInfoMap.get("type"));
            if (attrInfoMap.get("format") != null) {
                child.setType(child.getType() + "(" + attrInfoMap.get("format") + ")");
            }

            String finalType = "object";
            if (child.getType() != null && child.getType().equals("")) {
                child.setType(finalType);
            }

            Object ref = attrInfoMap.get("$ref");
            Object items = attrInfoMap.get("items");
            if (ref != null || (items != null && (ref = ((Map) items).get("$ref")) != null)) {
                String refName = ref.toString();
                //截取 #/definitions/ 后面的
                String clsName = refName.substring(14);
                modeAttr.setCompleted(true);
                ModelAttr refModel = getAndPutModelAttr(swaggerMap, resMap, clsName);
                if (refModel != null) {
                    child.setProperties(refModel.getProperties());
                }
                child.setType(child.getType() + ":" + clsName);
            }
            child.setDescription((String) attrInfoMap.get("description"));
            attrList.add(child);
        }
        Object title = swaggerMap.get(modeName).get("title");
        Object description = swaggerMap.get(modeName).get("description");
        modeAttr.setClassName(title == null ? "" : title.toString());
        modeAttr.setDescription(description == null ? "" : description.toString());
        modeAttr.setProperties(attrList);
        return modeAttr;
    }

}

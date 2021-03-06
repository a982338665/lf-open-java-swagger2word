package com.github.lfopenjavaswagger2word.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回属性
 */
public class ModelAttr implements Serializable {

    /**
     * 类名
     */
    private String className = "";
    /**
     * 属性名
     */
    private String name = "";
    /**
     * 类型
     */
    private String type = "";
    /**
     * 是否必填
     */
    private Boolean require = false;
    /**
     * 属性描述
     */
    private String description;
    /**
     * 嵌套属性列表
     */
    private List<ModelAttr> properties = new ArrayList<>();

    /**
     * 是否加载完成，避免循环引用
     */
    private boolean isCompleted = false;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequire() {
        return require;
    }

    public void setRequire(Boolean require) {
        this.require = require;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ModelAttr> getProperties() {
        return properties;
    }

    public void setProperties(List<ModelAttr> properties) {
        this.properties = properties;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "ModelAttr{" +
                "className='" + className + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", require=" + require +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

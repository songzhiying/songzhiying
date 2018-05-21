package cn.duc.global.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @描述 全局错误模型
 * @作者 wuxin
 * @创建时间 2016年12月21日
 * @版本 1.0.0
 */
public class GlobalErrorModel {

    private static final long serialVersionUID = -4055422739700912296L;

    private String objectName;

    @JsonIgnore
    private String objectType;

    private String defaultMessage;

    public GlobalErrorModel(String objectName, String defaultMessage) {
        this.objectName = objectName;
        this.defaultMessage = defaultMessage;
    }

    public GlobalErrorModel(String objectType, String objectName, String defaultMessage) {
        this.objectType = objectType;
        this.objectName = objectName;
        this.defaultMessage = defaultMessage;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
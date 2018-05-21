package cn.duc.global.validation;

/**
 * @描述 字段错误模型
 * @作者 wuxin
 * @创建时间 2016年12月21日
 * @版本 1.0.0
 */
public class FieldErrorModel extends GlobalErrorModel {

    private static final long serialVersionUID = 1006991561903594159L;

    private String field;

    public FieldErrorModel(String field, String objectName, String defaultMessage) {
        super(objectName, defaultMessage);
        this.field = field;
    }

    public FieldErrorModel(String field, String objectType, String objectName, String defaultMessage) {
        super(objectType, objectName, defaultMessage);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
//package cn.duc.cts.validation;
//
//import wr.cjds.common.exception.BusinessException;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @描述 校验失败异常
// * @作者 wuxin
// * @创建时间 2016年12月21日
// * @版本 1.0.0
// */
//public class ValidateFailureException extends BusinessException {
//
//    private static final long serialVersionUID = -5064653053108955221L;
//
//    private List<FieldErrorModel> fieldErrorModels;
//    private List<GlobalErrorModel> globalErrorModels;
//
//    public ValidateFailureException() {
//        super("校验失败");
//    }
//
//    public ValidateFailureException(String message) {
//        super(message);
//    }
//
//    public ValidateFailureException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public ValidateFailureException(List<GlobalErrorModel> globalErrorModels, List<FieldErrorModel> fieldErrorModels) {
//        this();
//        this.globalErrorModels = globalErrorModels;
//        this.fieldErrorModels = fieldErrorModels;
//    }
//
//    public List<FieldErrorModel> getFieldErrorModels() {
//
//        if (this.fieldErrorModels == null) {
//            this.fieldErrorModels = new ArrayList<>();
//        }
//
//        return this.fieldErrorModels;
//    }
//
//    public void setFieldErrorModels(List<FieldErrorModel> fieldErrorModels) {
//        this.fieldErrorModels = fieldErrorModels;
//    }
//
//    public boolean addFieldErrorModel(FieldErrorModel fieldErrorModel) {
//        return getFieldErrorModels().add(fieldErrorModel);
//    }
//
//    public boolean removeFieldErrorModel(FieldErrorModel fieldErrorModel) {
//        return getFieldErrorModels().remove(fieldErrorModel);
//    }
//
//    public List<GlobalErrorModel> getGlobalErrorModels() {
//
//        if (this.globalErrorModels == null) {
//            this.globalErrorModels = new ArrayList<>();
//        }
//
//        return this.globalErrorModels;
//    }
//
//    public void setGlobalErrorModels(List<GlobalErrorModel> globalErrorModels) {
//        this.globalErrorModels = globalErrorModels;
//    }
//
//    public boolean addGlobalErrorModel(GlobalErrorModel globalErrorModel) {
//        return getGlobalErrorModels().add(globalErrorModel);
//    }
//
//    public boolean removeGlobalErrorModel(GlobalErrorModel globalErrorModel) {
//        return getGlobalErrorModels().remove(globalErrorModel);
//    }
//}

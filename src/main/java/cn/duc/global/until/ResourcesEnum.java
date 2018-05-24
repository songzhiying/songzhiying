package cn.duc.global.until;

public enum ResourcesEnum {

	/**
	 * 登录失败
	 */
	LOGIN_FAIL,

	/**
	 * 登录成功
	 */
	LOGIN_SUCCESS,

	/**
	 * 没有登录,或登录已失效
	 */
	NOT_LOGGED,

	/**
	 * 账号被锁定
	 */
	ACCOUNT_LOCKED,

	/**
	 * 账号被禁用
	 */
	ACCOUNT_DISABLED,

	/**
	 * 账号使用到期
	 */
	ACCOUNT_EXPIRED,

	/**
	 * 登录失败，请检查用户名与密码！
	 */
	ACCOUNT_OR_PASSWORD_ERROR,

	/**
	 * 用户创建失败
	 */
	USER_CREATE_FAIL,

	/**
	 * 用户已经存在
	 */
	USER_REPEAT,

	/**
	 * 数联账号查询失败
	 */
	USER_DUC_FAIL,

	/**
	 * 请注销后重新登录
	 */
	PLEASE_LOGOUT,

	/**
	 * 用户角色错误
	 */
	USER_ROLE_ERROR,

	/**
	 * 修改密码失败
	 */
	UPDATE_PASSWORD_FAIL,

	/**
	 * 获取注册短信验证码失败,请稍后再试
	 */
	GET_REGISTER_SECURITY_CODE_FAIL,

	/**
	 * 短信验证码发送成功
	 */
	GET_SMS_SECURITY_CODE_SUCCESS,
	
	/**
	 * 手机验证码发送次数太多，稍后再试
	 */
	GET_SMS_SECURITY_CODE_LIMIT,

	/**
	 * 注册失败,请稍后再试
	 */
	REGISTER_FAIL,

	/**
	 * 获取密码找回短信验证码失败,请稍后再试
	 */
	GET_RETRIEVE_PASSWORD_SECURITY_CODE_FAIL,

	/**
	 * 密码找回失败,请稍后再试
	 */
	RETRIEVE_PASSWORD_FAIL,

	/**
	 * 未知错误
	 */
	UNKNOWN_ERROR,

	/**
	 * 参数错误：[%1$s]
	 */
	PARAMETER_ERROR,

	/**
	 * 参数是空值：[%1$s]
	 */
	PARAMETER_IS_NULL_ERROR,

	/**
	 * 禁止访问
	 */
	FORBIDDEN_ACCESS,

	/**
	 * 签名认证错误
	 */
	SIGNATURE_AUTHENTICATION_ERROR,

	/**
	 * 图片验证码错误
	 */
	IMAGE_VERIFICATION_CODE_ERROR,

	/**
	 * 邮箱不合法
	 */
	EMAIL_ILLEGAL,

	/**
	 * 手机号不合法
	 */
	PHONE_NUMBER_ILLEGAL,

	/**
	 * DUC接口访问失败
	 */
	DUC_INTERFACE_ERROR,
	/**
	 * 场景导购接口访问失败
	 */
	CJDG_INTERFACE_ERROR,
	/**
	 * 错误：上传文件发生未知错误
	 */
	UPLOAD_FILE_UNKNOWN_ERROR,

	/**
	 * 错误：上传文件后缀未知错误
	 */
	UPLOAD_EMPT_FILE_ERROR,

	/**
	 * 错误：上传文件后缀未知错误
	 */
	UPLOAD_FILE_SUFFIX_UNKNOWN_ERROR,

	/**
	 * 错误：上传文件大小超出限制
	 */
	UPLOAD_FILE_SIZE_EXCEEDED_LIMIT_ERROR,

	/**
	 * 账号已经在数联注册
	 */
	ACCOUNT_REGISTERED,

	/**
	 * 微信分享加密失败
	 */
	WX_SHARE_ENCRYPT_ERROR,

	/**
	 * 微信获取TOKEN错误
	 */
	WX_SHARE_ACCESSTOKEN_ERROR,

	/**
	 * 微信获取TICKET错误
	 */
	WX_SHARE_TICKET_ERROR,

	/**
	 *获取微信小程序二维码错误
	 */
	WX_SMALL_PROGRAM_GET_QR_CODE_ERROR,
	/**
	 * 用户不存在
	 */
	USER_NO_EXISTS,

	/**
	 * 图片验证码超时
	 */
	IMAGE_VERIFICATION_CODE_OVERTIM,

	/**
	 * 页面过期
	 */
	PAGE_EXPIRED

	;
}

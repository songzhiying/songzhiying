package cn.duc.global.until;

public interface RespCodeConstants {

	public interface Success {

		public static final Integer TRUE = 200;

		public static final Integer FALSE = -1;

	}

	public interface Error {

		public static final Integer FALSE = -1;

		public interface Basic {

			/**
			 * 未知错误
			 */
			public static final Integer UNKNOWN_ERROR = -100;

			/**
			 * 请求参数错误
			 */
			public static final Integer PARAMETER_ERROR = -101;
			
			/**
			 * 请求参数为空
			 */
			public static final Integer PARAMETER_IS_NULL_ERROR = -101;

			/**
			 * 禁止访问
			 */
			public static final Integer FORBIDDEN_ACCESS = -105;

			/**
			 * 签名认证错误
			 */
			public static final Integer SIGNATURE_AUTHENTICATION_ERROR = -106;
			
			/**
			 * 图片验证码错误
			 */
			public static final Integer IMAGE_VERIFICATION_CODE_ERROR = -107;
			
			/**
			 * 页面过期（session失效）
			 */
			public static final Integer PAGE_EXPIRED = -108;
		}

		/**
		 * 用户相关模块
		 */
		public interface User {

			/**
			 * 没有登录,或登录已失效
			 */
			public static final Integer NOT_LOGGED = -1002;

			/**
			 * 账号被锁定
			 */
			public static final Integer ACCOUNT_LOCKED = -1003;

			/**
			 * 账号被禁用
			 */
			public static final Integer ACCOUNT_DISABLED = -1004;

			/**
			 * 账号使用到期
			 */
			public static final Integer ACCOUNT_EXPIRED = -1005;

			/**
			 * 用户名或密码错误
			 */
			public static final Integer ACCOUNT_OR_PASSWORD_ERROR = -10;

			/**
			 * 用户创建失败
			 */
			public static final Integer USER_CREATE_FAIL = -11;

			/**
			 * 请注销
			 */
			public static final Integer PLEASE_LOGOUT = -12;

			/**
			 * 用户角色错误
			 */
			public static final Integer USER_ROLE_ERROR = -13;

			/**
			 * 修改密码失败
			 */
			public static final Integer UPDATE_PASSWORD_FAIL = -14;

			/**
			 * 获取注册短信验证码失败
			 */
			public static final Integer GET_REGISTER_SECURITY_CODE_FAIL = -15;

			/**
			 * 注册失败
			 */
			public static final Integer REGISTER_FAIL = -16;

			/**
			 * 获取密码找回短信验证码失败
			 */
			public static final Integer GET_RETRIEVE_PASSWORD_SECURITY_CODE_FAIL = -17;

			/**
			 * 密码找回失败
			 */
			public static final Integer RETRIEVE_PASSWORD_FAIL = -18;


			/**
			 * 帐号已注册
			 */
			public static final Integer ACCOUNT_REGISTERED = -19;

			/**
			 * 用户已存在
			 */
			public static final Integer USER_REPEAT = -20;

			/**
			 * 数联账号查询失败
			 */
			public static final Integer USER_DUC_FAIL = -21;
			
		}

		/**
		 * 文件模块
		 * 
		 * @author lixingxing
		 *
		 */
		public interface File {

			/**
			 * 上传文件未知错误
			 */
			public static final Integer UPLOAD_FILE_UNKNOWN_ERROR = -300;

			/**
			 * 上传空文件
			 */
			public static final Integer UPLOAD_EMPT_FILE_ERROR = -301;

			/**
			 * 上传文件后缀未知错误
			 */
			public static final Integer UPLOAD_FILE_SUFFIX_UNKNOWN_ERROR = -302;

			/**
			 * 上传文件大小超出限制
			 */
			public static final Integer UPLOAD_FILE_SIZE_EXCEEDED_LIMIT_ERROR = -303;


		}
		/**
		 * 第三方模块
		 *
		 * @author lixingxing
		 *
		 */
		public interface ThirdParty {
			/**
			 * 微信分享错误
			 */
			public static final Integer WX_SHARE_ERROR = -501;
			/**
			 *
			 */
			public static final Integer WX_SMALL_PROGRAM_GET_QR_CODE_ERROR = -502;
		}

	}

}

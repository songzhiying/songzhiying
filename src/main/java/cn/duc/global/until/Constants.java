package cn.duc.global.until;

import com.common.lang.util.PropertiesUtil;

public interface Constants {

	/** 客户端主题 */
	public static final String WEB_THEME = "WEB_THEME";

	/** 当前用户 */
	public static final String CURRENT_USER = "CURRENT_USER";
	
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = PropertiesUtil.getString("redis.cache.namespace")
			+ "ALLUSER_NUMBER";

	public static final int REQUEST_SUCCESS = 200;

	public static final int REQUEST_ERROR = -1;

	public static final int START_PAGE_NUM = 1;

	public static final int DEFAULT_PAGE_SIZE = 9;

	/**
	 * 1分钟
	 */
	public static final int ONE_MINUTES_SECOND = 1 * 60;

	/**
	 * 1分钟
	 */
	public static final long ONE_MINUTES_MILL = 1 * 60 * 1000;

	/**
	 * 3分钟
	 */
	public static final long THREE_MINUTES_MILL = 3 * 60 * 1000;

	/**
	 * 一亿
	 */
	public static final int ONE_HUNDRED_MILLION = 100000000;
}

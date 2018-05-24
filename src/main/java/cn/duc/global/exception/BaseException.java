package cn.duc.global.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {

	public BaseException(Throwable ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable ex) {
		super(message, ex);
	}

	public void handler(ModelMap modelMap) {
		modelMap.put("errorCode", getErrorCode());
		modelMap.put("msg", StringUtils.isEmpty(getMessage()) ? "" : getMessage());
		modelMap.put("debugMsg", StringUtils.isEmpty(getDebugMsg()) ? "" : getDebugMsg());
	}

	public abstract Integer getErrorCode();

	public abstract String getDebugMsg();
}

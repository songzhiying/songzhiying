package cn.duc.global.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.lang.util.DateUtil;

import cn.duc.global.exception.BaseException;
import cn.duc.global.until.Resources;
import cn.duc.global.until.ResourcesEnum;
import cn.duc.global.until.RespCodeConstants;

/**
 * 控制器基类
 */
public abstract class BaseController {

	protected final Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * 返回成功消息体，没有数据，没有提示信息，没有细节信息
	 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	/**
	 * 返回成功消息体，有数据，没有提示信息，没有细节信息
	 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
		return setSuccessModelMap(modelMap, data, null);
	}

	/**
	 * 返回成功消息体，有数据，有提示信息，没有细节信息
	 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data, String msg) {
		return setSuccessModelMap(modelMap, data, msg, null);
	}

	/**
	 * 返回成功消息体，有数据，有提示信息，有细节信息
	 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data, String msg, String debugMsg) {
		return setModelMap(modelMap, RespCodeConstants.Success.TRUE, data, RespCodeConstants.Error.FALSE, msg,
				debugMsg);
	}

	/**
	 * 返回失败消息体，没有数据，必须有错误提示码，没有提示信息，没有细节信息
	 */
	protected ResponseEntity<ModelMap> setErrorModelMap(ModelMap modelMap, Integer errorCode) {
		return setErrorModelMap(modelMap, errorCode, null);
	}

	/**
	 * 返回失败消息体，没有数据，必须有错误提示码，有提示信息，没有细节信息
	 */
	protected ResponseEntity<ModelMap> setErrorModelMap(ModelMap modelMap, Integer errorCode, String msg) {
		return setErrorModelMap(modelMap, errorCode, msg, null);
	}

	/**
	 * 返回失败消息体，没有数据，必须有错误提示码，有提示信息，有细节信息
	 */
	protected ResponseEntity<ModelMap> setErrorModelMap(ModelMap modelMap, Integer errorCode, String msg,
																											String debugMsg) {
		return setModelMap(modelMap, RespCodeConstants.Success.FALSE, null, errorCode, msg, debugMsg);
	}

	/**
	 * 返回消息体
	 * 
	 * @param modelMap
	 * @param code
	 *            成功/失败
	 * @param data
	 *            消息数据
	 * @param errorCode
	 *            错误编号
	 * @param msg
	 *            提示信息
	 * @param debugMsg
	 *            细节信息
	 * @return
	 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, Integer code, Object data, Integer errorCode,
																								 String msg, String debugMsg) {
		modelMap.remove("void");
		modelMap.put("code", code);
		modelMap.put("data", data);
		modelMap.put("errorCode", errorCode);
		modelMap.put("msg", StringUtils.isEmpty(msg) ? "" : msg);
		modelMap.put("debugMsg", StringUtils.isEmpty(debugMsg) ? "" : debugMsg);
		modelMap.put("timestamp", DateUtil.currentNowMsString());
		return ResponseEntity.ok(modelMap);
	}

	/** 异常处理 */
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		if (ex instanceof BaseException) {
			logger.error(request.getRequestURI() + "----------" + ex.getMessage() + "--------------"
					+ ((BaseException) ex).getDebugMsg(), ex);
		} else {
			logger.error(request.getRequestURI() + "----------" + ex.getMessage(), ex);
		}

		ModelMap modelMap = new ModelMap();
		setErrorModelMap(modelMap, RespCodeConstants.Error.Basic.UNKNOWN_ERROR,
				Resources.getMessage(ResourcesEnum.UNKNOWN_ERROR), Resources.getMessage(ResourcesEnum.UNKNOWN_ERROR));
		if (ex instanceof BaseException) {
			((BaseException) ex).handler(modelMap);
		}
		response.setContentType("text/plain;charset=UTF-8");
		byte[] bytes = JSON.toJSONBytes(modelMap,
				new SerializerFeature[] { SerializerFeature.DisableCircularReferenceDetect,
						SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames,
						SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse,
						SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty,
						SerializerFeature.PrettyFormat });
		response.getOutputStream().write(bytes);
	}

}

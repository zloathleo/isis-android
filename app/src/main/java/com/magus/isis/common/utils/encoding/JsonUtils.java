package com.magus.isis.common.utils.encoding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * 类描述： 
 * 创建人：仲李  
 * 联系方式：oathleo@gmail.com  18516152819
 * 创建时间：2015年9月6日 下午1:42:54 
 * &#64;version
 * </pre>
 */
public class JsonUtils {

	private static final Logger Logger = LoggerFactory.getLogger(JsonUtils.class);

	public static final ObjectMapper writeMapper = new ObjectMapper();

	public static final ObjectMapper readMapper = new ObjectMapper();

	static {
		writeMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		writeMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		writeMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		readMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		readMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		readMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static String ToJson(Object value) {
		try {
			return writeMapper.writeValueAsString(value);
		} catch (Exception e) {
			Logger.error("ToJson异常", e);
		}
		return "";
	}

	public static byte[] ToJsonAsBytes(Object value) {
		try {
			return writeMapper.writeValueAsBytes(value);
		} catch (Exception e) {
			Logger.error("ToJsonAsBytes异常", e);
		}
		return null;
	}

	public static <T> T ToObject(String content, Class<T> valueType) {
		try {
			return readMapper.readValue(content, valueType);
		} catch (Exception e) {
			Logger.error("ToObject异常", e);
		}
		return null;
	}

	public static <T> T ToObject(byte[] content, Class<T> valueType) {
		try {
			return readMapper.readValue(content, valueType);
		} catch (Exception e) {
			Logger.error("ToObject异常", e);
		}
		return null;
	}

}

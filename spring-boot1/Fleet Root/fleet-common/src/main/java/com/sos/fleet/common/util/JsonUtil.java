package com.sos.fleet.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * class util to process json data.
 */
public class JsonUtil {

	public static final ObjectMapper om = new ObjectMapper();
	/**
	 * @Author : JUNJZHU
	 * @Date : 2012-11-21
	 * @param data
	 * @param clazz
	 * @return null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEntity(Object data, Class<T> clazz) {
		if (data.getClass() == clazz) {
			return (T) data;
		}
		try {
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			String json = om.writeValueAsString(data);
			return om.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Author : HEAXIE
	 * @Date : 2012-11-21
	 * @param data
	 * @return null
	 */
	public static String getJson(Object data) {
		try {
			return om.writeValueAsString(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param json
	 * @param clazz
	 * @return null
	 */
	public static <T> T getEntity(String json, Class<T> clazz) {
		try {
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om.readValue(json, clazz);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Author : HEAXIE
	 * @Date : 2012-12-7
	 * @param json
	 * @param javatype
	 * @return null
	 */
	public static <T> T getEntity(String json, JavaType javatype) {
		try {
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om.readValue(json, javatype);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

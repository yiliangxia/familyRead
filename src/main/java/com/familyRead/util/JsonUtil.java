/**
 * 
 */
package com.familyRead.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
	/**
	 * slf4j log4j
	 */
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	/**
     * 
     */
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * create bean from json string
	 * 
	 * @param <T>
	 * @param bean
	 * @param jsonString
	 * @return bean object
	 */
	public static <T extends Object> T createBeanfromJsonString(Class<T> bean,
			String jsonString) {
		try {
			return mapper.readValue(jsonString, bean);
		} catch (Exception e) {
			logger.error("createBeanfromJsonString", e);
			return null;
		}
	}

	/**
	 * 
	 * json 转换成 list
	 * 
	 * @param list
	 * @param jsonString
	 * @return
	 */
	public static List<?> createListFromJsonString(String jsonString, Class<?> t) {
		try {
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(List.class, t);
			return mapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			logger.error("createListFromJsonString", e);
			return null;
		}
		// 如果是Map类型
		// mapper.getTypeFactory().constructParametricType(HashMap.class,String.class,
		// Bean.class);

	}

	/**
	 * json 转换成map
	 * 
	 * @param jsonString
	 * @param t
	 * @return
	 */
	public static Map<?, ?> createMapFromJsonString(String jsonString,
			Class<?> t) {
		try {
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(HashMap.class, t);
			return mapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			logger.error("createListFromJsonString", e);
			return null;
		}
	}

	/**
	 * @param jsonString
	 * @param propertyPaths
	 * @return propertyPath result list
	 */
	public static List<String> getPropertyPathResultsFromJson(
			String jsonString, List<String> propertyPaths) {
		try {
			Object object = mapper.readValue(jsonString, Object.class);
			return getPropertyPathResultsFromObject(object, propertyPaths);
		} catch (Exception e) {
			logger.error("getPropertyPathResultsFromJson", e);
			return null;
		}
	}

	/**
	 * @param object
	 * @param propertyPaths
	 * @return propertyPath result list
	 */
	public static List<String> getPropertyPathResultsFromObject(Object object,
			List<String> propertyPaths) {
		try {
			List<String> propertyPathResults = new ArrayList<String>();
			for (String propertyPath : propertyPaths) {
				propertyPathResults.add(BeanUtils.getProperty(object,
						propertyPath));
			}
			return propertyPathResults;
		} catch (Exception e) {
			logger.error("getPropertyPathResultsFromObject", e);
			return null;
		}
	}

	/**
	 * serialize bean to json string.
	 * 
	 * @param bean
	 * @return json string
	 */
	public static String serializeBeanToJsonString(Object bean) {
		try {
			return mapper.writeValueAsString(bean);
		} catch (Exception e) {
			logger.error("serializeBeanToJsonString", e);
			return null;
		}
	}
}

package org.terracotta.hill.excel.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UtilReflect {
	private static final Log log = LogFactory.getLog(UtilReflect.class);
	
	public static void setFieldValue(Object target, String fname, Class ftype,
			Object fvalue) {
		if (target == null
				|| fname == null
				|| fname.trim().length()==0
				|| (fvalue != null && !ftype
						.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod("set"
					+ Character.toUpperCase(fname.charAt(0))
					+ fname.substring(1), new Class[]{ftype});
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, new Object[]{fvalue});

		} catch (Exception me) {
			if (log.isDebugEnabled()) {
				log.debug(me);
			}
			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {
				if (log.isDebugEnabled()) {
					log.debug(fe);
				}
				throw new RuntimeException(fe.getMessage());
			}
		}
	}
	
	public static Object runMethod(Object target, String methodName, 
			Object[] paramValues, Class[] paramType) {
		if (target == null
				|| methodName == null
				|| methodName.trim().length()==0
				) {
			throw new RuntimeException("Error reflect call Method");
		}
		for(int i=0; i<paramValues.length; i++){
			if (!paramType[i].isAssignableFrom(paramValues[i].getClass())){
				throw new RuntimeException("Error reflect call Method");
			}
		}
		Class clazz = target.getClass();
		Object result = null;
		try{
			Method method = clazz.getDeclaredMethod(methodName, paramType);
			result = method.invoke(target, paramValues);
		 }catch (Exception fe) {
			if (log.isDebugEnabled()) {
				log.debug(fe);
			}
			throw new RuntimeException(fe.getMessage());
		}
		return result;
	}
	
	public static Object runAllMethod(Object target, String methodName, 
			Object[] paramValues, Class[] paramType) {
		if (target == null
				|| methodName == null
				|| methodName.trim().length()==0
				) {
			throw new RuntimeException("Error reflect call Method");
		}
		for(int i=0; i<paramValues.length; i++){
			if (!paramType[i].isAssignableFrom(paramValues[i].getClass())){
				throw new RuntimeException("Error reflect call Method");
			}
		}
		Class clazz = target.getClass();
		Object result = null;
		try{
			Method method = clazz.getMethod(methodName, paramType);
			result = method.invoke(target, paramValues);
		 }catch (Exception fe) {
			if (log.isDebugEnabled()) {
				log.debug(fe);
			}
			throw new RuntimeException(fe.getMessage());
		}
		return result;
	}
	
	public static Map getFieldAndValue(Object target){
		Map values = new HashMap();
		if (target == null) {
			return null;
		}
		Class clazz = target.getClass();
		try {
			Field[] fields = clazz.getDeclaredFields();
			for(int i=0; i<fields.length; i++){
				Field field = fields[i];
				if (Modifier.isPublic(field.getModifiers())) {
					if(!values.containsKey(field.getName())){
						values.put(field.getName(), field.get(target));
					}
				}
			}
		} catch (Exception fe) {
			if (log.isDebugEnabled()) {
				log.debug(fe);
			}
			throw new RuntimeException(fe.getMessage());
		}
		return values;
	}
	
	/**
	 * 取得 VO或Bean等容器对象中的属性值
	 * @param obj VO或Bean等容器对象
	 * @param propStr 属性名或者属性表达式
	 * @return
	 */
	public static Object getReflectValue(Object obj, String propStr) {
		Object objResult = null;
		if (propStr == null) {
			objResult = obj;
		} else {
			try {
				objResult = PropertyUtils.getProperty(obj, propStr);
			} catch (Exception e) {
				log.error("", e);
				throw new RuntimeException(e.getMessage());
			}
		}
		return objResult;
	}
	
	private UtilReflect(){}
}

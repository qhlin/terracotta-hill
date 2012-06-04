package org.terracotta.hill.excel.parse;

import java.util.List;

public interface ExcelSheetTable {
	/**
	 * excel中的数据转换成配置的对象集合
	 * */
	public List parserRecordToObject() throws Exception;
	
	/**
	 * 设置判断记录结束的回调
	 * */
	public void setRecordEnd(IRecordEndCallback recordEnd);
	
	
	/**
	 * 设置判断记录结束的回调接口定义
	 * */
	public interface IRecordEndCallback {
		public boolean isRecordEnd(Object record);
	}
}

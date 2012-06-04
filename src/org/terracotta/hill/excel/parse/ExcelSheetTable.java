package org.terracotta.hill.excel.parse;

import java.util.List;

public interface ExcelSheetTable {
	/**
	 * excel�е�����ת�������õĶ��󼯺�
	 * */
	public List parserRecordToObject() throws Exception;
	
	/**
	 * �����жϼ�¼�����Ļص�
	 * */
	public void setRecordEnd(IRecordEndCallback recordEnd);
	
	
	/**
	 * �����жϼ�¼�����Ļص��ӿڶ���
	 * */
	public interface IRecordEndCallback {
		public boolean isRecordEnd(Object record);
	}
}

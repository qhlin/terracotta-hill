package org.terracotta.hill.excel.config;


import java.io.Serializable;
import java.util.List;

public class TableDefinition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ELEMENT_NAME = "table";
	
	public static final String PROPERTY_ID = "id";
	public static final String PROPERTY_CLASS_NAME = "className";
	public static final String PROPERTY_DISPLAY_MODE = "displayMode";
	public static final String PROPERTY_RECORD_INDEX_BEGIN = "recordIndexBegin";
	/**
	 * 垂直方向，一列表示一条记录
	 * */
	public static final String DISPLAY_MODE_VERTICAL = "00";
	
	/**
	 *水平方向，一行表示一条记录
	 * */
	public static final String DISPLAY_MODE_HORIZONTAL = "01";
	
	private String id;
	private String className;
	private String displayMode;
	private int recordIndexBegin;
	private int fieldIndexBegin;
	private int recordCount;
	private int fieldCount;
	private List fieldList;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}

	public int getRecordIndexBegin() {
		return recordIndexBegin;
	}

	public void setRecordIndexBegin(int recordIndexBegin) {
		this.recordIndexBegin = recordIndexBegin;
	}

	public int getFieldIndexBegin() {
		return fieldIndexBegin;
	}

	public void setFieldIndexBegin(int fieldIndexBegin) {
		this.fieldIndexBegin = fieldIndexBegin;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getFieldList() {
		return fieldList;
	}

	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
	}
}

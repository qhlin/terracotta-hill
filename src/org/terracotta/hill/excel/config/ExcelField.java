package org.terracotta.hill.excel.config;


import java.io.Serializable;

public class ExcelField implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int index;
	private String propertyName;
	private String type;
	private boolean notNull;
	private String requireLabel;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getRequireLabel() {
		return requireLabel;
	}
	public void setRequireLabel(String requireLabel) {
		this.requireLabel = requireLabel;
	}
	

}

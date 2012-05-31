package org.terracotta.hill.excel.util;

import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.terracotta.hill.excel.config.ExcelField;

public class UtilProperty {
	public static void setFieldValue(ExcelField field, Object obj, HSSFRow row){
		String dataType = field.getType();
		String value = getCellValue(row.getCell(field.getIndex()));

		if("boolean".equals(dataType)){
			Boolean content = (value==null||value.equals(""))?null:new Boolean(value);
			UtilReflect.setFieldValue(obj, field.getPropertyName(), Boolean.class, content);
		}
		
		if("short".equals(dataType)){
			Short content = (value==null||value.equals(""))?null:new Short(value);
			UtilReflect.setFieldValue(obj, field.getPropertyName(), Short.class, content);
		}
		
		if("integer".equals(dataType)){
			Integer content = (value==null||value.equals(""))?null:new Integer(value);
			UtilReflect.setFieldValue(obj, field.getPropertyName(), Integer.class, content);
		}
		
		if("long".equals(dataType)){
			Long content = (value==null||value.equals(""))?null:new Long(value);
			UtilReflect.setFieldValue(obj, field.getPropertyName(), Long.class, content);
		}
		
		if("float".equals(dataType)){
			Float content = (value==null||value.equals(""))?null:new Float(value.toString());
			UtilReflect.setFieldValue(obj, field.getPropertyName(), Float.class, content);
		}
		
		if("double".equals(dataType)){
			Double content = (value==null||value.equals(""))?null:new Double(value.toString());
			UtilReflect.setFieldValue(obj, field.getPropertyName(), Double.class, content);
		}
		
		if("bigdecimal".equals(dataType)){
			BigDecimal content = (value==null||value.equals(""))?null:new BigDecimal(value.toString());
			UtilReflect.setFieldValue(obj, field.getPropertyName(), BigDecimal.class, content);
		}
		
		if("string".equals(dataType)||UtilString.isEmpty(dataType)){
			UtilReflect.setFieldValue(obj, field.getPropertyName(), String.class, value);
		}
	}
	
	/**
	 * 获得单元格中的数据
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public static String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				java.text.DecimalFormat formatter = new java.text.DecimalFormat(
						"###############0.00");
				value = formatter.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
				java.text.DecimalFormat formatter = new java.text.DecimalFormat(
				"###############0.00");
				value = formatter.format(cell.getNumericCellValue());
				
			}else {
				value = cell.getRichStringCellValue().toString().trim();
			}
		}
		return value;
	}
}	

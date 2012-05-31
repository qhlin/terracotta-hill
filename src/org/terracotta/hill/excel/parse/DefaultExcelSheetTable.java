package org.terracotta.hill.excel.parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.terracotta.hill.excel.config.ExcelField;
import org.terracotta.hill.excel.config.ExcelTable;
import org.terracotta.hill.excel.parse.callback.IRecordEndCallback;
import org.terracotta.hill.excel.util.UtilProperty;
import org.terracotta.hill.excel.util.UtilReflect;

public class DefaultExcelSheetTable implements ExcelSheetTable {
	private HSSFSheet sheet;
	private ExcelTable table;
	private IRecordEndCallback recordEnd;
	
	public DefaultExcelSheetTable(HSSFSheet sheet, ExcelTable table){
		this.sheet = sheet;
		this.table = table;
	}
	
	public List getRecordObjectList(){
		if(recordEnd == null){
			throw new RuntimeException("请注入判断记录结束的回调函数");
		}
		List result = new ArrayList();
		try{
			Class clazz = Class.forName(table.getClassName());
			
			
			if(ExcelTable.DISPLAY_MODE_HORIZONTAL.equals(table.getDisplayMode())){
				int recordIndex = table.getRecordIndexBegin();
				while(true){
					Object obj = clazz.newInstance();
					HSSFRow row = sheet.getRow(recordIndex);
					if(table.getFieldList() != null && table.getFieldList().size() > 0){
						Iterator it = table.getFieldList().iterator();
						while(it.hasNext()){
							ExcelField field = (ExcelField)it.next();
							
							UtilProperty.setFieldValue(field, obj, row);
						}
					}
					if(recordEnd.isRecordEnd(obj)){
						break;
					}
					result.add(obj);
					recordIndex ++;
				}
			}else{
				int recordIndex = table.getRecordIndexBegin();
				while(true){
					Object obj = clazz.newInstance();
					if(table.getFieldList() != null && table.getFieldList().size() > 0){
						Iterator it = table.getFieldList().iterator();
						while(it.hasNext()){
							ExcelField field = (ExcelField)it.next();
							HSSFRow row = sheet.getRow(field.getIndex());
							String value = UtilProperty.getCellValue(row.getCell(recordIndex));

							UtilReflect.setFieldValue(obj, field.getPropertyName(), String.class, value);
						}
					}
					if(recordEnd.isRecordEnd(obj)){
						break;
					}
					result.add(obj);
					recordIndex ++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}
	
	public IRecordEndCallback getRecordEnd() {
		return recordEnd;
	}
	public void setRecordEnd(IRecordEndCallback recordEnd) {
		this.recordEnd = recordEnd;
	}
}

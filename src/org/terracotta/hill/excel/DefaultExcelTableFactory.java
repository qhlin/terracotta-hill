package org.terracotta.hill.excel;

import java.io.File;
import java.util.Map;

import org.terracotta.hill.excel.parse.ExcelParser;
import org.terracotta.hill.excel.parse.DefaultExcelSheetTable;

public class DefaultExcelTableFactory implements ExcelTableFactory {
	private String configFilePath;
	private String excelFilePath;
	
	private Map excelTableMap;
	
	public DefaultExcelTableFactory(String configFileName, String excelFileName){
		this.configFilePath = configFileName;
		this.excelFilePath = excelFileName;
		
		File excelFile = new File(excelFilePath);
		File configFile = new File(configFilePath);
		
		ExcelParser parser = new ExcelParser(excelFile, configFile);
		try{
			excelTableMap = parser.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public DefaultExcelSheetTable getExcelSheetTable(String tableId){
		return (DefaultExcelSheetTable)excelTableMap.get(tableId);
	}
}

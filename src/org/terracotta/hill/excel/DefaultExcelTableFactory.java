package org.terracotta.hill.excel;

import java.io.File;
import java.util.Map;

import org.terracotta.hill.excel.parse.ExcelParser;
import org.terracotta.hill.excel.parse.ExcelSheetTable;
import org.terracotta.hill.excel.parse.poi.POIExcelParser;

public class DefaultExcelTableFactory implements ExcelTableFactory {
	private String configFilePath;
	private String excelFilePath;
	
	private Map excelTableMap;
	
	public DefaultExcelTableFactory(String configFileName, String excelFileName){
		this.configFilePath = configFileName;
		this.excelFilePath = excelFileName;
		
		File excelFile = new File(excelFilePath);
		File configFile = new File(configFilePath);
		
		ExcelParser parser = new POIExcelParser(excelFile, configFile);
		try{
			excelTableMap = parser.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public ExcelSheetTable getExcelSheetTable(String tableId){
		return (ExcelSheetTable)excelTableMap.get(tableId);
	}
}

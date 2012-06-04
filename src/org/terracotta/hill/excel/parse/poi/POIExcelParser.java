package org.terracotta.hill.excel.parse.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdom.JDOMException;
import org.terracotta.hill.excel.config.TableDefinition;
import org.terracotta.hill.excel.config.xml.XMLConfigParser;
import org.terracotta.hill.excel.parse.ExcelParser;

public class POIExcelParser implements ExcelParser {
	
	private File excelFile;
	private File configFile;
	
	public POIExcelParser(File excelFile, File configFile){
		this.excelFile = excelFile;
		this.configFile = configFile;
	}
		
	public Map execute()throws FileNotFoundException, 
			UnsupportedEncodingException, IOException, JDOMException{
		
		XMLConfigParser configParser = new XMLConfigParser();
		
		Map result = new HashMap();
		
		Map tableMap = configParser.parserToDefinitionMap(configFile);
		
		InputStream myxls = new FileInputStream(excelFile);
		HSSFWorkbook xwb = new HSSFWorkbook(myxls);
		HSSFSheet sheet = xwb.getSheetAt(0);
		
		Iterator it = tableMap.keySet().iterator();
		while(it.hasNext()){
			TableDefinition table = (TableDefinition)tableMap.get(it.next());
			POIExcelSheetTable sheetTable = new POIExcelSheetTable(sheet, table);
			
			result.put(table.getId(), sheetTable);
		}
		
		return result;
	}
}

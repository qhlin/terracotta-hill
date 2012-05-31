package org.terracotta.hill.excel.parse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.terracotta.hill.excel.config.ExcelField;
import org.terracotta.hill.excel.config.ExcelTable;

public class ExcelParser {
	public static String XML_ENCODING="UTF-8";
	
	private File excelFile;
	private File configFile;
	
	public ExcelParser(File excelFile, File configFile){
		this.excelFile = excelFile;
		this.configFile = configFile;
	}
		
	private HSSFWorkbook initExcelFile(File file) throws Exception{
		
		if (!file.exists()) {
			file.mkdir();
		}
		InputStream myxls = null;
		try {
			myxls = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), new Throwable());
		}
		HSSFWorkbook xwb = null;;
		try {
			xwb = new HSSFWorkbook(myxls);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage(), new Throwable());
		}
		return xwb;
	}
	
	public Map execute() throws Exception{
		Map result = new HashMap();
		
		Map tableMap = this.initExcelConfig(configFile);
		HSSFWorkbook xwb = this.initExcelFile(excelFile);
		HSSFSheet sheet = xwb.getSheetAt(0);
		
		Iterator it = tableMap.keySet().iterator();
		while(it.hasNext()){
			ExcelTable table = (ExcelTable)tableMap.get(it.next());
			POIExcelSheetTable sheetTable = new POIExcelSheetTable(sheet, table);
			
			result.put(table.getId(), sheetTable);
		}
		
		return result;
	}
	
	private Map initExcelConfig(File file) throws Exception{
		Map result = new HashMap();
		//1.将配置文件解析成配置对象
		FileInputStream fis = new FileInputStream(file);
		StringBuffer sbf = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(fis, XML_ENCODING));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sbf.append(inputLine);
			}
		} catch (Exception e) {

		}
		String msg = sbf.toString();
		Document doc = null;
		try {
			doc = new SAXBuilder().build(new ByteArrayInputStream(msg.getBytes(XML_ENCODING)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		Element root = doc.getRootElement();
		
		List classElements = root.getChildren("table");
		
		Iterator tableIterator = classElements.iterator();
		while(tableIterator.hasNext()){
			Element classElement = (Element)tableIterator.next();
			ExcelTable table = new ExcelTable();
			table.setId(classElement.getAttributeValue("id"));
			table.setClassName(classElement.getAttributeValue("className"));
			table.setDisplayMode(classElement.getAttributeValue("displayMode"));
			table.setRecordIndexBegin(Integer.valueOf(classElement.getAttributeValue("recordIndexBegin")).intValue());
			
			result.put(table.getId(), table);
			
			List fieldList = new ArrayList();
			List propertyList = classElement.getChildren("property");
			Iterator it = propertyList.iterator();
			while(it.hasNext()){
				Element property = (Element)it.next();
				ExcelField field = new ExcelField();
				field.setIndex(Integer.valueOf(property.getAttributeValue("index")).intValue());
				field.setNotNull(Boolean.valueOf(property.getAttributeValue("notNull")).booleanValue());
				field.setPropertyName(property.getAttributeValue("propertyName"));
				field.setRequireLabel(property.getAttributeValue("requireLabel"));
				field.setType(property.getAttributeValue("dataType"));
				
				fieldList.add(field);
			}
			table.setFieldList(fieldList);
		}
		
		
		return result;
	}
}

package org.terracotta.hill.excel.config.xml;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.terracotta.hill.excel.config.TableDefinition;
import org.terracotta.hill.excel.config.TableFieldDefinition;

public class XMLConfigParser {
	public static final String XML_ENCODING="UTF-8";
	
	public Map parserToDefinitionMap(File file) throws 
			FileNotFoundException, UnsupportedEncodingException, IOException, JDOMException{
		Map result = new HashMap();
		//1.将配置文件解析成配置对象
		FileInputStream fis = new FileInputStream(file);
		StringBuffer sbf = new StringBuffer();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(fis, XML_ENCODING));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sbf.append(inputLine);
		}
		
		String msg = sbf.toString();
		
		Document doc = new SAXBuilder().build(new ByteArrayInputStream(msg.getBytes(XML_ENCODING)));
		
		Element root = doc.getRootElement();
		
		List tableElements = root.getChildren(TableDefinition.ELEMENT_NAME);
		
		Iterator tableIterator = tableElements.iterator();
		while(tableIterator.hasNext()){
			Element tableElement = (Element)tableIterator.next();
			TableDefinition table = this.XMLToTableDefinition(tableElement);
			
			result.put(table.getId(), table);
		}
		return result;
	}
	
	private TableDefinition XMLToTableDefinition(Element tableElement){
		TableDefinition table = new TableDefinition();
		table.setId(tableElement.getAttributeValue(TableDefinition.PROPERTY_ID));
		table.setClassName(tableElement.getAttributeValue(TableDefinition.PROPERTY_CLASS_NAME));
		table.setDisplayMode(tableElement.getAttributeValue(TableDefinition.PROPERTY_DISPLAY_MODE));
		table.setRecordIndexBegin(Integer.valueOf(tableElement.getAttributeValue(TableDefinition.PROPERTY_RECORD_INDEX_BEGIN)).intValue());
				
		List fieldList = new ArrayList();
		List propertyList = tableElement.getChildren(TableFieldDefinition.ELEMENT_NAME);
		
		Iterator it = propertyList.iterator();
		while(it.hasNext()){
			Element property = (Element)it.next();
			TableFieldDefinition field = new TableFieldDefinition();
			field.setIndex(Integer.valueOf(property.getAttributeValue(TableFieldDefinition.PROPERTY_INDEX)).intValue());
			field.setNotNull(Boolean.valueOf(property.getAttributeValue(TableFieldDefinition.PROPERTY_NOT_NULL)).booleanValue());
			field.setPropertyName(property.getAttributeValue(TableFieldDefinition.PROPERTY_PROPERTY_NAME));
			field.setRequireLabel(property.getAttributeValue(TableFieldDefinition.PROPERTY_REQUIRE_LABEL));
			field.setType(property.getAttributeValue(TableFieldDefinition.PROPERTY_DATA_TYPE));
			
			fieldList.add(field);
		}
		
		table.setFieldList(fieldList);
		return table;
	} 
}

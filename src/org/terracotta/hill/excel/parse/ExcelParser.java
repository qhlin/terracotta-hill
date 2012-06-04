package org.terracotta.hill.excel.parse;

import java.util.Map;

public interface ExcelParser {
	public static final String XML_ENCODING="UTF-8";
	
	public Map execute() throws Exception;
}

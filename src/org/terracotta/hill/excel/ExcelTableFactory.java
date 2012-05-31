package org.terracotta.hill.excel;

import org.terracotta.hill.excel.parse.POIExcelSheetTable;

public interface ExcelTableFactory {
	public POIExcelSheetTable getExcelSheetTable(String tableId);
}

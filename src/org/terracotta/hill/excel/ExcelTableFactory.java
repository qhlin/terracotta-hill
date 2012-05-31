package org.terracotta.hill.excel;

import org.terracotta.hill.excel.parse.DefaultExcelSheetTable;

public interface ExcelTableFactory {
	public DefaultExcelSheetTable getExcelSheetTable(String tableId);
}

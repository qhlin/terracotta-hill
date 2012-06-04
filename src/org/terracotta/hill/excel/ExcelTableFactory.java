package org.terracotta.hill.excel;

import org.terracotta.hill.excel.parse.ExcelSheetTable;

public interface ExcelTableFactory {
	public ExcelSheetTable getExcelSheetTable(String tableId);
}

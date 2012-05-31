package org.terracotta.hill.excel;

import java.util.List;

import org.terracotta.hill.excel.parse.DefaultExcelSheetTable;
import org.terracotta.hill.excel.parse.callback.IRecordEndCallback;


public class ExcelTableFactoryTest {
	public static void main(String[] args){
		ExcelTableFactory factory = new DefaultExcelTableFactory("E:/study/java/opensource/terracotta/trunk/build/set.xml", "E:/study/java/opensource/terracotta/trunk/build/set.xls");
		DefaultExcelSheetTable table = factory.getExcelSheetTable("1");
		table.setRecordEnd(new RecordEnd());
		List list = table.getRecordObjectList();
		for(int i = 0; i < list.size(); i++){
			EbkSetSettleFbVO voFb = (EbkSetSettleFbVO)list.get(i);
			System.out.println(voFb.getRecAcntNo());
		}
	}
}

class RecordEnd implements IRecordEndCallback{

	public boolean isRecordEnd(Object record) {
		EbkSetSettleFbVO voFb = (EbkSetSettleFbVO)record;
		if(voFb.getRecAcntNo() == null || voFb.getRecAcntNo().equals("")){
			return true;
		}
		return false;
	}
	
}

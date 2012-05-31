package org.terracotta.hill.excel;

import java.math.BigDecimal;

public class EbkSetSettleFbVO {
	private Integer index;
	private String payAcntNo;
	private String payAcntName;
	private String currencyCode;
	private String bgtItemNo;
	private String bgtItemName;
	private BigDecimal amount;
	private String recAcntNo;
	private String recAcntName;
	

	public String getPayAcntNo() {
		return payAcntNo;
	}
	public void setPayAcntNo(String payAcntNo) {
		this.payAcntNo = payAcntNo;
	}
	public String getPayAcntName() {
		return payAcntName;
	}
	public void setPayAcntName(String payAcntName) {
		this.payAcntName = payAcntName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getBgtItemNo() {
		return bgtItemNo;
	}
	public void setBgtItemNo(String bgtItemNo) {
		this.bgtItemNo = bgtItemNo;
	}
	public String getBgtItemName() {
		return bgtItemName;
	}
	public void setBgtItemName(String bgtItemName) {
		this.bgtItemName = bgtItemName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRecAcntNo() {
		return recAcntNo;
	}
	public void setRecAcntNo(String recAcntNo) {
		this.recAcntNo = recAcntNo;
	}
	public String getRecAcntName() {
		return recAcntName;
	}
	public void setRecAcntName(String recAcntName) {
		this.recAcntName = recAcntName;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
}

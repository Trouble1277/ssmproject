package com.entity;

public class Corporate_financial_documentEntity {
	private Integer cfd_financial_document_id;
	private String cfd_financial_document;
	private String cfd_financial_report_period;
	private Integer enterprise_id;
	public Integer getCfd_financial_document_id(){
		return cfd_financial_document_id;
	}
	public String getCfd_financial_document(){
		return cfd_financial_document;
	}
	public String getCfd_financial_report_period(){
		return cfd_financial_report_period;
	}
	public Integer getEnterprise_id(){
		return enterprise_id;
	}
	public void setCfd_financial_document_id(Integer cfd_financial_document_id){
		this.cfd_financial_document_id=cfd_financial_document_id;
	}
	public void setCfd_financial_document(String cfd_financial_document){
		this.cfd_financial_document=cfd_financial_document;
	}
	public void setCfd_financial_report_period(String cfd_financial_report_period){
		this.cfd_financial_report_period=cfd_financial_report_period;
	}
	public void setEnterprise_id(Integer enterprise_id){
		this.enterprise_id=enterprise_id;
	}

}
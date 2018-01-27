package com.entity;

public class Report_periodEntity{
	private Integer report_period_id;
	private String report_period_name;
	private Integer fund_Id;
	private String report_period_type;
	private String report_period_context;
	private String re_issue_time;
	private String report_period_document;
	public Integer getReport_period_id(){
		return report_period_id;
	}
	public String getReport_period_name(){
		return report_period_name;
	}
	public Integer getFund_Id(){
		return fund_Id;
	}
	public String getReport_period_type(){
		return report_period_type;
	}
	public String getReport_period_context(){
		return report_period_context;
	}
	public String getRe_issue_time(){
		return re_issue_time;
	}
	public String getReport_period_document(){
		return report_period_document;
	}
	public void setReport_period_id(Integer report_period_id){
		this.report_period_id=report_period_id;
	}
	public void setReport_period_name(String report_period_name){
		this.report_period_name=report_period_name;
	}
	public void setFund_Id(Integer fund_Id){
		this.fund_Id=fund_Id;
	}
	public void setReport_period_type(String report_period_type){
		this.report_period_type=report_period_type;
	}
	public void setReport_period_context(String report_period_context){
		this.report_period_context=report_period_context;
	}
	public void setRe_issue_time(String re_issue_time){
		this.re_issue_time=re_issue_time;
	}
	public void setReport_period_document(String report_period_document){
		this.report_period_document=report_period_document;
	}

}
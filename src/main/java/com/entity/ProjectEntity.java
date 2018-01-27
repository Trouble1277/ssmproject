package com.entity;

public class ProjectEntity{
	private Integer project_id;
	private String project_name;
	private String project_phases;
	private Integer principal_id;
	private Integer contacts_id;
	private String project_total_amount;
	private String project_privately_fund;
	private String project_leverage_fund;
	private String project_shareholding_ratio;
	private Integer fund_Id;
	private Integer enterprise_id;
	private String project_remark;
	public Integer getProject_id(){
		return project_id;
	}
	public String getProject_name(){
		return project_name;
	}
	public String getProject_phases(){
		return project_phases;
	}
	public Integer getPrincipal_id(){
		return principal_id;
	}
	public Integer getContacts_id(){
		return contacts_id;
	}
	public String getProject_total_amount(){
		return project_total_amount;
	}
	public String getProject_privately_fund(){
		return project_privately_fund;
	}
	public String getProject_leverage_fund(){
		return project_leverage_fund;
	}
	public String getProject_shareholding_ratio(){
		return project_shareholding_ratio;
	}
	public Integer getFund_Id(){
		return fund_Id;
	}
	public Integer getEnterprise_id(){
		return enterprise_id;
	}
	public String getProject_remark(){
		return project_remark;
	}
	public void setProject_id(Integer project_id){
		this.project_id=project_id;
	}
	public void setProject_name(String project_name){
		this.project_name=project_name;
	}
	public void setProject_phases(String project_phases){
		this.project_phases=project_phases;
	}
	public void setPrincipal_id(Integer principal_id){
		this.principal_id=principal_id;
	}
	public void setContacts_id(Integer contacts_id){
		this.contacts_id=contacts_id;
	}
	public void setProject_total_amount(String project_total_amount){
		this.project_total_amount=project_total_amount;
	}
	public void setProject_privately_fund(String project_privately_fund){
		this.project_privately_fund=project_privately_fund;
	}
	public void setProject_leverage_fund(String project_leverage_fund){
		this.project_leverage_fund=project_leverage_fund;
	}
	public void setProject_shareholding_ratio(String project_shareholding_ratio){
		this.project_shareholding_ratio=project_shareholding_ratio;
	}
	public void setFund_Id(Integer fund_Id){
		this.fund_Id=fund_Id;
	}
	public void setEnterprise_id(Integer enterprise_id){
		this.enterprise_id=enterprise_id;
	}
	public void setProject_remark(String project_remark){
		this.project_remark=project_remark;
	}

}
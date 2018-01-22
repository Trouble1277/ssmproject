package com.entity;

public class Corporate_equity_structure_designEntity{
	private Integer cesd_cor_equity_id;
	private Integer enterprise_id;
	private String cesd_report_period;
	private String cesd_shareholder_name;
	private String cesd_shareholdering_ratio;
	public Integer getCesd_cor_equity_id(){
		return cesd_cor_equity_id;
	}
	public Integer getEnterprise_id(){
		return enterprise_id;
	}
	public String getCesd_report_period(){
		return cesd_report_period;
	}
	public String getCesd_shareholder_name(){
		return cesd_shareholder_name;
	}
	public String getCesd_shareholdering_ratio(){
		return cesd_shareholdering_ratio;
	}
	public void setCesd_cor_equity_id(Integer cesd_cor_equity_id){
		this.cesd_cor_equity_id=cesd_cor_equity_id;
	}
	public void setEnterprise_id(Integer enterprise_id){
		this.enterprise_id=enterprise_id;
	}
	public void setCesd_report_period(String cesd_report_period){
		this.cesd_report_period=cesd_report_period;
	}
	public void setCesd_shareholder_name(String cesd_shareholder_name){
		this.cesd_shareholder_name=cesd_shareholder_name;
	}
	public void setCesd_shareholdering_ratio(String cesd_shareholdering_ratio){
		this.cesd_shareholdering_ratio=cesd_shareholdering_ratio;
	}

}
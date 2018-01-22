package com.entity;

public class Corporate_visit_recordEntity{
	private Integer cvr_cor_visit_id;
	private Integer visit_record_id;
	private Integer enterprise_id;
	public Integer getCvr_cor_visit_id(){
		return cvr_cor_visit_id;
	}
	public Integer getVisit_record_id(){
		return visit_record_id;
	}
	public Integer getEnterprise_id(){
		return enterprise_id;
	}
	public void setCvr_cor_visit_id(Integer cvr_cor_visit_id){
		this.cvr_cor_visit_id=cvr_cor_visit_id;
	}
	public void setVisit_record_id(Integer visit_record_id){
		this.visit_record_id=visit_record_id;
	}
	public void setEnterprise_id(Integer enterprise_id){
		this.enterprise_id=enterprise_id;
	}

}
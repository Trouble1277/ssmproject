package com.entity;

public class Project_principleEntity{
	private Integer pp_id;
	private Integer contacts_id;
	private Integer enterprise_id;
	public Integer getPp_id(){
		return pp_id;
	}
	public Integer getContacts_id(){
		return contacts_id;
	}
	public Integer getEnterprise_id(){
		return enterprise_id;
	}
	public void setPp_id(Integer pp_id){
		this.pp_id=pp_id;
	}
	public void setContacts_id(Integer contacts_id){
		this.contacts_id=contacts_id;
	}
	public void setEnterprise_id(Integer enterprise_id){
		this.enterprise_id=enterprise_id;
	}

}
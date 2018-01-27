package com.entity;

public class Enterprise_informationEntity{
	private Integer enterprise_id;
	private String ent_CN_fullname;
	private String ent_CN_abbreviation;
	private String ent_data_establishment;
	private String ent_entering_register_classify;
	private Integer ent_employees_number;
	private Integer legal_id;
	private String ent_organization_code;
	private String ent_industry;
	private String ent_related_products;
	private Integer contacts_id;
	public Integer getEnterprise_id(){
		return enterprise_id;
	}
	public String getEnt_CN_fullname(){
		return ent_CN_fullname;
	}
	public String getEnt_CN_abbreviation(){
		return ent_CN_abbreviation;
	}
	public String getEnt_data_establishment(){
		return ent_data_establishment;
	}
	public String getEnt_entering_register_classify(){
		return ent_entering_register_classify;
	}
	public Integer getEnt_employees_number(){
		return ent_employees_number;
	}
	public Integer getLegal_id(){
		return legal_id;
	}
	public String getEnt_organization_code(){
		return ent_organization_code;
	}
	public String getEnt_industry(){
		return ent_industry;
	}
	public String getEnt_related_products(){
		return ent_related_products;
	}
	public Integer getContacts_id(){
		return contacts_id;
	}
	public void setEnterprise_id(Integer enterprise_id){
		this.enterprise_id=enterprise_id;
	}
	public void setEnt_CN_fullname(String ent_CN_fullname){
		this.ent_CN_fullname=ent_CN_fullname;
	}
	public void setEnt_CN_abbreviation(String ent_CN_abbreviation){
		this.ent_CN_abbreviation=ent_CN_abbreviation;
	}
	public void setEnt_data_establishment(String ent_data_establishment){
		this.ent_data_establishment=ent_data_establishment;
	}
	public void setEnt_entering_register_classify(String ent_entering_register_classify){
		this.ent_entering_register_classify=ent_entering_register_classify;
	}
	public void setEnt_employees_number(Integer ent_employees_number){
		this.ent_employees_number=ent_employees_number;
	}
	public void setLegal_id(Integer legal_id){
		this.legal_id=legal_id;
	}
	public void setEnt_organization_code(String ent_organization_code){
		this.ent_organization_code=ent_organization_code;
	}
	public void setEnt_industry(String ent_industry){
		this.ent_industry=ent_industry;
	}
	public void setEnt_related_products(String ent_related_products){
		this.ent_related_products=ent_related_products;
	}
	public void setContacts_id(Integer contacts_id){
		this.contacts_id=contacts_id;
	}

}
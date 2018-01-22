package com.entity;

public class NaturalEntity{
	private Integer natural_Id;
	private String natural_name;
	private String english_name;
	private String birthday;
	private String their_enterprise;
	private String capital_source;
	private String stay_for_fund;
	private String whether_cast;
	private Integer contacts_Id;
	public Integer getNatural_Id(){
		return natural_Id;
	}
	public String getNatural_name(){
		return natural_name;
	}
	public String getEnglish_name(){
		return english_name;
	}
	public String getBirthday(){
		return birthday;
	}
	public String getTheir_enterprise(){
		return their_enterprise;
	}
	public String getCapital_source(){
		return capital_source;
	}
	public String getStay_for_fund(){
		return stay_for_fund;
	}
	public String getWhether_cast(){
		return whether_cast;
	}
	public Integer getContacts_Id(){
		return contacts_Id;
	}
	public void setNatural_Id(Integer natural_Id){
		this.natural_Id=natural_Id;
	}
	public void setNatural_name(String natural_name){
		this.natural_name=natural_name;
	}
	public void setEnglish_name(String english_name){
		this.english_name=english_name;
	}
	public void setBirthday(String birthday){
		this.birthday=birthday;
	}
	public void setTheir_enterprise(String their_enterprise){
		this.their_enterprise=their_enterprise;
	}
	public void setCapital_source(String capital_source){
		this.capital_source=capital_source;
	}
	public void setStay_for_fund(String stay_for_fund){
		this.stay_for_fund=stay_for_fund;
	}
	public void setWhether_cast(String whether_cast){
		this.whether_cast=whether_cast;
	}
	public void setContacts_Id(Integer contacts_Id){
		this.contacts_Id=contacts_Id;
	}

}
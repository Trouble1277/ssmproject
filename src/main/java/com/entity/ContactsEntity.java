package com.entity;

public class ContactsEntity{
	private Integer contacts_Id;
	private String contacts_name;
	private String contacts_site;
	private String contacts_phone;
	private String contacts_postcode;
	private String contacts_faxes;
	private String contacts_postbox;
	public Integer getContacts_Id(){
		return contacts_Id;
	}
	public String getContacts_name(){
		return contacts_name;
	}
	public String getContacts_site(){
		return contacts_site;
	}
	public String getContacts_phone(){
		return contacts_phone;
	}
	public String getContacts_postcode(){
		return contacts_postcode;
	}
	public String getContacts_faxes(){
		return contacts_faxes;
	}
	public String getContacts_postbox(){
		return contacts_postbox;
	}
	public void setContacts_Id(Integer contacts_Id){
		this.contacts_Id=contacts_Id;
	}
	public void setContacts_name(String contacts_name){
		this.contacts_name=contacts_name;
	}
	public void setContacts_site(String contacts_site){
		this.contacts_site=contacts_site;
	}
	public void setContacts_phone(String contacts_phone){
		this.contacts_phone=contacts_phone;
	}
	public void setContacts_postcode(String contacts_postcode){
		this.contacts_postcode=contacts_postcode;
	}
	public void setContacts_faxes(String contacts_faxes){
		this.contacts_faxes=contacts_faxes;
	}
	public void setContacts_postbox(String contacts_postbox){
		this.contacts_postbox=contacts_postbox;
	}

}
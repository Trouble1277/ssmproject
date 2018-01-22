package com.entity;

public class Project_documentEntity{
	private Integer project_documents_id;
	private String project_documents_name;
	private String project_documents_class;
	private Integer project_id;
	private String pd_upload_name;
	private String pd_upload_time;
	public Integer getProject_documents_id(){
		return project_documents_id;
	}
	public String getProject_documents_name(){
		return project_documents_name;
	}
	public String getProject_documents_class(){
		return project_documents_class;
	}
	public Integer getProject_id(){
		return project_id;
	}
	public String getPd_upload_name(){
		return pd_upload_name;
	}
	public String getPd_upload_time(){
		return pd_upload_time;
	}
	public void setProject_documents_id(Integer project_documents_id){
		this.project_documents_id=project_documents_id;
	}
	public void setProject_documents_name(String project_documents_name){
		this.project_documents_name=project_documents_name;
	}
	public void setProject_documents_class(String project_documents_class){
		this.project_documents_class=project_documents_class;
	}
	public void setProject_id(Integer project_id){
		this.project_id=project_id;
	}
	public void setPd_upload_name(String pd_upload_name){
		this.pd_upload_name=pd_upload_name;
	}
	public void setPd_upload_time(String pd_upload_time){
		this.pd_upload_time=pd_upload_time;
	}

}
package com.entity;

public class Activity_modelEntity {
	private Integer activity_model_id;
	private String activity_model_url;
	private String activity_model_remark;
	public Integer getActivity_model_id(){
		return activity_model_id;
	}
	public String getActivity_model_url(){
		return activity_model_url;
	}
	public String getActivity_model_remark(){
		return activity_model_remark;
	}
	public void setActivity_model_id(Integer activity_model_id){
		this.activity_model_id=activity_model_id;
	}
	public void setActivity_model_url(String activity_model_url){
		this.activity_model_url=activity_model_url;
	}
	public void setActivity_model_remark(String activity_model_remark){
		this.activity_model_remark=activity_model_remark;
	}

}
package com.entity;

public class BlacklistEntity{
	private Integer lP_blacklist_id;
	private Integer lP_fund_ribution_Id;
	private String bl_staged_dunning;
	private String bl_remark;
	public Integer getLP_blacklist_id(){
		return lP_blacklist_id;
	}
	public Integer getLP_fund_ribution_Id(){
		return lP_fund_ribution_Id;
	}
	public String getBl_staged_dunning(){
		return bl_staged_dunning;
	}
	public String getBl_remark(){
		return bl_remark;
	}
	public void setLP_blacklist_id(Integer LP_blacklist_id){
		this.lP_blacklist_id=LP_blacklist_id;
	}
	public void setLP_fund_ribution_Id(Integer LP_fund_ribution_Id){
		this.lP_fund_ribution_Id=LP_fund_ribution_Id;
	}
	public void setBl_staged_dunning(String bl_staged_dunning){
		this.bl_staged_dunning=bl_staged_dunning;
	}
	public void setBl_remark(String bl_remark){
		this.bl_remark=bl_remark;
	}

}
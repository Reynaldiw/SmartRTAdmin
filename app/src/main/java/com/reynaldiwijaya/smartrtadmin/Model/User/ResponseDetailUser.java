package com.reynaldiwijaya.smartrtadmin.Model.User;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailUser{

	@SerializedName("result")
	private String result;

	@SerializedName("pesan")
	private String pesan;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}
}
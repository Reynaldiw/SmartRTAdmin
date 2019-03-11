package com.reynaldiwijaya.smartrtadmin.Model.Report;

import com.google.gson.annotations.SerializedName;

public class ResponseReply{

	@SerializedName("result")
	private String result;

	@SerializedName("message")
	private String message;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
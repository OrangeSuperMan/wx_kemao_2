package edu.gdkm.weixin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseError extends ResponseMessage {

	@JsonProperty("errorCode")
	private int errorCode;
	@JsonProperty("errormsg")
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}

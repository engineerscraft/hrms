package com.hamdard.hua.model;

public class ErrorBody {
	private String errorMessage;

	public ErrorBody(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

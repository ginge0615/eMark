package com.emart.model;

public class MessageModel {
	private String messageCode;
	private String[] args;
	private String message;
	
	public MessageModel() {
		
	}
	
	public MessageModel(String messageCode) {
		this.messageCode = messageCode;
	}
	
	public MessageModel(String messageCode, String[] args) {
		this.messageCode = messageCode;
		this.args = args;
	}
	
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

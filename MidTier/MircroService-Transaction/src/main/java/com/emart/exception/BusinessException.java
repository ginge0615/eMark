package com.emart.exception;

import com.emart.model.MessageModel;

public class BusinessException extends Throwable {
	private static final long serialVersionUID = 8539109213573501920L;
	
	private String messageCode;
	private String[] args;
	
	public BusinessException(String messageCode) {
		this.messageCode = messageCode;
	}
	
	public BusinessException(String messageCode, String... args) {
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
	public void setArgs(String... args) {
		this.args = args;
	}
	
	public MessageModel getMessageModel() {
		MessageModel model = new MessageModel();
		model.setMessageCode(messageCode);
		model.setArgs(args);
		return model;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[message code]=").append(this.messageCode);
		
		if (this.args != null) {
			buf.append("[args]=").append(this.args);
		}
		return buf.toString();
	}
}

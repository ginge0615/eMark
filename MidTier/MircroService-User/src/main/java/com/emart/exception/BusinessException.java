package com.emart.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BusinessException extends Throwable {
	private static final long serialVersionUID = 8539109213573501920L;
	
	@Autowired
	private MessageSource msgSource;
	
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
	
	@Override
	public String getMessage() {
		return msgSource.getMessage(messageCode, args, null);
	}
}

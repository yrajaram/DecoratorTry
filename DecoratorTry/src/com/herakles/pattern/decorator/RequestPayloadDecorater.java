package com.herakles.pattern.decorator;

import javax.activation.DataHandler;

public abstract class RequestPayloadDecorater implements RequestPayload{
	volatile RequestPayload reqPayload;
    
	public RequestPayloadDecorater(RequestPayload req) {
		reqPayload = req;
	}
	public DataHandler getContent() throws Exception {
		addedBehavior();
		return this.reqPayload.getContent();
	}
	protected abstract void addedBehavior() throws Exception;
	
	public void setContent(DataHandler content) {
		this.reqPayload.setContent(content);
	}
}


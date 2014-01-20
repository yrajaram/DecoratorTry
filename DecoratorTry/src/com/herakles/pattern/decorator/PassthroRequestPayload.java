package com.herakles.pattern.decorator;

import javax.activation.DataHandler;

public class PassthroRequestPayload implements RequestPayload {
	private DataHandler content;
	
	public PassthroRequestPayload(DataHandler dh) {
		this.content = dh;
	}

	public DataHandler getContent() {
		return content;
	}

	public void setContent(DataHandler content) {
		this.content = content;
	}
}


package com.herakles.pattern.decorator;

import java.io.IOException;

import javax.activation.DataHandler;

public interface RequestPayload {

	public DataHandler getContent() throws IOException, Exception;
	public void setContent(DataHandler dh);
}


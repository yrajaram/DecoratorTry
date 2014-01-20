package com.herakles.pattern.decorator;

public class CipherXfrRequestPayload extends RequestPayloadDecorater {
	
	public CipherXfrRequestPayload(RequestPayload req) {
		super(req);
	}
    protected void addedBehavior () {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
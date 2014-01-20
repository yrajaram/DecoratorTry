package com.herakles.pattern.decorator;

public class CryptoXfrRequestPayload extends RequestPayloadDecorater {

	public CryptoXfrRequestPayload(RequestPayload req) {
		super(req);
	}

    protected void addedBehavior () {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
package com.herakles.pattern.decorator;

import java.io.IOException;

import javax.activation.DataHandler;
import org.apache.commons.codec.binary.Base64InputStream;
import com.herakles.pattern.decorator.helper.InputStreamDataSource;


public class Base64DecodedRequestPayload extends RequestPayloadDecorater {
	private DataHandler dh;
	
	public Base64DecodedRequestPayload(RequestPayload req) throws IOException, Exception {
		super( req );
		dh = req.getContent();
	}
	
    protected void addedBehavior () throws IOException {
    	System.out.println("--Base64 Decoding on stream---");
    	// The default behavior of the Base64InputStream is to DECODE,
		reqPayload.setContent(new DataHandler(new InputStreamDataSource(new Base64InputStream(dh.getInputStream()))));
    }
}


package com.herakles.pattern.decorator;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.activation.DataHandler;

import com.herakles.pattern.decorator.helper.InputStreamDataSource;

public class GunzipRequestPayload extends RequestPayloadDecorater {
	private DataHandler dh;

	public GunzipRequestPayload(RequestPayload req) throws Exception {
		super(req);
		dh = req.getContent();
	}

	protected void addedBehavior () throws IOException {
		System.out.println("--GUNZip content or use the appropriate stream---");
		reqPayload.setContent(new DataHandler(new InputStreamDataSource(new GZIPInputStream(dh.getInputStream()))));
	}
}


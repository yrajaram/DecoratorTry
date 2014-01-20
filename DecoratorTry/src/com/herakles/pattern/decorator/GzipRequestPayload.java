package com.herakles.pattern.decorator;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.activation.DataHandler;

import com.Ostermiller.util.CircularByteBuffer;
import com.herakles.pattern.decorator.helper.InputStreamDataSource;

public class GzipRequestPayload extends RequestPayloadDecorater {	
	private CircularByteBuffer cbb = new CircularByteBuffer(CircularByteBuffer.INFINITE_SIZE);
	private DataHandler dh;
	  
	public GzipRequestPayload(RequestPayload req) throws Exception {
		super(req);
		dh = req.getContent();
	}

    protected void addedBehavior () throws IOException {
        System.out.println("--GZip content (nothing has to be done because content is stored as GZip in DB)---");
        GZIPOutputStream gzo = new GZIPOutputStream(cbb.getOutputStream());
        dh.writeTo(gzo);
        gzo.flush();gzo.close();
		reqPayload.setContent(new DataHandler(new InputStreamDataSource(cbb.getInputStream())));
    }
}


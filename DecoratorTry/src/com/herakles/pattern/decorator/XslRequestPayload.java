package com.herakles.pattern.decorator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.xml.transform.TransformerException;

import com.Ostermiller.util.CircularByteBuffer;
import com.herakles.pattern.decorator.helper.InputStreamDataSource;


public class XslRequestPayload extends RequestPayloadDecorater {
	private final String 	XslFilename;
	// Set up the transformation    
	javax.xml.transform.TransformerFactory tFactory =javax.xml.transform.TransformerFactory.newInstance();
	javax.xml.transform.Transformer transformer;
	private DataHandler dh;
	private CircularByteBuffer cbb = new CircularByteBuffer(CircularByteBuffer.INFINITE_SIZE);

	public XslRequestPayload(RequestPayload req, String xslFilename) throws Exception {
		super(req);
		XslFilename = xslFilename;
		transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource  (XslFilename));
		dh = req.getContent();
	}
	protected void addedBehavior () throws FileNotFoundException, IOException, TransformerException {
    	System.out.println("--XSL transforming on stream---");
		OutputStream os = cbb.getOutputStream();
		transformer.transform (new javax.xml.transform.stream.StreamSource(dh.getInputStream()), 
				new javax.xml.transform.stream.StreamResult( os) );
		os.flush(); os.close();
		reqPayload.setContent(new DataHandler(new InputStreamDataSource(cbb.getInputStream())));
	}
}


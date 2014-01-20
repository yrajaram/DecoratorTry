import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import com.herakles.pattern.decorator.Base64DecodedRequestPayload;
import com.herakles.pattern.decorator.Base64EncodedRequestPayload;
import com.herakles.pattern.decorator.GunzipRequestPayload;
import com.herakles.pattern.decorator.GzipRequestPayload;
import com.herakles.pattern.decorator.PassthroRequestPayload;
import com.herakles.pattern.decorator.RequestPayloadDecorater;
import com.herakles.pattern.decorator.XslRequestPayload;


public class Main {
	public static void main( String[] args ) {
		
		String inputFileName =  "data/CiscoB2BServicePULL.xml";
		String inputGzipFileName =  "data/CiscoB2BServicePULL.xml.gz";
		String outputFileName =  "data/CiscoB2BServicePULL-out.xml";
		String outputGzipFileName =  "data/CiscoB2BServicePULL-out.xml.gz";
		String outputEncodedFileName =  "data/CiscoB2BServicePULL-out.xml.enc";
		String outputDecodedFileName =  "data/CiscoB2BServicePULL-out.xml.dec";
		String outputXsledFileName =  "data/CiscoB2BServicePULL-out.xsled.xml";
		String xslFileName =  "data/l7-get-ccoid.xsl";

		OutputStream out = null;
		RequestPayloadDecorater tmp;
		PassthroRequestPayload origReq;

		try {
			System.out.println( "Processing" );

			DataHandler handler = new DataHandler(new FileDataSource (new File(inputFileName)));
			
			// Alternate way to get DH
//			DataHandler handler = new DataHandler(new InputStreamDataSource(new FileInputStream(inputFileName)));

			out = new FileOutputStream( outputFileName );
			origReq = new PassthroRequestPayload(handler);
			origReq.getContent().writeTo( out );
			out.flush();

			handler = new DataHandler(new FileDataSource (new File(inputFileName)));
			origReq = new PassthroRequestPayload(handler);
			out = new FileOutputStream(outputGzipFileName );
			tmp=new GzipRequestPayload(origReq);
			tmp.getContent().writeTo(out);
			out.flush();

			handler = new DataHandler(new FileDataSource (new File(inputGzipFileName)));
			origReq = new PassthroRequestPayload(handler);
			out = new FileOutputStream( outputFileName );
			tmp = new GunzipRequestPayload(origReq);
			tmp.getContent().writeTo( out );
			out.flush();

			handler = new DataHandler(new FileDataSource (new File(inputFileName)));
			origReq = new PassthroRequestPayload(handler);
			out = new FileOutputStream( outputXsledFileName );
			tmp = new XslRequestPayload(origReq,xslFileName);
			tmp.getContent().writeTo( out );
			out.flush();out.close();
			
			handler = new DataHandler(new FileDataSource (new File(inputGzipFileName)));
			origReq = new PassthroRequestPayload(handler);
			out = new FileOutputStream( outputEncodedFileName );
			tmp = new Base64EncodedRequestPayload(origReq);
			tmp.getContent().writeTo( out );
			out.flush();
			out.close();
			
			handler = new DataHandler(new FileDataSource (new File(inputGzipFileName)));
			origReq = new PassthroRequestPayload(handler);
			out = new FileOutputStream( outputDecodedFileName );
			tmp = new Base64DecodedRequestPayload(origReq);
			tmp.getContent().writeTo( out );
			out.flush();

			System.out.println( "Done" );
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
package rs.ac.uns.ftn.mapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

public class JaxbMapper {

	private static final String CONTEXT = "rs.ac.uns.ftn.jaxb.p1";
	
	public static ZahtevZaPriznanjePatenta unmarshalZahtevFromFile(String filePath) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		JAXBContext context = JAXBContext.newInstance(CONTEXT);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaPriznanjePatenta bookstore = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(new File(filePath));		
		
		return bookstore;
	}
	
	public static ZahtevZaPriznanjePatenta unmarshalZahtevFromNode(Node contentAsDOM) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");

		JAXBContext context = JAXBContext.newInstance(CONTEXT);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaPriznanjePatenta bookstore = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(contentAsDOM);
		
		return bookstore;
	}
	
	public static OutputStream marshallZahtev(ZahtevZaPriznanjePatenta patent) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CONTEXT);
		OutputStream os = new ByteArrayOutputStream();

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		 marshaller.setProperty("com.sun.xml.bind.xmlHeaders"," <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
         marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/grddl.xsl\"?>");
         
		marshaller.marshal(patent, os);
		
		return os;
	}

	
}

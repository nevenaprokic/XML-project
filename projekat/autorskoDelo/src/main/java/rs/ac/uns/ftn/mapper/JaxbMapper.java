package rs.ac.uns.ftn.mapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;

public class JaxbMapper {

	private static final String CONTEXT = "rs.ac.uns.ftn.jaxb.a1";
	private static final String CONTEXT_RESENJE = "rs.ac.uns.ftn.jaxb.resenje";
	
	public static ZahtevZaAutorskoDelo unmarshalZahtevZaAutorskoDeloFromFile(String filePath) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		JAXBContext context = JAXBContext.newInstance(CONTEXT);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaAutorskoDelo bookstore = (ZahtevZaAutorskoDelo) unmarshaller.unmarshal(new File(filePath));		
		
		return bookstore;
	}
	
	public static ZahtevZaAutorskoDelo unmarshalZahtevZaAutorskoDeloFromNode(Node contentAsDOM) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");

		JAXBContext context = JAXBContext.newInstance(CONTEXT);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaAutorskoDelo bookstore = (ZahtevZaAutorskoDelo) unmarshaller.unmarshal(contentAsDOM);
		
		return bookstore;
	}
	
	public static OutputStream marshallZahtevZaAutroskoDelo(ZahtevZaAutorskoDelo delo) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CONTEXT);
		OutputStream os = new ByteArrayOutputStream();

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		 marshaller.setProperty("com.sun.xml.bind.xmlHeaders"," <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
         marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/grddl.xsl\"?>");
         
		marshaller.marshal(delo, os);
		
		return os;
	}
	
	public static Resenje unmarshalResenjeFromFile(String filePath) throws JAXBException {
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");

		JAXBContext context = JAXBContext.newInstance(CONTEXT_RESENJE);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Resenje resenje = (Resenje) unmarshaller.unmarshal(new File(filePath));

		return resenje;
	}

	public static Resenje unmarshalResenjeFromNode(Node contentAsDOM) throws JAXBException {
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");

		JAXBContext context = JAXBContext.newInstance(CONTEXT_RESENJE);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Resenje resenje = (Resenje) unmarshaller.unmarshal(contentAsDOM);

		return resenje;
	}

	public static OutputStream marshallResenje(Resenje resenje) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CONTEXT_RESENJE);
		OutputStream os = new ByteArrayOutputStream();

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty("com.sun.xml.bind.xmlHeaders", " <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
				"<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/grddl.xsl\"?>");

		marshaller.marshal(resenje, os);

		return os;
	}

	
}

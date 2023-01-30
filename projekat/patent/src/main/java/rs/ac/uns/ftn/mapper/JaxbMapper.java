package rs.ac.uns.ftn.mapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import org.w3c.dom.Node;

import com.github.andrewoma.dexx.collection.HashMap;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.jaxb.resenje.Resenje;

public class JaxbMapper {

	private static final String CONTEXT = "rs.ac.uns.ftn.jaxb.p1";
	private static final String CONTEXT_RESENJE = "rs.ac.uns.ftn.jaxb.resenje";
	
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
		System.out.println(bookstore);
		return bookstore;
	}
	
	public static OutputStream marshallZahtev(ZahtevZaPriznanjePatenta patent) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CONTEXT);
		OutputStream os = new ByteArrayOutputStream();

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty("com.sun.xml.bind.xmlHeaders"," <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/grddl.xsl\"?>");
         
//         HashMap<String, String> urisToPrefixes = new HashMap<String, String>();
//         urisToPrefixes.put("http://ftn.uns.ac.rs/p1", "rootNS");
//         marshaller.setProperty(MarshallerProperties.NAMESPACE_PREFIX_MAPPER, urisToPrefixes);
        marshaller.marshal(patent, os);
		
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

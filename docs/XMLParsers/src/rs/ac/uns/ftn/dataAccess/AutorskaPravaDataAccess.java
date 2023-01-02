package rs.ac.uns.ftn.dataAccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.ftn.dataAccess.utils.ConnectionUtilities;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;

public class AutorskaPravaDataAccess {
	
	private JAXBContext context;
	private final String collectionId = "db/project/autorkaDela";
	private final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/a1";
	
	public AutorskaPravaDataAccess() {
		setContext();
	}

	private void setContext() {
		try {
			context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.a1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ZahtevZaAutorskoDelo unmarshalZahtevZaAutorskoDeloFromFile(String filePath) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaAutorskoDelo bookstore = (ZahtevZaAutorskoDelo) unmarshaller.unmarshal(new File(filePath));		
		
		return bookstore;
	}
	
	private ZahtevZaAutorskoDelo unmarshalZahtevZaAutorskoDeloFromNode(Node contentAsDOM) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaAutorskoDelo bookstore = (ZahtevZaAutorskoDelo) unmarshaller.unmarshal(contentAsDOM);
		
		return bookstore;
	}
	
	private OutputStream marshallZahtevZaAutroskoDelo(ZahtevZaAutorskoDelo delo) throws JAXBException {
		OutputStream os = new ByteArrayOutputStream();

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(delo, os);
		
		return os;
	}

	
	public void saveFile(String resourceId, String filePath) {
		Collection col = null;
		XMLResource res = null;
		try {
			ConnectionUtilities.setup();
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, resourceId);
			
			ZahtevZaAutorskoDelo delo = unmarshalZahtevZaAutorskoDeloFromFile(filePath);
			
			// do something to delo;
			
			OutputStream os = marshallZahtevZaAutroskoDelo(delo);
			
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public ZahtevZaAutorskoDelo getZahtevById(String documentId) {
		Collection col = null;
		XMLResource res = null;
		try {
			ConnectionUtilities.setup();
			col = ConnectionUtilities.getCollection(collectionId);
			res = ConnectionUtilities.getResource(col, documentId);
			
			if(res == null) {
	            System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
	            return null;
	        } else {
	        	
	        	return unmarshalZahtevZaAutorskoDeloFromNode(res.getContentAsDOM());
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	
	public ResourceSet getByXQuery(String xQueryExpression) {
		Collection col = null;
		try {
			ConnectionUtilities.setup();
			col = ConnectionUtilities.getCollection(collectionId);
			return ConnectionUtilities.getResourceSetByXQuery(col, TARGET_NAMESPACE, xQueryExpression);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col);
		}
	}
}

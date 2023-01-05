package rs.ac.uns.ftn.dataAccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.ftn.dataAccess.utils.ConnectionUtilities;
import rs.ac.uns.ftn.dataAccess.utils.DBManipulationUtilities;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

@Component
public class PatentDataAccess {
	
	private JAXBContext context;
	private final String collectionId = "db/project/patenti";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/p1";
	private static final String CONTEXT = "rs.ac.uns.ftn.jaxb.p1";
	
	public PatentDataAccess() {
		setContext();
		setupDB();
	}

	private void setContext() {
		try {
			context = JAXBContext.newInstance(CONTEXT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void setupDB() {
		try {
			ConnectionUtilities.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ZahtevZaPriznanjePatenta unmarshalZahtevZaPriznanjePatentaFromFile(String filePath) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaPriznanjePatenta bookstore = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(new File(filePath));		
		
		return bookstore;
	}
	
	private ZahtevZaPriznanjePatenta unmarshalZahtevZaPriznanjePatentaFromNode(Node contentAsDOM) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZahtevZaPriznanjePatenta bookstore = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(contentAsDOM);
		
		return bookstore;
	}
	
	private OutputStream marshallZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatenta delo) throws JAXBException {
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
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, resourceId);
			
			ZahtevZaPriznanjePatenta delo = unmarshalZahtevZaPriznanjePatentaFromFile(filePath);
			
			// do something to delo;
			
			OutputStream os = marshallZahtevZaPriznanjePatenta(delo);
			
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public void saveFile(String resourceId, ZahtevZaPriznanjePatenta delo) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, resourceId);
			
			//ZahtevZaAutorskoDelo delo = unmarshalZahtevZaAutorskoDeloFromFile(filePath);
			
			// do something to delo;
			
			OutputStream os = marshallZahtevZaPriznanjePatenta(delo);
			
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public ZahtevZaPriznanjePatenta getZahtevById(String documentId) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			res = ConnectionUtilities.getResource(col, documentId);
			
			if(res == null) {
	            System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
	            return null;
	        } else {
	        	
	        	return unmarshalZahtevZaPriznanjePatentaFromNode(res.getContentAsDOM());
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public ResourceSet getByXPath(String xPathExpression) {
		Collection col = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			return DBManipulationUtilities.getResourceSetByXPath(col, TARGET_NAMESPACE, xPathExpression);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col);
		}
	}
	
	public void updateDocument(String documentId, String contextXPath, String xmlFragment) {
		Collection col = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			DBManipulationUtilities.update(col, documentId, TARGET_NAMESPACE, contextXPath, xmlFragment);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col);
		}
	}
	
	public ResourceSet getByXQuery(String xQueryExpression) {
		Collection col = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			return DBManipulationUtilities.getResourceSetByXQuery(col, TARGET_NAMESPACE, xQueryExpression);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col);
		}
	}
	
	public int getLenghtOfCollection() {
		Collection col = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			return col.getResourceCount();

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			ConnectionUtilities.cleanup(col);
		}
	}
	
}

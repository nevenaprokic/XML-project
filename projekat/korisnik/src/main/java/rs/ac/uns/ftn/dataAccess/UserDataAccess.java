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
import rs.ac.uns.ftn.model.User;

@Component
public class UserDataAccess {
	
	private JAXBContext context;
	private final String collectionId = "db/project/korisnici";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/user";
	private static final String CONTEXT = "rs.ac.uns.ftn.model";
	
	public UserDataAccess() {
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

	
	private OutputStream marshallUser(User user) throws JAXBException {
		OutputStream os = new ByteArrayOutputStream();

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.marshal(user, os);
		
		return os;
	}
	
	private User unmarshalUserFromNode(Node contentAsDOM) throws JAXBException{		
		System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		User bookstore = (User) unmarshaller.unmarshal(contentAsDOM);
		
		return bookstore;
	}
	
	public void create(User user) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, user.getEmail());
			
			// do something to delo;
			
			OutputStream os = marshallUser(user);
			
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public User retrive(String email) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			res = ConnectionUtilities.getResource(col, email);
			
			if(res == null) {
	            System.out.println("[WARNING] User '" + email + "' can not be found!");
	            return null;
	        } else {
	        	
	        	return unmarshalUserFromNode(res.getContentAsDOM());
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

	
}

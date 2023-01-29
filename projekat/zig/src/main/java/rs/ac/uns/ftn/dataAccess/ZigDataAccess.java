package rs.ac.uns.ftn.dataAccess;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.ftn.dataAccess.utils.ConnectionUtilities;
import rs.ac.uns.ftn.dataAccess.utils.DBManipulationUtilities;
import rs.ac.uns.ftn.dataAccess.utils.QueryUtils;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.JaxbMapper;
import rs.ac.uns.ftn.services.MetadataService;

@Component
public class ZigDataAccess {
	
	@Autowired
	private MetadataService metadataService;
	
	
	private final String collectionId = "db/project/zigovi";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/z1";
	
	public ZigDataAccess() {
		setupDB();
	}

	private void setupDB() {
		try {
			ConnectionUtilities.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFile(String resourceId, String filePath) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, resourceId);
			
			ZahtevZaPriznanjeZiga delo = JaxbMapper.unmarshalZahtevFromFile(filePath);
			
			// do something to delo;
			
			OutputStream os = JaxbMapper.marshallZahtev(delo);
			metadataService.extractMetadata("/zig", os, resourceId);
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public void saveFile(String resourceId, ZahtevZaPriznanjeZiga delo) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, resourceId);

			OutputStream os = JaxbMapper.marshallZahtev(delo);
			metadataService.extractMetadata("/zig", os, resourceId);
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public ZahtevZaPriznanjeZiga getZahtevById(String documentId) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			res = ConnectionUtilities.getResource(col, documentId);
			
			if(res == null) {
	            System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
	            return null;
	        } else {
	        	
	        	return JaxbMapper.unmarshalZahtevFromNode(res.getContentAsDOM());
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
	
	public Node getXMLZahtevById(String documentId) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			res = ConnectionUtilities.getResource(col, documentId);
			
			return res.getContentAsDOM();
			
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
			return 0;
		} finally {
			ConnectionUtilities.cleanup(col);
		}
	}
	
	public ResourceSet getAllApproved() {
		return this.getByXQuery(QueryUtils.FIND_ALL_APPROVED);
	}
	
}

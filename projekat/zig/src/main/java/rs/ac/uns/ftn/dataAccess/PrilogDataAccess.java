package rs.ac.uns.ftn.dataAccess;

import java.io.OutputStream;

import org.springframework.stereotype.Component;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.ftn.dataAccess.utils.ConnectionUtilities;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;
import rs.ac.uns.ftn.mapper.JaxbMapper;

@Component
public class PrilogDataAccess {

	private final String collectionId = "db/project/prilozi";

	public void savePrilog(String resourceId, PrilogImage prilog) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.initCollection(collectionId);
			res = ConnectionUtilities.initResource(col, resourceId);

			OutputStream os = JaxbMapper.marshallPrilogImage(prilog);
			ConnectionUtilities.linkResourceToCollection(col, res, os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}		
	}
	
	public PrilogImage getById(String documentId) {
		Collection col = null;
		XMLResource res = null;
		try {
			col = ConnectionUtilities.getCollection(collectionId);
			res = ConnectionUtilities.getResource(col, documentId);
			
			if(res == null) {
	            System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
	            return null;
	        } else {
	        	
	        	return JaxbMapper.unmarshalPrilogImageFromNode(res.getContentAsDOM());
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtilities.cleanup(col , res);
		}
	}
}

package rs.ac.uns.ftn.dataAccess.utils;

import java.io.OutputStream;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

import rs.ac.uns.ftn.dataAccess.utils.AuthenticationUtilities.ConnectionProperties;

public class ConnectionUtilities {
	
	private static ConnectionProperties conn;
	
    
	public static void setup() throws Exception {
		conn = AuthenticationUtilities.loadProperties();
		
		 // initialize database driver
    	System.out.println("[INFO] Loading driver class: " + conn.driver);
    	Class<?> cl = Class.forName(conn.driver);

        // encapsulation of the database driver functionality
    	Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
	}
	
	public static void cleanup(Collection col, XMLResource res) {
		ConnectionUtilities.cleanup(col);
		ConnectionUtilities.cleanup(res);
	}
	
	public static void cleanup(Collection col) {
        if(col != null) {
            try { 
            	col.close(); 
            } catch (XMLDBException xe) {
            	xe.printStackTrace();
            }
        }
	}
	public static void cleanup(XMLResource res) {
		if(res != null) {
            try { 
            	((EXistResource)res).freeResources(); 
            } catch (XMLDBException xe) {
            	xe.printStackTrace();
            }
        }
	}
	
	public static Collection initCollection(String collectionId) throws XMLDBException {
		System.out.println("[INFO] Retrieving the collection: " + collectionId);
        return getOrCreateCollection(collectionId);
	}
	public static Collection getCollection(String collectionId) throws XMLDBException {
		// get the collection
    	System.out.println("[INFO] Retrieving the collection: " + collectionId);
    	Collection col = DatabaseManager.getCollection(conn.uri + collectionId);
        col.setProperty(OutputKeys.INDENT, "yes");
        return col;
	}
	

	public static XMLResource initResource(Collection col, String documentId) throws XMLDBException {
        System.out.println("[INFO] Inserting the document: " + documentId);
        return (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
	}
	
	public static XMLResource getResource(Collection col, String documentId) throws XMLDBException {
        System.out.println("[INFO] Retrieving the document: " + documentId);
        return (XMLResource)col.getResource(documentId);
	}
	
	private static XQueryService getXQueryService(Collection col, String targetNamespace) throws XMLDBException {
		// get an instance of xquery service
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
        
        // make the service aware of namespaces
        xqueryService.setNamespace("b", targetNamespace);
        xqueryService.setNamespace("zaj", "http://www.ftn.uns.ac.rs/zaj");
        return xqueryService;
	}
	public static ResourceSet getResourceSetByXQuery(Collection col, String targetNamespace, String xQueryExpression) throws XMLDBException {
		XQueryService xqueryService = ConnectionUtilities.getXQueryService(col, targetNamespace);
		// compile and execute the expression
        CompiledExpression compiledXquery = xqueryService.compile(xQueryExpression);
        ResourceSet result = xqueryService.execute(compiledXquery);
		return result;
	}

	public static void linkResourceToCollection(Collection col, XMLResource res, OutputStream os) throws XMLDBException {
		// link the stream to the XML resource
        res.setContent(os);
        System.out.println("[INFO] Storing the document: " + res.getId());
        
        col.storeResource(res);
        System.out.println("[INFO] Done.");
	}
    
    private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        
        // create the collection if it does not exist
        if(col == null) {
        
         	if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            
        	String pathSegments[] = collectionUri.split("/");
            
        	if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
            
                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }
                
                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);
                
                if (startCol == null) {
                	
                	// child collection does not exist
                    
                	String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);
                    
                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
                    
                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    
                    col.close();
                    parentCol.close();
                    
                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

}

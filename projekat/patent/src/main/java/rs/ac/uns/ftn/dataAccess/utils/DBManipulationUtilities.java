package rs.ac.uns.ftn.dataAccess.utils;

import static rs.ac.uns.ftn.dataAccess.utils.XUpdateTemplate.INSERT_AFTER;
import static rs.ac.uns.ftn.dataAccess.utils.XUpdateTemplate.INSERT_BEFORE;
import static rs.ac.uns.ftn.dataAccess.utils.XUpdateTemplate.APPEND;
import static rs.ac.uns.ftn.dataAccess.utils.XUpdateTemplate.UPDATE;
import static rs.ac.uns.ftn.dataAccess.utils.XUpdateTemplate.REMOVE;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

public class DBManipulationUtilities {
	
	private final static String ZAJ_NAMESPACE = "http://www.ftn.uns.ac.rs/zaj";


	private static XQueryService getXQueryService(Collection col, String targetNamespace) throws XMLDBException {
		// get an instance of xquery service
        XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
        xqueryService.setProperty("indent", "yes");
        
        // make the service aware of namespaces
        xqueryService.setNamespace("", targetNamespace);
        xqueryService.setNamespace("zaj", ZAJ_NAMESPACE);
        return xqueryService;
	}

	public static ResourceSet getResourceSetByXQuery(Collection col, String targetNamespace, String xQueryExpression) throws XMLDBException {
		XQueryService xqueryService = getXQueryService(col, targetNamespace);
		// compile and execute the expression
        CompiledExpression compiledXquery = xqueryService.compile(xQueryExpression);
        ResourceSet result = xqueryService.execute(compiledXquery);
		return result;
	}
	
	
	private static XPathQueryService getXPathService(Collection col, String targetNamespace) throws XMLDBException {
		// get an instance of xpath query service
        XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xpathService.setProperty("indent", "yes");
        
        // make the service aware of namespaces, using the default one
        xpathService.setNamespace("", targetNamespace);
        xpathService.setNamespace("zaj", ZAJ_NAMESPACE);
        return xpathService;
	}
	
	public static ResourceSet getResourceSetByXPath(Collection col, String targetNamespace, String xPathExpression) throws XMLDBException {
			System.out.println("[INFO] Invoking XPath query service for: " + xPathExpression);
			XPathQueryService xpathService = getXPathService(col, targetNamespace);
	        ResourceSet result = xpathService.query(xPathExpression);
	        return result;		
	}
	

	private static XUpdateQueryService getXUpdateService(Collection col) throws XMLDBException {
		// get an instance of xupdate query service
        System.out.println("[INFO] Fetching XUpdate service for the collection.");
        XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
        xupdateService.setProperty("indent", "yes");
        return xupdateService;
	}
	
	public static void insertAfter(Collection col, String documentId, String targetNamespace, String contextXPath, String xmlFragment) throws XMLDBException {
		XUpdateQueryService xupdateService = getXUpdateService(col);
		System.out.println("[INFO] Inserting fragments after " + contextXPath + " node.");
		long mods = xupdateService.updateResource(documentId, String.format(INSERT_AFTER, targetNamespace, contextXPath, xmlFragment));
		System.out.println("[INFO] " + mods + " modifications processed.");
	}
	
	public static void insertBefore(Collection col, String documentId, String targetNamespace, String contextXPath, String xmlFragment) throws XMLDBException {
		XUpdateQueryService xupdateService = getXUpdateService(col);
		System.out.println("[INFO] Inserting fragments after " + contextXPath + " node.");
		long mods = xupdateService.updateResource(documentId, String.format(INSERT_BEFORE, targetNamespace, contextXPath, xmlFragment));
		System.out.println("[INFO] " + mods + " modifications processed.");
	}

	
	public static void append(Collection col, String documentId, String targetNamespace, String contextXPath, String xmlFragment) throws XMLDBException{
		XUpdateQueryService xupdateService = getXUpdateService(col);
		System.out.println("[INFO] Appending fragments as last child of " + contextXPath + " node.");
        long mods = xupdateService.updateResource(documentId, String.format(APPEND, targetNamespace, contextXPath, xmlFragment));
        System.out.println("[INFO] " + mods + " modifications processed.");
	}
	
	public static void update(Collection col, String documentId, String targetNamespace, String contextXPath, String patch) throws XMLDBException{
		XUpdateQueryService xupdateService = getXUpdateService(col);
		System.out.println("[INFO] Updating " + contextXPath + " node.");
	    long mods = xupdateService.updateResource(documentId, String.format(UPDATE, targetNamespace, contextXPath, patch));
	    System.out.println("[INFO] " + mods + " modifications processed.");
	}
	
	
	public static void remove(Collection col, String documentId, String targetNamespace, String contextXPath) throws XMLDBException{
		XUpdateQueryService xupdateService = getXUpdateService(col);
		System.out.println("[INFO] Removing " + contextXPath + " node.");
	    long mods = xupdateService.updateResource(documentId, String.format(REMOVE, targetNamespace, contextXPath));
	    System.out.println("[INFO] " + mods + " modifications processed.");
	}
}

package rs.ac.uns.ftn.dataAccess;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;

public class Test {

	public static void main(String[] args) {
		AutorskoDeloDataAccess da = new AutorskoDeloDataAccess();
		
//		testSaveFile(da);
//		testGetZahtevById(da); 
//		testXPath(da);
//		testUpdate(da);

//		testXQuery(da);
	}
	
	private static void testUpdate(AutorskoDeloDataAccess da) {
		da.updateDocument("A1.xml", "//Autori/Autor[@primarni = 'true']/zaj:Ime", "<zaj:Ime>PERAA</zaj:Ime>");
	}
	private static void testGetZahtevById(AutorskoDeloDataAccess da) {
		ZahtevZaAutorskoDelo delo = da.getZahtevById("A1.xml");
		System.out.println(delo);
	}
	
	private static void testSaveFile(AutorskoDeloDataAccess da) {
		da.saveFile("A1.xml", "data/A1.xml");
	}
	private static void testXPath(AutorskoDeloDataAccess da) {
		String xPath ="//Autori/Autor[@primarni = 'true']";

		ResourceSet result = da.getByXPath(xPath);
		ResourceIterator i;
		try {
			i = result.getIterator();
			Resource res = null;
		        
	        while(i.hasMoreResources()) {
	        	try {
	                res = i.nextResource();
	                System.out.println(res.getContent());
	            } finally {		                
	                try { 
	                	((EXistResource)res).freeResources(); 
	                } catch (XMLDBException xe) {
	                	xe.printStackTrace();
	                }
	            }
	        }
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}
	
//	private static void testXQuery(AutorskaPravaDataAccess da) {
//		// XPATH:   "/child::Pravno_lice"
//
//		String xQuery = "for $i in //Autorsko_delo "
////						+ "let $name := $i/Identifikator/Naslov "
//						+ "return <Autorsko_delo><Identifikator><Naslov>{$i}</Naslov><Alternativni_naslov>{$i}</Alternativni_naslov></Identifikator></Autorsko_delo>";
//		ResourceSet result = da.getByXQuery(xQuery);
//		System.out.println(result);
//		ResourceIterator i;
//		try {
//			i = result.getIterator();
//			Resource res = null;
//		        
//	        while(i.hasMoreResources()) {
//	    		System.out.println(result);
//
//	        	try {
//	                res = i.nextResource();
//	                System.out.println(res.getContent());
//		                
//	            } finally {
//		                
//	            	// don't forget to cleanup resources
//	                try { 
//	                	((EXistResource)res).freeResources(); 
//	                } catch (XMLDBException xe) {
//	                	xe.printStackTrace();
//	                }
//	            }
//	        }
//			
//		} catch (XMLDBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}

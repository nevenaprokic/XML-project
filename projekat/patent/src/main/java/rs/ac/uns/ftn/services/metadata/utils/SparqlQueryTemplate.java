package rs.ac.uns.ftn.services.metadata.utils;

import java.util.Map;

public class SparqlQueryTemplate {

	public static String BASIC_TEMPLATE = 
			"SELECT * FROM <http://localhost:8080/fusekiP/Patent/data/project/metadata/patent>"
			+ "		WHERE {"
			+ "		    ?zahtevZaPriznanjePatenta <http://examples/predicate/datum_prijema_prijave> ?datum_prijema_prijave . \r\n"
			+ "			?zahtevZaPriznanjePatenta <http://examples/predicate/pronalazak_naslov> ?pronalazak_naslov . \r\n"
			+ "			?zahtevZaPriznanjePatenta <http://examples/predicate/pronalazac> ?pronalazac . \r\n"
			+ "			?zahtevZaPriznanjePatenta <http://examples/predicate/podnosilac_email> ?podnosilac_email . \r\n"
			+ "			?zahtevZaPriznanjePatenta <http://examples/predicate/ime_podnosioca> ?ime_podnosioca . \r\n"
			+ "			OPTIONAL{  ?zahtevZaPriznanjePatenta <http://examples/predicate/broj_prvobitne_prijave> ?broj_prvobitne_prijave . } \r\n"
			+ "		  	%1$s \r\n"
			+ "			%2$s"
			+ "		}";
	
	private static String FILTER_BY_ID_CLAUSE = " FILTER (?zahtevZaPriznanjePatenta = <http://ftn.uns.ac.rs/p1/%s>) ";
	
	private static String RANIJE_PRIJAVE_OPTIONAL = " OPTIONAL{  ?zahtevZaPriznanjePatenta <http://examples/predicate/ranija_prijava%1$c> ?ranija_prijava%2$c . } \r\n";
	

	public static String filterById(String id, Map<String, String> ranijePrijave) {
		String ranijePrijaveQuery = "";
		for (String key : ranijePrijave.keySet()) {
			char i = key.charAt(key.length() - 1);
			ranijePrijaveQuery += String.format(RANIJE_PRIJAVE_OPTIONAL, i, i);
		}
		String filter = String.format(FILTER_BY_ID_CLAUSE, id);
		return String.format(BASIC_TEMPLATE, ranijePrijaveQuery, filter);
	}
	
	public static String rdfMetadataTemplate = ""
			+ "<rdf:RDF\r\n"
			+ "    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n"
			+ "    xmlns:h=\"http://www.w3.org/1999/xhtml\"\r\n"
			+ "    xmlns:pred=\"http://examples/predicate/\">\r\n"
			+ "  <rdf:Description rdf:about=\"%1$s\">\r\n"
			+ "    %2$s\r\n"
			+ "  </rdf:Description>\r\n"
			+ "</rdf:RDF>";
	
	public static String datumPrijave = "<pred:datum_prijema_prijave rdf:datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">%1$s</pred:datum_prijema_prijave>\r\n";
	public static String pronalazak = "<pred:pronalazak_naslov>%1$s</pred:pronalazak_naslov>\r\n";
	public static String pronalazac = "<pred:pronalazac>%1$s</pred:pronalazac>\r\n";
	public static String podnosilacEmail = "<pred:podnosilac_email rdf:resource=\"%1$s\"/>\r\n";
	public static String imePodnosioca = "<pred:ime_prodnosioca>%1$s</pred:ime_prodnosioca>\r\n";
	public static String brPrvobitnePrijave = "<pred:broj_prvobitne_prijave rdf:resource=\"%1$s\"/>\r\n";
	public static String ranijaPrijava = "<pred:ranija_prijava%2$s>%1$s</pred:ranija_prijava%3$s>\r\n";
	
	public static String formatRdf(Map<String, String> params, Map<String, String> ranijePrijave) {
		String preds = "";
		if(params.get(MetadataKeys.DATUM_PRIJEMA_PRIJAVE)!=null) {
			preds+= String.format(datumPrijave, params.get(MetadataKeys.DATUM_PRIJEMA_PRIJAVE));
		} 
		if(params.get(MetadataKeys.PRONALAZAK_NASLOV)!=null) {
			preds+= String.format(pronalazak, params.get(MetadataKeys.PRONALAZAK_NASLOV));
		}
		if(params.get(MetadataKeys.PRONALAZAC)!=null) {
			preds+= String.format(pronalazac, params.get(MetadataKeys.PRONALAZAC));
		}
		if(params.get(MetadataKeys.PODNOSILAC_EMAIL)!=null) {
			preds+= String.format(podnosilacEmail, params.get(MetadataKeys.PODNOSILAC_EMAIL));
		}
		if(params.get(MetadataKeys.IME_PODNOSIOCA)!=null) {
			preds+= String.format(imePodnosioca, params.get(MetadataKeys.IME_PODNOSIOCA));
		}
		if(params.get(MetadataKeys.BROJ_PRVOBITNE_PRIJAVE)!=null) {
			preds+= String.format(brPrvobitnePrijave, params.get(MetadataKeys.BROJ_PRVOBITNE_PRIJAVE));
		}
		
		for (Map.Entry<String, String> entry : ranijePrijave.entrySet()) {
			String predicate = entry.getKey();
			String object = entry.getValue();
			char i = predicate.charAt(predicate.length() - 1);
			preds += String.format(ranijaPrijava, object, i, i);
		}
		
		return String.format(rdfMetadataTemplate, params.get(MetadataKeys.ZAHTEV_ZA_PRIZNANJE_PATENTA), preds);
	}

}

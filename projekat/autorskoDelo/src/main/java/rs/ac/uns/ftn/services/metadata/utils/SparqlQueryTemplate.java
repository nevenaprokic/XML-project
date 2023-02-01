package rs.ac.uns.ftn.services.metadata.utils;

import java.util.Map;

public class SparqlQueryTemplate {

	public static String rdfMetadataTemplate = ""
			+ "<rdf:RDF\r\n"
			+ "    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n"
			+ "    xmlns:h=\"http://www.w3.org/1999/xhtml\"\r\n"
			+ "    xmlns:pred=\"http://examples/predicate/\">\r\n"
			+ "  <rdf:Description rdf:about=\"%1$s\">\r\n"
			+ "    %2$s\r\n"
			+ "  </rdf:Description>\r\n"
			+ "</rdf:RDF>";
	
	public static String datum_podnosenja = "<pred:datum_podnosenja rdf:datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">%1$s</pred:datum_podnosenja_prijave>\r\n";
	public static String autorsko_delo = "<pred:autorsko_delo>%1$s</pred:autorsko_delo>\r\n";
	public static String primarni_autor = "<pred:primarni_autor>%1$s</pred:primarni_autor>\r\n";
	public static String koautor = "<pred:koautor>%1$s</pred:koautor>\r\n";
	public static String podnosilac = "<pred:podnosilac>%1$s</pred:podnosilac>\r\n";
	public static String ime_prodnosioca = "<pred:ime_prodnosioca>%1$s</pred:ime_prodnosioca>\r\n";
	
	public static String prilog_opis = "<pred:prilog_opis rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_primer = "<pred:prilog_primer rdf:resource=\"%1$s\"/>\r\n";
	
	public static String formatRdf(Map<String, String> params) {
		String preds = "";
		if(params.get(MetadataKeys.DATUM_PODNOSENJA)!=null) {
			preds+= String.format(datum_podnosenja, params.get(MetadataKeys.DATUM_PODNOSENJA));
		} 
		if(params.get(MetadataKeys.AUTORSKO_DELO)!=null) {
			preds+= String.format(autorsko_delo, params.get(MetadataKeys.AUTORSKO_DELO));
		}
		if(params.get(MetadataKeys.PRIMARNI_AUTOR)!=null) {
			preds+= String.format(primarni_autor, params.get(MetadataKeys.PRIMARNI_AUTOR));
		}
		if(params.get(MetadataKeys.KOAUTOR)!=null) {
			preds+= String.format(koautor, params.get(MetadataKeys.KOAUTOR));
		}
		if(params.get(MetadataKeys.PODNOSILAC)!=null) {
			preds+= String.format(podnosilac, params.get(MetadataKeys.PODNOSILAC));
		}
		if(params.get(MetadataKeys.IME_PRODNOSIOCA)!=null) {
			preds+= String.format(ime_prodnosioca, params.get(MetadataKeys.IME_PRODNOSIOCA));
		}

		if(params.get(MetadataKeys.PRILOG_OPIS)!=null) {
			preds+= String.format(prilog_opis, params.get(MetadataKeys.PRILOG_OPIS));
		}
		if(params.get(MetadataKeys.PRILOG_PRIMER)!=null) {
			preds+= String.format(prilog_primer, params.get(MetadataKeys.PRILOG_PRIMER));
		}
		
		return String.format(rdfMetadataTemplate, params.get(MetadataKeys.ZAHTEV_ZA_AUTORSKO_DELO), preds);
	}

}

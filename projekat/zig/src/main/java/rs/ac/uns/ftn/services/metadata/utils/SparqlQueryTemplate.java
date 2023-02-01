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
	
	public static String datum_podnosenja_prijave = "<pred:datum_podnosenja_prijave rdf:datatype=\"http://www.w3.org/2001/XMLSchema#dateTime\">%1$s</pred:datum_podnosenja_prijave>\r\n";
	public static String podnosilac_prijave = "<pred:podnosilac_prijave>%1$s</pred:podnosilac_prijave>\r\n";
	public static String vrsta = "<pred:vrsta>%1$s</pred:vrsta>\r\n";
	public static String ukupna_taksa = "<pred:ukupna_taksa rdf:resource=\"%1$s\"/>\r\n";
	
	public static String prilog_dokaz_o_uplati_takse = "<pred:prilog_dokaz_o_uplati_takse rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_opsti_akt_o_kolektivnom_zigu_garancije = "<pred:prilog_opsti_akt_o_kolektivnom_zigu_garancije rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_punomocje = "<pred:prilog_punomocje rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_punomocje_ranije_prilozeno = "<pred:prilog_punomocje_ranije_prilozeno rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_punomocje_naknadno_dostavljeno = "<pred:prilog_punomocje_naknadno_dostavljeno rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_spisak_robe_i_usluga = "<pred:prilog_spisak_robe_i_usluga rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_primerak_znaka = "<pred:prilog_primerak_znaka rdf:resource=\"%1$s\"/>\r\n";
	public static String prilog_dokaz_o_pravu_prvenstva = "<pred:prilog_dokaz_o_pravu_prvenstva rdf:resource=\"%1$s\"/>\r\n";

	
	public static String formatRdf(Map<String, String> params) {
		String preds = "";
		if(params.get(MetadataKeys.DATUM_PODNOSENJA_PRIJAVE)!=null) {
			preds+= String.format(datum_podnosenja_prijave, params.get(MetadataKeys.DATUM_PODNOSENJA_PRIJAVE));
		} 
		if(params.get(MetadataKeys.PODNOSILAC_PRIJAVE)!=null) {
			preds+= String.format(podnosilac_prijave, params.get(MetadataKeys.PODNOSILAC_PRIJAVE));
		}
		if(params.get(MetadataKeys.VRSTA)!=null) {
			preds+= String.format(vrsta, params.get(MetadataKeys.VRSTA));
		}
		if(params.get(MetadataKeys.UKUPNA_TAKSA)!=null) {
			preds+= String.format(ukupna_taksa, params.get(MetadataKeys.UKUPNA_TAKSA));
		}

		if(params.get(MetadataKeys.PRILOG_DOKAZ_O_UPLATI_TAKSE)!=null) {
			preds+= String.format(prilog_dokaz_o_uplati_takse, params.get(MetadataKeys.PRILOG_DOKAZ_O_UPLATI_TAKSE));
		}
		if(params.get(MetadataKeys.PRILOG_OPSTI_AKT_O_KOLEKTIVNOM_ZIGU_GARANCIJE)!=null) {
			preds+= String.format(prilog_opsti_akt_o_kolektivnom_zigu_garancije, params.get(MetadataKeys.PRILOG_OPSTI_AKT_O_KOLEKTIVNOM_ZIGU_GARANCIJE));
		}
		if(params.get(MetadataKeys.PRILOG_PUNOMOCJE)!=null) {
			preds+= String.format(prilog_punomocje, params.get(MetadataKeys.PRILOG_PUNOMOCJE));
		}
		if(params.get(MetadataKeys.PRILOG_PUNOMOCJE_RANIJE_PRILOZENO)!=null) {
			preds+= String.format(prilog_punomocje_ranije_prilozeno, params.get(MetadataKeys.PRILOG_PUNOMOCJE_RANIJE_PRILOZENO));
		}
		if(params.get(MetadataKeys.PRILOG_PUNOMOCJE_NAKNADNO_DOSTAVLJENO)!=null) {
			preds+= String.format(prilog_punomocje_naknadno_dostavljeno, params.get(MetadataKeys.PRILOG_PUNOMOCJE_NAKNADNO_DOSTAVLJENO));
		}
		if(params.get(MetadataKeys.PRILOG_SPISAK_ROBE_I_USLUGA)!=null) {
			preds+= String.format(prilog_spisak_robe_i_usluga, params.get(MetadataKeys.PRILOG_SPISAK_ROBE_I_USLUGA));
		}
		if(params.get(MetadataKeys.PRILOG_PRIMERAK_ZNAKA)!=null) {
			preds+= String.format(prilog_primerak_znaka, params.get(MetadataKeys.PRILOG_PRIMERAK_ZNAKA));
		}
		if(params.get(MetadataKeys.PRILOG_DOKAZ_O_PRAVU_PRVENSTVA)!=null) {
			preds+= String.format(prilog_dokaz_o_pravu_prvenstva, params.get(MetadataKeys.PRILOG_DOKAZ_O_PRAVU_PRVENSTVA));
		}

		return String.format(rdfMetadataTemplate, params.get(MetadataKeys.ZAHTEV_ZA_PRIZNANJE_ZIGA), preds);
	}

}

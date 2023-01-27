package rs.ac.uns.ftn.services.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import rs.ac.uns.ftn.services.MetadataService;
import rs.ac.uns.ftn.services.metadata.utils.AuthenticationUtilities;
import rs.ac.uns.ftn.services.metadata.utils.AuthenticationUtilities.ConnectionProperties;
import rs.ac.uns.ftn.services.metadata.utils.FileUtil;
import rs.ac.uns.ftn.services.metadata.utils.MetadataExtractor;
import rs.ac.uns.ftn.services.metadata.utils.SparqlUtil;

@Service
public class MetadataServiceImpl implements MetadataService{

	private static final String SPARQL_NAMED_GRAPH_URI = "/project/metadata";
	private static final String GRAPH = "/autorskoDelo";
	private static String GRAPH_URI = "";
	private static ConnectionProperties conn;

	private void setupConnection() throws IOException {
		conn = AuthenticationUtilities.loadProperties();
		GRAPH_URI = conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI + GRAPH;
	}
	@Override
	public void extractMetadata(OutputStream xml, String documentId)
			throws IOException, SAXException, TransformerException {

		setupConnection();

		ByteArrayOutputStream extractedMetadata = extractMetadata(xml);
		Model model = createRDFModel(extractedMetadata, documentId);
		
		// ispis u fajl RDF/XML format
		model.write(new FileOutputStream(new File("src/main/resources/rdf_data/" + documentId + ".rdf")), SparqlUtil.RDF_XML);
		
		// ispis na konzolu RDF/XML format
		System.out.println("[INFO] Extracted metadata as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);
		
		// UPIS U BAZU u Ntriples formatu
		insertTriplesIntoDB(model);

		// Read the triples from the named graph, 
		// Provera uspesnosti upisa
		getTriplesFromDB();


		System.out.println("[INFO] End.");
	}

	private void insertTriplesIntoDB(Model model) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);

		// Writing the named graph
		System.out.println("[INFO] Populating named graph (uri: \"" + GRAPH_URI + "\") with extracted metadata.");
		String sparqlUpdateRequest = SparqlUtil.insertData(GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdateRequest);
		
		execUpdate(sparqlUpdateRequest);
	}
	
	private void getTriplesFromDB() {
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + GRAPH_URI + "\" named graph.");

		System.out.println("[INFO] Selecting the triples from the named graph \"" + GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(GRAPH_URI, " ?zahtevZaAutorskoDelo <http://examples/predicate/datum_podnosenja> ?datum_podnosenja ");

		getFromDB(sparqlQuery);
	}

	private void getFromDB(String sparqlQuery) {
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		// Query the collection, dump output response as XML
		ResultSet results = query.execSelect();
		ResultSetFormatter.out(System.out, results);
		query.close();
	}

	private void execUpdate(String sqarqlUpdate) {
		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sqarqlUpdate);
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
	}


	private Model createRDFModel(ByteArrayOutputStream extractedMetadata, String documentId) throws IOException {
		Model model = ModelFactory.createDefaultModel();
		model.read(new StringReader(metadataToString(extractedMetadata)), documentId);
		return model;
	}

	private ByteArrayOutputStream extractMetadata(OutputStream xml) throws SAXException, IOException, TransformerException {
		MetadataExtractor metadataExtractor = new MetadataExtractor();

		ByteArrayInputStream inStream = new ByteArrayInputStream(((ByteArrayOutputStream) xml).toByteArray());
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		// IZDVAJANJE RDF iz xml-a
		metadataExtractor.extractMetadata(inStream, outStream);

		return outStream;
	}

	private String metadataToString(ByteArrayOutputStream outStream) throws IOException {
		ByteArrayInputStream rdfStream = new ByteArrayInputStream(outStream.toByteArray());

		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(new InputStreamReader(rdfStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		}
		return textBuilder.toString();
	}

	@Override
	public InputStreamResource getAsJson(String documentId) throws IOException {
		setupConnection();
		
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, filterByIdQuery(documentId));
		ResultSet resultSet = query.execSelect();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		ResultSetFormatter.outputAsJSON(outputStream, resultSet);
		query.close();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(outputStream.toByteArray());
		return new InputStreamResource(bis);
	}

	@Override
	public InputStreamResource getAsRdf(String documentId) throws IOException {
		setupConnection();
		
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, filterByIdQuery(documentId));
		ResultSet resultSet = query.execSelect();
		
		String varName;
		RDFNode varValue;
		
		Map<String, String> params = new HashMap<String, String>();
		
		while(resultSet.hasNext()) {
			QuerySolution querySolution = resultSet.next() ;
			Iterator<String> variableBindings = querySolution.varNames();

		    while (variableBindings.hasNext()) {
		    	varName = variableBindings.next();
		    	varValue = querySolution.get(varName);
		    	params.put(varName, varValue.toString());
		    	System.out.println(varName + ": " + varValue);
		    }
		    System.out.println();
		}
		
		String rdf = formatRDFXMLTemplate(params);
		byte[] byteArrray = rdf.getBytes();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArrray);
		return new InputStreamResource(bis);
	}
	

	private String filterByIdQuery(String documentId) throws IOException {
		String XML_RDF_template = FileUtil.readFile("src/main/resources/rdf_data/sparql_filter_by_id_template.rq",StandardCharsets.UTF_8);
		return String.format(XML_RDF_template, documentId);
	}	
	
	
	private String formatRDFXMLTemplate(Map<String, String> params) throws IOException {
		String XML_RDF_template = FileUtil.readFile("src/main/resources/rdf_data/rdf_metadata_template.rq",StandardCharsets.UTF_8);
		
		return String.format(XML_RDF_template, 
				params.get("zahtevZaAutorskoDelo"), 
				params.get("datum_podnosenja"), 
				params.get("autorsko_delo"), 
				params.get("primarni_autor"), 
				params.get("koautor"), 
				params.get("podnosilac"), 
				params.get("ime_prodnosioca"));
	}
}

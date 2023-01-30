package rs.ac.uns.ftn.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.springframework.core.io.InputStreamResource;
import org.xml.sax.SAXException;

public interface MetadataService {
	public void extractMetadata(String graph, OutputStream os, String documentId) throws IOException, SAXException, TransformerException;

	public InputStreamResource getAsRdf(String documentId) throws IOException;

	public InputStreamResource getAsJson(String documentId) throws IOException;

	public List<String> searchByMetadata(String request) throws IOException;
}

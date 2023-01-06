package rs.ac.uns.ftn.services;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface MetadataService {
	public void extractMetadata(String graph, OutputStream os, String documentId) throws IOException, SAXException, TransformerException;
}

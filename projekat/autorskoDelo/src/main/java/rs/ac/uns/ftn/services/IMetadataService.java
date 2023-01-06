package rs.ac.uns.ftn.services;

import java.io.OutputStream;

public interface IMetadataService {
	public void extractMetadata(String graph, OutputStream os, String documentId);
}

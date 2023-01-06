package rs.ac.uns.ftn.services.impl;


import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.repository.AutorskoDeloRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

@Service
public class AutorskoDeloIServicempl implements AutorskoDeloService{
public static final String INPUT_FILE = "data/A1.xml";
	
	public static final String XSL_FILE = "data/xslt/A1.xsl";
	
	public static final String HTML_FILE = "data/xslt/A1.html";
	
	public static final String OUTPUT_FILE = "data/xslt/A1.pdf";

	@Autowired
	private AutorskoDeloRepository autorskoDeloRepository;
	
	@Override
	public void saveNewFile(ZahtevZaAutorskoDelo zahtev) {
		String documentId = generateDocumentId();
		System.out.println(documentId);
		autorskoDeloRepository.saveAutorskoDelo(zahtev, documentId);
	}

	@Override
	public ZahtevZaAutorskoDelo getZahtevZaAutorskoDeloById(String id) {
		return autorskoDeloRepository.getZahtevZaAutorskoDelobyId(id);
	}

	@Override
	public String generateDocumentId() {
		int curretnNumber = autorskoDeloRepository.getLenghtOfCollection();
		return "A" + String.valueOf(curretnNumber + 1); 
	}
	
	@Override
	public void getPDF() throws IOException, DocumentException {
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(OUTPUT_FILE);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		pdfTransformer.generateHTML(INPUT_FILE, XSL_FILE);
		pdfTransformer.generatePDF(OUTPUT_FILE);
		
		System.out.println("[INFO] File \"" + OUTPUT_FILE + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}

}

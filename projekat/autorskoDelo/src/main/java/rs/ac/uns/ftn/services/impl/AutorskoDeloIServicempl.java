package rs.ac.uns.ftn.services.impl;


import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.repository.AutorskoDeloRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

@Service
public class AutorskoDeloIServicempl implements AutorskoDeloService{
	
	public static final String INPUT_FILE = "data/A1.xml";


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
	public void getPDF(String documentId) throws IOException, DocumentException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//ovde da ne bude uvek A1 vec A pa indeks koji se posalje
		String outputFilePDF = "src/main/resources/xslt/A1" + timeStamp + ".pdf";
		String outputFileXHTML = "src/main/resources/xslt/A1" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		pdfTransformer.generateHTML(INPUT_FILE, outputFileXHTML);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}

}

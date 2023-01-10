package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.mapper.PatentMapper;
import rs.ac.uns.ftn.repository.PatentRepository;
import rs.ac.uns.ftn.services.PatentService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class PatentServiceImpl implements PatentService {
	
	public static final String PATH = "src/main/resources/xslt/";

	@Autowired
	private PatentRepository patentRepository;
	
	public void saveNewFile(ZahtevZaPriznanjePatenta zahtevDTO) {
		String documentId = generateDocumentId();
		System.out.println(documentId);
		ZahtevZaPriznanjePatenta zahtev = PatentMapper.mapFromDTO(zahtevDTO, documentId);
		patentRepository.saveZahtevZaPriznanjePatenta(zahtev, documentId);
	}

	@Override
	public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String id) {
		return patentRepository.getZahtevZaPriznanjePatentaById(id);
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = patentRepository.getLenghtOfCollection();
		return "P" + String.valueOf(curretnNumber + 1); 
	}
	
	@Override
	public void getPDF(String documentId) throws IOException, DocumentException {
		//ucitavanje xml-a iz baze
		Node zaPatent = patentRepository.getXMLZahtevZaPatentbyId(documentId);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//ovde da ne bude uvek A1 vec A pa indeks koji se posalje
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		pdfTransformer.generateHTML(zaPatent, outputFileXHTML);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}
}

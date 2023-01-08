package rs.ac.uns.ftn.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.mapper.AutorskoDeloMapper;
import rs.ac.uns.ftn.repository.AutorskoDeloRepository;
import rs.ac.uns.ftn.services.AutorskoDeloService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

import java.io.File;
import java.io.IOException;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

@Service
public class AutorskoDeloIServicempl implements AutorskoDeloService{
	public static final String PATH = "src/main/resources/xslt/";

	@Autowired
	private AutorskoDeloRepository autorskoDeloRepository;
	
	@Override
	public void saveNewFile(ZahtevZaAutorskoDelo zahtevDTO) {
		String documentId = generateDocumentId();
		System.out.println(documentId);
		ZahtevZaAutorskoDelo zahtev = AutorskoDeloMapper.mapFromDTO(zahtevDTO, documentId);
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
		//ucitavanje xml-a iz baze
		Node zaAutorskoDelo = autorskoDeloRepository.getXMLZahtevZaAutorskoDelobyId(documentId);
		
		//kreiranje imena za pdf i html
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFilePDF = PATH + documentId + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
		//generisanje pdf-a i html-a
		pdfTransformer.generateHTML(zaAutorskoDelo, outputFileXHTML);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}

}

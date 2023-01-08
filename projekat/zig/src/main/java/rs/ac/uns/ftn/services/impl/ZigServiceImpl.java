package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.mapper.ZigMapper;
import rs.ac.uns.ftn.repository.ZigRepository;
import rs.ac.uns.ftn.services.ZigService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class ZigServiceImpl implements ZigService {
	
	public static final String PATH = "src/main/resources/xslt/";
	
	@Autowired
	ZigRepository ZigRepository;

	@Override
	public void saveNewFile(ZahtevZaPriznanjeZiga zahtevDTO) {
		String documentId = generateDocumentId();
		System.out.println(documentId);
		ZahtevZaPriznanjeZiga zahtev = ZigMapper.mapFromDTO(zahtevDTO, documentId);
		ZigRepository.saveZahtevZaPriznanjeZiga(zahtev, documentId);
		
	}

	@Override
	public ZahtevZaPriznanjeZiga getZahtevZaPriznanjeZiga(String id) {
		return ZigRepository.getZahtevZaPriznanjeZigaById(id);
	}
	
	@Override
	public String generateDocumentId() {
		int curretnNumber = ZigRepository.getLenghtOfCollection();
		return "Z" + String.valueOf(curretnNumber + 1); 
	}
	
	@Override
	public void getPDF(String documentId) throws IOException, DocumentException {
		
		//ucitavanje xml-a iz baze
		Node zaZig = ZigRepository.getXMLZahtevZaZigbyId(documentId);
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//ovde da ne bude uvek A1 vec A pa indeks koji se posalje
		String outputFilePDF = PATH + documentId  + "-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + documentId + "-" + timeStamp + ".html";
    	
    	// Creates parent directory if necessary
    	File pdfFile = new File(outputFilePDF);
    	
		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}
    	
		PDFTransformer pdfTransformer = new PDFTransformer();
		
//		String xml_fileString = "src/main/resources/data/zahtev_za_prijavu_ziga.xml";
		
		pdfTransformer.generateHTML(zaZig, outputFileXHTML);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);
		
		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}

}

package rs.ac.uns.ftn.services.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;

import com.ibm.icu.text.SimpleDateFormat;
import com.itextpdf.text.DocumentException;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;
import rs.ac.uns.ftn.jaxb.p1.StatusZahteva;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.lists.ListaZahtevaPatent;
import rs.ac.uns.ftn.services.IzvestajService;
import rs.ac.uns.ftn.services.PatentService;
import rs.ac.uns.ftn.transformations.PDFTransformer;

@Service
public class IzvestajServiceImpl implements IzvestajService {
	public static final String PATH = "src/main/resources/xslt/";
	public static final String XSL_FILE = "src/main/resources/xslt/Izvestaj.xsl";

	@Autowired
	private PatentService patentService;

	@Override
	public Izvestaj getIzvestaj(Izvestaj izvestaj) throws XMLDBException, JAXBException {
		Integer brojPodnetihZahteva = 0;
		Integer brojOdbijenihZahteva = 0;
		Integer brojPrihvacenihZahteva = 0;

		ListaZahtevaPatent zahtevi = patentService.findAll();
		for (ZahtevZaPriznanjePatenta zahtev : zahtevi.getListaZahtevaPatent()) {
			if (zahtev.getDatumPrijemaPrijave().toGregorianCalendar()
					.compareTo(izvestaj.getPocetniDatumIzvestaja().toGregorianCalendar()) >= 0
					&& izvestaj.getKrajnjiDatumIzvestaja().toGregorianCalendar()
							.compareTo(zahtev.getDatumPrijemaPrijave().toGregorianCalendar()) >= 0) {
				brojPodnetihZahteva += 1;
				
				if (zahtev.getStatus() == StatusZahteva.ODBIJEN) {
					brojOdbijenihZahteva += 1;
				}
				if (zahtev.getStatus() == StatusZahteva.ODOBREN) {
					brojPrihvacenihZahteva += 1;
				}
			}
		}

		izvestaj.setBrojOdbijenihZahteva(BigInteger.valueOf(brojOdbijenihZahteva.intValue()));
		izvestaj.setBrojPodnetihZahteva(BigInteger.valueOf(brojPodnetihZahteva));
		izvestaj.setBrojPrihvacenihZahteva(BigInteger.valueOf(brojPrihvacenihZahteva));
		return izvestaj;
	}

	@Override
	public void getPDF(Izvestaj izvestaj)
			throws IOException, DocumentException, ParserConfigurationException, JAXBException {
		Document document = marshalIzvestaj(izvestaj);

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String outputFilePDF = PATH + "iz-" + timeStamp + ".pdf";
		String outputFileXHTML = PATH + "iz-" + timeStamp + ".html";

		// Creates parent directory if necessary
		File pdfFile = new File(outputFilePDF);

		if (!pdfFile.getParentFile().exists()) {
			System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
			pdfFile.getParentFile().mkdir();
		}

		PDFTransformer pdfTransformer = new PDFTransformer();
		pdfTransformer.generateHTML(document, outputFileXHTML, XSL_FILE);
		pdfTransformer.generatePDF(outputFilePDF, outputFileXHTML);

		System.out.println("[INFO] File \"" + outputFilePDF + "\" generated successfully.");
		System.out.println("[INFO] End.");
	}

	private Document marshalIzvestaj(Izvestaj izvestaj) throws JAXBException, ParserConfigurationException {
		JAXBContext jc = JAXBContext.newInstance(Izvestaj.class);
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(izvestaj, document);
		return document;
	}

}

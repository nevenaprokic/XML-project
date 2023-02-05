package rs.ac.uns.ftn.transformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Node;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import java.nio.file.Paths;
import com.itextpdf.text.DocumentException;


public class PDFTransformer {

	private static DocumentBuilderFactory documentFactory;

	private static TransformerFactory transformerFactory;

    public PDFTransformer(){

		/* Inicijalizacija DOM fabrike */
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);

		/* Inicijalizacija Transformer fabrike */
		transformerFactory = TransformerFactory.newInstance();

	}
    
    public void generatePDF(String filePath, String xtmlFile) throws IOException, DocumentException {
        PdfDocument pdfDocument = new PdfDocument(new com.itextpdf.kernel.pdf.PdfWriter(filePath));
        pdfDocument.setDefaultPageSize(new PageSize(780, 1150));
        HtmlConverter.convertToPdf(Files.newInputStream(Paths.get(xtmlFile)), pdfDocument);
    }

	public void generateSource(Node xmlPath, String inputFile, String xslFile) throws FileNotFoundException {
		try {
			// Initialize Transformer instance
			StreamSource transformSource = new StreamSource(new File(xslFile));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Generate XHTML
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

			// Transform DOM to HTML
			DOMSource source = new DOMSource(xmlPath);
			StreamResult result = new StreamResult(new FileOutputStream(inputFile));
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}

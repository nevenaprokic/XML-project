package rs.ac.uns.ftn.transformations;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFTransformer {
	
private static DocumentBuilderFactory documentFactory;
	
	private static TransformerFactory transformerFactory;
	private FopFactory fopFactory;

    public PDFTransformer(){

		/* Inicijalizacija DOM fabrike */
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);

		/* Inicijalizacija Transformer fabrike */
		transformerFactory = TransformerFactory.newInstance();
		try {
            this.fopFactory = FopFactory.newInstance(new File("src/main/resources/xslt/fop.xconf"));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Creates a PDF using iText Java API
	 * 
	 * @param filePath
	 * @throws IOException
	 * @throws DocumentException
	 * @throws FOPException 
	 * @throws TransformerException 
	 */
	public void generatePDF(String xslFo, String xmlPath, String outputPath) throws IOException, DocumentException, FOPException, TransformerException {
//		// step 1
//		Document document = new Document();
//		//ByteArrayInputStream bis = new ByteArrayInputStream(xtmlFile.getBytes());
//		// step 2
//		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
//		// step 3
//		document.open();
//		// step 4
//		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(xtmlFile),
//				Charset.forName("UTF-8"));
//		// step 5
//		document.close();
//		//return bis;
		System.out.println("[INFO] " + PDFTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = new File(xslFo);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        Source source = new StreamSource(new StringReader(xmlPath));

        //StreamSource source = new StreamSource(new File(INPUT_FILE));


        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        SAXResult res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = new File(outputPath);
        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());

        System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
        out.close();

        System.out.println("[INFO] End.");

	}

	public void generateHTML(Node xmlPath, String htmlFile, String xslFile) throws FileNotFoundException {

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
			StreamResult result = new StreamResult(new FileOutputStream(htmlFile));
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

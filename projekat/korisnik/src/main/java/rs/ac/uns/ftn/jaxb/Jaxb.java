package rs.ac.uns.ftn.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class Jaxb {
	
	public <T> T unmarshall(Class genericClass, String text) throws JAXBException {

        // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
        JAXBContext context = JAXBContext.newInstance(genericClass);

        // Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Unmarshalling generiše objektni model na osnovu XML fajla
        T createdObject = (T) unmarshaller.unmarshal(new StringReader(text));

        return createdObject;
    }

    public <T> String marshall(Class genericClass, T objectToMarshall) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(genericClass);

        // Marshaller je objekat zadužen za konverziju iz objektnog u XML model
        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(objectToMarshall, sw);

        return sw.toString();
    }

	public <T> boolean validate(Class genericClass, T objectValidate) {

        try {
            String objectValidateString = this.marshall(genericClass, objectValidate);

            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(ResourceUtils.getFile("classpath:/user.xsd"));

            JAXBContext context = JAXBContext.newInstance(genericClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);

            unmarshaller.unmarshal(new StringReader(objectValidateString));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}

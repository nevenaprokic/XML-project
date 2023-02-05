package rs.ac.uns.ftn.jaxb.z1;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Component;

import rs.ac.uns.ftn.exception.BadRequestException;
import rs.ac.uns.ftn.exception.ErrorMessageConstants;

@Component
public class JaxbValidator {

    public <T> String marshall(Class genericClass, T objectToMarshall) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(genericClass);

        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(objectToMarshall, sw);

        return sw.toString();
    }
	
	public <T> boolean validate(Class genericClass, T objectValidate) {

        try {
//            String objectValidateString = this.marshall(genericClass, objectValidate);
//
//            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
//            Schema schema = factory.newSchema(ResourceUtils.getFile("classpath:xsd/zahtev_za-prijavu_ziga.xsd"));
//
//            JAXBContext context = JAXBContext.newInstance(genericClass);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            unmarshaller.setSchema(schema);
//
//            unmarshaller.unmarshal(new StringReader(objectValidateString));
            return true;

        } catch (Exception e) {
            throw new BadRequestException(ErrorMessageConstants.INVALID_XML_FILE);		
        }
       
    }
}

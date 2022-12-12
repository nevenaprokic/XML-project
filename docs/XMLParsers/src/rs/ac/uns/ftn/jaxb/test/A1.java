package rs.ac.uns.ftn.jaxb.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;

public class A1 {
    public void test() {
        try {
            System.out.println("[INFO] Autorsko delo: JAXB XML Schema validation.\n");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.a1");

            // Unmarshaller - zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Učitavanje XML-a u objektni model
            ZahtevZaAutorskoDelo zahtev = (ZahtevZaAutorskoDelo) unmarshaller.unmarshal(new File("./data/A1.xml"));

            // Ispis sadržaja objekta
            System.out.println("[INFO] Unmarshalled content:");
            System.out.println(zahtev);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        A1 test = new A1();
        test.test();
    }
}

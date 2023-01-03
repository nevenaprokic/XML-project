package rs.ac.uns.ftn.jaxb.test;

import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class P1 {
    public void test() {
        try {
            System.out.println("[INFO] Zahtev za priznanje patenta: JAXB XML Schema validation.\n");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.p1");

            // Unmarshaller - zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Učitavanje XML-a u objektni model
            ZahtevZaPriznanjePatenta zahtev = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(
                    new File("./data/Zahtev_za_priznanje_patenta.xml"));

            // Ispis sadržaja objekta
            System.out.println("[INFO] Unmarshalled content:");
            System.out.println(zahtev);

            // Upis u fajl sadrzaja sa izmenama
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File("./data/zahtev_za_priznanje_patenta2 .xml");
            marshaller.marshal(zahtev, file);

            System.out.println("[INFO] File created");


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        P1 test = new P1();
        test.test();
    }
}

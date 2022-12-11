package rs.ac.uns.ftn.jaxb.test;

import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Z1 {
    public void test() {
        try {
            System.out.println("[INFO] Zig: JAXB XML Schema validation.\n");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("rs.ac.uns.ftn.jaxb.z1");

            // Unmarshaller - zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Učitavanje XML-a u objektni model
            ZahtevZaPriznanjeZiga zahtev = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(new File("./data/zahtev_za_prijavu_ziga.xml"));

            System.out.println("[INFO] Unmarshalled content:");
            System.out.println(zahtev);

            zahtev.setBrojPrijaveZiga("Ž-682/22");

            // Upis u fajl sadrzaja sa izmenama
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File("./data/zahtev_za_prijavu_ziga_novi.xml");
            marshaller.marshal(zahtev, file);

            System.out.println("[INFO] File created");


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Z1 test = new Z1();
        test.test();
    }
}

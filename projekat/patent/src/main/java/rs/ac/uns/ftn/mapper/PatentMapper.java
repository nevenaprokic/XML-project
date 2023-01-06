package rs.ac.uns.ftn.mapper;

import java.math.BigInteger;

import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.p1.ObjectFactory;
import rs.ac.uns.ftn.jaxb.p1.TPrimalacZahteva;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.jaxb.zajednicko.Adresa;
import rs.ac.uns.ftn.jaxb.zajednicko.KontaktPodaci;
import rs.ac.uns.ftn.jaxb.zajednicko.TFizickoLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TPravnoLice;

public class PatentMapper {
	
	private static final String PRED_PREFIX = "http://examples/predicate/";
	private static final String TARGET_NS_PREFIX = "http://ftn.uns.ac.rs/p1/";

	private static ObjectFactory objectFactory = new ObjectFactory();
	private static rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory objectFactoryZajednicki = new rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory();
	
	private static final String GRAD_ZAVODA = "Beograd";
	private static final String ULICA_ZAVODA = "Kneginje Ljubice";
	private static final BigInteger BROJ_ZAVODA = BigInteger.valueOf(2);
	private static final String NAZIV_ZAVODA = "Zavod za intelektualnu svojinu";
	
	public static ZahtevZaPriznanjePatenta mapFromDTO(ZahtevZaPriznanjePatenta zahtevDTO, String id) {
		ZahtevZaPriznanjePatenta zahtev = objectFactory.createZahtevZaPriznanjePatenta();		
		
		zahtev.getOtherAttributes().put(new QName("vocab"), PRED_PREFIX);
		zahtev.getOtherAttributes().put(new QName("about"),  TARGET_NS_PREFIX + id);
		zahtev.getOtherAttributes().put(new QName("property"), "pred:datum_prijema_prijave");
		zahtev.getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
		zahtev.getOtherAttributes().put(new QName("content"), zahtevDTO.getDatumPrijemaPrijave().toString());
		zahtev.setDatumPrijemaPrijave(zahtevDTO.getDatumPrijemaPrijave());
		
		return zahtev;
	}




	private static TPravnoLice getPravnoLiceFromDTO(TPravnoLice pravnoLiceDto) {
		TPravnoLice pravnoLice = objectFactoryZajednicki.createTPravnoLice();
		
		pravnoLice.setAdresa(getAdresaFromDTO(pravnoLiceDto.getAdresa()));		
		pravnoLice.setKontaktPodaci(getKontaktPodaciFromDTO(pravnoLiceDto.getKontaktPodaci()));
		pravnoLice.setNaziv(pravnoLiceDto.getNaziv());
		
		return pravnoLice;
	}


	private static TFizickoLice getFizickoLiceFromDTO(TFizickoLice punomocnikDto) {
		TFizickoLice fizickoLice = objectFactoryZajednicki.createTFizickoLice();

		fizickoLice.setAdresa(getAdresaFromDTO(punomocnikDto.getAdresa()));
		fizickoLice.setKontaktPodaci(getKontaktPodaciFromDTO(punomocnikDto.getKontaktPodaci()));
		fizickoLice.setIme(punomocnikDto.getIme());
		fizickoLice.setPrezime(punomocnikDto.getPrezime());
		
		return fizickoLice;
	}
	
	private static Adresa getAdresaFromDTO(Adresa adresaDto) {
		Adresa adresa = objectFactoryZajednicki.createAdresa();
		adresa.setBroj(adresaDto.getBroj());
		adresa.setGrad(adresaDto.getGrad());
		adresa.setUlica(adresaDto.getUlica());
		return adresa;
	}
	
	private static KontaktPodaci getKontaktPodaciFromDTO(KontaktPodaci kontaktPodaciDto) {
		KontaktPodaci kontaktPodaci = objectFactoryZajednicki.createKontaktPodaci();
		kontaktPodaci.setEmail(kontaktPodaciDto.getEmail());
		kontaktPodaci.setFaks(kontaktPodaciDto.getFaks());
		kontaktPodaci.setTelefon(kontaktPodaciDto.getTelefon());
		return kontaktPodaci;
	}


	private static TPrimalacZahteva createZavod() {
		Adresa adresa = objectFactoryZajednicki.createAdresa();
		
		adresa.setBroj(BROJ_ZAVODA);
		adresa.setGrad(GRAD_ZAVODA);
		adresa.setUlica(ULICA_ZAVODA);

		TPrimalacZahteva zavod = objectFactory.createTPrimalacZahteva();

		zavod.setAdresa(adresa);
		zavod.setNaziv(NAZIV_ZAVODA);
		return zavod;
	}
}

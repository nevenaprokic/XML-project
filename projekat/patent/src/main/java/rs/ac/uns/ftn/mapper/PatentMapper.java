package rs.ac.uns.ftn.mapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import javafx.geometry.Pos;
import rs.ac.uns.ftn.jaxb.p1.ObjectFactory;
import rs.ac.uns.ftn.jaxb.p1.PodaciODodatnojPrijavi;
import rs.ac.uns.ftn.jaxb.p1.PodaciODostavljanju;
import rs.ac.uns.ftn.jaxb.p1.Pronalazak;
import rs.ac.uns.ftn.jaxb.p1.RanijaPrijava;
import rs.ac.uns.ftn.jaxb.p1.TPodnosilacZahteva;
import rs.ac.uns.ftn.jaxb.p1.TPrimalacZahteva;
import rs.ac.uns.ftn.jaxb.p1.TPronalazac;
import rs.ac.uns.ftn.jaxb.p1.TPunomocnik;
import rs.ac.uns.ftn.jaxb.p1.TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava;
import rs.ac.uns.ftn.jaxb.p1.ZahtevZaPriznanjePatenta;
import rs.ac.uns.ftn.jaxb.zajednicko.Adresa;
import rs.ac.uns.ftn.jaxb.zajednicko.KontaktPodaci;
import rs.ac.uns.ftn.jaxb.zajednicko.TFizickoLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TPravnoLice;

public class PatentMapper {
	
	private static final String PRED_PREFIX = "http://examples/predicate/";
	private static final String TARGET_NS_PREFIX = "http://ftn.uns.ac.rs/p1/";
	private static final String USERS_PREFIX = "http://ftn.uns.ac.rs/user/";

	private static ObjectFactory objectFactory = new ObjectFactory();
	private static rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory objectFactoryZajednicki = new rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory();
	
	private static final String GRAD_ZAVODA = "Beograd";
	private static final String ULICA_ZAVODA = "Kneginje Ljubice";
	private static final String DRZAVA_ZAVODA = "Republika Srbija";
	private static final int POSTANSKI_BROJ_ZAVODA = 11000;
	private static final BigInteger BROJ_ZAVODA = BigInteger.valueOf(2);
	private static final String NAZIV_ZAVODA = "Zavod za intelektualnu svojinu";
	
	public static ZahtevZaPriznanjePatenta mapFromDTO(ZahtevZaPriznanjePatenta zahtevDTO, String id) {
		ZahtevZaPriznanjePatenta zahtev = objectFactory.createZahtevZaPriznanjePatenta();		
		
		zahtev.setBrojPrijave(zahtevDTO.getBrojPrijave());
		zahtev.setDatumPrijemaPrijave(zahtevDTO.getDatumPrijemaPrijave());
		zahtev.setPriznatiDatumPodnosenja(zahtevDTO.getPriznatiDatumPodnosenja());
		zahtev.setPrimalacZahteva(createZavod());
		zahtev.setPronalazak(getPronalazak(zahtevDTO.getPronalazak()));
		zahtev.setPronalazac(getPronalazac(zahtevDTO.getPronalazac()));
		zahtev.setPodnosilacZahteva(getPodnosilacZahteva(zahtevDTO.getPodnosilacZahteva()));
		zahtev.setPunomocnik(getPunomocnik(zahtevDTO.getPunomocnik()));
		zahtev.setPodaciODostavljanju(zahtevDTO.getPodaciODostavljanju());
		zahtev.setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(getIzRanijihPrijava(zahtevDTO.getZahtevZaPriznanjePrvenstvaIzRanijihPrijava()));
		zahtev.setPodaciODodatnojPrijavi(getPodaciODodatnojPrijavi(zahtevDTO.getODodatnojPrijavi()));
		
		zahtev.getOtherAttributes().put(new QName("vocab"), PRED_PREFIX);
		zahtev.getOtherAttributes().put(new QName("about"),  TARGET_NS_PREFIX + id);
		zahtev.getOtherAttributes().put(new QName("property"), "pred:datum_prijema_prijave");
		zahtev.getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
		zahtev.getOtherAttributes().put(new QName("content"), zahtevDTO.getDatumPrijemaPrijave().toString());
		zahtev.setDatumPrijemaPrijave(zahtevDTO.getDatumPrijemaPrijave());
		
		return zahtev;
	}


	private static PodaciODodatnojPrijavi getPodaciODodatnojPrijavi(PodaciODodatnojPrijavi oDodatnojPrijavi) {
		if (oDodatnojPrijavi != null) {
			PodaciODodatnojPrijavi podaci = objectFactory.crePodaciODodatnojPrijavi();
			podaci.setTipDodatnePrijave(oDodatnojPrijavi.getTipDodatnePrijave());
			podaci.setBrojPrvobitnePrijave(oDodatnojPrijavi.getBrojPrvobitnePrijave());
			podaci.setDatumPrvobitnePrijave(oDodatnojPrijavi.getDatumPrvobitnePrijave()); 
			 
			return podaci;
		}
		return null;
		
	}


	public static Pronalazak getPronalazak(Pronalazak pronalazak) {
		Pronalazak p = objectFactory.createPronalazak();
		p.setNazivNaEngleskom(pronalazak.getNazivNaEngleskom());
		p.setNazivNaSrpskom(pronalazak.getNazivNaSrpskom());
		
		p.getOtherAttributes().put(new QName("property"), "pred:pronalazak_naslov");
		p.getOtherAttributes().put(new QName("datatype"), "xs:string");
		p.getOtherAttributes().put(new QName("content"), pronalazak.getNazivNaEngleskom() + " | " + pronalazak.getNazivNaSrpskom());
		return p;
	}
	
	public static TPronalazac getPronalazac(TPronalazac pronalazac) {
		TPronalazac p = objectFactory.createTPronalazac();
		p.setAnoniman(pronalazac.isAnoniman());
		p.setAdresa(getAdresaFromDTO(pronalazac.getAdresa()));
		p.setKontaktPodaci(getKontaktPodaciFromDTO(pronalazac.getKontaktPodaci()));
		p.setIme(pronalazac.getIme());
		p.setPrezime(pronalazac.getPrezime());
		
		p.getOtherAttributes().put(new QName("property"), "pred:pronalazac");
		p.getOtherAttributes().put(new QName("datatype"), "xs:string");
		p.getOtherAttributes().put(new QName("content"), formatName(pronalazac.getIme(), pronalazac.getPrezime()));
		
		return p;
	}
	
	public static TPodnosilacZahteva getPodnosilacZahteva(TPodnosilacZahteva podnosilac) {
		TPodnosilacZahteva p = objectFactory.createTPodnosilacZahteva();
		
		p.getOtherAttributes().put(new QName("id"), podnosilac.getLice().getKontaktPodaci().getEmail());
		p.getOtherAttributes().put(new QName("rel"), "pred:podnosilac_email");
		p.getOtherAttributes().put(new QName("href"), USERS_PREFIX + podnosilac.getLice().getKontaktPodaci().getEmail());
		
		if (podnosilac.getLice() instanceof TFizickoLice) {
			TFizickoLice lice = getFizickoLiceFromDTO((TFizickoLice) podnosilac.getLice());
			p.setLice(lice);
			p.setDrzavljanstvo(podnosilac.getDrzavljanstvo());
			
			p.getOtherAttributes().put(new QName("property"), "pred:ime_podnosioca");
			p.getOtherAttributes().put(new QName("datatype"), "xs:string");
			p.getOtherAttributes().put(new QName("content"), formatName(lice.getIme(), lice.getPrezime()));
		}
		else {
			TPravnoLice lice = getPravnoLiceFromDTO((TPravnoLice) podnosilac.getLice());
			p.setLice(lice);
			p.getOtherAttributes().put(new QName("property"), "pred:ime_podnosioca");
			p.getOtherAttributes().put(new QName("datatype"), "xs:string");
			p.getOtherAttributes().put(new QName("content"), lice.getNaziv());
		}
		p.setPronalazac(podnosilac.isPronalazac());
		return p;
	}
	
	public static TPunomocnik getPunomocnik(TPunomocnik punomocnik) {
		if(punomocnik != null) {
			TPunomocnik p = objectFactory.createTPunomocnik();
			if (punomocnik.getLice() instanceof TFizickoLice) {
				TFizickoLice lice = getFizickoLiceFromDTO((TFizickoLice) punomocnik.getLice());
				p.setLice(lice);
			}
			else {
				TPravnoLice lice = getPravnoLiceFromDTO((TPravnoLice) punomocnik.getLice());
				p.setLice(lice);
			}
			p.setZaPrijemPismena(punomocnik.isZaPrijemPismena());
			p.setZaZastupanje(punomocnik.isZaPrijemPismena());
			return p;
		}
		return null;
	}
	
	public static PodaciODostavljanju getPodaciODostavljanju(PodaciODostavljanju podaci) {
		PodaciODostavljanju p = objectFactory.createPodaciODostavljanju();
		p.setAdresa(getAdresaFromDTO(podaci.getAdresa()));
		p.setNacinDostavljanja(podaci.getNacinDostavljanja());
		return p;
	}
	
	public static TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava getIzRanijihPrijava(TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava zahtevi) {
		if(zahtevi != null) {
			TZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava izRanijihPrijava = objectFactory.createTZahtevZaPriznanjePravaPrvenstvaIzRanijihPrijava();
			List<RanijaPrijava> listaRanijihPrijava = new ArrayList<RanijaPrijava>();
			int counter = 1;
			System.out.println(zahtevi.getRanijaPrijava().size());
			for (RanijaPrijava p : zahtevi.getRanijaPrijava()) {
				RanijaPrijava ranijaPrijava = getraRanijaPrijava(p, counter);
				listaRanijihPrijava.add(ranijaPrijava);
				counter += 1;
			}
			izRanijihPrijava.setRanijaPrijava(listaRanijihPrijava);
			return izRanijihPrijava;
		}
		return null;
	}
	
	public static RanijaPrijava getraRanijaPrijava(RanijaPrijava ranijaPrijava, int order) {
		RanijaPrijava p = objectFactory.createRanijaPrijava();
		p.setDatumPodnosenja(ranijaPrijava.getDatumPodnosenja());
		p.setBrojPrijave(ranijaPrijava.getBrojPrijave());
		p.setDvoslovnaOznakaDrzave(ranijaPrijava.getDvoslovnaOznakaDrzave());
		
		p.getOtherAttributes().put(new QName("property"), "pred:ranija_prijava" + String.valueOf(order));
		p.getOtherAttributes().put(new QName("datatype"), "xs:string");
		p.getOtherAttributes().put(new QName("content"), ranijaPrijava.getBrojPrijave());
		
		return p;
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
		adresa.setPostanskiBroj(adresaDto.getPostanskiBroj());
		adresa.setDrzava(adresaDto.getDrzava());
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
		adresa.setPostanskiBroj(POSTANSKI_BROJ_ZAVODA);
		adresa.setDrzava(DRZAVA_ZAVODA);

		TPrimalacZahteva zavod = objectFactory.createTPrimalacZahteva();

		zavod.setAdresa(adresa);
		zavod.setNaziv(NAZIV_ZAVODA);
		return zavod;
	}
	
	private static String formatName(String ime, String prezime) {
		ime = ime != null ? ime : "";
		prezime = prezime != null ? prezime : "";
		return String.format("%1$s %2$s", ime, prezime).trim();
	}
}

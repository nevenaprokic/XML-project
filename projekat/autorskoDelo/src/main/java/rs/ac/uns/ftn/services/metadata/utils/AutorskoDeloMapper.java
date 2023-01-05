package rs.ac.uns.ftn.services.metadata.utils;

import java.math.BigInteger;

import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.a1.ObjectFactory;
import rs.ac.uns.ftn.jaxb.a1.TAutor;
import rs.ac.uns.ftn.jaxb.a1.TAutori;
import rs.ac.uns.ftn.jaxb.a1.TAutorskoDelo;
import rs.ac.uns.ftn.jaxb.a1.TOsnovniPodaciODelu.Identifikator;
import rs.ac.uns.ftn.jaxb.a1.TPodnosilac;
import rs.ac.uns.ftn.jaxb.a1.TZavod;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.zajednicko.Adresa;
import rs.ac.uns.ftn.jaxb.zajednicko.KontaktPodaci;
import rs.ac.uns.ftn.jaxb.zajednicko.TFizickoLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TPravnoLice;

public class AutorskoDeloMapper {

	private static ObjectFactory objectFactory = new ObjectFactory();
	private static rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory objectFactoryZajednicki = new rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory();
	
	private static final String NASLOV_ZAHTEVA = "ZAHTEV ZA UNOSENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA";
	private static final String GRAD_ZAVODA = "Beograd";
	private static final String ULICA_ZAVODA = "Kneginje Ljubice";
	private static final BigInteger BROJ_ZAVODA = BigInteger.valueOf(2);
	private static final String NAZIV_ZAVODA = "Zavod za intelektualnu svojinu";
	
	public static ZahtevZaAutorskoDelo mapFromDTO(ZahtevZaAutorskoDelo zahtevDTO, String id) {
		ZahtevZaAutorskoDelo zahtev = objectFactory.createZahtevZaAutorskoDelo();		
		
		zahtev.setAutorskoDelo(getAutorskoDeloFromDTO(zahtevDTO.getAutorskoDelo()));
		zahtev.setBrojPrijave(zahtevDTO.getBrojPrijave());
		zahtev.setDatumPodnosenja(zahtevDTO.getDatumPodnosenja());
		zahtev.setNaslov(NASLOV_ZAHTEVA);
		zahtev.setPodnosilac(getPodnosilacFromDTO(zahtevDTO.getPodnosilac()));	// TODO: ispraviti
		zahtev.setPrilozi(null);	// TODO: ISPRAVITI
		zahtev.setZavod(createZavod());
		
		zahtev.getOtherAttributes().put(new QName("vocab"), "http://examples/predicate/");
		zahtev.getOtherAttributes().put(new QName("property"), "pred:datumPodnosenja");
		zahtev.getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
		zahtev.getOtherAttributes().put(new QName("content"), zahtevDTO.getDatumPodnosenja().toString());
		
		return zahtev;
	}


	private static TAutorskoDelo getAutorskoDeloFromDTO(TAutorskoDelo tAutorskoDelo) {
		TAutorskoDelo autorskoDelo = objectFactory.createTAutorskoDelo();
		TAutori autori = objectFactory.createTAutori();
		autorskoDelo.setFormaZapisa(tAutorskoDelo.getFormaZapisa());
		autorskoDelo.setIdentifikator(getIdentifikatorDela(tAutorskoDelo.getIdentifikator()));
		autorskoDelo.setNacinKoriscenja(tAutorskoDelo.getNacinKoriscenja());
		autorskoDelo.setPodaciOOriginalu(null);
		autorskoDelo.setPrerada(tAutorskoDelo.isPrerada());
		autorskoDelo.setRadniOdnos(tAutorskoDelo.getRadniOdnos());
		autorskoDelo.setVrsta(tAutorskoDelo.getVrsta());
		
		if(tAutorskoDelo.getAutori()!= null) {
			for (TAutor autor : tAutorskoDelo.getAutori().getAutor()) {
				autori.getAutor().add(getAutorFromDTO(autor));
			}
		}
		autorskoDelo.setAutori(autori);

			
		return autorskoDelo;		
	}
	
	private static Identifikator getIdentifikatorDela(Identifikator identifikatorDto) {
		Identifikator identifikator = objectFactory.createTOsnovniPodaciODeluIdentifikator();
		identifikator.setNaslov(identifikatorDto.getNaslov());
		identifikator.setAlternativniNaslov(identifikatorDto.getAlternativniNaslov());
		return identifikator;
	}
	
	private static TPodnosilac getPodnosilacFromDTO(TPodnosilac podnosilacDto) {
		TPodnosilac podnosilac = objectFactory.createTPodnosilac();
		TPravnoLice pravnoLice = null;
		TFizickoLice fizickoLice = null;
		TAutor autor = null;
		
		if(podnosilacDto.getPravnoLice()!=null) {
			pravnoLice = getPravnoLiceFromDTO(podnosilacDto.getPravnoLice());
		}
		else if(podnosilacDto.getPunomocnik()!=null) {
			fizickoLice = getFizickoLiceFromDTO(podnosilacDto.getPunomocnik());
		}
		else if(podnosilacDto.getAutor()!=null){
			autor = getAutorFromDTO(podnosilacDto.getAutor());
		}
		
		podnosilac.setPravnoLice(pravnoLice);
		podnosilac.setPunomocnik(fizickoLice);
		podnosilac.setAutor(autor);
		return podnosilac;
	}

	private static TAutor getAutorFromDTO(TAutor autorDto) {
		TAutor autor = objectFactory.createTAutor();
		autor.setAdresa(null);
		return autor;
	}


	private static TPravnoLice getPravnoLiceFromDTO(TPravnoLice pravnoLiceDto) {
		TPravnoLice pravnoLice = objectFactoryZajednicki.createTPravnoLice();
		
		pravnoLice.setAdresa(getAdresaFromDTO(pravnoLice.getAdresa()));		
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


	private static TZavod createZavod() {
		Adresa adresa = objectFactoryZajednicki.createAdresa();
		
		adresa.setBroj(BROJ_ZAVODA);
		adresa.setGrad(GRAD_ZAVODA);
		adresa.setUlica(ULICA_ZAVODA);

		TZavod zavod = objectFactory.createTZavod();

		zavod.setAdresa(adresa);
		zavod.setNaziv(NAZIV_ZAVODA);
		return zavod;
	}
}

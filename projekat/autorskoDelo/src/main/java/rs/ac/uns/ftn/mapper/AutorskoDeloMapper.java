package rs.ac.uns.ftn.mapper;

import java.math.BigInteger;

import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.a1.IdAutorskogDela;
import rs.ac.uns.ftn.jaxb.a1.ObjectFactory;
import rs.ac.uns.ftn.jaxb.a1.TAutor;
import rs.ac.uns.ftn.jaxb.a1.TAutori;
import rs.ac.uns.ftn.jaxb.a1.TAutorskoDelo;
import rs.ac.uns.ftn.jaxb.a1.TOsnovniPodaciODelu;
import rs.ac.uns.ftn.jaxb.a1.TOsnovniPodaciODelu.Identifikator;
import rs.ac.uns.ftn.jaxb.a1.TPodnosilac;
import rs.ac.uns.ftn.jaxb.a1.TPrilog;
import rs.ac.uns.ftn.jaxb.a1.TPrilozi;
import rs.ac.uns.ftn.jaxb.a1.TZavod;
import rs.ac.uns.ftn.jaxb.a1.ZahtevZaAutorskoDelo;
import rs.ac.uns.ftn.jaxb.zajednicko.Adresa;
import rs.ac.uns.ftn.jaxb.zajednicko.KontaktPodaci;
import rs.ac.uns.ftn.jaxb.zajednicko.TFizickoLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TPravnoLice;

public class AutorskoDeloMapper {
	
	private static final String PRED_PREFIX = "http://examples/predicate/";
	private static final String TARGET_NS_PREFIX = "http://ftn.uns.ac.rs/a1/";
	private static final String USERS_PREFIX = "http://ftn.uns.ac.rs/user/";
	private static final String PRILOG_PREFIX = "http://ftn.uns.ac.rs/prilog/";

	private static ObjectFactory objectFactory = new ObjectFactory();
	private static rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory objectFactoryZajednicki = new rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory();
	
	private static final String NASLOV_ZAHTEVA = "ZAHTEV ZA UNOSENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA";
	private static final String GRAD_ZAVODA = "Beograd";
	private static final String ULICA_ZAVODA = "Kneginje Ljubice";
	private static final BigInteger BROJ_ZAVODA = BigInteger.valueOf(2);
	private static final String NAZIV_ZAVODA = "Zavod za intelektualnu svojinu";
	
	public static ZahtevZaAutorskoDelo mapFromDTO(ZahtevZaAutorskoDelo zahtevDTO, String id) {
		ZahtevZaAutorskoDelo zahtev = objectFactory.createZahtevZaAutorskoDelo();		
		
		zahtev.setIdAutorskogDela(getIdAutorskogDelaFromDTO(id));
		zahtev.setAutorskoDelo(getAutorskoDeloFromDTO(zahtevDTO.getAutorskoDelo()));
		zahtev.setBrojPrijave(zahtevDTO.getBrojPrijave());
		zahtev.setNaslov(NASLOV_ZAHTEVA);
		zahtev.setPodnosilac(getPodnosilacFromDTO(zahtevDTO.getPodnosilac()));
		zahtev.setPrilozi(getPriloziFromDTO(zahtevDTO.getPrilozi(), id));	
		zahtev.setZavod(createZavod());
		zahtev.setStatus(zahtevDTO.getStatus());
		zahtev.setDatumPodnosenja(zahtevDTO.getDatumPodnosenja());
				
		zahtev.getOtherAttributes().put(new QName("vocab"), PRED_PREFIX);
		zahtev.getOtherAttributes().put(new QName("about"),  TARGET_NS_PREFIX + id);
		
		zahtev.getOtherAttributes().put(new QName("property"), "pred:datum_podnosenja");
		zahtev.getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
		zahtev.getOtherAttributes().put(new QName("content"), zahtev.getDatumPodnosenja().toString());
		return zahtev;
	}

	private static IdAutorskogDela getIdAutorskogDelaFromDTO(String id) {
		IdAutorskogDela idAutorskogDela = objectFactory.createIdAutorskogDela();
		idAutorskogDela.setIdA(id);
		return idAutorskogDela;
	}

	private static TPrilozi getPriloziFromDTO(TPrilozi priloziDto, String documentId) {
		TPrilozi prilog = objectFactory.createTPrilozi();
		prilog.setPrisutanOpis(getPrilogFromDTO(priloziDto.getPrisutanOpis(), "opis", documentId));
		prilog.setPrisutanPrimer(getPrilogFromDTO(priloziDto.getPrisutanPrimer(), "primer", documentId));
		return prilog;
	}
	
	private static TPrilog getPrilogFromDTO(TPrilog prilogDTO, String prilogType, String documentId) {
		TPrilog prilog = objectFactory.createTPrilog();
		if(prilogDTO != null && prilogDTO.getPutanjaDoFajla() != null) {
			prilog.setPutanjaDoFajla(prilogDTO.getPutanjaDoFajla());
			prilog.setDostavljeno(true);
			
			String prilogId =  documentId+"-"+prilogDTO.getPutanjaDoFajla();
			prilog.getOtherAttributes().put(new QName("rel"), "pred:prilog_" + prilogType);
			prilog.getOtherAttributes().put(new QName("href"), PRILOG_PREFIX + prilogId);
		}
//		if(dopuna.isDostavljeno() != null) {
//			dopuna.setDostavljeno(dopunaDTO.isDostavljeno());
//		}
		return prilog;
	}

	private static TAutorskoDelo getAutorskoDeloFromDTO(TAutorskoDelo autorskoDeloDto) {
		TAutorskoDelo autorskoDelo = objectFactory.createTAutorskoDelo();
		
		autorskoDelo.setFormaZapisa(autorskoDeloDto.getFormaZapisa());
		autorskoDelo.setIdentifikator(getIdentifikatorDelaFromDTO(autorskoDeloDto.getIdentifikator()));
		autorskoDelo.setNacinKoriscenja(autorskoDeloDto.getNacinKoriscenja());
		autorskoDelo.setPodaciOOriginalu(getPodaciOOriginaluFromDTO(autorskoDeloDto.getPodaciOOriginalu()));
		autorskoDelo.setPrerada(autorskoDeloDto.isPrerada());
		autorskoDelo.setRadniOdnos(autorskoDeloDto.getRadniOdnos());
		autorskoDelo.setVrsta(autorskoDeloDto.getVrsta());
		autorskoDelo.setAutori(getAutoriFromDTO(autorskoDeloDto.getAutori()));
		
		autorskoDelo.getOtherAttributes().put(new QName("id"), autorskoDelo.getIdentifikator().getNaslov());
		autorskoDelo.getOtherAttributes().put(new QName("property"), "pred:autorsko_delo");
		autorskoDelo.getOtherAttributes().put(new QName("datatype"), "xs:string");
		autorskoDelo.getOtherAttributes().put(new QName("content"), autorskoDelo.getIdentifikator().getNaslov());

		return autorskoDelo;		
	}
	
	private static TOsnovniPodaciODelu getPodaciOOriginaluFromDTO(TOsnovniPodaciODelu originalDto) {
		TOsnovniPodaciODelu original = objectFactory.createTOsnovniPodaciODelu();
		original.setAutori(getAutoriFromDTO(originalDto.getAutori()));
		original.setIdentifikator(getIdentifikatorDelaFromDTO(originalDto.getIdentifikator()));
		return original;
	}
	
	private static TAutori getAutoriFromDTO(TAutori autoriDto) {
		TAutori autori = objectFactory.createTAutori();
		if(autoriDto!= null) {
			for (TAutor autorDto : autoriDto.getAutor()) {
				TAutor autor = getAutorFromDTO(autorDto);
				
				String predicate = autor.isPrimarni() ? "pred:primarni_autor" : "pred:koautor";
				autor.getOtherAttributes().put(new QName("property"), predicate);
				autor.getOtherAttributes().put(new QName("datatype"), "xs:string");
				autor.getOtherAttributes().put(new QName("content"), formatName(autor.getIme(), autor.getPrezime(), autor.getPseudonim()));
				
				autori.getAutor().add(autor);
			}
		}
		return autori;
	}

	private static String formatName(String ime, String prezime, String pseudonim) {
		ime = ime != null ? ime : "";
		prezime = prezime != null ? prezime : "";
		pseudonim = pseudonim != null ? pseudonim : "";
		return String.format("%1$s %2$s %3$s", ime, prezime, pseudonim).trim();
	}

	private static Identifikator getIdentifikatorDelaFromDTO(Identifikator identifikatorDto) {
		Identifikator identifikator = objectFactory.createTOsnovniPodaciODeluIdentifikator();
		identifikator.setNaslov(identifikatorDto.getNaslov());
		identifikator.setAlternativniNaslov(identifikatorDto.getAlternativniNaslov());
		return identifikator;
	}
	
	private static TPodnosilac getPodnosilacFromDTO(TPodnosilac podnosilacDto) {
		TPodnosilac podnosilac = objectFactory.createTPodnosilac();
		
		String email = null;
		String name = null;
		
		TPravnoLice pravnoLice = null;
		TFizickoLice fizickoLice = null;
		TAutor autor = null;
		
		if(podnosilacDto.getPravnoLice()!=null) {
			pravnoLice = getPravnoLiceFromDTO(podnosilacDto.getPravnoLice());
			email = pravnoLice.getKontaktPodaci().getEmail();
			name = pravnoLice.getNaziv();
			podnosilac.setPravnoLice(pravnoLice);
		}
		else if(podnosilacDto.getPunomocnik()!=null) {
			fizickoLice = getFizickoLiceFromDTO(podnosilacDto.getPunomocnik());
			email = fizickoLice.getKontaktPodaci().getEmail();
			name = formatName(fizickoLice.getIme(), fizickoLice.getPrezime(), null);
			podnosilac.setPunomocnik(fizickoLice);
		}
		else if(podnosilacDto.getAutor()!=null){
			autor = getAutorFromDTO(podnosilacDto.getAutor());
			email = autor.getKontaktPodaci().getEmail();
			name = formatName(autor.getIme(), autor.getPrezime(), autor.getPseudonim());
			podnosilac.setAutor(autor);
		}
		
		if(email != null) {
			podnosilac.getOtherAttributes().put(new QName("id"), email);
			podnosilac.getOtherAttributes().put(new QName("rel"), "pred:podnosilac");
			podnosilac.getOtherAttributes().put(new QName("href"), USERS_PREFIX + email);
		}
		if(name != null) {
			podnosilac.getOtherAttributes().put(new QName("property"), "pred:ime_prodnosioca");
			podnosilac.getOtherAttributes().put(new QName("datatype"), "xs:string");
			podnosilac.getOtherAttributes().put(new QName("content"), name);
		}
		return podnosilac;
	}

	private static TAutor getAutorFromDTO(TAutor autorDto) {
		TAutor autor = objectFactory.createTAutor();
		autor.setAdresa(getAdresaFromDTO(autorDto.getAdresa()));
		autor.setAnonimni(autorDto.isAnonimni());
		autor.setGodinaSmrti(autorDto.getGodinaSmrti());
		autor.setIme(autorDto.getIme());
		autor.setPrezime(autorDto.getPrezime());
		autor.setKontaktPodaci(getKontaktPodaciFromDTO(autorDto.getKontaktPodaci()));
		autor.setPrimarni(autorDto.isPrimarni());
		autor.setPseudonim(autorDto.getPseudonim());
		autor.setDrzavljanstvo(autorDto.getDrzavljanstvo());
		return autor;
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
		fizickoLice.setDrzavljanstvo(punomocnikDto.getDrzavljanstvo());
		
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

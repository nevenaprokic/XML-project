package rs.ac.uns.ftn.mapper;

import javax.xml.namespace.QName;

import rs.ac.uns.ftn.jaxb.z1.IdZiga;
import rs.ac.uns.ftn.jaxb.z1.ObjectFactory;
import rs.ac.uns.ftn.jaxb.z1.TDopuna;
import rs.ac.uns.ftn.jaxb.z1.TPrilozi;
import rs.ac.uns.ftn.jaxb.z1.TTakse;
import rs.ac.uns.ftn.jaxb.z1.TZig;
import rs.ac.uns.ftn.jaxb.z1.ZahtevZaPriznanjeZiga;
import rs.ac.uns.ftn.jaxb.z1.ZajednickiPredstavnik;
import rs.ac.uns.ftn.jaxb.zajednicko.Adresa;
import rs.ac.uns.ftn.jaxb.zajednicko.KontaktPodaci;
import rs.ac.uns.ftn.jaxb.zajednicko.TFizickoLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TLice;
import rs.ac.uns.ftn.jaxb.zajednicko.TPravnoLice;

public class ZigMapper {
	
	private static final String PRED_PREFIX = "http://examples/predicate/";
	private static final String TARGET_NS_PREFIX = "http://ftn.uns.ac.rs/z1/";
	private static final String PRILOG_PREFIX = "http://ftn.uns.ac.rs/prilog/";

	private static ObjectFactory objectFactory = new ObjectFactory();
	private static rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory objectFactoryZajednicki = new rs.ac.uns.ftn.jaxb.zajednicko.ObjectFactory();
	
	public static ZahtevZaPriznanjeZiga mapFromDTO(ZahtevZaPriznanjeZiga zahtevDTO, String id) {
		ZahtevZaPriznanjeZiga zahtev = objectFactory.createZahtevZaPriznanjeZiga();		
		
		if(zahtevDTO.getBrojPrijaveZiga() != null) {
			zahtev.setBrojPrijaveZiga(zahtevDTO.getBrojPrijaveZiga());
		}
		
		IdZiga idZiga = objectFactory.createIdZiga();
		idZiga.setIdZ(id);
		zahtev.setId(idZiga);
		
		zahtev.setKod(zahtevDTO.getKod());
		zahtev.setIdResenja(zahtevDTO.getIdResenja());
		
		for(TLice lice: zahtevDTO.getPodnosiocPrijave()) {
			TLice podnosilacZahteva = getLiceFromDTO(lice);
			podnosilacZahteva.getOtherAttributes().put(new QName("id"), lice.getKontaktPodaci().getEmail());
			podnosilacZahteva.getOtherAttributes().put(new QName("property"), "pred:podnosilac_prijave");
			podnosilacZahteva.getOtherAttributes().put(new QName("datatype"), "xs:string");
			podnosilacZahteva.getOtherAttributes().put(new QName("content"), lice.getKontaktPodaci().getEmail());
			
			zahtev.getPodnosiocPrijave().add(podnosilacZahteva);
		}
		
		if(zahtevDTO.getPunomocnik() != null) {
			zahtev.setPunomocnik(getLiceFromDTO(zahtevDTO.getPunomocnik()));
		}
		if(zahtevDTO.getZajednickiPredstavnik() != null) {
			zahtev.setZajednickiPredstavnik(getZajednickiPredstavnik(zahtevDTO.getZajednickiPredstavnik()));
		}
		
		zahtev.setZig(getZigFromDTO(zahtevDTO.getZig()));
		if(zahtevDTO.getPravoPrvenstvaIOsnov() != null) {
			zahtev.setPravoPrvenstvaIOsnov(zahtevDTO.getPravoPrvenstvaIOsnov());
		}
		
		zahtev.setPlaceneTakse(getPlaceneTakseFromDTO(zahtevDTO.getPlaceneTakse()));
		zahtev.setPriloziUzZahtev(getPriloziUzZahtevFromDTO(zahtevDTO.getPriloziUzZahtev(), id));
		zahtev.setStatus(zahtevDTO.getStatus());
		
		zahtev.getOtherAttributes().put(new QName("vocab"), PRED_PREFIX);
		zahtev.getOtherAttributes().put(new QName("about"),  TARGET_NS_PREFIX + id);
		zahtev.getOtherAttributes().put(new QName("property"), "pred:datum_podnosenja_prijave");
		zahtev.getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
		zahtev.getOtherAttributes().put(new QName("content"), zahtevDTO.getDatumPodnosenjaPrijave().toString());
		zahtev.setDatumPodnosenjaPrijave(zahtevDTO.getDatumPodnosenjaPrijave());
		
		return zahtev;
	}
	
	private static TDopuna getDopunaFromDTO(TDopuna dopunaDTO, String prilogType, String documentId) {
		TDopuna dopuna = objectFactory.createTDopuna();
		if(dopunaDTO.getPutanjaDoFajla() != null) {
			dopuna.setPutanjaDoFajla(dopunaDTO.getPutanjaDoFajla());
			dopuna.setDostavljeno(true);
		}
		if(dopunaDTO.isDostavljeno() != null) {
			dopuna.setDostavljeno(dopunaDTO.isDostavljeno());
			if(dopunaDTO.isDostavljeno()) {
				String prilogId =  documentId + "-" + dopunaDTO.getPutanjaDoFajla();
				dopuna.getOtherAttributes().put(new QName("rel"), "pred:prilog_" + prilogType);
				dopuna.getOtherAttributes().put(new QName("href"), PRILOG_PREFIX + prilogId);	
			}
		}
		return dopuna;
	}
	
	private static TPrilozi getPriloziUzZahtevFromDTO(TPrilozi priloziDTO, String documentId) {
		TPrilozi prilozi = objectFactory.createTPrilozi();
		prilozi.setPrimerakZnaka(getDopunaFromDTO(priloziDTO.getPrimerakZnaka(), "primerak_znaka", documentId));
		prilozi.setSpisakRobeIUsluga(getDopunaFromDTO(priloziDTO.getSpisakRobeIUsluga(), "spisak_robe_i_usluga", documentId));
		if(priloziDTO.getPunomocje() != null) {
			prilozi.setPunomocje(getDopunaFromDTO(priloziDTO.getPunomocje(), "punomocje", documentId));
		}
		if(priloziDTO.getPunomocjeRanijePrilozeno() != null) {
			prilozi.setPunomocjeRanijePrilozeno(getDopunaFromDTO(priloziDTO.getPunomocjeRanijePrilozeno(), "punomocje_ranije_prilozeno", documentId));
		}
		if(priloziDTO.getPunomocjeNaknadnoDostavljeno() != null) {
			prilozi.setPunomocjeNaknadnoDostavljeno(getDopunaFromDTO(priloziDTO.getPunomocjeNaknadnoDostavljeno(), "punomocje_naknadno_dostavljeno", documentId));
		}
		
		prilozi.setOpstiAktOKolektivnomZiguGarancije(getDopunaFromDTO(priloziDTO.getOpstiAktOKolektivnomZiguGarancije(), "opsti_akt_o_kolektivnom_zigu_garancije", documentId));
		prilozi.setDokazOPravuPrvenstva(getDopunaFromDTO(priloziDTO.getDokazOPravuPrvenstva(), "dokaz_o_pravu_prvenstva", documentId));
		prilozi.setDokazOUplatiTakse(getDopunaFromDTO(priloziDTO.getDokazOUplatiTakse(), "dokaz_o_uplati_takse", documentId));
		return prilozi;
	}
	
	private static TTakse getPlaceneTakseFromDTO(TTakse takseDTO) {
		TTakse takse = objectFactory.createTTakse();
		takse.setOsnovnaTaksa(takseDTO.getOsnovnaTaksa());
		if(takseDTO.getGrafickoResenje() != null) {
			takse.setGrafickoResenje(takse.getGrafickoResenje());
		}
		if(takseDTO.getZaKlasu() != null) {
			takse.setZaKlasu(takseDTO.getZaKlasu());
		}
		
		takse.setUkupanIznosTakse(takseDTO.getUkupanIznosTakse());
		
		takse.getOtherAttributes().put(new QName("property"), "pred:ukupna_taksa");
		takse.getOtherAttributes().put(new QName("datatype"), "xs:positiveInteger");
		takse.getOtherAttributes().put(new QName("content"), String.valueOf(takse.getUkupanIznosTakse()));
		return takse;
	}
	
	private static TZig getZigFromDTO(TZig zigDTO) {
		TZig zig = objectFactory.createTZig();
		
		zig.setVrstaZigaNaOsnovuKorisnika(zigDTO.getVrstaZigaNaOsnovuKorisnika());
		zig.setVrstaZigaNaOsnovuIzgleda(zigDTO.getVrstaZigaNaOsnovuIzgleda());
		zig.setIzgledZnaka(zigDTO.getIzgledZnaka());
		if(zigDTO.getPodaciOBojiZnaka() != null) {
			zig.setPodaciOBojiZnaka(zigDTO.getPodaciOBojiZnaka());
		}
		if(zigDTO.getTransliteracijaZnaka() != null) {
			zig.setTransliteracijaZnaka(zigDTO.getTransliteracijaZnaka());
		}
		if(zigDTO.getPrevodZnaka() != null) {
			zig.setPrevodZnaka(zigDTO.getPrevodZnaka());
		}
		if(zigDTO.getOpisZnaka() != null) {
			zig.setOpisZnaka(zigDTO.getOpisZnaka());
		}
		
		for(Integer number: zigDTO.getPodaciOBrojevimaKlasaRobeIUsluga()) {
			zig.getPodaciOBrojevimaKlasaRobeIUsluga().add(number);
		}

//		zig.getOtherAttributes().put(new QName("property"), "pred:vrsta_ziga_korisnik");
//		zig.getOtherAttributes().put(new QName("datatype"), "xs:string");
//		zig.getOtherAttributes().put(new QName("content"), zig.getVrstaZigaNaOsnovuKorisnika().value());
		
		zig.getOtherAttributes().put(new QName("property"), "pred:vrsta");
		zig.getOtherAttributes().put(new QName("datatype"), "xs:string");
		zig.getOtherAttributes().put(new QName("content"), zig.getVrstaZigaNaOsnovuIzgleda() + " | " + zig.getVrstaZigaNaOsnovuKorisnika().value());
		
		return zig;
		
	}
	
	private static ZajednickiPredstavnik getZajednickiPredstavnik(ZajednickiPredstavnik zajednickiPredstavnikDTO) {
		ZajednickiPredstavnik zajednickiPredstavnik = objectFactory.createZajednickiPredstavnik();
		zajednickiPredstavnik.setAdresa(getAdresaFromDTO(zajednickiPredstavnikDTO.getAdresa()));
		zajednickiPredstavnik.setKontaktPodaci(getKontaktPodaciFromDTO(zajednickiPredstavnikDTO.getKontaktPodaci()));
		return zajednickiPredstavnik;
	}
	
	private static TLice getLiceFromDTO(TLice liceDTO) {
		
		if(liceDTO instanceof TFizickoLice) {
			return getFizickoLiceFromDTO((TFizickoLice) liceDTO);
		}
		else {
			return getPravnoLiceFromDTO((TPravnoLice) liceDTO);
		}
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
}

package rs.ac.uns.ftn.mapper;

import rs.ac.uns.ftn.jaxb.prilog.ObjectFactory;
import rs.ac.uns.ftn.jaxb.prilog.PrilogImage;

public class PrilogMapper {

	private static ObjectFactory objectFactory = new ObjectFactory();

	public static PrilogImage mapFromDTO(String prilogDto) {
		PrilogImage prilog = objectFactory.createPrilogImage();	
		
		String[] prilogData = parsePrilogDto(prilogDto);
		prilog.setNazivPriloga(prilogData[0]);
		prilog.setSadrzajPriloga(prilogData[1]);
		return prilog;
	}
	
	private static String[] parsePrilogDto(String prilogDto) {
		return prilogDto.split(";custom_separator;");
	}

}

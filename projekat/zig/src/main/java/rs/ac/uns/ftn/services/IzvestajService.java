package rs.ac.uns.ftn.services;

import javax.xml.bind.JAXBException;

import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.jaxb.izvestaj.Izvestaj;

public interface IzvestajService {

	Izvestaj getIzvestaj(Izvestaj izvestaj) throws XMLDBException, JAXBException;
}

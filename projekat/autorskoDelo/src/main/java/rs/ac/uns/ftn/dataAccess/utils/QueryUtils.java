package rs.ac.uns.ftn.dataAccess.utils;

public class QueryUtils {
	
	private static final String DECLARE_NAMESPACES = "declare namespace a1 = \"http://ftn.uns.ac.rs/a1\";";
	
	public static final String FIND_ALL = DECLARE_NAMESPACES
			+ "for $zahtev in collection('/db/project/autorskaDela')\r\n"
			+ "    let $txt := $zahtev/a1:Zahtev_za_autorsko_delo\r\n"
			+ "return $txt";
}

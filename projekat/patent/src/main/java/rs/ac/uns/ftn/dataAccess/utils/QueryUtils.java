package rs.ac.uns.ftn.dataAccess.utils;

public class QueryUtils {

	private static final String DECLARE_NAMESPACES = "declare namespace p1 = \"http://www.ftn.uns.ac.rs/p1\";";
	
	private static final String DECLATE_MATCH_FUNC = "" + "declare function p1:someMatch($txt, $keyword) {\r\n"
	+ " (some $t in $txt satisfies (contains(lower-case($t), lower-case($keyword))))\r\n" + "};";
	
	public static final String FIND_ALL = DECLARE_NAMESPACES + "for $zahtev in collection('/db/project/patenti')\r\n"
	+ "    let $txt := $zahtev/p1:Zahtev_za_priznanje_patenta\r\n" + "return $txt";
	
	public static final String SEARCH_TEXT = DECLARE_NAMESPACES + DECLATE_MATCH_FUNC
	+ "for $file in collection('/db/project/patenti')\r\n"
	+ "    let $zahtev := $file/p1:Zahtev_za_priznanje_patenta\r\n" + " let $txt := $zahtev//text()\r\n"
	+ " where  %1$s \r\n" + "return $zahtev";
	
	public static final String CONDITION_TEPMLATE = "p1:someMatch($txt, %1$s)";
}

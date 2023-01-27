package rs.ac.uns.ftn.dataAccess.utils;

public class QueryUtils {
	
	private static final String DECLARE_NAMESPACES = "declare namespace a1 = \"http://ftn.uns.ac.rs/a1\";";
	
	private static final String DECLATE_MATCH_FUNC = ""
			+ "declare function a1:someMatch($txt, $keyword) {\r\n"
			+ "	(some $t in $txt satisfies (contains(lower-case($t), lower-case($keyword))))\r\n"
			+ "};";
	
	public static final String FIND_ALL = DECLARE_NAMESPACES
			+ "for $zahtev in collection('/db/project/autorskaDela')\r\n"
			+ "    let $txt := $zahtev/a1:Zahtev_za_autorsko_delo\r\n"
			+ "return $txt";
	
	public static final String SEARCH_TEXT = DECLARE_NAMESPACES + DECLATE_MATCH_FUNC +
			"for $file in collection('/db/project/autorskaDela')\r\n"
			+ "    let $zahtev := $file/a1:Zahtev_za_autorsko_delo\r\n"
			+ "	let $txt := $zahtev//text()\r\n"
			+ "	where  %1$s \r\n"
			+ "return $zahtev";
	
	public static final String CONDITION_TEPMLATE = "a1:someMatch($txt, %1$s)";
}

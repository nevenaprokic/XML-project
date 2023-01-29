package rs.ac.uns.ftn.dataAccess.utils;

public class QueryUtils {

	private static final String DECLARE_NAMESPACES = "declare namespace z1 = \"http://ftn.uns.ac.rs/zig\";";

	private static final String DECLATE_MATCH_FUNC = "" + "declare function z1:someMatch($txt, $keyword) {\r\n"
			+ "	(some $t in $txt satisfies (contains(lower-case($t), lower-case($keyword))))\r\n" + "};";

	public static final String FIND_ALL = DECLARE_NAMESPACES + "for $zahtev in collection('/db/project/zigovi')\r\n"
			+ "    let $txt := $zahtev/z1:Zahtev_za_priznanje_ziga\r\n" + "return $txt";

	public static final String SEARCH_TEXT = DECLARE_NAMESPACES + DECLATE_MATCH_FUNC
			+ "for $file in collection('/db/project/zigovi')\r\n"
			+ "    let $zahtev := $file/z1:Zahtev_za_priznanje_ziga\r\n" + "	let $txt := $zahtev//text()\r\n"
			+ "	where  %1$s \r\n" + "return $zahtev";

	public static final String CONDITION_TEPMLATE = "z1:someMatch($txt, %1$s)";
	
	public static final String FIND_ALL_APPROVED = DECLARE_NAMESPACES + "for $zahtev in collection('/db/project/zigovi')\r\n"
			+ "    let $txt := $zahtev/z1:Zahtev_za_priznanje_ziga\r\n" + "where $txt/@status = 'odobren'\r\n" + 
					"" + "return $txt";
}

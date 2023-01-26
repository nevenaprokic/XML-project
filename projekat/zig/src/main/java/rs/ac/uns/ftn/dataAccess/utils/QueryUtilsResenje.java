package rs.ac.uns.ftn.dataAccess.utils;

public class QueryUtilsResenje {
	private static final String DECLARE_NAMESPACES = "declare namespace r = \"http://ftn.uns.ac.rs/resenje\";";

	private static final String DECLATE_MATCH_FUNC = "" + "declare function r:someMatch($txt, $keyword) {\r\n"
			+ "	(some $t in $txt satisfies (contains(lower-case($t), lower-case($keyword))))\r\n" + "};";

	public static final String FIND_ALL = DECLARE_NAMESPACES + "for $zahtev in collection('/db/project/resenja')\r\n"
			+ "    let $txt := $zahtev/r:Resenje\r\n" + "return $txt";

	public static final String SEARCH_TEXT = DECLARE_NAMESPACES + DECLATE_MATCH_FUNC
			+ "for $file in collection('/db/project/resenja')\r\n" + "    let $zahtev := $file/r:Resenje\r\n"
			+ "	let $txt := $zahtev//text()\r\n" + "	where  %1$s \r\n" + "return $zahtev";

	public static final String CONDITION_TEPMLATE = "r:someMatch($txt, %1$s)";
}

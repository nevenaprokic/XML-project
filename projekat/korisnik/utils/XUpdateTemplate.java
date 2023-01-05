package rs.ac.uns.ftn.dataAccess.utils;


import org.exist.xupdate.XUpdateProcessor;

/**
 * http://xmldb-org.sourceforge.net/xupdate/xupdate-wd.html
 * 
 */
public class XUpdateTemplate {

	private final static String ZAJ_NAMESPACE = "http://www.ftn.uns.ac.rs/zaj";
	
	/*
	 * There are two instructions in XUpdate that support insertion of nodes:
	 * xupdate:insert-before and xupdate:insert-after. Both elements have a required
	 * select attribute, which specifies the node selected by an XPath expression.
	 * This select expression must evaluate to a node-set. The appendix of before
	 * and after to the definition of insert is meant to specify the direction
	 * where, in relation to the selected context node, the new node will be
	 * inserted.
	 * 
	 */
	public static final String INSERT_AFTER = "<xu:modifications version=\"1.0\" xmlns:xu=\""
			+ XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"%1$s\" xmlns:zaj=\"" + ZAJ_NAMESPACE + "\">"
			+ "<xu:insert-after select=\"%2$s\">%3$s</xu:insert-after>" + "</xu:modifications>";

	/*
	 * The xupdate:insert-before inserts the given node as the preceding sibling of
	 * the selected context node, where xupdate:insert-after inserts the given node
	 * as the following sibling of the selected context node.
	 */
	public static final String INSERT_BEFORE = "<xu:modifications version=\"1.0\" xmlns:xu=\""
			+ XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"%1$s\" xmlns:zaj=\"" + ZAJ_NAMESPACE + "\">"
			+ "<xu:insert-before select=\"%2$s\">%3$s</xu:insert-before>" + "</xu:modifications>";

	/*
	 * The xupdate:append element allows a node to be created and appended as a
	 * child of the context node. An xupdate:append element must have a select
	 * attribute which selects the context node as the parent of the new child node.
	 * The select expression must evaluate to a node-set of element nodes. The
	 * optional child attribute specifies the position of the newly appended child
	 * node. The child expression must evaluate to an Integer value. If this
	 * attribute is omitted, the new child is appended as the last child of the
	 * selected context node.
	 */
	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" 
			+ XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"%1$s\" xmlns:zaj=\"" + ZAJ_NAMESPACE + "\">"
			+ "<xu:append select=\"%2$s\" child=\"last()\">%3$s</xu:append>"
			+ "</xu:modifications>";
	/*
	 * The xupdate:update element can be used to update the content of existing
	 * nodes. An xupdate:update element must have a select attribute, which selects
	 * the context node for update. This select expression must evaluate to a
	 * node-set.
	 */
	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" 
			+ XUpdateProcessor.XUPDATE_NS +  "\" xmlns=\"%1$s\" xmlns:zaj=\"" + ZAJ_NAMESPACE + "\">"
			+ "<xu:update select=\"%2$s\">%3$s</xu:update>"
			+ "</xu:modifications>";

	/*
	 * The xupdate:remove element allows a node to be removed from the result tree.
	 * The xupdate:remove element has a required select attribute, which specifies
	 * the node selected by an XPath expression. The select expression must evaluate
	 * to a node-set.
	 */
	public static final String REMOVE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" 
			+ XUpdateProcessor.XUPDATE_NS +  "\" xmlns=\"%1$s\" xmlns:zaj=\"" + ZAJ_NAMESPACE + "\">"
			+ "<xu:remove select=\"%2$s\"/>" + "</xu:modifications>";
}

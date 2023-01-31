package rs.ac.uns.ftn.services.metadata.utils;

public class SearchRequestParser {


	public static String parseFilterClause(String request) {
		String[] params = request.split(";");
		
		String filterClause = parseOperand(params[0]);
		
		for (int i = 1; i < params.length; i+=2) {
			filterClause += parseWithOperator(params[i], params[i+1]);
		}
		return filterClause;
	}

	private static String parseWithOperator(String operator, String operand) {
		String parsed = parseOperator(operator);
		parsed += parseOperand(operand);
		if(parsed.contains("!")) {
			parsed += " ) ";
		}
		return parsed;
	}


	private static String parseOperand(String operand) {
		if(operand.toLowerCase().contains("datum")) {
			return parseDatum(operand);
		}
		if(operand.toLowerCase().contains("ranija_prijava")) {
			return parseRanijaPrijava(operand);
		}
		String[] pair = operand.split("=");
		return "regex(UCASE(str(?" + pair[0] + ")), UCASE(\".*"+ pair[1].trim()+".*\"))";
	}
	
	private static String parseRanijaPrijava(String operand) {
		// TODO: 
		return "";
//		String[] pair = operand.split("=");
//		return "( " + 
//			   "regex(UCASE(str(?" + MetadataKeys.PRILOG_OPIS + ")), UCASE(\".*"+ pair[1].trim()+".*\"))" +  " || " +
//			   "regex(UCASE(str(?" + MetadataKeys.PRILOG_PRIMER + ")), UCASE(\".*"+ pair[1].trim()+".*\"))" + 
//			   " )";
	}

	private static String parseDatum(String operand) {
		String relation = getDateRelation(operand);
		
		String[] pair = operand.split(relation);
		String key = pair[0];
		String value = pair[1].trim();
		
		value = value.replace(' ', '+');
		value+="^^<http://www.w3.org/2001/XMLSchema#dateTime>";
		return "?" + key + relation + value;
	}

	private static String parseOperator(String operator) {
		if(operator.equals("AND")) {
			return " && ";
		}
		else if(operator.equals("OR")) {
			return " || ";
		}
		
		else if(operator.equals("NOT")) {
			return " && !( ";
		}
		return " ";
	}

	private static String getDateRelation(String operand) {
		if(operand.contains("=")) {
			return "=";
		}
		else if(operand.contains("<")) {
			return "<";
		}
		else if(operand.contains(">")) {
			return ">";
		}
		return "";
	}

}

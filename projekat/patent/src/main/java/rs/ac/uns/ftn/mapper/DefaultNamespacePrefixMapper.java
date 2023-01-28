package rs.ac.uns.ftn.mapper;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import java.util.HashMap;
import java.util.Map;

public class DefaultNamespacePrefixMapper extends NamespacePrefixMapper {

    private static final String FAULT_PREFIX = "patent";

    private Map<String, String> namespaceMap = new HashMap<>();

    public DefaultNamespacePrefixMapper() {
        this.namespaceMap.put("http://ftn.uns.ac.rs/p1", FAULT_PREFIX);
    }

    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return namespaceMap.getOrDefault(namespaceUri, suggestion);
    }
}


package rs.ac.uns.ftn.jaxb.resenje;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the rs.ac.uns.ftn.jaxb.resenje package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.jaxb.resenje
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resenje }
     *
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link TSluzbenik }
     *
     */
    public TSluzbenik createTSluzbenik() {
        return new TSluzbenik();
    }

    /**
     * Create an instance of {@link TOdobren }
     *
     */
    public TOdobren createTOdobren() {
        return new TOdobren();
    }

    /**
     * Create an instance of {@link TOdbijen }
     *
     */
    public TOdbijen createTOdbijen() {
        return new TOdbijen();
    }

}

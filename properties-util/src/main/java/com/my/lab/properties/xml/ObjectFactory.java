
package com.my.lab.properties.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.my.lab.properties.xml package. 
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
@SuppressWarnings("ALL")
@XmlRegistry
public class ObjectFactory {

    private final static QName _Properties_QNAME = new QName("", "properties");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.my.lab.properties.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PropertiesType }
     * 
     */
    public PropertiesType createPropertiesType() {
        return new PropertiesType();
    }

    /**
     * Create an instance of {@link FileType }
     * 
     */
    public FileType createFileType() {
        return new FileType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropertiesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "properties")
    public JAXBElement<PropertiesType> createProperties(PropertiesType value) {
        return new JAXBElement<>(_Properties_QNAME, PropertiesType.class, null, value);
    }

}

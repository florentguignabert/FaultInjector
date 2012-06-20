package preparationCampagne;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class InterpretationXML {

	private String xmlFile = "example.xml";
	private String xsdFile = "faultinjector.xsd";
	private Document xmlDoc;
	private CampagneDeTest camp;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InterpretationXML ixml = new InterpretationXML();
		ixml.isValid();
		ixml.preparerCampagne();

	}
	
	public InterpretationXML(){
		
	}

	public Boolean isValid() {
		 // parse an XML document into a DOM tree
	    DocumentBuilder parser;
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	    try {
			xmlDoc = parser.parse(new File(xmlFile));
		} catch (SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    // create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	    // load a WXS schema, represented by a Schema instance
	    Source schemaFile = new StreamSource(new File(xsdFile));
	    Schema schema;
		try {
			schema = factory.newSchema(schemaFile);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

	    // create a Validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();

	    // validate the DOM tree
	    try {
	        validator.validate(new DOMSource(xmlDoc));
	    } catch (SAXException | IOException e) {
	    	System.out.print(e.getMessage()); 
	    	return false;
	    }

	    return true;
	}

	public void preparerCampagne() {
		
		if (!isValid()){
			return;
		}
		
		String n ="échec";
		Node campNode = xmlDoc.getElementsByTagName("campaign").item(0);
		n = campNode.getAttributes().getNamedItem("campaignId").getNodeValue();
		System.out.print(n); 
		camp = new CampagneDeTest(n, xmlFile);
		
		
		
//		for (iterable_type iterable_element : iterable) {
//			
//		}
		
		
		
	}

	public void genererCode() {

	}

}

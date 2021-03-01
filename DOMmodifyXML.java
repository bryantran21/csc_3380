package mygroup.myproject;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMmodifyXML {
	public static void main(String argv[]) {
		
	       try {
	        String filepath = "C:/Users/huval/eclipse-workspace/Goober/src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        //File schemaFile = new File("C:/Users/huval/eclipse-workspace/Goober/src/main/gooberDatabase.xsd");
	        //String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
	        // SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
	        // Schema schema = xsdFactory.newSchema(schemaFile);
	        // 
	        // // set schema
	        // docFactory.setSchema(schema);
	        // 
	        // System.out.println("" + docFactory.getSchema());

	        // Get the root element
	        //Node Users = doc.getFirstChild();

	        // Get the staff element , it may not working if tag has spaces, or
	        // whatever weird characters in front...it's better to use
	        // getElementsByTagName() to get it directly.
	        // Node staff = company.getFirstChild();

	        // Get the staff element by tag name directly
	        Node Users = doc.getElementsByTagName("Users").item(0);

	        // update staff attribute
	        //NamedNodeMap attr = staff.getAttributes();
	        //Node nodeAttr = attr.getNamedItem("id");
	        //nodeAttr.setTextContent("2");

	        // append a new node to staff
	        Element newUser = doc.createElement("User");
	        Element email = doc.createElement("email");
	        email.appendChild(doc.createTextNode("dtayl76"));
	        Element username = doc.createElement("username");
	        username.appendChild(doc.createTextNode("taylordrake"));
	        Element password = doc.createElement("password");
	        password.appendChild(doc.createTextNode("ThuggaDrake"));
	        Element role = doc.createElement("role");
	        role.appendChild(doc.createTextNode("Student"));
	        Element year = doc.createElement("year");
	        year.appendChild(doc.createTextNode("Sophomore"));
	        
	        Element daysAvailable = doc.createElement("daysAvailable");
	        Element day = doc.createElement("day");
	        day.appendChild(doc.createTextNode("Monday"));
	        daysAvailable.appendChild(day);
	        
	        Element courses = doc.createElement("courses");
	        Element course = doc.createElement("course");
	        course.appendChild(doc.createTextNode("phys2120"));
	        courses.appendChild(course);
	        
	        Element enrolledUsers = doc.createElement("enrolledUsers");
	        Element enrolledUser = doc.createElement("enrolledUser");
	        enrolledUser.appendChild(doc.createTextNode("DeSouza"));
	        enrolledUsers.appendChild(enrolledUser);
	        
	        newUser.appendChild(email);
	        newUser.appendChild(username);
	        newUser.appendChild(password);
	        newUser.appendChild(role);
	        newUser.appendChild(year);
	        newUser.appendChild(daysAvailable);
	        newUser.appendChild(courses);
	        newUser.appendChild(enrolledUsers);
	        
	        Users.appendChild(newUser);

	        // loop the staff child node
	        //NodeList list = newUser.getChildNodes();

	        //for (int i = 0; i < list.getLength(); i++) {
	        //    
	        //           Node node = list.item(i);
            //
	        //   // get the salary element, and update the value
	        //   if ("salary".equals(node.getNodeName())) {
	        //    node.setTextContent("2000000");
	        //   }
            //   
	        //         //remove firstname
	        //   if ("firstname".equals(node.getNodeName())) {
	        //    staff.removeChild(node);
	        //   }
            //
	        //}

	        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(filepath));
	        transformer.transform(source, result);

	        System.out.println("Done");

	       } catch (ParserConfigurationException pce) {
	        pce.printStackTrace();
	       } catch (TransformerException tfe) {
	        tfe.printStackTrace();
	       } catch (IOException ioe) {
	        ioe.printStackTrace();
	       } catch (SAXException sae) {
	        sae.printStackTrace();
	       } catch (Exception ex) {
	    	   ex.printStackTrace();
	       }
	    }
}

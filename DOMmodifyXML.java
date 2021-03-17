package main;

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
	
	public static void createAccount(String email, String firstName, String lastName, String password, String role) {
		try {
	        String filepath = "C:/Users/huval/eclipse-workspace/Goober/src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        // Get the staff element by tag name directly
	        Node Users = doc.getElementsByTagName("Users").item(0);

	        // append a new node to staff
	        Element newUser = doc.createElement("User");
	        Element emailElement = doc.createElement("email");
	        emailElement.appendChild(doc.createTextNode(email));
	        Element firstNameElement = doc.createElement("firstName");
	        firstNameElement.appendChild(doc.createTextNode(firstName));
	        Element lastNameElement = doc.createElement("lastName");
	        lastNameElement.appendChild(doc.createTextNode(lastName));
	        Element passwordElement = doc.createElement("password");
	        passwordElement.appendChild(doc.createTextNode(password));
	        Element roleElement = doc.createElement("role");
	        roleElement.appendChild(doc.createTextNode(role));
	        Element ratings = doc.createElement("ratings");
	        
	        Element numOfRatings = doc.createElement("numOfRatings");
	        numOfRatings.appendChild(doc.createTextNode("0"));
	        ratings.appendChild(numOfRatings);

	        
	        newUser.appendChild(emailElement);
	        newUser.appendChild(firstNameElement);
	        newUser.appendChild(lastNameElement);
	        newUser.appendChild(passwordElement);
	        newUser.appendChild(roleElement);
	        newUser.appendChild(ratings);
	        
	        Users.appendChild(newUser);

	        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(filepath));
	        transformer.transform(source, result);

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
	
	public static void ratings(String email, String rating) {
		try {
	        String filepath = "C:/Users/huval/eclipse-workspace/Goober/src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        NodeList nList = doc.getElementsByTagName("User");

		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	Element newRating = doc.createElement("rating");
		    	        newRating.appendChild(doc.createTextNode(rating));
		            	eElement.getElementsByTagName("ratings").setTextContent(newRating);
		            }
		        }
		    }
	        
	        // Get the staff element by tag name directly
	        Node Ratings = doc.getElementsByTagName("ratings").item(0);

	        // append a new node to staff
	        Element ratingElement = doc.createElement("rating");
	        ratingElement.appendChild(doc.createTextNode(rating));
	        
	        Ratings.appendChild(ratingElement);

	        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(filepath));
	        transformer.transform(source, result);

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

	        // Get the root element
	        //Node Users = doc.getFirstChild();

	        // Get the staff element , it may not working if tag has spaces, or
	        // whatever weird characters in front...it's better to use
	        // getElementsByTagName() to get it directly.
	        // Node staff = company.getFirstChild();

	        // Get the Users element by tag name directly
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

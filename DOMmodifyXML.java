package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

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
	        String filepath = "src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        docFactory.setValidating(false);
	        //docFactory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(new FileInputStream(new File(filepath)));
	        
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
	        ratings.setAttribute("numOf", "0");
	        
	        Element classes = doc.createElement("classes");
	        classes.setAttribute("numOf", "0");
	        
	        Element schedule = doc.createElement("schedule");
	        

	        Element monday = doc.createElement("Monday");
	        monday.setAttribute("availability", "");
	        Element tuesday = doc.createElement("Tuesday");
	        tuesday.setAttribute("availability", "");
	        Element wednesday = doc.createElement("Wednesday");
	        wednesday.setAttribute("availability", "");
	        Element thursday = doc.createElement("Thursday");
	        thursday.setAttribute("availability", "");
	        Element friday = doc.createElement("Friday");
	        friday.setAttribute("availability", "");
	        Element saturday = doc.createElement("Saturday");
	        saturday.setAttribute("availability", "");
	        Element sunday = doc.createElement("Sunday");
	        sunday.setAttribute("availability", "");
	        
	        schedule.appendChild(monday);
	        schedule.appendChild(tuesday);
	        schedule.appendChild(wednesday);
	        schedule.appendChild(thursday);
	        schedule.appendChild(friday);
	        schedule.appendChild(saturday);
	        schedule.appendChild(sunday);
	        
	        
	        newUser.appendChild(emailElement);
	        newUser.appendChild(firstNameElement);
	        newUser.appendChild(lastNameElement);
	        newUser.appendChild(passwordElement);
	        newUser.appendChild(roleElement);
	        newUser.appendChild(ratings);
	        newUser.appendChild(classes);
	        newUser.appendChild(schedule);
	        
	        Users.appendChild(newUser);

	     // write the content into xml file
	        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //Transformer transformer = transformerFactory.newTransformer();
	        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File(filepath));
	        //transformer.transform(source, result);
	        
	        doc.normalize();
	        
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));
	        //System.out.println(out.toString());

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
	
	public static void deleteAccount(String userEmail) {
		try {
	        String filepath = "src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        NodeList nList = doc.getElementsByTagName("User");

		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (userEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	Node parentNode = eElement.getParentNode();
		            	parentNode.removeChild(eElement);
		            }
		        }
		    }
		    
		 // write the content into xml file
	        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //Transformer transformer = transformerFactory.newTransformer();
	        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File(filepath));
	        //transformer.transform(source, result);
	        
	        doc.normalize();
	        
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));
	        //System.out.println(out.toString());

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
	
	public static void updateEmail(String userEmail, String newEmail) {
		try {
	        String filepath = "src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        NodeList nList = doc.getElementsByTagName("User");

		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (userEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	eElement.getElementsByTagName("email").item(0).setTextContent(newEmail);
		            }
		        }
		    }
		    
	        doc.normalize();
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));

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
	
	public static void updatePassword(String userEmail, String newPassword) {
		try {
	        String filepath = "src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        NodeList nList = doc.getElementsByTagName("User");

		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (userEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	eElement.getElementsByTagName("password").item(0).setTextContent(newPassword);
		            }
		        }
		    }
		    
		 // write the content into xml file
	        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //Transformer transformer = transformerFactory.newTransformer();
	        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File(filepath));
	        //transformer.transform(source, result);
	        
	        doc.normalize();
	        
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));
	        //System.out.println(out.toString());

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
	
	public static void schedule(String studentEmail, String tutorEmail, String meetingDay, String classCode) {
		try {
	        String filepath = "src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        NodeList nList = doc.getElementsByTagName("User");

		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (tutorEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	
		            	Element meetingWith = doc.createElement("meetingWith");
		    	        meetingWith.appendChild(doc.createTextNode(studentEmail));
		    	        meetingWith.setAttribute("classCode", classCode);
		            	
		    	        Node day = eElement.getElementsByTagName(meetingDay).item(0);
		            	day.appendChild(meetingWith);
		            	
		            	NamedNodeMap attr = day.getAttributes();
		                Node dayAttr = attr.getNamedItem("availability");
		                dayAttr.setTextContent("available");
		            }
		            
		            if (studentEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	
		            	Element meetingWith = doc.createElement("meetingWith");
		    	        meetingWith.appendChild(doc.createTextNode(tutorEmail));
		    	        meetingWith.setAttribute("classCode", classCode);
		            	
		            	Node day = eElement.getElementsByTagName(meetingDay).item(0);
		            	day.appendChild(meetingWith);
		            	
		            	NamedNodeMap attr = day.getAttributes();
		                Node dayAttr = attr.getNamedItem("availability");
		                dayAttr.setTextContent("available");
		                
		            	//Element timeScheduled = doc.createElement("meetingTime");
		            	//timeScheduled.appendChild(doc.createTextNode(meetingTime));		legacy
		            	//
		            	//timeScheduled.setAttribute("meetingWith", tutorEmail);
		            	//
		    	        //Node schedule = eElement.getElementsByTagName(meetingDay).item(0);
		    	        //schedule.appendChild(timeScheduled);
		            }
		            
		            
		        }
		    }
		    
		 // write the content into xml file
	        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //Transformer transformer = transformerFactory.newTransformer();
	        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File(filepath));
	        //transformer.transform(source, result);
	        
	        doc.normalize();
	        
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));
	        //System.out.println(out.toString());

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
	
	public static void addClass(String email, String newClass, String grade) {
		try {
	        String filepath = "src/main/gooberDatabase.xml";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse(filepath);
	        
	        NodeList nList = doc.getElementsByTagName("User");

		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	Element classElement = doc.createElement("class");
		    	        classElement.appendChild(doc.createTextNode(newClass));
		    	        classElement.setAttribute("grade", grade);
		    	        Node Classes = eElement.getElementsByTagName("classes").item(0);
		    	        Classes.appendChild(classElement);
		            	NamedNodeMap attr = Classes.getAttributes();
		                Node classesAttr = attr.getNamedItem("numOf");
		                int numOfClasses = Integer.valueOf(classesAttr.getTextContent());
		                numOfClasses++;
		                classesAttr.setTextContent(String.valueOf(numOfClasses));
		            }
		        }
		    }
		    
		 // write the content into xml file
	        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //Transformer transformer = transformerFactory.newTransformer();
	        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File(filepath));
	        //transformer.transform(source, result);
	        
	        doc.normalize();
	        
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));
	        //System.out.println(out.toString());

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
	        String filepath = "src/main/gooberDatabase.xml";
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
		    	        Node Ratings = eElement.getElementsByTagName("ratings").item(0);
		    	        Ratings.appendChild(newRating);
		            	NamedNodeMap attr = Ratings.getAttributes();
		                Node nodeAttr = attr.getNamedItem("numOf");
		                int numOfRatings = Integer.valueOf(nodeAttr.getTextContent());
		                numOfRatings++;
		                nodeAttr.setTextContent(String.valueOf(numOfRatings));
		            }
		        }
		    }
		    
		 // write the content into xml file
	        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        //Transformer transformer = transformerFactory.newTransformer();
	        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File(filepath));
	        //transformer.transform(source, result);
	        
	        doc.normalize();
	        
	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        Writer out = new StringWriter();
	        tf.transform(new DOMSource(doc), new StreamResult(filepath));
	        //System.out.println(out.toString());

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
	
//	public static void main(String argv[]) {
//		
//	       try {
//	        String filepath = "src/main/gooberDatabase.xml";
//	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//	        Document doc = docBuilder.parse(filepath);
//	        
//	        //File schemaFile = new File("C:/Users/huval/eclipse-workspace/Goober/src/main/gooberDatabase.xsd");
//	        //String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//	        // SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
//	        // Schema schema = xsdFactory.newSchema(schemaFile);
//
//	        // Get the root element
//	        //Node Users = doc.getFirstChild();
//
//	        // Get the staff element , it may not working if tag has spaces, or
//	        // whatever weird characters in front...it's better to use
//	        // getElementsByTagName() to get it directly.
//	        // Node staff = company.getFirstChild();
//
//	        // Get the Users element by tag name directly
//	        Node Users = doc.getElementsByTagName("Users").item(0);
//
//	        // update staff attribute
//	        //NamedNodeMap attr = staff.getAttributes();
//	        //Node nodeAttr = attr.getNamedItem("id");
//	        //nodeAttr.setTextContent("2");
//
//	        // append a new node to staff
//	        Element newUser = doc.createElement("User");
//	        Element email = doc.createElement("email");
//	        email.appendChild(doc.createTextNode("dtayl76"));
//	        Element username = doc.createElement("username");
//	        username.appendChild(doc.createTextNode("taylordrake"));
//	        Element password = doc.createElement("password");
//	        password.appendChild(doc.createTextNode("ThuggaDrake"));
//	        Element role = doc.createElement("role");
//	        role.appendChild(doc.createTextNode("Student"));
//	        Element year = doc.createElement("year");
//	        year.appendChild(doc.createTextNode("Sophomore"));
//	        
//	        Element daysAvailable = doc.createElement("daysAvailable");
//	        Element day = doc.createElement("day");
//	        day.appendChild(doc.createTextNode("Monday"));
//	        daysAvailable.appendChild(day);
//	        
//	        Element courses = doc.createElement("courses");
//	        Element course = doc.createElement("course");
//	        course.appendChild(doc.createTextNode("phys2120"));
//	        courses.appendChild(course);
//	        
//	        Element enrolledUsers = doc.createElement("enrolledUsers");
//	        Element enrolledUser = doc.createElement("enrolledUser");
//	        enrolledUser.appendChild(doc.createTextNode("DeSouza"));
//	        enrolledUsers.appendChild(enrolledUser);
//	        
//	        newUser.appendChild(email);
//	        newUser.appendChild(username);
//	        newUser.appendChild(password);
//	        newUser.appendChild(role);
//	        newUser.appendChild(year);
//	        newUser.appendChild(daysAvailable);
//	        newUser.appendChild(courses);
//	        newUser.appendChild(enrolledUsers);
//	        
//	        Users.appendChild(newUser);
//
//	        // loop the staff child node
//	        //NodeList list = newUser.getChildNodes();
//
//	        //for (int i = 0; i < list.getLength(); i++) {
//	        //    
//	        //           Node node = list.item(i);
//            //
//	        //   // get the salary element, and update the value
//	        //   if ("salary".equals(node.getNodeName())) {
//	        //    node.setTextContent("2000000");
//	        //   }
//            //   
//	        //         //remove firstname
//	        //   if ("firstname".equals(node.getNodeName())) {
//	        //    staff.removeChild(node);
//	        //   }
//            //
//	        //}
//
//	        // write the content into xml file
//	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//	        Transformer transformer = transformerFactory.newTransformer();
//	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//	        DOMSource source = new DOMSource(doc);
//	        StreamResult result = new StreamResult(new File(filepath));
//	        transformer.transform(source, result);
//
//	        System.out.println("Done");
//
//	       } catch (ParserConfigurationException pce) {
//	        pce.printStackTrace();
//	       } catch (TransformerException tfe) {
//	        tfe.printStackTrace();
//	       } catch (IOException ioe) {
//	        ioe.printStackTrace();
//	       } catch (SAXException sae) {
//	        sae.printStackTrace();
//	       } catch (Exception ex) {
//	    	   ex.printStackTrace();
//	       }
//	    }
}

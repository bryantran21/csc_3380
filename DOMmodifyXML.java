/*
 * 
 */

package main;

import java.io.File;
import java.io.FileInputStream;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMmodifyXML {

	public static void createAccount(String email, String firstName, String lastName, String password, String role) {
		try {
			
			/*
			 * The purpose of this function is to take the basic parameters of a user and
			 * creates a user node into the xml structure.
			 */
			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setValidating(false);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new FileInputStream(new File(filepath)));
			doc.getDocumentElement().normalize();

			// Get the staff element by tag name directly
			Node Users = doc.getElementsByTagName("Users").item(0);

			// append a new node to staff
			Element newUser = doc.createElement("User");
			
			/*
			 * Data is stored in elements and is added into respective sub-structures
			 * So all subnodes of the user are created and at the end appended
			 */
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

			Element monday = doc.createElement("day");
			Element mondayText = doc.createElement("dayText");
			mondayText.appendChild(doc.createTextNode("Monday"));
			monday.appendChild(mondayText);
			monday.setAttribute("availability", "unavailable");

			Element tuesday = doc.createElement("day");
			Element tuesdayText = doc.createElement("dayText");
			tuesdayText.appendChild(doc.createTextNode("Tuesday"));
			tuesday.appendChild(tuesdayText);
			tuesday.setAttribute("availability", "unavailable");

			Element wednesday = doc.createElement("day");
			Element wednesdayText = doc.createElement("dayText");
			wednesdayText.appendChild(doc.createTextNode("Wednesday"));
			wednesday.appendChild(wednesdayText);
			wednesday.setAttribute("availability", "unavailable");

			Element thursday = doc.createElement("day");
			Element thursdayText = doc.createElement("dayText");
			thursdayText.appendChild(doc.createTextNode("Thursday"));
			thursday.appendChild(thursdayText);
			thursday.setAttribute("availability", "unavailable");

			Element friday = doc.createElement("day");
			Element fridayText = doc.createElement("dayText");
			fridayText.appendChild(doc.createTextNode("Friday"));
			friday.appendChild(fridayText);
			friday.setAttribute("availability", "unavailable");

			Element saturday = doc.createElement("day");
			Element saturdayText = doc.createElement("dayText");
			saturdayText.appendChild(doc.createTextNode("Saturday"));
			saturday.appendChild(saturdayText);
			saturday.setAttribute("availability", "unavailable");

			Element sunday = doc.createElement("day");
			Element sundayText = doc.createElement("dayText");
			sundayText.appendChild(doc.createTextNode("Sunday"));
			sunday.appendChild(sundayText);
			sunday.setAttribute("availability", "unavailable");

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

			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void deleteCourse(String userEmail, String course) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {		//Go through the nodelist of users

				Node nNode = nList.item(temp);		//update temp node to current nodelist node

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {		//ensure that current node is an element and not whitespace

					Element eElement = (Element) nNode;
					if (userEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {	//if the passed user email matches the current element's email
						NodeList courseList = eElement.getElementsByTagName("class");	//new nodelist of all courses in the user
						for (int iCourse = 0; iCourse < courseList.getLength(); iCourse++) {	//go through all courses in the nodelist
							Node courseNode = courseList.item(iCourse);		//update temp node to current nodelist node
							if (courseNode.getNodeType() == Node.ELEMENT_NODE) {	//ensure that current node is an element and not whitespace
								Element courseElement = (Element) courseNode;		//create element from current node
								if (course.equals(courseElement.getTextContent())) {	//if the passed course matches the current course
									Node parentNode = courseElement.getParentNode();	//get the parent node of the course
									parentNode.removeChild(courseElement);			//then delete the child(the target course)
								}
							}
						}
					}
				}
			}
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void deleteAccount(String userEmail) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

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
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void updateEmail(String userEmail, String newEmail) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

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
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

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
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void setAvailability(String tutorEmail, String meetingDay, String availability) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (tutorEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {

						NodeList week = eElement.getElementsByTagName("day");
						for (int iWeek = 0; iWeek < week.getLength(); iWeek++) {
							Node day = week.item(iWeek);

							if (day.getNodeType() == Node.ELEMENT_NODE) {

								Element dayElement = (Element) day;
								if (meetingDay
										.equals(dayElement.getElementsByTagName("dayText").item(0).getTextContent())) {
									NamedNodeMap attr = day.getAttributes();
									Node dayAttr = attr.getNamedItem("availability");
									dayAttr.setTextContent(availability);
								}
							}
						}
					}
				}
			}
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void schedule(String studentEmail, String tutorEmail, String meetingDay, String classCode) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {		//go through nodelist of users

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (tutorEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {	//if passed tutor email matches the current node's email

						Element meetingWith = doc.createElement("meetingWith");
						meetingWith.appendChild(doc.createTextNode(studentEmail));		//
						meetingWith.setAttribute("classCode", classCode);
						NodeList week = eElement.getElementsByTagName("day");
						for (int iWeek = 0; iWeek < week.getLength(); iWeek++) {
							Node day = week.item(iWeek);

							if (day.getNodeType() == Node.ELEMENT_NODE) {

								Element dayElement = (Element) day;
								if (meetingDay
										.equals(dayElement.getElementsByTagName("dayText").item(0).getTextContent())) {
									day.appendChild(meetingWith);

								}
							}
						}
					}

					if (studentEmail.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {

						Element meetingWith = doc.createElement("meetingWith");
						meetingWith.appendChild(doc.createTextNode(tutorEmail));
						meetingWith.setAttribute("classCode", classCode);

						NodeList week = eElement.getElementsByTagName("day");
						for (int iWeek = 0; iWeek < week.getLength(); iWeek++) {
							Node day = week.item(iWeek);

							if (day.getNodeType() == Node.ELEMENT_NODE) {

								Element dayElement = (Element) day;
								if (meetingDay
										.equals(dayElement.getElementsByTagName("dayText").item(0).getTextContent())) {
									day.appendChild(meetingWith);

								}
							}
						}

					}

				}
			}
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void addClass(String email, String newClass, String grade) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			doc.getDocumentElement().normalize();

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
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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

	public static void ratings(String email, String rating) {
		try {			
			 /* 
			 * This top section takes the xml document, and puts it into a document builder 
			 * to allow for manipulation and editing
			 */
			String filepath = "src/main/gooberDatabase.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			doc.getDocumentElement().normalize();

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
			
			/*
			 * Writes the documentbuilder document into a readable xml document using the java transformer
			 */
			doc.normalize();
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
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
}

/*
 * @author Trevor Huval
 */

package main;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import main.Classes.classClass;
import main.UserSchedule.Day;
import main.UserSchedule.Meeting;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;

public class DOMreadXML {

	public static Boolean login(String email, String password) {

		try {

			/*
			 * This top section takes the xml document, and puts it into a document builder
			 * to allow for access and reading
			 */
			File fXmlFile = new File("src/main/gooberDatabase.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {		//go through user nodelist

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {	//if the passed email matches the current user's email then...
						if (password.equals(eElement.getElementsByTagName("password").item(0).getTextContent())) {	//if the passed password matches the current user's password then...
							return true;	//successful login and return true
						} else
							return false;	//otherwise failed login and return false
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static User returnUser(String email) {

		try {

			/*
			 * This top section takes the xml document, and puts it into a document builder
			 * to allow for access and reading
			 */
			File fXmlFile = new File("src/main/gooberDatabase.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {		//go through user nodelist

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {	//if passed email matches the current user then we begin building the user object...
						String firstName = eElement.getElementsByTagName("firstName").item(0).getTextContent();
						String lastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();	//by allocating the first name, last name, password,
						String password = eElement.getElementsByTagName("password").item(0).getTextContent();	//and if tutor or student to strings
						String role = eElement.getElementsByTagName("role").item(0).getTextContent();

						Classes classes = new Classes();
						NodeList classNodeList = eElement.getElementsByTagName("class");
						for (int temp2 = 0; temp2 < classNodeList.getLength(); temp2++) {
							classClass newClass = new classClass();
							Node classNode = classNodeList.item(temp2);

							if (classNode.getNodeType() == Node.ELEMENT_NODE) {

								Element classElement = (Element) classNode;

								newClass.className = classElement.getTextContent();
								newClass.grade = classElement.getAttribute("grade");
								classes.classList.add(newClass);
							}
						}

						Ratings ratings = new Ratings();
						ratings.numOfRatings = Integer.valueOf(eElement.getElementsByTagName("ratings").item(0)	//set the ratings object's number of ratings
								.getAttributes().getNamedItem("numOf").getTextContent());
						Integer newRating;
						NodeList ratingNodeList = eElement.getElementsByTagName("rating");
						for (int temp3 = 0; temp3 < ratingNodeList.getLength(); temp3++) {	//make a nodelist from all the rating elements in the user and go through the nodelist
							Node ratingNode = ratingNodeList.item(temp3);

							if (ratingNode.getNodeType() == Node.ELEMENT_NODE) {

								Element ratingElement = (Element) ratingNode;

								newRating = Integer.valueOf(ratingElement.getTextContent());	//set the newRating integer to the rating stored in the rating element
								ratings.ratingList.add(newRating);								//add the new rating to the arraylist of ratings in the ratings object
							}
						}

						UserSchedule schedule = new UserSchedule();
						NodeList dayNodeList = eElement.getElementsByTagName("day");
						for (int temp4 = 0; temp4 < dayNodeList.getLength(); temp4++) {		//make a nodelist from all the day elements in the user and go through the nodelist
							Day newDay = new Day();
							Node dayNode = dayNodeList.item(temp4);		//set a new day node from the current day node in the nodelist
							if (dayNode.getNodeType() == Node.ELEMENT_NODE) {

								Element dayElement = (Element) dayNode;

								newDay.dayName = dayElement.getElementsByTagName("dayText").item(0).getTextContent();	//set the new day object's name to the name of the day element

								NamedNodeMap attr = dayNode.getAttributes();
								Node dayAttr = attr.getNamedItem("availability");	//set the new day object's availability to the availability of the day node
								newDay.availability = dayAttr.getTextContent();

								NodeList meetingNodeList = dayElement.getElementsByTagName("meetingWith");
								for (int temp5 = 0; temp5 < meetingNodeList.getLength(); temp5++) {			//make a new nodelist of the meetingWith element and go through it
									Meeting newMeeting = new Meeting();
									Node meetingNode = meetingNodeList.item(temp5);		//set a new meeting node from the current meeting node in the nodelist

									if (meetingNode.getNodeType() == Node.ELEMENT_NODE) {
										Element meetingElement = (Element) meetingNode;
										newMeeting.meetingWith = meetingElement.getTextContent();			//set the new meeting object's 'meetingWith' email to the one in the 
										newMeeting.classCode = meetingElement.getAttribute("classCode");	//current meeting element, and its class code to the class code in the element
										newDay.meetingList.add(newMeeting);								//and add the new meeting into the new day object's arraylist of meetings
									}
								}
								schedule.week[temp4] = newDay;		//and set the current day of the week array's day object to the new day
							}
						}

						//using the builder pattern, pass all the parameters into the builder and build the user object
						User user = new User.userBuilder(email, firstName, lastName, password, role, ratings, classes, schedule).build();
						return user;	//and return the populated user object
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static User[] tutorsInClass(String classCode) {
		User[] tutorList = new User[100];	//declare an array of users that will be the list of tutors in a particular class, specified by the class code
		int listIndex = 0;

		try {

			/*
			 * This top section takes the xml document, and puts it into a document builder
			 * to allow for access and reading
			 */
			File fXmlFile = new File("src/main/gooberDatabase.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {		//go through the nodelist of users

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (eElement.getElementsByTagName("role").item(0).getTextContent().equals("Tutor")) {	//if the current user node is a tutor
						NodeList nList2 = eElement.getElementsByTagName("class");		//make a new nodelist of the classes the tutor has taken

						for (int temp2 = 0; temp2 < nList2.getLength(); temp2++) {	//go through the nodelist of classes

							Node nNode2 = nList2.item(temp2);

							if (nNode2.getNodeType() == Node.ELEMENT_NODE) {

								Element eElement2 = (Element) nNode2;
								if (eElement2.getTextContent().equals(classCode)) {			//if the current class matches the passed class code...
									tutorList[listIndex] = returnUser(eElement.getElementsByTagName("email").item(0).getTextContent());	//add a new user object returned from returnUser 
									listIndex++;																		//into the array and increase the index counter of the array
								}
							}
						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tutorList;
	}

	public static User[] listOfTutors() {
		User[] tutorList = new User[100];	//declare an array of type User to store all the tutors in the database
		int listIndex = 0;

		try {

			/*
			 * This top section takes the xml document, and puts it into a document builder
			 * to allow for access and reading
			 */
			File fXmlFile = new File("src/main/gooberDatabase.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {		//go through a nodelist of users

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (eElement.getElementsByTagName("role").item(0).getTextContent().equals("Tutor")) {	//if the current user is a tutor...
						tutorList[listIndex] = returnUser(eElement.getElementsByTagName("email").item(0).getTextContent());	//add add a new user object returned from returnUser 
						listIndex++;																	//into the array and increase the index counter of the array
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tutorList;
	}

	public static float avgRating(String email) {
		/*
		 * This function will return the average rating of a tutor stored in a float variable
		 */
		float retAvg = 0;
		try {

			/*
			 * This top section takes the xml document, and puts it into a document builder
			 * to allow for access and reading
			 */
			File fXmlFile = new File("src/main/gooberDatabase.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {	//go through nodelist of tutors

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {	//if the passed email matches the email of the current tutor...
						Node Ratings = eElement.getElementsByTagName("ratings").item(0);		//make a new ratings node from the ratings element of the tutor
						NodeList ratingNodeList = eElement.getElementsByTagName("rating");				//and make a nodelist of the ratings a tutor has

						for (int temp2 = 0; temp2 < ratingNodeList.getLength(); temp2++) {		//go through nodelist of ratings

							Node rating = ratingNodeList.item(temp2);	//new rating node from current rating in nodelist

							if (rating.getNodeType() == Node.ELEMENT_NODE) {

								Element ratingElement = (Element) rating;
								retAvg += Integer.valueOf(ratingElement.getTextContent());	//add the value of the current rating to the average rating
							}
						}

						NamedNodeMap attr = Ratings.getAttributes();
						Node nodeAttr = attr.getNamedItem("numOf");
						retAvg = retAvg / Integer.valueOf(nodeAttr.getTextContent());	//divide the average rating variable by the number of ratings a tutor has
						return retAvg;			//and return the average
					}
				}
			}

			return retAvg;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retAvg;
	}
}

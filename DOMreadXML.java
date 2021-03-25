package main;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;
import java.math.BigDecimal;

public class DOMreadXML {
	
	public static Boolean login(String email, String password) {
		
		try {

		    File fXmlFile = new File("src/main/gooberDatabase.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		            
		    //optional, but recommended
		    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		    doc.getDocumentElement().normalize();
		            
		    NodeList nList = doc.getElementsByTagName("User");
		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	if (password.equals(eElement.getElementsByTagName("password").item(0).getTextContent())) {
		            		return true;
		            	}
		            	else
		            		return false;
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

		    File fXmlFile = new File("src/main/gooberDatabase.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		            
		    //optional, but recommended
		    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		    doc.getDocumentElement().normalize();
		            
		    NodeList nList = doc.getElementsByTagName("User");
		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		            	String firstName = eElement.getElementsByTagName("firstName").item(0).getTextContent();
		            	String lastName = eElement.getElementsByTagName("lastName").item(0).getTextContent();
		            	String password = eElement.getElementsByTagName("password").item(0).getTextContent();
		            	String role = eElement.getElementsByTagName("role").item(0).getTextContent();
		            	Ratings ratings = new Ratings();
		            	ratings.numOfRatings = Integer.valueOf(eElement.getElementsByTagName("ratings").item(0).getAttributes().getNamedItem("numOf").getTextContent());
		            	//User user1 = new User.userBuilder(email, firstName, lastName, password, role, ratings);
		            	User user1 = new User.userBuilder(email, firstName, lastName, password, role, ratings).build();
		            	return user1;
		            }
		        }
		    }
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		
		return null;
		
	}

public static User[] tutorsInClass(String classCode) {
	User[] tutorList = new User[100];
	int listIndex = 0;
	
	try {

	    File fXmlFile = new File("src/main/gooberDatabase.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(fXmlFile);

	    doc.getDocumentElement().normalize();
	            
	    NodeList nList = doc.getElementsByTagName("User");
	            
	    for (int temp = 0; temp < nList.getLength(); temp++) {

	        Node nNode = nList.item(temp);
	                
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	            Element eElement = (Element) nNode;

	            if (eElement.getElementsByTagName("role").item(0).getTextContent().equals("Tutor")) {
	            	NodeList nList2 = eElement.getElementsByTagName("class");
	    	        
	    	        
	    	        for (int temp2 = 0; temp2 < nList2.getLength(); temp2++) {

	    		        Node nNode2 = nList2.item(temp2);
	    		        if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
	    		            Element eElement2 = (Element) nNode2;
	    		            if (eElement2.getTextContent().equals(classCode)){
	    		            	tutorList[listIndex] = returnUser(eElement.getElementsByTagName("email").item(0).getTextContent());
	    		            	listIndex++;
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
	User[] tutorList = new User[100];
	int listIndex = 0;
	
	try {

	    File fXmlFile = new File("src/main/gooberDatabase.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(fXmlFile);

	    doc.getDocumentElement().normalize();
	            
	    NodeList nList = doc.getElementsByTagName("User");
	            
	    for (int temp = 0; temp < nList.getLength(); temp++) {

	        Node nNode = nList.item(temp);
	                
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	            Element eElement = (Element) nNode;

	            if (eElement.getElementsByTagName("role").item(0).getTextContent().equals("Tutor")) {
	            	tutorList[listIndex] = returnUser(eElement.getElementsByTagName("email").item(0).getTextContent());
	            	listIndex++;
	            }


	        }
	    }
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	
	
	return tutorList;
}
	
public static float round(float d, int decimalPlace){
	if(d == 0)
	{
		return 0;
	}
	return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();     
}	
	
public static float avgRating(String email) {
	float retAvg = 0;
		try {
			
		    File fXmlFile = new File("src/main/gooberDatabase.xml");
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		            
		    //optional, but recommended
		    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		    doc.getDocumentElement().normalize();
		            
		    NodeList nList = doc.getElementsByTagName("User");
		    
		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {

		        Node nNode = nList.item(temp);
		                
		                
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		            Element eElement = (Element) nNode;
		            if (email.equals(eElement.getElementsByTagName("email").item(0).getTextContent())) {
		    	        Node Ratings = eElement.getElementsByTagName("ratings").item(0);
		    	        NodeList nList2 = eElement.getElementsByTagName("rating");
		    	        
		    	        
		    	        for (int temp2 = 0; temp2 < nList2.getLength(); temp2++) {

		    		        Node nNode2 = nList2.item(temp2);
		    		                
		    		                
		    		        if (nNode2.getNodeType() == Node.ELEMENT_NODE) {

		    		            Element eElement2 = (Element) nNode2;
		    		            retAvg += Integer.valueOf(eElement2.getTextContent());
		    		        }
		    		    }
		    	        
		    	        
		    	        
		            	NamedNodeMap attr = Ratings.getAttributes();
		                Node nodeAttr = attr.getNamedItem("numOf");
		    	        retAvg = retAvg / Integer.valueOf(nodeAttr.getTextContent());
		    	        //return round(retAvg,2);
		    	        return retAvg;
		            }
		        }
		    }
		    
		    //return round(retAvg,2);
		    return retAvg;
		    
		    
		    
		    
		    } catch (Exception e) {
		    e.printStackTrace();
		    }
		//return round(retAvg,2);
		return retAvg;
	}
	
//	public static void main(String argv[]) {
//
//	    try {
//
//	    File fXmlFile = new File("C:/Users/huval/eclipse-workspace/Goober/src/main/gooberDatabase.xml");
//	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//	    Document doc = dBuilder.parse(fXmlFile);
//	            
//	    //optional, but recommended
//	    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
//	    doc.getDocumentElement().normalize();
//
//	    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//	            
//	    NodeList nList = doc.getElementsByTagName("User");
//	            
//	    System.out.println("----------------------------");
//
//	    for (int temp = 0; temp < nList.getLength(); temp++) {
//
//	        Node nNode = nList.item(temp);
//	                
//	        System.out.println("\nCurrent Element :" + nNode.getNodeName());
//	                
//	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//	            Element eElement = (Element) nNode;
//
//	            System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
//	            System.out.println("Days available : " + eElement.getElementsByTagName("daysAvailable").item(0).getTextContent());
//	            System.out.println("Courses : " + eElement.getElementsByTagName("courses").item(0).getTextContent());
//
//	        }
//	    }
//	    } catch (Exception e) {
//	    e.printStackTrace();
//	    }
//	  }
	
}

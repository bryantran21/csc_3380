package main;

import java.util.ArrayList;

import main.Classes.classClass;
import main.UserSchedule.Meeting;

public class User {
	/*
	 * The user class which stores all the data a user would have in the database into an object that can be used and manipulated in the frontend
	 */
	
	private final String email;
	private final String firstName;
	private final String lastName;
	private final String password;
	private final String role;
	private Ratings ratings;
	private Classes classes;
	private UserSchedule schedule;
	
	private User(userBuilder builder) {		//Builder Pattern
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.password = builder.password;
		this.role = builder.role;
		this.ratings = builder.ratings;
		this.classes = builder.classes;
		this.schedule = builder.schedule;
	}
	
	public String getEmail() {		//email getter
		return email;
	}
	
	public String getFirstName() {		//first name getter
		return firstName;
	}
	
	public String getLastName() {		//last name getter
		return lastName;
	}
	
	public String getPassword() {		//password getter
		return password;
	}
	
	public String getRole() {		//role getter
		return role;
	}
	
	public Ratings getRatings() {		//ratings getter
		return ratings;
	}
	
	public Classes getClasses() {		//classes getter
		return classes;
	}
	
	public UserSchedule getSchedule() {		//schedule getter
		return schedule;
	}
	
	public ArrayList<classClass> getclassList() {		//arraylist of classes getter
		return classes.classList;
	}
	
	public ArrayList<Meeting> getMeetingList(String day) {		//arraylist of meetings getter for a specific day of the week
		int index = 0;
		
		switch (day) {		//this switch will get the index that a particular day is stored in the week array
		case "Monday":		//the week array keeps Monday at index 0, Tuesday at 1,...Sunday at 6
			index = 0;
			break;
		case "Tuesday":
			index = 1;
			break;
		case "Wednesday":
			index = 2;
			break;
		case "Thursday":
			index = 3;
			break;
		case "Friday":
			index = 4;
			break;
		case "Saturday":
			index = 5;
			break;
		case "Sunday":
			index = 6;
			break;
		}
		
		return schedule.week[index].meetingList;
	}
	
	
	public static class userBuilder {
		private final String email;
		private final String firstName;
		private final String lastName;
		private final String password;
		private final String role;
		private Ratings ratings;
		private Classes classes;
		private UserSchedule schedule;
		
		/*
		 * User Builder
		 */
		
		public userBuilder(String email, String firstName, String lastName, String password, String role, Ratings ratings, Classes classes, UserSchedule schedule) {
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.role = role;
			this.ratings = ratings;
			this.classes = classes;
			this.schedule = schedule;
		}
		
		public userBuilder addRating(int newRating) {	//adds a rating to the user

			this.ratings.ratingList.add(newRating);
			this.ratings.numOfRatings++;
			return this;
		}
		
		public userBuilder addClass(String className, String grade) {	//adds a class to the user
			classClass newClass = new classClass();
			newClass.className = className;
			newClass.grade = grade;
			this.classes.classList.add(newClass);
			this.classes.numOfClasses++;
			return this;
		}
		
		public userBuilder addSchedule(UserSchedule schedule) {		//adds a schedule to the user
			this.schedule = schedule;
			return this;
		}
		
		
		public User build() {		//builder
			User user = new User(this);		
			return user;
		}
		
	}
}

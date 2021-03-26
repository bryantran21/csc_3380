package main;

import main.Classes.classClass;
//import main.UserSchedule.Day;

public class User {
	
	private final String email;
	private final String firstName;
	private final String lastName;
	private final String password;
	private final String role;
	private Ratings ratings;
	private Classes classes;
	private UserSchedule schedule;
	
	private User(userBuilder builder) {
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.password = builder.password;
		this.role = builder.role;
		this.ratings = builder.ratings;
		this.classes = builder.classes;
		this.schedule = builder.schedule;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
	public Ratings getRatings() {
		return ratings;
	}
	
	public Classes getClasses() {
		return classes;
	}
	
	public UserSchedule getSchedule() {
		return schedule;
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
		//String daysAvailable[];
		//String courses[];
		//String enrolledUsers[];
		
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
		
		public userBuilder addRating(int newRating) {

			this.ratings.ratingList.add(newRating);
			this.ratings.numOfRatings++;
			return this;
		}
		
		public userBuilder addClass(String className, String grade) {
			classClass newClass = new classClass();
			newClass.className = className;
			newClass.grade = grade;
			this.classes.classList.add(newClass);
			this.classes.numOfClasses++;
			return this;
		}
		
		public userBuilder addSchedule(UserSchedule schedule) {
			//Day newDay = new Day();
			this.schedule = schedule;
			return this;
		}
		
		
		public User build() {
			User user = new User(this);
			return user;
		}
		
	}
}

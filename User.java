package main;


public class User {
	
	private final String email;
	private final String firstName;
	private final String lastName;
	private final String password;
	private final String role;
	private final Ratings ratings;
	
	private User(userBuilder builder) {
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.password = builder.password;
		this.role = builder.role;
		this.ratings = builder.ratings;
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
	
	
	
	public static class userBuilder {
		private final String email;
		private final String firstName;
		private final String lastName;
		private final String password;
		private final String role;
		private Ratings ratings;
		//String daysAvailable[];
		//String courses[];
		//String enrolledUsers[];
		
		public userBuilder(String email, String firstName, String lastName, String password, String role, Ratings ratings) {
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.role = role;
			this.ratings = ratings;
		}
		
		public userBuilder addRating(int newRating) {

			this.ratings.ratingList[this.ratings.numOfRatings-1] = newRating;
			this.ratings.numOfRatings++;
			return this;
		}
		
		public User build() {
			User user = new User(this);
			return user;
		}
		
	}
}

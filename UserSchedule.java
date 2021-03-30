package main;

import java.util.ArrayList;

public class UserSchedule {
	/*
	 * Schedule class that contains an array of the days in the week
	 */
	
	public static class Meeting {
		/*
		 * Each meeting has the class it is for, and who the email of the user they are meeting with
		 */
		String classCode = "";
		String meetingWith = "";
	}
	
	public static class Day {
		/*
		 * Each day has the name of the day, the availability on that day, and an arraylist of meetings on that day
		 */
		String dayName = "";
		String availability = "unavailable";
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
	}
	
	Day week[] = new Day[7];
	
}

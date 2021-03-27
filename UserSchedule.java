package main;

import java.util.ArrayList;

public class UserSchedule {
	
	public static class Meeting {
		String classCode = "";
		String meetingWith = "";
	}
	
	public static class Day {
		String dayName = "";
		String availability = "";
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
	}
	
	Day week[] = new Day[7];
	
}

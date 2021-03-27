package main;

import java.util.ArrayList;

public class UserSchedule {
	
	public static class Day {
		String dayName = "";
		String availability = "";
		ArrayList<String> meetingList = new ArrayList<String>();
	}
	
	Day week[] = new Day[7];
	
}

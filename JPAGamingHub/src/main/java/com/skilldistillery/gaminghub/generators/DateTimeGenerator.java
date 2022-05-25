package com.skilldistillery.gaminghub.generators;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DateTimeGenerator {
	
	int startYear = 2017;
	int startMonth = 3;
	
	int endYear = 2022;
	int endMonth = 5;
	
	int startTime = 1;
	int endTime = 23;
	
	int[] months30DaysArr = {04, 06, 9, 11};
	int[] months31DaysArr = {01, 03, 5, 7, 8, 10, 12};
	
	String dateDelim = "-";
	String timeDelim = ":";
	String separator = " ";
	
	int numToGenerate = 1144;
	
	boolean duplicate = true;
	
	public static void main(String[] args) {
		DateTimeGenerator app = new DateTimeGenerator();
		app.run();
	}

	private void run() {
		
		List<Integer> months30Days = new ArrayList<>();
		for (int x : months30DaysArr) {
			months30Days.add(x);
		}
		
		List<Integer> months31Days = new ArrayList<>();
		for (int x : months31DaysArr) {
			months31Days.add(x);
		}
		
		Set<String> output = new LinkedHashSet<>();
		
		for (int i = 0; i < numToGenerate; i++) {
			
			StringBuilder sb = new StringBuilder();
			
			// random year within range and append
			int year = (int) (Math.random() * (endYear - startYear + 1)) + startYear;
			
			// random month within range
			int month = 0;
			if (year == startYear) {
				month =  (int) ( (Math.random() * (12 - startMonth) + 1) + startMonth);
			} else if (year == endYear){
				month = (int) ( (Math.random() * endMonth) + 1);
			} else {
				month = (int) ( (Math.random() * 12) + 1);
			}
			
			
			// random day within month
			int day = 0;			
			if (months30Days.contains(month)) {
				day = (int) ( (Math.random() * 30) + 1);
			} else if (months31Days.contains(month)) {
				day = (int) ( (Math.random() * 31) + 1);
			} else {
				day = (int) ( (Math.random() * 28) + 1);
			}
			
			// append year
			sb.append(year + dateDelim);
			
			// add leading 0 to month and append
			if (month < 10) {
				sb.append("0");
			}
			sb.append(month + dateDelim);
			
			// add leading 0 to day and append
			if (day < 10) {
				sb.append("0");
			}
			sb.append(day + separator);
			
			// random hour
			int hour = (int) (Math.random() * 24);
			
			// add leading 0 and append
			if (hour < 10) {
				sb.append("0");
			}
			sb.append(hour + timeDelim);
			
			// random min
			int min = (int) ( (Math.random() * 60));
			
			// add leading 0 and append
			if (min < 10) {
				sb.append("0");
			}
			sb.append(min + timeDelim);
			
			// random sec
			int sec = (int) ( (Math.random() * 60));
			
			// add leading 0 and append
			if (sec < 10) {
				sb.append("0");
			}
			sb.append(sec);
			
			if (year < startYear || year > endYear
					|| month < 1 || month > 12
					|| day < 1 || day > 31
					|| hour < 0 || hour > 23
					|| min < 0 || min > 59
					|| sec < 0 || sec > 59) {
				System.err.println("INVALID: " + sb.toString());
			}
			
			output.add(sb.toString());
		}
		
		for (String str: output) {
			if (duplicate) {
				System.out.println(str);			
			}
			System.out.println(str);
		}
		
		

		
	}
}

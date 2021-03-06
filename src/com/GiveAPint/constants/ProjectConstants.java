package com.GiveAPint.constants;

import java.text.SimpleDateFormat;


public class ProjectConstants {

	// Test data for creating dummy users, used by CreateObjects class.
	// Make sure that values are changed before making a new call, i.e; while
	// inserting another new user.
	public static final String firstName = "apachetest1";
	public static final String lastName = "apache";
	public static final double xPoint = 35.032384;
	public static final double yPoint = -118.285385;
	public static final String userName = "yerra@usc.edu";
	public static final String gender = "Male";
	public static final String passcode = "abhipardhu";
	public static final String phone = "213-298-0474";
	public static final String healthStatus = "Good";
	public static final String bloodGroup = "AB+";
	// Objects and fields related to date.
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	public static final String dob = "22-01-1994 10:20:56";
	public static final String nextAvailableDate = "10-01-2016 12:00:00";
	public static final String donatedDate = "16-09-2016 12:00:00";
	public static final String status = "Pending";
	public static final String dummyToken = "dummyTokenForDD";

	// pattern matching regex validation
	public static final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String token = "5po6gr6lorf48";
	public static final Integer sampleUserId = 1;

}

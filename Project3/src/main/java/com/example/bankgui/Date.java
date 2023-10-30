package com.example.bankgui;
/**
 * The Date class represents a date with a year, month, and day
 * 
 * @author Krishma Kapoor, Daniel Elwell
 */
public class Date implements Comparable<Date>
{
	private int year;
	private int month;
	private int day;

	public static final int JANUARY = 1;
	public static final int FEBRUARY = 2;
	public static final int MARCH = 3;
	public static final int APRIL = 4;
	public static final int MAY = 5;
	public static final int JUNE = 6;
	public static final int JULY = 7;
	public static final int AUGUST = 8;
	public static final int SEPTEMBER = 9;
	public static final int OCTOBER = 10;
	public static final int NOVEMBER = 11;
	public static final int DECEMBER = 12;

	public static final int MAX_DAY_JMMJAOD = 31;
	public static final int MAX_DAY_AJSN = 30;
	public static final int MAX_DAY_FEBRUARY = 28;
	public static final int MAX_DAY_FEBRUARY_LEAP_YEAR = 29;

	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100;
	public static final int QUATERCENTENNIAL = 400;

	/**
	 * Constructor for initializing the instance variables
	 * 
	 * @param year the date's year
	 * @param month the date's month
	 * @param day the date's day
	 */

	public Date(int year, int month, int day)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	} 

	/**
	 * Function for checking whether this instance's year is a leap year
	 * 
	 * @return whether this instance's year is a leap year
	 */
	private boolean isLeapYear()
	{
		if (year % QUADRENNIAL != 0)
			return false;

		if(year % CENTENNIAL != 0)
			return true;

		if(year % QUATERCENTENNIAL == 0)
			return true;
			
		return false;
	}
		
	/**
	 * Getter for the year
	 * @return the date's year
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Getter for the month
	 * @return the date's month
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * Getter for the day
	 * @return the date's day
	 */
	public int getDay()
	{
		return day;
	}

	/**
	 * Function for checking whether this instance's date is valid, meaning
	 * its month is in the range [1, 12] and it has an appropriate number of days
	 * 
	 * @return whether this instance's date is valid
	 */
	public boolean isValid()
	{
		if (month < JANUARY || month > DECEMBER)
			return false;
	
		if (day <= 0 || day > MAX_DAY_JMMJAOD)
			return false;

		if ((month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) && day > MAX_DAY_AJSN) //only 30 days if April, June, September, and November.
			return false;

		if (month == FEBRUARY && (day > MAX_DAY_FEBRUARY_LEAP_YEAR || (!isLeapYear() && day > MAX_DAY_FEBRUARY)))
			return false;

		return true;
	}
	
	/**
	 * compareTo method override
	 */
	@Override
	public int compareTo(Date otherDate) 
	{
		if (this.year != otherDate.year) 
			return Integer.compare(this.year, otherDate.year);
		else if (this.month != otherDate.month)
			return Integer.compare(this.month, otherDate.month);
		else
			return Integer.compare(this.day, otherDate.day);
	}

	/**
	 * equals method override
	 */
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof Date))
			return false;
			
		Date otherDate = (Date)other;
		return day == otherDate.day && month == otherDate.month && year == otherDate.year;
	}

	/**
	 * toString method override
	 */
	@Override
	public String toString()
	{
		return month + "/" + day + "/" + year;		
	}

	/**
	 * Main function for testing the isValid method
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		//run methods for testing the isValid()

		Date test1_validDateRange = new Date(2024, 35, 25);
		boolean test1 = test1_validDateRange.isValid(); //Valid month and year, not day
		System.out.println(test1); //Expected Output:False

		Date test2_FebLeapYear = new Date(2021, 2, 29);
		boolean test2 = test2_FebLeapYear.isValid(); //There are not 29 days in a non-leap year
		System.out.println(test2); //Expected Output:False

		Date test3_validMonthRange = new Date(2024, 13, 20);
		boolean test3 = test3_validMonthRange.isValid(); //The month is not within the range
		System.out.println(test3); //Expected Output:False
	}
}   






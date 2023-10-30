package com.example.bankgui;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit class for testing the isValid method in Date
 * 
 * @author Daniel Elwell
 */
public class JUnitDate
{
	/**
	 * Tests the isValid method for a normal, valid date
	 */
	@Test
	public void testValidNormal()
	{
		Date validNormal = new Date(2004, 10, 31);
		assertEquals("Valid Date (normal): " + validNormal.toString(), true, validNormal.isValid());
	}

	/**
	 * Tests the isValid method for a valid leap year date
	 */
	@Test
	public void testValidLeapYear()
	{
		Date validLeapYear = new Date(2020, 2, 29);
		assertEquals("Valid Date (leap year): " + validLeapYear.toString(), true, validLeapYear.isValid());	
	}

	
	/**
	 * Tests the isValid method for an out of range date
	 */
	@Test
	public void testInvalidOutOfRange1()
	{
		Date invalidDayOutOfRange = new Date(1999, 9, 31);
		assertEquals("Invalid date (out of range): " + invalidDayOutOfRange.toString(), false, invalidDayOutOfRange.isValid());
	}
	
	/**
	 * Tests the isValid method for an out of range date
	 */
	@Test
	public void testInvalidOutOfRange2()
	{
		Date invalidDayOutOfRange2 = new Date(1974, 10, 32);
		assertEquals("Invalid date (out of range): " + invalidDayOutOfRange2.toString(), false, invalidDayOutOfRange2.isValid());
	}
	
	/**
	 * Tests the isValid method for an out of range date
	 */
	@Test
	public void testInvalidOutOfRange3()
	{
		Date invalidDayOutOfRange3 = new Date(1974, 2, 30);
		assertEquals("Invalid date (out of range): " + invalidDayOutOfRange3.toString(), false, invalidDayOutOfRange3.isValid());
	}
	
	/**
	 * Tests the isValid method for an out of range date
	 */
	@Test
	public void testInvalidNotLeapYear()
	{
		Date invalidNotLeapYear = new Date(1981, 2, 29);
		assertEquals("Invalid date (not leap year): " + invalidNotLeapYear.toString(), false, invalidNotLeapYear.isValid());
	}
	
	/**
	 * Tests the isValid method for an out of range date
	 */
	@Test
	public void testInvalidSkippedLeapYear()
	{
		Date invalidSkippedLeapYear = new Date(1900, 2, 29);
		assertEquals("Invalid date (skipped leap year): " + invalidSkippedLeapYear.toString(), false, invalidSkippedLeapYear.isValid());
	}
}
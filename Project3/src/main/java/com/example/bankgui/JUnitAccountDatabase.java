package com.example.bankgui;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * JUnit class for testing the close method in AccountDatabase
 * 
 * @author Daniel Elwell
 */
public class JUnitAccountDatabase 
{
	/**
	 * Tests the close method
	 */
	@Test
	public void testClose()
	{
		AccountDatabase database = new AccountDatabase();

		Account realAcc1, realAcc2;
		Account fakeAcc;
		try
		{
			realAcc1 = new CollegeChecking(new Profile("Daniel", "Elwell", new Date(2004, 10, 19)), 1000.0, 0);
			realAcc2 = new MoneyMarket(new Profile("John", "Doe", new Date(1999, 3, 2)), true, 3000.0);

			fakeAcc = new MoneyMarket(new Profile("Fake", "Person", new Date(1990, 4, 5)), true, 2500.0);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return;
		}

		database.open(realAcc1);
		database.open(realAcc2);

		assertEquals("Closing real account: ", true, database.close(realAcc1));
		assertEquals("Closing fake account: ", false, database.close(fakeAcc));
	}
}

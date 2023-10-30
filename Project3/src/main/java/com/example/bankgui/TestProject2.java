package com.example.bankgui;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Main class for running JUnit tests
 *
 * @author Daniel Elwell
 */
public class TestProject2
{
	/**
	 * Main method, runs JUnit tests
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(JUnitDate.class, JUnitAccountDatabase.class);

		for(Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}

		System.out.println("JUnit tests were successful: " + result.wasSuccessful());
	}
}

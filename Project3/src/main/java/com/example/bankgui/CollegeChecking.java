package com.example.bankgui;

/**
 * The CollegeChecking class extends the Checking class; includes one instance variable only, and defines the
 * constants for interest rate and fee.
 * 
 * @author Krishma Kapoor
 */
public class CollegeChecking extends Checking {
    
	private int campusCode;

	/**
	 * Construtor for setting values
     * @param profile the account holder profile
     * @param balance the account's initial balance
	 * @param code the account's campus code
	 * @throws Exception if the campus code was invalid
	 */
	public CollegeChecking(Profile profile, double balance, int code) throws Exception
	{
		super(profile, balance);

		if(code < 0 || code > 2)
			throw new Exception("Invalid campus code.");

		campusCode = code;
	}

	/**
	 * Checks what campus the user is part of
	 * @return String, the campus name
	 */

	private String campusString(){
		if (campusCode == 0){
			return "NEW_BRUNSWICK";
		}

		else if (campusCode == 1){
			return "NEWARK";

		}

		else{
			return "CAMDEN";

		}
	}
	
	/**
     * calculates the monthly fee in a College Checking account
     * @return the monthly fee  
     */

	@Override
    public double monthlyFee() {
		 return 0.0; //no monthly fee for college checking account
	}

	/**
     * updates the value of the balance after the monthly interest
     */

	@Override
    public void update() {
      balance += monthlyInterest();
     
    }

	/**
	 * @return String, prints a user's account status
	 */
	@Override
    public String toString() {
      // Checking::Jason Brown 3/31/1998::Balance $1,200.00
      return "College Checking::" + holder.printProfile() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + campusString();
    }


}

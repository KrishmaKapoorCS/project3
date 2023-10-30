package com.example.bankgui;

/**
 * The Savings class extends the Account class; includes one instance variable only, and defines the constants for
 * interest rate and fee.
 * 
 * @author Krishma Kapoor
 */
public class Savings extends Account {
    
	protected boolean isLoyal;
	final double monthlyFee = 25;
    private double interestRate = 0.04 / 12 ; //annual interest rate divided by 12 months

	/**
	 * Constructor for seting values
     * @param profile the account holder profile
     * @param bal the account's initial balance
	 * @param isLoyal whether the customer is loyal
	 */
	public Savings(Profile profile, double bal, boolean isLoyal)
	{
		super(profile, bal);
		this.isLoyal = isLoyal;
	}

	/**
     * calculates the monthly interest in a Savings account
     *@return the monthly interest
     */

    @Override
    public double monthlyInterest() 
    {
        final double newRate = 0.0025; //for loyalty status
        if(isLoyal)
        {
            interestRate += newRate;
        }
        return interestRate * balance;
    }

	
	/**
     * calculates the monthly fee in a Savings account
     * @return the monthly fee  
     */
    @Override
    public double monthlyFee() 
    {
        final int balanceMin = 500;
        if(balance >= balanceMin)
        {
            return 0.0;
        }

        return monthlyFee;
    }

	@Override
	public void update() {
		balance += monthlyInterest();
		balance -= monthlyFee();
		
	}

	/**
	 * helper method to print loyalty status
	 * @return String
	 */

	protected String loyalString(){
		if (isLoyal){
			return "::is loyal";

		}
		else{
			return "";
		}
	}

	/**
	 * returns the String indicating the user's account type, their profile info,
	 * the balance, their loyalty status, and if they made any withdrawls
	 */
	@Override
	public String toString() {
		
		return "Savings::" + holder.printProfile() + "::" + "Balance $" + String.format("%,.2f", balance) + loyalString();
	}
}
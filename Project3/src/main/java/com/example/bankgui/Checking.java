package com.example.bankgui;

/**
 * The Checking class extends the Account class without defining any additional instance variable, but defines the
 * constants for interest rate and fee.
 * 
 * @author Krishma Kapoor
 */
public class Checking extends Account {

    final double monthlyFee = 12;
    final double interestRate = (0.01)/12; //annual interest rate divided by months in a year
    
    /**
     * Constructor for setting values
     * @param profile the account holder profile
     * @param bal the account's initial balance
     */
    public Checking(Profile profile, double bal)
    {
        super(profile, bal);
    }

    /**
     * calculates the monthly interest in a Checking account
     *@return the monthly interest  
     */

    @Override
    public double monthlyInterest() 
    {
        return interestRate * balance;
    }

     /**
     * calculates the monthly fee in a Checking account
     * @return the monthly fee  
     */

    @Override
    public double monthlyFee() 
    {
        final int balanceMin = 1000;
        if(balance >= balanceMin)
        {
            return 0.0;
        }

        return monthlyFee;
    }

    /**
     * equals method override
     * @param o the object to compare to
     * @return whether the objects are equal
     */
    @Override
    public boolean equals(Object o)
    {
        if(this.getClass().isAssignableFrom(o.getClass()) || o.getClass().isAssignableFrom(this.getClass()))
        {
            Account acc = (Account)o;
            return this.holder.compareTo(acc.holder) == 0;
        }

        return this.getClass().getName().equals(o.getClass().getName());
    }

    /**
     * updates the value of the balance after the monthly interest 
     * and monthly fee is applied
     */
    @Override
    public void update() {
      balance += monthlyInterest();
      balance -= monthlyFee();
    }

    /**
	 * @return String, prints a user's account status
	 */
    @Override
    public String toString()
    {
        return "Checking::" + holder.printProfile() + "::" + "Balance $" + String.format("%,.2f", balance);
    }
    
}
package com.example.bankgui;

/**
 * The MoneyMarket class extends the Savings class; includes one instance variable only, and defines the constants
 * for interest rate and fee.
 * 
 * @author Krishma Kapoor
 */
public class MoneyMarket extends Savings {
	private int withdrawal; //number of withdrawals
    final double monthlyFee = 25;
    private double interestRate = 0.045/ 12; //annual interest rate divided by 12 months
    final int balanceMin = 2000;
    final int numWithdrawal = 3;
    final int withdrawalFee = 10;
	

	/**
     * Constructor for setting values
     * @param profile the account holder profile
     * @param initial whether this is an initial creation of the account, determines whether we check the balance
     * @param bal the account's initial balance
	 * @throws Exception when bal is less than $2000 since that is the min deposit needed
     */
	public MoneyMarket(Profile profile, boolean initial, double bal) throws Exception
	{
		super(profile, bal, true);

		if(initial && bal < 2000.0)
			throw new Exception("Minimum of $2000 to open a Money Market account.");
	}

	/**
	 * Calculates the monthly interest 
	 * @return double value that indicates the interest rate
	 */
    
	@Override
    public double monthlyInterest() 
    {
		double newRate = interestRate + (0.0025)/12; //automatic 0.25% added for default loyal customer status

        return newRate * balance;
    }

	/**
	 * Returns true when the amount the user needs is succesfully withdrawn
	 * Returns false otherwise
	 * Alters the balance if money is succefully withdrawn and charges a fee if this method is used after 3x
	 * @param amount indicates the amount of money the user intends to withdraw
	 * @return boolean value that indicates whether or not the amount is successfully withdrawn 
	 */

	@Override
    public boolean withdraw(double amount)
    {
        if(amount > balance)
        {
            return false;
        }

        balance -= amount;
		withdrawal++; //update how many withdrawels user has made

        if(withdrawal > numWithdrawal)
        {
            balance -= withdrawalFee;

        }

		if(balance < balanceMin)
        {
            isLoyal = false;
        }

        return true;
    }

	/**
	 * returns the String indicating the user's account type, their profile info,
	 * the balance, their loyalty status, and if they made any withdrawls
	 */

	@Override
    public String toString() {
      
      return "Money Market::Savings::" + holder.printProfile()+ "::" +
	  "Balance $" + String.format("%,.2f", balance) + loyalString() + "::" + "withdrawl: " + 
	  withdrawal;
     
    }
}

	
    


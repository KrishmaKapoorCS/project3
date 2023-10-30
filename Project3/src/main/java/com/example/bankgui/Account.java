package com.example.bankgui;

/**
 * The Account class is an abstract class that is the general type of the other
 * account types; each account has a profile that uniquely identifies the account holder.
 * 
 * @author Krishma Kapoor
 */
public abstract class Account implements Comparable<Account> 
{
    protected Profile holder;
    protected double balance;

    /**
     * Abstract function for getting the monthly interest
     * @return the monthly interest
     */
    public abstract double monthlyInterest();
    /**
     * Abstract function for getting the monthly fee
     * @return the monthly fee
     */
    public abstract double monthlyFee();
    /**
     * Abstract function for applying the monthly fees and interest rates
     */
    public abstract void update();

    /**
     * Getter method for balance
     * @return the balance
     */

     
    public double getBalance()
    {
        return balance;
    }

    /**
     * Withdraws money from the account
     * @param amount the amount to withdraw
     * @return whether or not the account contained enough money to withdraw
     */
    public boolean withdraw(double amount)
    {
        if(amount > balance)
            return false;
        
        balance -= amount;
        return true;
    }

    /**
     * Deposits money into the account
     * @param amount the amount to deposit
     */
    public void deposit(double amount)
    {
        balance += amount;
    }

    /**
     * compareTo override
     */
    @Override
    public int compareTo(Account o) 
    {
        if(this.getClass().equals(o.getClass()))
        {
            return this.holder.compareTo(o.holder);
        }

        return this.getClass().getName().compareTo(o.getClass().getName());
    }

    /**
     * equals override
     */
    @Override 
    public boolean equals(Object o)
    {
        return compareTo((Account)o) == 0;
    }

    /**
     * Gets a string containing the monthly fees and interest rate
     * @return the string containing the fees
     */
    public String feesString()
    {
        if(monthlyFee() > 0.0)
            return toString() + "::fee $" + String.format("%,.2f", monthlyFee()) + "::monthly interest $" + String.format("%,.2f", monthlyInterest());
        else
            return toString() + "::monthly interest $" + String.format("%,.2f", monthlyInterest());
    }

    /**
     * Constructor for setting values
     * @param profile the account holder profile
     * @param bal the account's initial balance
     */
    public Account(Profile profile, double bal)
    {
        holder = profile;
        balance = bal;
    }
}
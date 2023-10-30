package com.example.bankgui;

/**
 * The AccountDatabase stores a list of all of the opened accounts,
 * with methods to modify the list and the accounts in it
 * 
 * @author Daniel Elwell
 */
public class AccountDatabase 
{
	private Account [] accounts; //list of various types of accounts
	private int numAcct; //number of accounts in the array

	private static final int NOT_FOUND = -1;

	/**
	 * Searches for an account in the database
	 * @param account the account to search for
	 * @return the index of the account in the array, or NOT_FOUND of it is not found
	 */
	private int find(Account account) 
	{
		for(int i = 0; i < numAcct; i++)
			if(account.equals(accounts[i]))
				return i;

		return NOT_FOUND;
	}

	/**
	 * Increases the capacity of the accounts array by 4
	 */
	private void grow()
	{
		Account[] newArr = new Account[accounts.length + 4];
		for(int i = 0; i < numAcct; i++)
			newArr[i] = accounts[i];
		
		accounts = newArr;
	}

	/**
	 * Sorts the database by account type and holder profile
	 */
	private void sort()
	{
		int i, j;
		Account key;
		for (i = 1; i < numAcct; i++) 
		{
			key = accounts[i];
			j = i - 1;

			while (j >= 0 && accounts[j].compareTo(key) > 0) 
			{
				accounts[j + 1] = accounts[j];
				j = j - 1;
			}
			accounts[j + 1] = key;
		}
	}

	/**
	 * Returns whether the database is empty
	 * @return whether the database is empty
	 */
	public boolean empty()
	{
		return numAcct == 0;
	}

	/**
	 * Returns whether or not the database contains a given account
	 * @param account the account to search for
	 * @return whether the account exists in the database
	 */
	public boolean contains(Account account)
	{
		int idx = find(account);
		return idx != NOT_FOUND && accounts[idx].getClass().equals(account.getClass());
	}

	/**
	 * Adds an account to the database if one doesn't already exist
	 * @param account the account to add
	 * @return true if the account was added successfully
	 */
	public boolean open(Account account)
	{
		if(find(account) != NOT_FOUND)
			return false;

		if(accounts.length == numAcct)
			grow();
		
		accounts[numAcct++] = account;
		return true;
	}

	/**
	 * Removes an account from the database if it exists
	 * @param account the account to remove
	 * @return true if the account was added successfully
	 */
	public boolean close(Account account)
	{
		if(!contains(account))
			return false;

		int idx = find(account);
		for(int i = idx + 1; i < accounts.length; i++)
			accounts[i - 1] = accounts[i];

		numAcct--;
		return true;
	}

	/**
	 * Withdraws funds from an account
	 * @param account the account to withdraw from, with a balance of the desired withdraw amount
	 * @return whether the account contained sufficient funds to withdraw
	 */
	public boolean withdraw(Account account)
	{
		if(!contains(account))
			return false;

		int idx = find(account);
		return accounts[idx].withdraw(account.getBalance());
	}

	/**
	 * Deposits money into an account
	 * @param account the account to deposit into, with a balance of the desired deposit amount
	 */
	public void deposit(Account account)
	{
		if(!contains(account))
			return;
		
		int idx = find(account);
		accounts[idx].deposit(account.getBalance());	
	}

	/**
	 * Prints all of the accounts in the database, sorted by type and holder
	 */
	public void printSorted()
	{
		sort();

		for(int i = 0; i < numAcct; i++)
			System.out.println(accounts[i].toString());
	}

	/**
	 * Prints all of the accounts in the database along with their fees and interest rates
	 */
	public void printFeesAndInterests()
	{
		sort();

		for(int i = 0; i < numAcct; i++)
			System.out.println(accounts[i].feesString());
	}

	/**
	 * Updates all of the account balances in the database and prints the new balances
	 */
	public void printUpdatedBalances()
	{
		sort();

		for(int i = 0; i < numAcct; i++)
		{
			accounts[i].update();
			System.out.println(accounts[i].toString());
		}
	}

	/**
	 * Default constructor
	 */
	public AccountDatabase()
	{
		accounts = new Account[0];
		numAcct = 0;
	}
}
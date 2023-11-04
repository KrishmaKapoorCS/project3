package com.example.bankgui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * The TransactionManagerController handles the events
 *
 * @author Daniel Elwell
 */
public class TransactionManagerController
{
    //FXML fields:
    @FXML
    private TextField openFirstName;
    @FXML
    private TextField openLastName;
    @FXML
    private ToggleGroup openAccountTypeTG;
    @FXML
    private DatePicker openDateOfBirth;
    @FXML
    private ToggleGroup openCampusTG;

    @FXML
    private TextField depFirstName;
    @FXML
    private TextField depLastName;
    @FXML
    private ToggleGroup depAccountTypeTG;
    @FXML
    private DatePicker depDateOfBirth;
    @FXML
    private TextField depAmount;

    @FXML
    private TextArea outputBox;

    //instance vars:
    private Date today;
    private SimpleDateFormat dateFormat;
    private AccountDatabase database;

    /**
     * Parses a date of birth
     * @param str the string to parse
     * @param minAge the minimum age to allow
     * @param maxAge the maximum age to allow
     * @return the parsed date
     * @throws Exception if the date is in any way invalid
     */
    private Date parseDOB(String str, int minAge, int maxAge) throws Exception {
        String[] dateStrings = str.split("/");
        int month, day, year;

        try {
            month = Integer.parseInt(dateStrings[0]);
            day = Integer.parseInt(dateStrings[1]);
            year = Integer.parseInt(dateStrings[2]);
        } catch (NumberFormatException e) {
            throw new Exception("DOB invalid: " + str + " not a valid calendar date!");
        }

        Date date = new Date(year, month, day);

        if (!date.isValid())
            throw new Exception("DOB invalid: " + str + " not a valid calendar date!");

        if (date.compareTo(today) >= 0)
            throw new Exception("DOB invalid: " + str + " cannot be today or a future day.");

        Date minDate = new Date(today.getYear() - maxAge, today.getMonth(), today.getDay());
        Date maxDate = new Date(today.getYear() - minAge, today.getMonth(), today.getDay());

        if (maxAge > 0 && date.compareTo(minDate) < 0)
            throw new Exception("DOB invalid: " + str + " over " + maxAge + ".");

        if (minAge > 0 && date.compareTo(maxDate) > 0)
            throw new Exception("DOB invalid: " + str + " under " + minAge + ".");

        return date;
    }

    /**
    * Parses an account
    * @param type the account's type
    * @param profile the account holder's profile
    * @param balance the initial balance of the account
    * @param initial whether this is an initial account creation, to be opened
    * @return the parsed account
    * @throws Exception if the account was in any way invalid
    */
   private Account parseAccount(String type, Profile profile, double balance, boolean initial) throws Exception
   {
       switch (type)
       {
           case "Checking" ->
           {
               return new Checking(profile, balance);
           }
           case "College Checking" ->
           {
               //int campusCode = Integer.parseInt(extraArg);
               if (openCampusTG.getSelectedToggle() == null)
                   throw new Exception("Invalid campus code.");
               String campusString = ((RadioButton) openCampusTG.getSelectedToggle()).getText();
               int campusCode = switch (campusString) {
                   case "NB" -> 0;
                   case "Newark" -> 1;
                   case "Camden" -> 2;
                   default -> -1;
               };
               return new CollegeChecking(profile, balance, campusCode);
           }
           case "Savings" ->
           {
               //String loyal = extraArg;
               //TODO: get loyal from loyal checkbox
               return new Savings(profile, balance, true);
           }
           case "Money Market" ->
           {
               return new MoneyMarket(profile, initial, balance);
           }
           default -> throw new Exception("Not a valid account type.");
       }
   }

    /**
     * Parses an account from an argument list, not gui input
     * @param type the account's type
     * @param profile the account holder's profile
     * @param balance the initial balance of the account
     * @param extraArg an additional argument, use for certain account types
     * @param initial whether this is an initial account creation, to be opened
     * @return the parsed account
     * @throws Exception if the account was in any way invalid
     */
    private Account parseAccountFromArgs(String type, Profile profile, double balance, String extraArg, boolean initial) throws Exception
    {
        switch (type)
        {
            case "Checking" ->
            {
                return new Checking(profile, balance);
            }
            case "College Checking" ->
            {
                int campusCode;
                try
                {
                    campusCode = Integer.parseInt(extraArg);
                }
                catch(NumberFormatException e)
                {
                    throw new Exception("Invalid campus code.");
                }

                return new CollegeChecking(profile, balance, campusCode);
            }
            case "Savings" ->
            {
                String loyal = extraArg;
                return new Savings(profile, balance, loyal.equals("1"));
            }
            case "Money Market" ->
            {
                return new MoneyMarket(profile, initial, balance);
            }
            default -> throw new Exception("Not a valid account type.");
        }
    }

    /**
     * Parses a balance
     * @param bal the balance as a string to parse
     * @return the balance
     * @throws Exception if the balance was invalid
     */
    private double parseBalance(String bal) throws Exception
    {
        double balance;
        try
        {
            balance = Double.parseDouble(bal);
        }
        catch(NumberFormatException e)
        {
            throw new Exception("Not a valid amount.");
        }
        return balance;
    }

    /**
     * Adds an account from a list of string arguments
     * @param args the arguments of the account
     */
    private void addAccountFromArgs(String[] args)
    {
        if(args.length < 5)
            outputBox.appendText("Missing data for opening an account");

        try
        {
            String accountType = args[0];
            String firstName = args[1];
            String lastName = args[2];
            Date dob = parseDOB(args[3], -1, -1);
            Profile profile = new Profile(firstName, lastName, dob);
            double balance = parseBalance(args[4]);
            if(balance <= 0.0)
                throw new Exception("Initial balance cannot be 0 or negative.");
            String extraArg = (accountType.equals("College Checking") || accountType.equals("Savings")) ? args[5] : "";

            Account account = parseAccountFromArgs(accountType, profile, balance, extraArg, true);

            String briefString = firstName + " " + lastName + " " + dob.toString() + "(" + accountType + ")";
            if(database.open(account))
                outputBox.appendText(briefString + " opened.\n");
            else
                throw new Exception(briefString + " already in the database.");
        }
        catch(Exception e)
        {
            outputBox.appendText(e.getMessage() + "\n");
            return;
        }
    }

    /**
     * Runs when the "Open" button is clicked, opens an account
     */
    public void openAccountButtonClicked()
    {
        if(openFirstName.getText() == null || openFirstName.getText().isEmpty()
                || openLastName.getText() == null || openLastName.getText().isEmpty()
                || openAccountTypeTG.getSelectedToggle() == null || openDateOfBirth.getValue() == null)
        {
            outputBox.appendText("Missing data for opening an account.\n");
            return;
        }

        try
        {
            String accountType = ((RadioButton) openAccountTypeTG.getSelectedToggle()).getText();
            int minAge = 16, maxAge = accountType.equals("College Checking") ? 24 : -1;

            String firstName = openFirstName.getText();
            String lastName = openLastName.getText();
            Date dob = parseDOB(dateFormat.format(openDateOfBirth.getValue()), minAge, maxAge);
            Profile profile = new Profile(firstName, lastName, dob);
            double balance = parseBalance("10.0"); //TODO: get this string from an fxml box
            if(balance <= 0.0)
                throw new Exception("Initial deposit cannot be 0 or negative.");

            Account account = parseAccount(accountType, profile, balance, true);

            String briefString = firstName + " " + lastName + " " + dob.toString() + "(" + accountType + ")";
            if(database.open(account))
                outputBox.appendText(briefString + " opened.\n");
            else
                throw new Exception(briefString + " already in the database.");
        }
        catch(Exception e)
        {
            outputBox.appendText(e.getMessage() + "\n");
            return;
        }
    }

    /**
     * Runs when the "Close" button is clicked, closes an account
     */
    public void closeAccountButtonClicked()
    {
        if(openFirstName.getText() == null || openFirstName.getText().isEmpty()
                || openLastName.getText() == null || openLastName.getText().isEmpty()
                || openAccountTypeTG.getSelectedToggle() == null || openDateOfBirth.getValue() == null)
        {
            outputBox.appendText("Missing data for closing an account.\n");
            return;
        }

        try
        {
            String accountType = ((RadioButton) openAccountTypeTG.getSelectedToggle()).getText();
            String firstName = openFirstName.getText();
            String lastName = openLastName.getText();
            Date dob = parseDOB(dateFormat.format(openDateOfBirth.getValue()), -1, -1);
            Profile profile = new Profile(firstName, lastName, dob);

            Account account = parseAccount(accountType, profile, 0.0, false);

            String briefString = firstName + " " + lastName + " " + dob.toString() + "(" + accountType + ")";
            if(database.close(account))
                outputBox.appendText(briefString + " has been closed.\n");
            else
                throw new Exception(briefString + " is not in the database.");
        }
        catch(Exception e)
        {
            outputBox.appendText(e.getMessage() + "\n");
            return;
        }
    }

    /**
     * Runs when one of the account type options are clicked, may make certain fields editable/uneditable
     */
    public void accountTypeOptionClicked()
    {
        String accountType = ((RadioButton) openAccountTypeTG.getSelectedToggle()).getText();

        boolean showCampus = accountType.equals("College Checking");
        openCampusTG.getToggles().forEach(toggle ->
        {
            Node node = (Node)toggle;
            node.setDisable(!showCampus);
        });

        boolean showLoyalty = accountType.equals("Savings");
        //TODO: hide/show loyalty checkbox

        if(accountType.equals("Money Market"))
        {
            //TODO: set loyalty checkbox to true
        }
    }

    /**
     * Runs when the "Deposit" button is clicked, deposits into an account
     */
    public void depositButtonClicked()
    {
        if(depFirstName.getText() == null || depFirstName.getText().isEmpty()
                || depLastName.getText() == null || depLastName.getText().isEmpty()
                || depAccountTypeTG.getSelectedToggle() == null || depDateOfBirth.getValue() == null)
        {
            outputBox.appendText("Missing data for depositing to an account.\n");
            return;
        }

        try
        {
            String accountType = ((RadioButton) depAccountTypeTG.getSelectedToggle()).getText();

            String firstName = depFirstName.getText();
            String lastName = depLastName.getText();
            Date dob = parseDOB(dateFormat.format(depDateOfBirth.getValue()), -1, -1);
            Profile profile = new Profile(firstName, lastName, dob);
            double balance = parseBalance(depAmount.getText());
            if(balance <= 0.0)
                throw new Exception("Deposit - amount cannot be 0 or negative.");

            Account account = parseAccount(accountType, profile, balance, false);

            String briefString = firstName + " " + lastName + " " + dob.toString() + "(" + accountType + ")";
            if(database.contains(account))
            {
                database.deposit(account);
                outputBox.appendText(briefString + " Deposit - balance updated.\n");
            }
            else
                throw new Exception(briefString + " is not in the database.");
        }
        catch(Exception e)
        {
            outputBox.appendText(e.getMessage() + "\n");
            return;
        }
    }

    /**
     * Runs when the "Withdraw" button is clicked, withdraws from an account
     */
    public void withdrawButtonClicked()
    {
        if(depFirstName.getText() == null || depFirstName.getText().isEmpty()
                || depLastName.getText() == null || depLastName.getText().isEmpty()
                || depAccountTypeTG.getSelectedToggle() == null || depDateOfBirth.getValue() == null)
        {
            outputBox.appendText("Missing data for withdrawing from an account.\n");
            return;
        }

        try
        {
            String accountType = ((RadioButton) depAccountTypeTG.getSelectedToggle()).getText();

            String firstName = depFirstName.getText();
            String lastName = depLastName.getText();
            Date dob = parseDOB(dateFormat.format(depDateOfBirth.getValue()), -1, -1);
            Profile profile = new Profile(firstName, lastName, dob);
            double balance = parseBalance(depAmount.getText());
            if(balance <= 0.0)
                throw new Exception("Withdraw - amount cannot be 0 or negative.");

            Account account = parseAccount(accountType, profile, balance, false);

            String briefString = firstName + " " + lastName + " " + dob.toString() + "(" + accountType + ")";
            if(database.contains(account))
            {
                database.withdraw(account);
                outputBox.appendText(briefString + " Withdraw - balance updated.\n");
            }
            else
                throw new Exception(briefString + " is not in the database.");
        }
        catch(Exception e)
        {
            outputBox.appendText(e.getMessage() + "\n");
            return;
        }
    }

    /**
     * Runs when the "print accounts" button is clicked, prints all accounts
     */
    public void printButtonClicked()
    {
        if(database.empty())
            outputBox.appendText("Account Database is empty!\n");
        else
        {
            outputBox.appendText("\n*Accounts sorted by account type and profile.\n");
            outputBox.appendText(database.getAccountsString());
            outputBox.appendText("*end of list.\n");
        }
    }

    /**
     * Runs when the "print accounts with interest and fees" button is clicked, prints all accounts + interest + fees
     */
    public void printFeesButtonClicked()
    {
        if(database.empty())
            outputBox.appendText("Account Database is empty!\n");
        else
        {
            outputBox.appendText("\n*list of accounts with fee and monthly interest\n");
            outputBox.appendText(database.getFeesAndInterestString());
            outputBox.appendText("*end of list.\n");
        }
    }

    /**
     * Run when the "update accounts" button is clicked, updates all accounts + prints them
     */
    public void updateButtonClicked()
    {
        if(database.empty())
            outputBox.appendText("Account Database is empty!\n");
        else
        {
            outputBox.appendText("\n*list of accounts with fees and interests applied.\n");
            outputBox.appendText(database.getUpdatedBalances());
            outputBox.appendText("*end of list.\n");
        }
    }

    public void fileButtonClicked()
    {
        Scanner scan;
        try
        {
            scan = new Scanner(new File("bankAccounts.txt"));
        }
        catch(IOException e)
        {
            outputBox.appendText("Could not open bankAccounts.txt!\n");
            return;
        }

        while(scan.hasNext())
        {
            String line = scan.nextLine();

            String[] args = line.split(",");
            switch (args[0])
            {
                case "C" -> args[0] = "Checking";
                case "CC" -> args[0] = "College Checking";
                case "S" -> args[0] = "Savings";
                case "MM" -> args[0] = "Money Market";
                default -> args[0] = "Unknown";
            }

            addAccountFromArgs(args);
        }

        scan.close();
    }

    /**
     * Default constructor
     */
    public TransactionManagerController()
    {
        today = new Date(2023, 11, 4);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        database = new AccountDatabase();
    }
}
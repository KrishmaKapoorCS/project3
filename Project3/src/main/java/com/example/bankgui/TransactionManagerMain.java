package com.example.bankgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The TransactionManagerMain launches the application
 *
 * @author Krishma Kapoor
 */

public class TransactionManagerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TransactionManagerMain.class.getResource("TransactionManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 500);
        stage.setTitle("Project 3 - Transaction Manager");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }


//    private boolean shouldQuit = false;
//    private AccountDatabase database;
//    private Date today;
//
//    /**
//     * Parses a date of birth
//     * @param str the string to parse
//     * @param minAge the minimum age to allow
//     * @param maxAge the maximum age to allow
//     * @return the parsed date
//     * @throws Exception if the date is in any way invalid
//     */
//    private Date parseDOB(String str, int minAge, int maxAge) throws Exception
//    {
//        String[] dateStrings = str.split("/");
//        int month, day, year;
//
//        try
//        {
//            month = Integer.parseInt(dateStrings[0]);
//            day   = Integer.parseInt(dateStrings[1]);
//            year  = Integer.parseInt(dateStrings[2]);
//        }
//        catch(NumberFormatException e)
//        {
//            throw new Exception("DOB invalid: " + str + " not a valid calendar date!");
//        }
//
//        Date date = new Date(year, month, day);
//
//        if(!date.isValid())
//            throw new Exception("DOB invalid: " + str + " not a valid calendar date!");
//
//        if(date.compareTo(today) >= 0)
//            throw new Exception("DOB invalid: " + str + " cannot be today or a future day.");
//
//        Date minDate = new Date(today.getYear() - maxAge, today.getMonth(), today.getDay());
//        Date maxDate = new Date(today.getYear() - minAge, today.getMonth(), today.getDay());
//
//        if(maxAge > 0 && date.compareTo(minDate) < 0)
//            throw new Exception("DOB invalid: " + str + " over " + maxAge + ".");
//
//        if(minAge > 0 && date.compareTo(maxDate) > 0)
//            throw new Exception("DOB invalid: " + str + " under " + minAge + ".");
//
//        return date;
//    }
//
//    /**
//     * Parses a profile
//     * @param firstName the profile's first name
//     * @param lastName the profile's last name
//     * @param date the profile's date of birth
//     * @param minAge the minimum age the profile can be
//     * @param maxAge the maximum age the profile can be
//     * @return the parsed profile
//     * @throws Exception if the profile was in any way invalid
//     */
//    private Profile parseProfile(String firstName, String lastName, String date, int minAge, int maxAge) throws Exception
//    {
//        Date dob = parseDOB(date, minAge, maxAge);
//        return new Profile(firstName, lastName, dob);
//    }
//
//    /**
//     * Parses a balance
//     * @param bal the balance as a string to parse
//     * @return the balance
//     * @throws Exception if the balance was invalid
//     */
//    private double parseBalance(String bal) throws Exception
//    {
//        double balance;
//        try
//        {
//            balance = Double.parseDouble(bal);
//        }
//        catch(NumberFormatException e)
//        {
//            throw new Exception("Not a valid amount.");
//        }
//
//        return balance;
//    }
//
//    /**
//     * Parses an account
//     * @param type the account's type
//     * @param profile the account holder's profile
//     * @param balance the intial balance of the account
//     * @param extraArg an optional extra argument, some account types require this
//     * @param initial whether or not this is an initial account creation, to be opened
//     * @return the parsed account
//     * @throws Exception if the account was in any way invalud
//     */
//    private Account parseAccount(String type, Profile profile, double balance, String extraArg, boolean initial) throws Exception
//    {
//        if(type.equals("C"))
//            return new Checking(profile, balance);
//        else if(type.equals("CC"))
//        {
//            int campusCode = Integer.parseInt(extraArg);
//            return new CollegeChecking(profile, balance, campusCode);
//        }
//        else if(type.equals("S"))
//        {
//            String loyal = extraArg;
//            return new Savings(profile, balance, loyal.equals("1"));
//        }
//        else if(type.equals("MM"))
//            return new MoneyMarket(profile, initial, balance);
//        else
//        {
//            throw new Exception("Not a valid account type.");
//        }
//    }
//
//    /**
//     * Processes the "O" command
//     * @param args the arguments passed to the command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandOpen(String[] args) throws Exception
//    {
//        if(args.length < 6 || ((args[1].equals("CC") || args[1].equals("S")) && args.length < 7))
//        {
//            System.out.println("Missing data for opening an account.");
//            return;
//        }
//
//        String type = args[1];
//        String firstName = args[2];
//        String lastName = args[3];
//        String date = args[4];
//        String balance = args[5];
//        String extraArg = (args[1].equals("CC") || args[1].equals("S")) ? args[6] : "0";
//
//        Profile profile = parseProfile(firstName, lastName, date, 16, type.equals("CC") ? 24 : -1);
//        double bal = parseBalance(balance);
//        if(bal <= 0.0)
//            throw new Exception("Initial deposit cannot be 0 or negative.");
//
//        Account account = parseAccount(type, profile, bal, extraArg, true);
//
//        String briefString = firstName + " " + lastName + " " + date + "(" + type + ")";
//        if(database.open(account))
//            System.out.println(briefString + " opened.");
//        else
//            throw new Exception(briefString + " already in the database.");
//    }
//
//    /**
//     * Processes the "C" command
//     * @param args the arguments passed to the command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandClose(String[] args) throws Exception
//    {
//        if(args.length < 5)
//        {
//            System.out.println("Missing data for closing an account.");
//            return;
//        }
//
//        String type = args[1];
//        String firstName = args[2];
//        String lastName = args[3];
//        String date = args[4];
//        String extraArg = "0";
//
//        Profile profile = parseProfile(firstName, lastName, date, -1, -1);
//        Account account = parseAccount(type, profile, 0.0, extraArg, false);
//
//        String briefString = firstName + " " + lastName + " " + date + "(" + type + ")";
//        if(database.close(account))
//            System.out.println(briefString + " has been closed.");
//        else
//            throw new Exception(briefString + " is not in the database.");
//    }
//
//    /**
//     * Processes the "D" command
//     * @param args the arguments passed to the command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandDeposit(String[] args) throws Exception
//    {
//        if(args.length < 6)
//        {
//            System.out.println("Missing data for depositing to an account.");
//            return;
//        }
//
//        String type = args[1];
//        String firstName = args[2];
//        String lastName = args[3];
//        String date = args[4];
//        String balance = args[5];
//        String extraArg = "0";
//
//        Profile profile = parseProfile(firstName, lastName, date, -1, -1);
//        double bal = parseBalance(balance);
//        if(bal <= 0.0)
//            throw new Exception("Deposit - amount cannot be 0 or negative.");
//
//        Account account = parseAccount(type, profile, bal, extraArg, false);
//
//        String briefString = firstName + " " + lastName + " " + date + "(" + type + ")";
//        if(database.contains(account))
//        {
//            database.deposit(account);
//            System.out.println(briefString + " Deposit - balance updated.");
//        }
//        else
//            throw new Exception(briefString + " is not in the database.");
//    }
//
//    /**
//     * Processes the "W" command
//     * @param args the arguments passed to the command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandWithdraw(String[] args) throws Exception
//    {
//        if(args.length < 6)
//        {
//            System.out.println("Missing data for depositing to an account.");
//            return;
//        }
//
//        String type = args[1];
//        String firstName = args[2];
//        String lastName = args[3];
//        String date = args[4];
//        String balance = args[5];
//        String extraArg = "0";
//
//        Profile profile = parseProfile(firstName, lastName, date, -1, -1);
//        double bal = parseBalance(balance);
//        if(bal <= 0.0)
//            throw new Exception("Withdraw - amount cannot be 0 or negative.");
//
//        Account account = parseAccount(type, profile, bal, extraArg, false);
//
//        String briefString = firstName + " " + lastName + " " + date + "(" + type + ")";
//        if(database.contains(account))
//        {
//            if(database.withdraw(account))
//                System.out.println(briefString + " Withdraw - balance updated.");
//            else
//                throw new Exception(briefString + " Withdraw - insufficient fund.");
//        }
//        else
//            throw new Exception(briefString + " is not in the database.");
//    }
//
//    /**
//     * Processes the "P" command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandPrint() throws Exception
//    {
//        if(database.empty())
//            throw new Exception("Account Database is empty!");
//        else
//        {
//            System.out.println("\n*Accounts sorted by account type and profile.");
//            database.printSorted();
//            System.out.println("*end of list.\n");
//        }
//    }
//
//    /**
//     * Processes the "PI" command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandPrintInterest() throws Exception
//    {
//        if(database.empty())
//            throw new Exception("Account Database is empty!");
//        else
//        {
//            System.out.println("\n*list of accounts with fee and monthly interest");
//            database.printFeesAndInterests();
//            System.out.println("*end of list.\n");
//        }
//    }
//
//    /**
//     * Processes the "UB" command
//     * @throws Exception if the command was invalid
//     */
//    private void processCommandUpdate() throws Exception
//    {
//        if(database.empty())
//            throw new Exception("Account Database is empty!");
//        else
//        {
//            System.out.println("\n*list of accounts with fees and interests applied.");
//            database.printUpdatedBalances();
//            System.out.println("*end of list.\n");
//        }
//    }
//
//    /**
//     * Processes a single command
//     * @param command the command to process
//     */
//    private void processCommand(String command)
//    {
//        String[] args = command.split("\\s+");
//
//        try
//        {
//            if(args[0].equals("O"))
//                processCommandOpen(args);
//            else if(args[0].equals("C"))
//                processCommandClose(args);
//            else if(args[0].equals("D"))
//                processCommandDeposit(args);
//            else if(args[0].equals("W"))
//                processCommandWithdraw(args);
//            else if(args[0].equals("P"))
//                processCommandPrint();
//            else if(args[0].equals("PI"))
//                processCommandPrintInterest();
//            else if(args[0].equals("UB"))
//                processCommandUpdate();
//            else if(args[0].equals("Q"))
//                shouldQuit = true;
//            else if(args[0].length() > 0)
//                System.out.println("Invalid command!");
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    /**
//     * Default constructor
//     */
//    public TransactionManager()
//    {
//        database = new AccountDatabase();
//        today = new Date(2023, 10, 16);
//    }
//
//    /**
//     * Runs the Transaction Manager, keeping it in a loop of command processing
//     */
//    public void run()
//    {
//        System.out.println("Transaction Manager is running.");
//        Scanner scan = new Scanner(System.in);
//
//        while(true)
//        {
//            String input = scan.nextLine();
//            String[] commands = input.split(System.lineSeparator());
//
//            for(int i = 0; i < commands.length; i++)
//            {
//                processCommand(commands[i]);
//                if(shouldQuit)
//                    break;
//            }
//
//            if(shouldQuit)
//                break;
//        }
//
//        scan.close();
//        System.out.println("Transaction Manager is terminated.");
//    }


}
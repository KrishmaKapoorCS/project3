package com.example.bankgui;

/**
 * The Profile class is an account holder
 * 
 * @author Krishma Kapoor
 */
public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

     /**
     * Constructor for setting values
     * @param first the first name
     * @param last the last name
     * @param date the fate of birth
     */
    public Profile(String first, String last, Date date)
    {
        fname = first;
        lname = last;
        dob = date;
    }
    /**
     * printProfile() prints info about the user
     * @return String: first and last name along with the dob
     */

    public String printProfile(){
        return fname + " " + lname + " " + dob.toString();
    }

    /**
     * Compare To method allows for comparsion between two users
     * @param o the user being compared to another user
     * @return int, whether or not the user already has an account
     */
    @Override
    public int compareTo(Profile o) {
        // First names are the same, compare last names
        int lnameComparison = this.lname.compareToIgnoreCase(o.lname);
        if (lnameComparison != 0) {
            return lnameComparison;
        }
        
        // Compare first names
        int fnameComparison = this.fname.compareToIgnoreCase(o.fname);
        if (fnameComparison != 0) {
            return fnameComparison;
        }
        
        // Last names are the same, compare dates of birth
        return this.dob.compareTo(o.dob);
    }

   
}

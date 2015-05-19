package com.example.chris.nyas_final;

/**
 * Created by Chris on 19/05/2015.
 * Date class, holds the date as integers and combined string
 * Instantiated byt passing a string containing a date YYMMDD or YYYYMMDD
 */
public class DateClass {
    private int year;  // Year of the appointment

    private int month; // Month of the appointment
    private int day;   // Day of the appointment
    private String date; // String holding combined date YYMMDD

    // Constructor populates members based on string argument
    public DateClass(String date) {
        String temp;

        // Ensure date is not null or empty
        if(date == null)
            this.date = "";

        if(date.length() == 0)
            return;

        // Date is not empty or null
        this.date = date;

        int index = date.length();

        temp = date.substring(index - 2, index);
        day = Integer.parseInt(temp);
        index -= 2;
        temp = date.substring(index - 2, index);
        month = Integer.parseInt(temp);
        index-=2;
        temp = date.substring(0, index);
        year = Integer.parseInt(temp);
    }

    // Getters for date fields
    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public String getDate() { return date; }

}

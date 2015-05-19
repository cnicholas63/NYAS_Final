package com.example.chris.nyas_final;

import android.database.Cursor;

/**
 * Created by Chris on 19/05/2015.
 * Class holds an appointment, used with DatabaseHelper class
 */
public class Appointment implements AppConstants{

    // Define members
    long id; // The id of the appointment as retrieved from database
    int type; // The type of appointment, 0 = appointment, 1 = diary entry
    DateClass appDate; // Date of the appointment / entry
    TimeClass startTime; // Start time of the appointment
    TimeClass endTime; // End time of the appointment
    String title; // Title fot the entry
    String content; // Content/details of the entry

    // Constructor without parameters
    public Appointment() {

    }

    // Overloaded constructor accepting Cursor and populates appointment info
    public Appointment(Cursor cursor) {
        id = Integer.parseInt(cursor.getString(0));   // Get the ID (primary key)
        type = Integer.parseInt(cursor.getString(1)); // Get the type
        appDate = new DateClass(cursor.getString(2)); // Get the date
        startTime = new TimeClass(cursor.getString(3));// get the start time
        endTime = new TimeClass(cursor.getString(4)); // get the end time
        title = cursor.getString(5);   // Get the title
        content = cursor.getString(6); // Get the comments
    }

    // Getters
    public String getContent() { return content; }
    public long getId() { return id; }
    public int getType() { return type; }
    public DateClass getAppDate() { return appDate; }
    public TimeClass getStartTime() { return startTime; }
    public TimeClass getEndTime() { return endTime; }
    public String getTitle() { return title; }
}

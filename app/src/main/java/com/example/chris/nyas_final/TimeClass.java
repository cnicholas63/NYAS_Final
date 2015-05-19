package com.example.chris.nyas_final;

/**
 * Created by Chris on 19/05/2015.
 * TimeClass holds the time as integers and string
 * Instantiated byt passing a string containing a time HHMM
 */
public class TimeClass {
    private int hour; // Hour of the appointment
    private int minute; // Minute of the appointment
    private String time; // String holding combined time HHMM

    // Constructor populates members based on string argument
    public TimeClass (String time) {
        String temp;

        // Ensure time is not null or empty
        if(time == null)
            this.time = "";

        if(time.length() == 0)
            return;

        int index = time.length();

        this.time = time;
        temp = time.substring(index - 2, index);
        hour = Integer.parseInt(temp);
        index -= 2;
        temp = time.substring(index - 2, index);
        minute = Integer.parseInt(temp);

    }

    // Getters for time fields
    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public String getTime() { return time; }
}

package com.example.chris.nyas_final;

/**
 * Created by Chris on 19/05/2015.
 * Class holds constants definitions
 *
 */
public interface AppConstants {
    // Define constants that will be used by the DatabaseHelper class
    public static final String DATABASE_NAME = "appointments.db"; // The name of the database
    public static final String TABLE_ENTRIES = "entries"; // The name of the table that will hold the data - single table flat file
    public static final int DATABASE_VERSION = 1; // The database version, needed to facilitate database updates (structure not content)
    public static final String COLUMN_ID = "_id"; // The ID field, Android likes the primary key to be called _id
    public static final String COLUMN_TYPE = "type"; // The type field identifies the type of entry 0 = appointment, 1 = diary
    public static final String COLUMN_DATE = "entry_date"; // The date of the appointment / diary entry
    public static final String COLUMN_START_TIME = "start_time"; // The time of the appointment (not used for diary)
    public static final String COLUMN_END_TIME = "end_time"; // The end time for the appointment (not used for diary)
    public static final String COLUMN_TITLE = "title"; // Title for the entry
    public static final String COLUMN_CONTENT = "content"; // The content/notes for the entry

    // Appointment entry types
    public static final int TYPE_NOTSET = -1; // Not set
    public static final int TYPE_APPOINTMENT = 0; // Indicates appointment
    public static final int TYPE_CALENDAR = 1; // Indicates Calendar



}

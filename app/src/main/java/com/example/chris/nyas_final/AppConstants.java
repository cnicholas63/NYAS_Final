package com.example.chris.nyas_final;

/**
 * Created by Chris on 19/05/2015.
 * Class holds constants definitions
 *
 */
public interface AppConstants {
    // Define constants that will be used by the DatabaseHelper class
    String DATABASE_NAME = "appointments"; // The name of the database
    String TABLE_ENTRIES = "appointments"; // The name of the table that will hold the data - single table flat file
    int DATABASE_VERSION = 1; // The database version, needed to facilitate database updates (structure not content)
    String COLUMN_ID = "_id"; // The ID field, Android likes the primary key to be called _id
    String COLUMN_TYPE = "type"; // The type field identifies the type of entry 0 = appointment, 1 = diary
    String COLUMN_DATE = "entry_date"; // The date of the appointment / diary entry
    String COLUMN_START_TIME = "start_time"; // The time of the appointment (not used for diary)
    String COLUMN_END_TIME = "end_time"; // The end time for the appointment (not used for diary)
    String COLUMN_TITLE = "title"; // Title for the entry
    String COLUMN_CONTENT = "content"; // The content/notes for the entry

    // Appointment entry types
    int TYPE_NOTSET = -1; // Not set
    int TYPE_APPOINTMENT = 0; // Indicates appointment
    int TYPE_CALENDAR = 1; // Indicates Calendar



}

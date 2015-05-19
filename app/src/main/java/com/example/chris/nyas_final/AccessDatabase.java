package com.example.chris.nyas_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 19/05/2015.
 * Provides CRUD functionality for the database
 * Adapted from http://www.vogella.com/tutorials/AndroidSQLite/article.html
 *  */

public class AccessDatabase implements AppConstants {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = { // String array containing all database column names
            COLUMN_ID, COLUMN_TYPE, COLUMN_DATE,
            COLUMN_START_TIME, COLUMN_END_TIME,
            COLUMN_TITLE, COLUMN_CONTENT
    };

    // Constructor
    public AccessDatabase(Context context) {
        dbHelper = new DatabaseHelper(context); // Instantiate DatabaseHelper object
    }

    // Open database connection
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Close database connection
    public void close() {
        dbHelper.close();
    }

    // Creates an appointment entry in the database
    public Appointment createAppointment(Appointment app) {
        ContentValues values = new ContentValues(); // Name value pairs

        // Populate values with Appointment info
        values.put(COLUMN_TYPE, app.type); // Add the type to values
        values.put(COLUMN_DATE, app.appDate.getDate()); // Add date to values
        values.put(COLUMN_START_TIME, app.startTime.getTime()); // Add start time to values
        values.put(COLUMN_END_TIME, app.endTime.getTime()); // Add end time to values
        values.put(COLUMN_TITLE, app.title); // Add title to values
        values.put(COLUMN_CONTENT, app.content); // Add content to values

        // Add the record to the database
        long insertId = database.insert(TABLE_ENTRIES, null, values);

        // Query the database as double check
        Cursor cursor = database.query(TABLE_ENTRIES,
                allColumns, COLUMN_ID + " = " + insertId, null, null, null, null);

        cursor.moveToFirst(); // Make sure cursor is at beginning of results


        Appointment appointment = cursorToAppointment(cursor);

        appointment.printAppointment();
        cursor.close();
        return appointment;
    }

    // Delete a record from the database based on its ID
    public void deleteAppointment(Appointment appointment) {
        long id = appointment.getId();

        if(id == 0) { // This appointment was not retrieved from the database, do not try to delete it
            System.out.println("Invalid Appointment ID, record not deleted");
            return;
        }

        System.out.println("Comment deleted with id: " + id);
        database.delete(TABLE_ENTRIES, COLUMN_ID + " = " + id, null);
    }

    // Query the database for all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<Appointment>();

        // Run the query, cursor will point to the resultset
        Cursor cursor = database.query(TABLE_ENTRIES, allColumns, null, null, null, null, null);

        cursor.moveToFirst(); // Make sure cursor is pointing at first result

        // Run through the results adding appointments to the list
        while (!cursor.isAfterLast()) {
            Appointment appointment = cursorToAppointment(cursor); // get a populated appointment
            appointments.add(appointment); // Add the appointment to the appointments ArrayList
            cursor.moveToNext(); // Move cursor to next record
        }

        // make sure to close the cursor
        cursor.close();

        // Return the appointments list
        return appointments;
    }

    // Take the cursor values and convert into an appointment
    private Appointment cursorToAppointment(Cursor cursor) {

        // Instantiate and populate new appointment
        return new Appointment(cursor);
    }
}

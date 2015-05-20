package com.example.chris.nyas_final;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import database.AccessDatabase;


public class DiaryActivity extends ActionBarActivity implements AppConstants{
    Appointment appointment; // Appointment (diary entries are treated as appointment) is used if we are viewing an existing entry
    boolean isExistingEntry = false; // flag indicates if this is an existing entry or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        String entry; // Used to try to get APPOINTMENT field from bundle

        Bundle diaryEntry = getIntent().getExtras();

        // Look for the APPOINTMENT field, if it's there then we are viewing an existing appointment
        if(diaryEntry != null) { // A bundle exists get the data from it
            entry = diaryEntry.getString(APPOINTMENT_TYPE); // Is the APPOINTMENT value in bundle?

            if(entry == null) { // Make sure entry is not null
                System.out.println(">>>>>>>>>>>> Wrong bundle information <<<<<<<<<<<<<<");
                // Do nothing
            }
            else if (entry.equals(DIARY)) { // Existing diary entry passed in bundle
                appointment = new Appointment();

                appointment.unpackAppointment(diaryEntry); // Unpack the details from bundle
                isExistingEntry = true; // this is an existing entry

                populateExistingAppointment(); // Populate the diary screen with existing data

                return;
            }
        }

        // No bundle stored or wrong data so this is not an existing entry.
        // Set up new diary entry
        // Get todays date and format it to dd/mm/yyyy
        Date today = new Date();
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(today);

        // Access the date TextView
        TextView tv = (TextView) findViewById(R.id.myNyasDiaryDate);

        // Set the text in the TextView eg. Date 19/05/2015
        tv.setText("Date: " + formattedDate);

    }


    // Populate the diary activity fields with the existing data and change button titles
    private void populateExistingAppointment() {
        // Access the date TextView
        TextView textView = (TextView) findViewById(R.id.myNyasDiaryDate);
        EditText editText = (EditText) findViewById(R.id.appointment_notes_text);
        Button button = (Button) findViewById(R.id.appointment_cancel_button);

        // Set the text in the TextView eg. Date 19/05/2015
        textView.setText("Date: " + appointment.getAppDate().getDateFormatted());

        // Set the text in the EditText field
        editText.setText(appointment.getContent());

        // Set the button text to Delete - this will delete the entry
        button.setText("DELETE");
    }


    /**
     * Helpline button clicked, open the Helpline activity
     * View view - the view from where the click originated
     */
    public void myNyasDiaryHelpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    /**
     * Save button clicked, validate diary has an entry before saving
     * View view - the view from where the click originated
     */
    public void myNyasDiarySaveClicked(View view) {

        EditText description = (EditText) findViewById(R.id.appointment_notes_text); // used for populating Appointment object

        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT")); // Instantiate GregorianCalendar object
        int day = cal.get(Calendar.DATE); // Get the day
        int month = cal.get(Calendar.MONTH)+ 1;
        int year = cal.get(Calendar.YEAR);


        // Validate Diary entry exists
        if(description.length() == 0) { // End time is set before start time
            Toast.makeText(DiaryActivity.this, "Please make an entry before saving", Toast.LENGTH_LONG).show();
            return; // return to activity
        }


        // Format the appointment data before populating Appointment
        String appointmentDate = String.format("%02d%02d%02d", year, month, day);

        String appointmentNotes = description.getText().toString(); // Get the appointment notes

        // Diary entry validates, write it to the database
        Appointment newAppointment = new Appointment(TYPE_DIARY, appointmentDate, "000000", "000000", "Diary Entry", appointmentNotes);

        // Open the database and write the appointment to it
        AccessDatabase db = new AccessDatabase(this); // Instantiate database object

        // Try catch block incase db.open fails
        try {
            db.open(); // Open the database
        } catch(SQLException e) { // Exception caught display error message
            Toast.makeText(DiaryActivity.this, "Database failed, appointment not saved", Toast.LENGTH_LONG).show();
            System.out.println("Database failed to open in Appointment Activity error = " + e.getMessage());
            return;
        }

        if(!isExistingEntry) // Add new entry
            db.createAppointment(newAppointment); // Write new appointment to database
        else {// Update existing entry
            appointment.content = appointmentNotes; // Update the appointment notes with any changes
            db.updateAppointment(appointment); // Update the existing appointment
        }

        db.close(); // Close the database

        Toast.makeText(this, "Appointment Saved", Toast.LENGTH_SHORT).show();

        finish(); // close the AppointmentActivity

    }

    /**
     * Cancel button clicked, if this was a new entry, close the activity
     * otherwise, the button is acting as DELETE button, delete the entry from the database
     * View view - the view from where the click originated
     */
    public void myNyasDiaryCancelClicked(View view) {
        if(!isExistingEntry) { // This was a new entry, just cancel it
            // Should display a warning message here
            finish();
            return;
        }

        // Button was DELETE existing entry
        // Open the database ready to delete record
        AccessDatabase db = new AccessDatabase(this); // Instantiate database object

        // Try catch block incase db.open fails
        try {
            db.open(); // Open the database
        } catch(SQLException e) { // Exception caught display error message
            Toast.makeText(DiaryActivity.this, "Database failed, appointment not saved", Toast.LENGTH_LONG).show();
            System.out.println("Database failed to open in Appointment Activity *myNyasCancelClicked* error = " + e.getMessage());
            return;
        }

        db.deleteAppointment(appointment); // Write appointment to database

        db.close();

        finish(); // Finish this activity

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

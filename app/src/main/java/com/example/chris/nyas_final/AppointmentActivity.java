package com.example.chris.nyas_final;

import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

// Appointment activity manages
public class AppointmentActivity extends ActionBarActivity implements AppConstants {
    int year, month, day;
    int hour, minute;
    int startHour = 0;   // Start of appointment hour
    int startMinute = 0; // Start of appointment minute
    int endHour = 0;     // End of appointment hour
    int endMinute = 0;   // End of appointment minute

    boolean setStart = true; // Flag used to determine if the start time is being set.
    static final int DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        String formattedString;

        // Get the selected date from intent Extras
        Intent intent = getIntent();            // Get calling intent
        year = intent.getIntExtra("YEAR", 0);   // Get the Year from Extras - or set to 0 if not found
        month = intent.getIntExtra("MONTH", 0); // Get the Month value
        day = intent.getIntExtra("DAY", 0);     // Get the Day value

        // format time string to ensure it has 2 digits each for hours and minutes
        formattedString = String.format("%02d/%02d/%02d", day, month, year);
        TextView appointmentDate = (TextView) findViewById(R.id.myNyasAppointmentDate);
        appointmentDate.setText(formattedString); // Append the date of the appointment to the s

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if(id == DIALOG_ID) // show dialog 12 hour format
            return new TimePickerDialog(this, timePickerListener, hour, minute, false);

        return null;
    }

    // Listener for time picker onTimeSet, interrogates the dialog for hour and minutes
    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
            hour = hourOfDay;
            minute = minuteOfHour;

            // Set the time of the appointment
            setAppointmentTime(hour, minute);
        }
    };

    /**
     * setAppointmentTime validates and sets either the start or end time for an appointment
     * @param hour      Hour as set by time picker
     * @param minutes   Minute as set by time picker
     */
    private void setAppointmentTime(int hour, int minutes) {
        String formattedString;

        // format time string to ensure it has 2 digits each for hours and minutes
        formattedString = String.format("Start: %02d:%02d", hour, minute);

        // Is this the start or end time that is being set?
        if(setStart) { // Setting the start time
            startHour = hour; // Get the hour
            startMinute = minutes; // Get the minutes

            Button b = (Button) findViewById(R.id.appointment_start_button);
            b.setText(formattedString);

        }
        else { // Setting the end time
            endHour = hour;
            endMinute = minutes;

            Button b = (Button) findViewById(R.id.appointment_end_button);
            b.setText(formattedString);
        }

        Toast.makeText(AppointmentActivity.this, "In setAppointmentTime:" + hour + " : " + minute, Toast.LENGTH_SHORT).show();
    }

    /**
     * Start button clicked, open the start time picker
     * View view - the view from where the click originated
     */
    public void myNyasAppointmentStartClicked(View view) {
        setStart = true; // Indicate that the start time is being set
        showDialog(DIALOG_ID); // Invoke the timePickerDialog
    }

    /**
     * End button clicked, open the end time picker
     * View view - the view from where the click originated
     */
    public void myNyasAppointmentEndClicked(View view) {
        setStart = false; // Indicate that the end time is being set
        showDialog(DIALOG_ID); // Invoke the timePickerDialog
    }

    /**
     * Helpline button clicked, open the Helpline activity
     * View view - the view from where the click originated
     */
    public void myNyasAppointmentHelpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    /**
     * Save button clicked, check appointment is valid save the appointment
     * View view - the view from where the click originated
     */
    public void myNyasAppointmentSaveClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);
        EditText title = (EditText) findViewById(R.id.appointment_title_text); // used for validation and populating Appointment object
        EditText description = (EditText) findViewById(R.id.appointment_notes_text); // used for populating Appointment object

        // Validate start and end times
        if(endHour <= startHour & endMinute < startMinute) { // End time is set before start time
            Toast.makeText(AppointmentActivity.this, "Please ensure appointment end time is not before start time", Toast.LENGTH_LONG).show();
            return; // return to activity
        }

        // Validate that the title field has been populated
        if(title.getText().length() == 0) {
            Toast.makeText(AppointmentActivity.this, "Please enter an appointment title.\nAppointment notes can be left empty", Toast.LENGTH_LONG).show();
            return;
        }

        // Format the appointment data before populating Appointment
        String appointmentDate = String.format("%02d%02d%02d", year, month, day);

        // Format time string to ensure it has 2 digits each for hours and minutes
        String startTime = String.format("%02d%02d", startHour, startMinute); // Start time
        String endTime = String.format("%02d%02d", endHour, endMinute); // End time

        String appointmentTitle = title.getText().toString(); // Get the appointment title
        String appointmentNotes = description.getText().toString(); // Get the appointment notes

        // Appointment validates, write it to the database
        Appointment appointment = new Appointment(TYPE_APPOINTMENT, appointmentDate, startTime, endTime, appointmentTitle, appointmentNotes);

        // Open the database and write the appointment to it
        AccessDatabase db = new AccessDatabase(this); // Instantiate database object

        // Try catch block incase db.open fails
        try {
            db.open(); // Open the database
        } catch(SQLException e) { // Exception caught display error message
            Toast.makeText(AppointmentActivity.this, "Database failed, appointment not saved", Toast.LENGTH_LONG).show();
            System.out.println("Database failed to open in Appointment Activity error = " + e.getMessage());
            return;
        }

        db.createAppointment(appointment); // Write appointment to database

        db.close();

        Toast.makeText(this, "Save Button Clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * Cancel button clicked, cancel the appointment
     * View view - the view from where the click originated
     */
    public void myNyasAppointmentCancelClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        //startActivity(intent);
        Toast.makeText(this, "Cancel Button Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment, menu);
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
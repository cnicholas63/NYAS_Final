package com.example.chris.nyas_final;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;


public class CalendarActivity extends ActionBarActivity {
    CalendarView calendar;
    Long date; // Used to help identify when a date is selected by the user and not as a result of scrolling te calendar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        setupCalendar();
    }


    /**
     * Helpline button clicked, open the Calendar activity
     * View view - the view from where the click originated
     */
    public void myNyasCalendarHelpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    public void setupCalendar() {
        // Point calendar at the calendar view
        calendar = (CalendarView) findViewById(R.id.myNyasCalendarView);

        // Get today's date from the calendar
        date = calendar.getDate();

        // Set Monday as the first day of the calendar week
        calendar.setFirstDayOfWeek(2);

        // Set listener to be called on date change
        calendar.setOnDateChangeListener(new DateChangedListener());
    }


    // Callback for CalendarView
    private class DateChangedListener implements CalendarView.OnDateChangeListener {

        // The date change listener is called when the user selects a date, but also if they just scroll to a new month - we need to filter this out!
        public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

            if(calendar.getDate() == date) { // Filters out the data change call originating from scrolling through calendar
                return;
            }
            date = calendar.getDate();
            Toast.makeText(getApplicationContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
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

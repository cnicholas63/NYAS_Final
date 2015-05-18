package com.example.chris.nyas_final;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


    public void setupCalendar() {
        calendar = (CalendarView) findViewById(R.id.myNyasCalendarView);

        date = calendar.getDate(); // Get today's date from the calendar

        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);

        //The background color for the selected week.
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.nyas_blue));

        //sets the color for the dates of an unfocused month.
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.nyas_red));

        //sets the color for the separator line between weeks.
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.nyas_purple));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendar.setSelectedDateVerticalBar(R.color.nyas_green);

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new DateChangedListener());
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            //show the selected date as a toast
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
//                Toast.makeText(getApplicationContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    // private class for date change listener
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

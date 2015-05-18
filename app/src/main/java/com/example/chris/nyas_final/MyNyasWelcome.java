package com.example.chris.nyas_final;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyNyasWelcome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nyas_welcome);
    }


    /**
     * Helpline button clicked, open the Contact Us activity
     * View view - the view from where the click originated
     */
    public void myNyasWelcomeHelpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    /**
     * Calendar button clicked, open the Calendar activity
     * View view - the view from where the click originated
     */
    public void myNyasWelcomeCalendarClicked(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_nyas_welcome, menu);
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

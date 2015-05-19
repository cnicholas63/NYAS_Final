package com.example.chris.nyas_final;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    AccessDatabase appointmentsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appointmentsDatabase = new AccessDatabase(this);
        appointmentsDatabase.open();

        List<Appointment> values = appointmentsDatabase.getAllAppointments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        for(Appointment app : values) {
            app.printAppointment();

        }


        appointmentsDatabase.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * Helpline button clicked, open the Contact Us activity
     * View view - the view from where the click originated
     */
    public void helpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    /**
     * Advice button clicked, open the Advice activity
     * View view - the view from where the click originated
     */
    public void adviceClicked(View view) {
        Intent intent = new Intent(this, AdviceServicesActivity.class);

        startActivity(intent);
    }

    /**
     * Our Services button clicked, open the Our Services activity
     * View view - the view from where the click originated
     */
    public void ourServicesClicked(View view) {
        Intent intent = new Intent(this, OurServicesActivity.class);

        startActivity(intent);
    }

    /**
     * My NYAS button clicked, open the My NYAS activity
     * View view - the view from where the click originated
     */
    public void myNyasServicesClicked(View view) {
        Intent intent = new Intent(this, MyNyasLogIn.class);

        startActivity(intent);
    }
}

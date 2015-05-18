package com.example.chris.nyas_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.TooManyListenersException;


public class MyNyasLogIn extends ActionBarActivity {
    SharedPreferences sharedPreferences; // Shared preferences
    String prefs; // Identifier for shared preferences
    String nyasPin; // Key for shared preferences (key, value) pair
    String pin = "0000"; // This will hold the pin number, default pin = 0000

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nyas_log_in);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        // Name of preferences string
        String prefs = getResources().getString(R.string.nyas_preferences); // Get the name for preferences
        String nyasPin = getResources().getString(R.string.nyas_pin); // Get the key for key/value pair

        // Load the shared preferences
        sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(nyasPin)) { // If the pin been set recover it
            pin = new String(sharedPreferences.getString(nyasPin, ""));
        }

        Toast.makeText(this, "Pin = " + pin, Toast.LENGTH_LONG).show();

        pin = "2001"; // Test value

        // Editing the shared preferences to update pin number - is required
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nyasPin, pin); // Write the pin to the preferences
        editor.commit(); // Commit the changes to the shared prefs

    }

    /**
     * On click listener for go button. Validate pin number before moving on
     * @param view View where call originated
     */
    public void myNyasGoButtonClicked(View view){
        String pinNumber;

        // Get the input text from the pin number field (editTextPin)
        EditText pinField = (EditText) findViewById(R.id.editTextPin);
        pinNumber = pinField.getText().toString();

        // Check the length of the pin number and validity
        if(pinNumber.length() < 4) {
            Toast.makeText(this, "Pin too short, please re-enter", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "pin=" + pin, Toast.LENGTH_SHORT).show();

        // Check if pin is correct
        if(!pinNumber.equals(pin)) {
            Toast.makeText(this, "Incorrect pin, please re-enter", Toast.LENGTH_LONG).show();
            return;


        }

        Toast.makeText(this, "Correct Pin yay", Toast.LENGTH_SHORT).show();
        // Pin number valid length

        // Put call to MY NYAS HERE

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Editing the shared preferences to update pin number - is required
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nyasPin, pin); // Write the pin to the preferences
        editor.commit(); // Commit the changes to the shared prefs
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_nyas_log_in, menu);
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

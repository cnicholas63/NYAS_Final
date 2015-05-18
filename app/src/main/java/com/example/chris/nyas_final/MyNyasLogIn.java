package com.example.chris.nyas_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TooManyListenersException;


public class MyNyasLogIn extends ActionBarActivity {
    SharedPreferences sharedPreferences; // Shared preferences
    String prefs; // Identifier for shared preferences
    String nyasPin; // Key for shared preferences (key, value) pair
    String pin; // This will hold the pin number
    boolean firstTimeLogin = true; // Firts time log in flag, default to true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nyas_log_in);

        TextView textView; // Used to change the text view strings if this is not first time login
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        // Name of preferences string
        String prefs = getResources().getString(R.string.nyas_preferences); // Get the name for preferences
        String nyasPin = getResources().getString(R.string.nyas_pin); // Get the key for key/value pair

        // Load the shared preferences
        sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);

        // If the pin has not been set, this is first time login. Set up screen appropriately
        if(!sharedPreferences.contains(nyasPin)) {
            firstTimeLogin = true; // This is a first time login
            // Access the sub title text view and set the text to first time login text
            textView = (TextView) findViewById(R.id.logInServicesSubTitle);
            textView.setText(getString(R.string.sub_title_my_nyas_first_log_in));

            // Access the note text view and set the text to first time login text
            textView = (TextView) findViewById(R.id.myNyasLoginNote);
            textView.setText(getString(R.string.note_my_nyas_first_log_in));
        }
        else { // This is not the first time log in, get the pin number from shared preferences
            firstTimeLogin = false; // This is not the first time login
            pin = new String(sharedPreferences.getString(nyasPin, "no pin"));
        }

        // Show pin - this is for debugging purposes only and will be removed from the program
        Toast.makeText(this, "Pin = " + pin, Toast.LENGTH_LONG).show();
    }

    /**
     * On click listener for go button. Validate pin number before moving on
     * @param view View where call originated
     */
    public void myNyasGoButtonClicked(View view){
        String pinNumber;
        String nyasPin = getResources().getString(R.string.nyas_pin); // Get the key for key/value pair


        // Get the input text from the pin number field (editTextPin)

        EditText pinField = (EditText) findViewById(R.id.editTextPin);
        pinNumber = pinField.getText().toString();

        // Check the length of the pin number and validity (Entry field is limited to 4 characters)
        if(pinNumber.length() != 4) { // Check length entered
            Toast.makeText(this, "Please enter a 4 digit pin", Toast.LENGTH_LONG).show();
            return;
        }

        if(firstTimeLogin) { // If this is the first time login set pin to that entered and save to shared preferences
            Toast.makeText(this, "Your pin has been saved", Toast.LENGTH_LONG).show();
            savePreferences(nyasPin, pinNumber);
        }
        else if(!pinNumber.equals(pin)){ // Else not the first time login, check if pin is correct
            // Pin is incorrect, display error message
            Toast.makeText(this, "Incorrect pin, please re-enter", Toast.LENGTH_LONG).show();
            return; // Return, ignore go button and do not log in
        }

        // Pin number valid, user can enter My NYAS Welcome activity
        // Start the My NYAS Welcome activity
        Intent intent = new Intent(this, MyNyasWelcome.class);
        startActivity(intent);

    }

    /**
     * Helpline button clicked, open the Contact Us activity
     * View view - the view from where the click originated
     */
    public void myNyasLoginHelpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    private void savePreferences(String key, String value) {
        // Editing the shared preferences to update pin number - is required
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value); // Write the pin to the preferences
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

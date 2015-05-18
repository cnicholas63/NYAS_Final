package com.example.chris.nyas_final;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HelplineActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
    }

    /**
     * Call now button clicked. This invokes the built in phone activity passing it the number to call
     * param: view, the context for this view
     */
    public void callNowClicked(View view) {
        String phoneNumber = getResources().getString(R.string.nyas_phone_number); // Get the phone number from resources

        // Set the phone intent to open the telephone activity
        Intent intent = new Intent(Intent.ACTION_DIAL);

        // fill in the phone number
        intent.setData(Uri.parse(phoneNumber));

        // Start the telephone intent with Intent.ACTION_DIAL - this completes the number ready to dial
        // The number can be automatically dialled by using Intent.ACTION_CALL
        // However, I wanted to allow the user to change their mind before dialing
        startActivity(intent);
    }

    /**
     * Email button clicked. This invokes a built in email activity. It fills in the email address ready to be sent
     * @param view
     */
    public void emailClicked(View view) {
        String emailAddress = getResources().getString(R.string.nyas_email_address); // Get the amil address from resources

        // Set the email intent to open the email activity and complete the email address
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailAddress, null));

        // Fill in subject string
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Request");

        // Start the email activity
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
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

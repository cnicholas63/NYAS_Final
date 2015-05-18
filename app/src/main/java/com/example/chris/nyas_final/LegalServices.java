package com.example.chris.nyas_final;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;


public class LegalServices extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_services);

        // Load nyas_legal_services.html into webview
        WebView webView;
        webView = (WebView) findViewById(R.id.legalServicesWebView);


        webView.loadUrl("file:///android_asset/webpages/nyas_legal_services.html");


//        File lFile = new File(Environment.getExternalStorageDirectory() + "<FOLDER_PATH_TO_FILE>/<FILE_NAME>");
//        lWebView.loadUrl("file:///" + lFile.getAbsolutePath());

    }

    /**
     * Helpline button clicked within Legal Services activity. Open the Contact Us activity
     * View view - the view from where the click originated
     */
    public void legalServicesHelpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_legal_services, menu);
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

package com.example.chris.nyas_final;
/**
 * Chris Nicholas 16 May 2015
 * OurServicesActivity. Displays the Our Services Activity, this include an expandable list view
 * Code for this was adapted from: http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
 */

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class OurServicesActivity extends ActionBarActivity {
    MyExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<List<String>> listContent; // ArrayList of String ArrayLists
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services);

        // expandable list view controller
        expListView = (ExpandableListView) findViewById(R.id.ourServicesExpandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new MyExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listContent = new ArrayList<List<String>>();


        // Array pointing to service description titles
        String[] sectionTitles = getResources().getStringArray(R.array.service_description_titles);
        // Array pointing to service descriptions
        String[] sectionDescriptions = getResources().getStringArray(R.array.service_descriptions);

        // Adding child data - this will be the section headings fro the expandable list
        listDataHeader = Arrays.asList(sectionTitles); // Generate ArrayList based on listDataHeader array

        // Add all section descriptions to their own ArrayList and append to listContent list
        for(int t = 0; t < sectionTitles.length; t++ ) {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(sectionDescriptions[t]);
            listContent.add(temp); // Add the description to ListContent
        }

        // Put the title and description into the listDataChild HashMap
        for(int t = 0; t < sectionTitles.length; t++) {
           listDataChild.put(sectionTitles[t],listContent.get(t));
        }
    }

    /**
     * Helpline button clicked, open the Contact Us activity
     * View view - the view from where the click originated
     */
    public void helpLineClicked(View view) {
        Intent intent = new Intent(this, HelplineActivity.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_our_services, menu);
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

package com.costinpapuc.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    private class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            if (button.getId() == R.id.show_button) {
                LinearLayout additionalDetails = (LinearLayout)findViewById(R.id.additional_details);
                if (additionalDetails.getVisibility() == View.GONE) {
                    additionalDetails.setVisibility(View.VISIBLE);
                    button.setText(R.string.hide_button_text);
                }
                else {
                    additionalDetails.setVisibility(View.GONE);
                    button.setText(R.string.show_button_text);
                }
            }
            if (button.getId() == R.id.save_button) {
                String name = ((EditText)findViewById(R.id.name_edit_text)).getText().toString();
                String number = ((EditText)findViewById(R.id.number_edit_text)).getText().toString();
                String email = ((EditText)findViewById(R.id.name_edit_text)).getText().toString();
                String address = ((EditText)findViewById(R.id.address_edit_text)).getText().toString();
                String job = ((EditText)findViewById(R.id.job_edit_text)).getText().toString();
                String company = ((EditText)findViewById(R.id.company_edit_text)).getText().toString();
                String website = ((EditText)findViewById(R.id.website_edit_text)).getText().toString();
                String im = ((EditText)findViewById(R.id.im_edit_text)).getText().toString();

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (number != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, number);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (job != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, job);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
            if (button.getId() == R.id.cancel_button) {
                finish();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        Button showButton   = (Button)findViewById(R.id.show_button);
        Button saveButton   = (Button)findViewById(R.id.save_button);
        Button cancelButton = (Button)findViewById(R.id.cancel_button);
        MyButtonListener myButtonListener = new MyButtonListener();

        showButton.setOnClickListener(myButtonListener);
        saveButton.setOnClickListener(myButtonListener);
        cancelButton.setOnClickListener(myButtonListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts_manager, menu);
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

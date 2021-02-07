package gparap.apps.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

/**
 * Created by gparap on 2020-09-22.
 */
public class MainActivity extends AppCompatActivity {
    Button buttonShowContacts;
    ArrayList<Contact> contacts;
    RecyclerView recyclerViewContacts;
    ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //request permission to read phone contacts and call phone numbers
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,
                                            Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);
        }
    }

    private void init() {
        buttonShowContacts = findViewById(R.id.buttonShowContacts);
        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        contacts = new ArrayList<>();
    }

    public void onClickShowContacts(View view) {
        //hide button
        view.setVisibility(View.INVISIBLE);

        //get all contacts
        Cursor cursorContacts = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        //if there are contacts
        assert cursorContacts != null : "cursorContacts is null";
        if (cursorContacts.getCount() > 0) {
            //loop through all contacts
            while (cursorContacts.moveToNext()) {
                String id = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                String hasPhoneNumber = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                //create a new contact object
                Contact contact = new Contact(id, name);

                //if contact has at least one phone number
                assert hasPhoneNumber != null : "hasPhoneNumber is null";
                if (Integer.parseInt(hasPhoneNumber) > 0) {
                    //get contact's phone number(s)
                    Cursor cursorPhoneNumbers = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                            new String[]{id},
                            null);

                    //if contact has phone number(s)
                    assert cursorPhoneNumbers != null : "cursorPhoneNumbers is null";
                    if (cursorPhoneNumbers.getCount() > 0) {
                        //loop through each phone number
                        while (cursorPhoneNumbers.moveToNext()) {
                            String phoneNumber = cursorPhoneNumbers.getString(cursorPhoneNumbers.getColumnIndex(ContactsContract
                                    .CommonDataKinds.Phone.NUMBER));

                            //add phone number(s) to contact object
                            if (contact.getPhoneNumberPrimary().isEmpty()) {
                                contact.setPhoneNumberPrimary(phoneNumber);
                            } else {
                                contact.setPhoneNumberSecondary(phoneNumber);
                            }
                        }
                    }
                    //free up cursor
                    cursorPhoneNumbers.close();
                }
                //add contact object to contacts list
                contacts.add(contact);
            }
        }
        //free up cursor
        cursorContacts.close();

        //show contacts
        contactsAdapter = new ContactsAdapter(this, contacts);
        recyclerViewContacts.setAdapter(contactsAdapter);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
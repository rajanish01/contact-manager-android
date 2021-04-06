package com.example.contactintentapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactintentapp.domain.ContactModel;

public class FormActivity extends AppCompatActivity {

    EditText contactName, contactNumber, contactWebsite, contactLocation;
    ImageView btnSmile, btnSenti, btnSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        contactName = findViewById(R.id.et_contact_name);
        contactNumber = findViewById(R.id.et_contact_number);
        contactWebsite = findViewById(R.id.et_contact_website);
        contactLocation = findViewById(R.id.et_contact_location);

        btnSmile = findViewById(R.id.img_smile);
        btnSenti = findViewById(R.id.img_senti);
        btnSad = findViewById(R.id.img_sad);

        ContactModel contact = new ContactModel();

        btnSmile.setOnClickListener(v -> {
            navigateToMainActivity(contact, R.drawable.ic_round_sentiment_satisfied_24);
        });

        btnSenti.setOnClickListener(v -> {
            navigateToMainActivity(contact, R.drawable.ic_baseline_sentiment_dissatisfied_24);
        });

        btnSad.setOnClickListener(v -> {
            navigateToMainActivity(contact, R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        });
    }

    private boolean checkBeforeNavigate(ContactModel contact) {
        if (contact.getName() == null || contact.getName().equals("")) {
            Toast.makeText(this, "Name Field Not Provided !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (contact.getLocation() == null || contact.getLocation().equals("")) {
            Toast.makeText(this, "Location Field Not Provided !", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (contact.getWebsite() == null || contact.getWebsite().equals("")) {
            Toast.makeText(this, "Website Field Not Provided !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void navigateToMainActivity(ContactModel contact, int thumbnailCode) {
        contact.setName(contactName.getText().toString());
        if (contactNumber.getText().toString().equals("")) {
            Toast.makeText(this, "Number Field Not Provided !", Toast.LENGTH_SHORT).show();
            return;
        }
        contact.setNumber(Integer.parseInt(contactNumber.getText().toString()));
        contact.setWebsite(contactWebsite.getText().toString());
        contact.setLocation(contactLocation.getText().toString());
        contact.setThumbnail(thumbnailCode);

        if (!checkBeforeNavigate(contact)) return;

        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.putExtra("data", contact);
        setResult(Activity.RESULT_OK, mainActivityIntent);
        finish();
    }
}
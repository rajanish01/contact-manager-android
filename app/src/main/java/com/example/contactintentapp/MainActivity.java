package com.example.contactintentapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactintentapp.domain.ContactModel;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    TextView tvName;
    ImageView imgThumbnail, imgNumber, imgWebsite, imgLocation;

    View hiddenLayout;

    private static final int MY_CHILD_ACTIVITY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btn_create);

        tvName = findViewById(R.id.tv_name);

        imgThumbnail = findViewById(R.id.img_thumbnail);
        imgLocation = findViewById(R.id.img_location);
        imgNumber = findViewById(R.id.img_number);
        imgWebsite = findViewById(R.id.img_website);

        hiddenLayout = findViewById(R.id.lt_hidden);

        btnCreate.setOnClickListener(v -> {
            Intent intentForFormActivity = new Intent(this, FormActivity.class);
            startActivityForResult(intentForFormActivity, MY_CHILD_ACTIVITY);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (MY_CHILD_ACTIVITY): {
                if (resultCode == Activity.RESULT_OK) {
                    ContactModel contactModel = (ContactModel) data.getSerializableExtra("data");
                    populateResultFromFormActivity(contactModel);
                }
                break;
            }
        }
    }

    private void populateResultFromFormActivity(ContactModel contact) {
        hiddenLayout.setVisibility(View.VISIBLE);
        tvName.setText(contact.getName());
        imgThumbnail.setImageResource(contact.getThumbnail());
        imgLocation.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=:" + contact.getLocation()));
            startActivity(intent);
        });

        imgNumber.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getNumber()));
            startActivity(intent);
        });

        imgWebsite.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + contact.getWebsite()));
            startActivity(intent);
        });
    }
}
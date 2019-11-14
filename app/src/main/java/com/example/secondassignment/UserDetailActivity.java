package com.example.secondassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailActivity extends AppCompatActivity {

     ImageView imageView;
     TextView tvN, tvG, tvD, tvC, tvP, tvE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        imageView= findViewById(R.id.ivUserImg);


        tvN = findViewById(R.id.tvUsrName);
        tvG = findViewById(R.id.tvUsrGender);
        tvD = findViewById(R.id.tvUsrDoB);
        tvC = findViewById(R.id.tvUsrCountry);
        tvP = findViewById(R.id.tvUsrPhone);
        tvE = findViewById(R.id.tvUsrEmail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String gender = intent.getStringExtra("Gender");
        String doB = intent.getStringExtra("DoB");
        String country = intent.getStringExtra("Country");
        String phone = intent.getStringExtra("Phone");
        String email = intent.getStringExtra("Email");
        String image = intent.getStringExtra("Image");



        String uri = "@drawable/" + image;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        imageView.setImageResource(imageResource);


        imageView.setImageResource(imageResource);
        tvN.setText("Name: "+name);
        tvG.setText("Gender: "+gender);
        tvD.setText("DoB: "+doB);
        tvC.setText("Country: "+country);
        tvP.setText("Phone: "+phone);
        tvE.setText("Email: "+email);


    }
}

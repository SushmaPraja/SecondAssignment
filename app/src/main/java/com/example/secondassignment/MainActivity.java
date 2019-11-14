package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.secondassignment.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{


    EditText editTextN,editTextE,editTextD,editTextP;
    RadioGroup radioG;
    Spinner spinner;
    Button buttonsubmit,buttonView;
    String name,gender,dob,country,email,image,phone;
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<User> usersList = new ArrayList<>();
    String[] img = {"user1", "user2"};

    Calendar calendardata = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendardata.set(Calendar.YEAR,i);
            calendardata.set(Calendar.MONTH,i1);
            calendardata.set(Calendar.DAY_OF_MONTH,i2);

            String mydateFormat ="dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            editTextD.setText(simpleDateFormat.format(calendardata.getTime()));


        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextN = findViewById(R.id.etName);
        editTextD = findViewById(R.id.etDob);
        editTextP = findViewById(R.id.etPhone);
        editTextE = findViewById(R.id.etEmail);
        radioG = findViewById(R.id.rgGender);
        spinner = findViewById(R.id.spCountry);
        buttonsubmit = findViewById(R.id.btnSubmit);
        buttonView = findViewById(R.id.btnView);
        List<String> countries = new ArrayList<>();


        countries.add(0,"Select");
        countries.add("Nepal");
        countries.add("India");
        countries.add("Pakistan");
        countries.add("Bhutan");
        countries.add("China");
        countries.add("Japan");

        ArrayAdapter<String> adapter =new ArrayAdapter(this,R.layout.spinner_values,countries);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> dummyimageadapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, img);
        autoCompleteTextView = findViewById(R.id.autocompletetextview);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(dummyimageadapter);



        radioG.setOnCheckedChangeListener(this);
        buttonsubmit.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        editTextD.setOnClickListener(this);

        setSpinnerValue();
    }

    @Override
    public void onClick(View view) {
        name = editTextN.getText().toString();
        dob=editTextD.getText().toString();
        image=autoCompleteTextView.getText().toString();
        email=editTextE.getText().toString();
        phone = editTextP.getText().toString();



        if(view.getId()==R.id.btnSubmit)
        {
            if(validate())
            {
                usersList.add(new User(name,gender,dob,country,phone,email,image));
                Toast.makeText(this,usersList.get(0).getName(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this,userList.get(0).getName(), Toast.LENGTH_SHORT).show();
            }


        }

        if(view.getId()==R.id.etDob)
        {
            new DatePickerDialog(this,mydatepicker,calendardata.get(Calendar.YEAR),calendardata.get(Calendar.MONTH),
                    calendardata.get(Calendar.DAY_OF_MONTH)).show();
        }

        if(view.getId()==R.id.btnView)
        {

//
            Intent intent = new Intent(this, RecyclerViewActivity.class);

            intent.putExtra("userlist",usersList);


            startActivity(intent);

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.rbMale)
        {
            gender="Male";
            //Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }
        if(i == R.id.rbFemale)
        {
            gender = "Female";
            //Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }
        if(i== R.id.rbOthers)
        {
            gender ="Other";
            //Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        if(TextUtils.isEmpty(name))
        {
            editTextN.setError("Enter A Name");
            editTextN.requestFocus();
            editTextN.setHint("Please Enter a Name");
            return false;
        }
        if(TextUtils.isEmpty(dob))
        {
            editTextD.setError("Enter A DOB");
            editTextD.requestFocus();
            editTextD.setHint("Please Enter a DOB");
            return false;
        }

        if(TextUtils.isEmpty(image))
        {
            autoCompleteTextView.setError("Enter a image name");
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.setHint("Please enter a image name");
            return false;
        }

        if(TextUtils.isEmpty(email))
        {
            editTextE.setError("Enter A Email");
            editTextE.requestFocus();
            editTextE.setHint("Please Enter a Email");
            return false;
        }

        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(country))
        {
            Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phone))
        {
            editTextP.setError("Enter empty Phone");
            editTextP.requestFocus();
            editTextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone))
        {
            editTextP.setError("Invalid Phone");
            editTextP.requestFocus();
            editTextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextE.setError("Invalid  Email");
            editTextE.requestFocus();
            editTextE.setHint("Please Enter a Email");
            return false;
        }

        return  true;
    }


    private void setSpinnerValue(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Choose Country"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select any one option",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    country = adapterView.getSelectedItem().toString();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

}

package com.example.codehead.criminalintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

public class local_register_activity extends AppCompatActivity {

    EditText email_id, name, phone, username, password, area_name, pincode;
    Button submit_local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_register_activity);

        email_id = (EditText) findViewById(R.id.email);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        area_name = (EditText) findViewById(R.id.area_name);
        pincode = (EditText) findViewById(R.id.pincode);

        submit_local = (Button) findViewById(R.id.submit_local);
        submit_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    public void sendData(){
        AndroidNetworking.post("http://sih-location-api.herokuapp.com/signup")
                .addBodyParameter("rank", "Local")
                .addBodyParameter("email_id", email_id.getText().toString())
                .addBodyParameter("name", name.getText().toString())
                .addBodyParameter("phone", phone.getText().toString())
                .addBodyParameter("username", username.getText().toString())
                .addBodyParameter("pwd", password.getText().toString())
                .addBodyParameter("area_name", area_name.getText().toString())
                .addBodyParameter("pincode", pincode.getText().toString())
                .setPriority(Priority.LOW)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0"))
                            Toast.makeText(getApplicationContext(), "Registration Not Successful", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(local_register_activity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}

package com.example.codehead.criminalintent;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class local_register_activity extends AppCompatActivity {

    EditText email_id, name, phone, username, password, area_name, pincode;
    Button submit_local;
    FirebaseAuth mAuth;
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
                insertIntoFireBase();
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

    public void insertIntoFireBase(){
        mAuth = FirebaseAuth.getInstance();
        final String email = email_id.getText().toString();
        final String passwordStr = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, passwordStr).addOnCompleteListener(local_register_activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(local_register_activity.this, "sign up error", Toast.LENGTH_SHORT).show();
                }else{
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_id);
                    current_user_db.setValue(true);
                }
            }
        });
    }
}

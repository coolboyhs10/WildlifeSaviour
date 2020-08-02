package com.example.codehead.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    EditText editUsername, editPassword;



    FirebaseAuth mAuth;
    Button local_registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.pass);

        local_registration = (Button) findViewById(R.id.local_registration);
        local_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, local_register_activity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.b_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignIn(editUsername.getText().toString() ,editPassword.getText().toString());
            }
        });


    }
    private void verifySignIn(String username, String password){
        getData(username, password);
    }

    private void getData(String username, String pwd){
        AndroidNetworking.post("http://sih-location-api.herokuapp.com/authuser")
                .addBodyParameter("username", username)
                .addBodyParameter("pwd", pwd)
                .setTag("GetData")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length() == 0)
                            Toast.makeText(getApplicationContext(), "INVALID USER OR PASSWORD", Toast.LENGTH_SHORT).show();
                        else{
                            try {
                                JSONObject obj = response.getJSONObject(0);
                                String rank = obj.getString("rank");

                                if(rank.equals("Admin")){
                                    Intent intent = new Intent(LoginActivity.this, admin_main_activity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else if(rank.equals("Regional Officer")){
                                    Intent intent = new Intent(LoginActivity.this, beat_officers_list_activity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else if(rank.equals("Beat Officer")){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else if (rank.equals("Local") || rank.equals("Tourist")){
                                    //insertIntoFireBase();
                                    Intent intent = new Intent(LoginActivity.this, local_main_activity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "ERROR IN LOGIN ACTIVITY", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

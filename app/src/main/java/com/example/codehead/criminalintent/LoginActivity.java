package com.example.codehead.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {


    EditText editUsername, editPassword;



    FirebaseAuth mAuth;


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

        findViewById(R.id.b_reg_crime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "register a crime", Toast.LENGTH_LONG).show();
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
        /*try {
            URL url = new URL("http://sih-location-api.herokuapp.com/authuser");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("username", username);
            jsonParam.put("pwd", password);
            Log.i("JSON", jsonParam.toString());

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonParam.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                Log.i("abc", response.toString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
        //sendPostData(username, password);
        getData(username, password);
        //Toast.makeText(LoginActivity.this, "logged in", Toast.LENGTH_LONG).show();
        //Intent newintent = new Intent(LoginActivity.this, MainActivity.class);
        //startActivity(newintent);
    }

    public void sendPostData(final String username, final String password){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://sih-location-api.herokuapp.com/authuser");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("username", username);
                    jsonParam.put("pwd", password);


                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    try(BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        Log.i("abc",response.toString());
                    }
                    os.flush();
                    os.close();

                    Log.i("MSG" , conn.getResponseMessage());
                    //Log.i.("ABC", )

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
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
                                }

                                else if(rank.equals("Regional Officer")){
                                    Intent intent = new Intent(LoginActivity.this, beat_officers_list_activity.class);
                                    startActivity(intent);
                                }

                                else if(rank.equals("Beat Officer")){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}

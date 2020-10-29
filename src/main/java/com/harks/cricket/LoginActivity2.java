package com.harks.cricket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class LoginActivity2 extends AppCompatActivity  {

    private EditText email;
    private AdView mAdView;
    private EditText pass;
    private Button Login;
    private TextView textView;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email=findViewById(R.id.email22);
        pass=findViewById(R.id.pas) ;
        Login=findViewById(R.id.but);
        textView=findViewById(R.id.txt2);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView1L);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(LoginActivity2.this,RegisterActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });


        auth=FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String txt_email=email.getText().toString();
                String txt_pass=pass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass) )
                {
                    Toast.makeText(LoginActivity2.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }else
                {
                    loginUser(txt_email,txt_pass);
                }



            }
        });
    }

    private void loginUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity2.this, "Update the profile for better experience", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity2.this, firstpageActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }




}
package com.harks.cricket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivityCricKo extends AppCompatActivity {

        private Button register;
        private Button login;
        private TextView texthj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register=findViewById(R.id.log1);
        login=findViewById(R.id.reg1);
        texthj=findViewById(R.id.textView2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityCricKo.this,RegisterActivity2.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityCricKo.this,LoginActivity2.class));

            }
        });

        texthj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityCricKo.this,firstpageActivity2.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() !=null)
        {
            startActivity(new Intent(MainActivityCricKo.this,firstpageActivity2.class));
            finish();
        }
    }
}
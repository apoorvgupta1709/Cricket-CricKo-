package com.harks.cricket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.HashMap;

public class RegisterActivity2 extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText pass;
    private Button register;
    private TextView login;
    private AdView mAdView;

    private FirebaseAuth auth;

    private DatabaseReference mRootRef;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        name =findViewById(R.id.name1);
        login=findViewById(R.id.textView1);
        email = findViewById(R.id.email);
        pass=findViewById((R.id.pass));
        register=findViewById((R.id.button2));
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView1R);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        auth=FirebaseAuth.getInstance();
        mRootRef= FirebaseDatabase.getInstance().getReference();
        pd= new ProgressDialog(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity2.this,LoginActivity2.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_name=name.getText().toString();
                String txt_email=email.getText().toString();
                String txt_pass=pass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass) || TextUtils.isEmpty(txt_name))
                {
                    Toast.makeText(RegisterActivity2.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }else if (txt_pass.length()<4)
                {
                    Toast.makeText(RegisterActivity2.this, "Password Too Short", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registerUser(txt_email,txt_pass,txt_name);
                }
            }
        });
    }


    private void registerUser(final String email, final String pass, final String name) {
        pd.setMessage("please wait");
        pd.show();

      auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
          @Override
          public void onSuccess(AuthResult authResult) {
              HashMap<String,Object> map=new HashMap<>();

              map.put("Name",name);
              map.put("email",email);
              map.put("password",pass);
              map.put("id",auth.getCurrentUser().getUid());

              mRootRef.child("user").child(auth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful())
                      {
                          pd.dismiss();
                          Toast.makeText(RegisterActivity2.this, "Update the profile for better experience", Toast.LENGTH_SHORT).show();
                          Intent intent=new Intent(RegisterActivity2.this,firstpageActivity2.class);
                          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                          startActivity(intent);
                          finish();
                      }

                  }
              });
          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              pd.dismiss();
              Toast.makeText(RegisterActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
      });
    }
}
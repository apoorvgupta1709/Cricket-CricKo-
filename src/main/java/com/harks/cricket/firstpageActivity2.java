package com.harks.cricket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;




import fragments.addFragment;
import fragments.homeFragment;
import fragments.todayFragment;
import fragments.yesterdayFragment;

public class firstpageActivity2 extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage2);

        bottomNavigationView = findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId())
                {
                    case R.id.home_img:

                        selectorFragment = new homeFragment();
                        break;

                    case R.id.today_img:
                        selectorFragment=new todayFragment();
                        break;

                    case R.id.yesterday_img:
                        selectorFragment=new yesterdayFragment();
                        break;

                    case R.id.add_img:
                        selectorFragment=null;
                        startActivity(new Intent(firstpageActivity2.this,PostActivity.class));
                        break;
                }
                if(selectorFragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectorFragment).commit();
                }
                return true;
            }
        });
       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();


    }
}
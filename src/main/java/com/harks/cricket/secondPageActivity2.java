package com.harks.cricket;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class secondPageActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<today> todaylist;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;
    private AdView mAdView1S;
    private AdView mAdView2S;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page2);



        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        myRef= FirebaseDatabase.getInstance().getReference();
        todaylist=new ArrayList<>();
        mAdView1S=findViewById(R.id.adView1S);
        mAdView2S=findViewById(R.id.adView2S);

        MobileAds.initialize(this, "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView1S = (AdView) findViewById(R.id.adView1S);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1S.loadAd(adRequest1);

        MobileAds.initialize(this, "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView2S = (AdView) findViewById(R.id.adView2S);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2S.loadAd(adRequest2);


        ClearAll();

        GetDataFromFirebase();


    }
    private void GetDataFromFirebase() {

        Query query=myRef.child("imageClick");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    today tod=new today();
                    try {
                        tod.setImageurl(snapshot1.child("image").getValue().toString());
                        tod.setName(snapshot1.child("name").getValue().toString());
                    }
                    catch (Exception e)
                    {

                    }

                    todaylist.add(tod);


                 }
                recyclerAdapter=new RecyclerAdapter(getApplicationContext(),todaylist);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ClearAll()
    {
        if(todaylist!=null)
        {
            todaylist.clear();
        }
        if (recyclerAdapter!=null)
        {
            recyclerAdapter.notifyDataSetChanged();
        }
        todaylist=new ArrayList<>();
    }
}
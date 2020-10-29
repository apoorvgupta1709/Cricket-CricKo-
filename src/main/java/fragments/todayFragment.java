package fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.harks.cricket.R;
import com.harks.cricket.RecyclerAdapter;
import com.harks.cricket.RegisterActivity2;
import com.harks.cricket.today;
import com.google.android.gms.ads.InterstitialAd;


import java.util.ArrayList;



public class todayFragment extends Fragment {

    private View contactsview;
    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    private ArrayList<today> todaylist;


    private RecyclerAdapter recyclerAdapter;

    private Context mContext;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mdView1;
    private Button Report;
    private Button Reportdoit;

    private InterstitialAd mInterstitialAd;




    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contactsview= inflater.inflate(R.layout.fragment_today, container, false);



        // AD on bottom of fragment
        MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView = (AdView) contactsview.findViewById(R.id.adView2);
               AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        // Ad on top of fragment
        MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView1 = (AdView) contactsview.findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        recyclerView=(RecyclerView) contactsview.findViewById(R.id.recycleviewid);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);


        myRef= FirebaseDatabase.getInstance().getReference();
        todaylist=new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();



        return contactsview;
    }

    private void GetDataFromFirebase() {

        Query query=myRef.child("today");
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
                recyclerAdapter=new RecyclerAdapter(getContext(),todaylist);

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
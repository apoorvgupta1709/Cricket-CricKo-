package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.harks.cricket.R;
import com.harks.cricket.RecyclerAdapter;
import com.google.android.gms.ads.InterstitialAd;
import com.harks.cricket.today;

import java.util.ArrayList;


public class homeFragment extends Fragment {

    private AdView mAdView1H;
    private RecyclerView recyclerView2;
    private RecyclerAdapter recyclerAdapter2;

    private AdView mAdView2H;

    private InterstitialAd mInterstitialAd;

    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    private ArrayList<today> todaylist;


    private RecyclerAdapter recyclerAdapter;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View rootview= inflater.inflate(R.layout.fragment_home, container, false);




//        //ad on Top
        MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView1H = (AdView) rootview.findViewById(R.id.adView1H);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1H.loadAd(adRequest1);

        // ad on Bottom
        MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView2H = (AdView) rootview.findViewById(R.id.adView2H);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2H.loadAd(adRequest2);






        recyclerView=(RecyclerView) rootview.findViewById(R.id.recycleviewidH);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);


        myRef= FirebaseDatabase.getInstance().getReference();
        todaylist=new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();

        return rootview;



    }

    private void GetDataFromFirebase() {

        Query query=myRef.child("home");
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
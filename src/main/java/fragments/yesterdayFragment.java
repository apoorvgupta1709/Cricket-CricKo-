package fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.harks.cricket.R;
import com.harks.cricket.RecyclerAdapter;
import com.harks.cricket.today;

import java.util.ArrayList;



public class yesterdayFragment extends Fragment {
    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    private ArrayList<today> todaylist;

    private RecyclerAdapter recyclerAdapter;
    private AdView mAdView1Y;
    private AdView mAdView2Y;
    private View contactsview;

    private Context mContext;
    public yesterdayFragment()
    {

    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         contactsview = inflater.inflate(R.layout.fragment_yesterday, container, false);




        try {
            MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
            AdView mAdView2Y = (AdView) contactsview.findViewById(R.id.adView1Y);
            AdRequest adRequest2Y = new AdRequest.Builder().build();
            mAdView2Y.loadAd(adRequest2Y);

            MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
            AdView mAdView1Y = (AdView) contactsview.findViewById(R.id.adView2Y);
            AdRequest adRequest1Y = new AdRequest.Builder().build();
            mAdView1Y.loadAd(adRequest1Y);
        }
        catch(Exception e)
        {

        }


        recyclerView=(RecyclerView) contactsview.findViewById(R.id.recycc);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);


        myRef= FirebaseDatabase.getInstance().getReference();
        todaylist=new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();

        return contactsview;
    }

    private void GetDataFromFirebase() {

        Query query=myRef.child("yesterday");
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
package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class addFragment extends Fragment {

    private View contactsview2;
    private RecyclerView recyclerView;

    private DatabaseReference myRef;
    private ArrayList<today> todaylist;
    private RecyclerAdapter recyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contactsview2 =inflater.inflate(R.layout.fragment_add, container, false);



        MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView = (AdView) contactsview2.findViewById(R.id.adView1A);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(getActivity(), "ca-app-pub-2281923791402266/6070207490");
        AdView mAdView2 = (AdView) contactsview2.findViewById(R.id.adView2A);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);



        recyclerView=(RecyclerView) contactsview2.findViewById(R.id.RecyclerADD);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);


        myRef= FirebaseDatabase.getInstance().getReference();
        todaylist=new ArrayList<>();
        ClearAll();

        GetDataFromFirebase();




        return contactsview2;
    }
    private void GetDataFromFirebase() {

        Query query=myRef.child("add");
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
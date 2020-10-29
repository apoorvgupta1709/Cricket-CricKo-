package com.harks.cricket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {



    private static final String Tag ="RecyclerView";

    private InterstitialAd mInterstitialAd;

    private Context mContext;
    private ArrayList<today> todaylist;
    private Button Report;
    ImageView imageViewClick;
    TextView textimageClick;
    private Object adsManager;
    private AdView adview;


    public RecyclerAdapter(Context mContext, ArrayList<today> todaylist) {
        this.mContext = mContext;
        this.todaylist = todaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.today_item,parent,false);



        //interstial Ads on all fragment
        textimageClick=view.findViewById(R.id.textView);
        imageViewClick=view.findViewById(R.id.imageview);
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId("ca-app-pub-2281923791402266/3097317960");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        textimageClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    Intent intent=new Intent(mContext,secondPageActivity2.class);
                    mContext.startActivity(intent);

                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
        imageViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    Intent intent=new Intent(mContext,secondPageActivity2.class);
                    mContext.startActivity(intent);

                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });


       // Native Ads
//        adview=view.findViewById(R.id.my_template);
//        adsManager adsManager=new adsManager(mContext);
//       adsManager.createAds(adview);
//
//        adsManager.createUnifiedAds(R.string.ads_native_ads, new AdsunifiedListening() {
//            @Override
//            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//
//                TemplateView ads=view.findViewById(R.id.my_template);
//                NativeTemplateStyle style=new NativeTemplateStyle.Builder().withMainBackgroundColor(new ColorDrawable(Color.parseColor("#ffffff"))).build();
//
//                ads.setStyles(style);
//                ads.setNativeAd(unifiedNativeAd);
//            }
//        });

        









        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.textView.setText(todaylist.get(position).getName());
        GlideApp.with(mContext).load(todaylist.get(position).getImageurl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
    return todaylist.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        // Ad for todays fragment
        private AdView mAdView;
        private AdView mAdView1;
        private AdView mAdView3;
        private AdView mAdView4;

        // ads for home fragment
        private AdView mAdView1H;
        private AdView mAdView2H;

        // ads for yesterday frgment

        private AdView mAdView1Y;
        private AdView mAdView2Y;
        Button Reportdoit;

        //ads for add fragment
        private AdView mAdView1A;
        private AdView mAdView2A;





        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.textView);

            //ad for today fragment
            mAdView=itemView.findViewById(R.id.adView2);
            mAdView1=itemView.findViewById(R.id.adView1);


            // ad for home fragment
            mAdView1H=itemView.findViewById(R.id.adView1H);
            mAdView2H=itemView.findViewById(R.id.adView2H);

            // ads for yesterday fragment
            mAdView1Y=itemView.findViewById(R.id.adView1Y);
            mAdView2Y=itemView.findViewById(R.id.adView2Y);

           // Reportdoit=itemView.findViewById(R.id.ReportDoit);

            // ads for add fragment
            mAdView1A=itemView.findViewById(R.id.adView1A);
            mAdView2A=itemView.findViewById(R.id.adView2A);








        }
    }
//    public void createUnifiedAds(int unitid, AdsunifiedListening listening)
//    {
//        AdLoader.Builder builder=new AdLoader.Builder(mContext,mContext.getString(unitid));
//
//        builder.forUnifiedNativeAd(listening);
//        builder.withAdListener(listening);
//        AdLoader adLoad =builder.build();
//        adLoad.loadAd(new AdRequest.Builder().build());
//
//
//    }

}

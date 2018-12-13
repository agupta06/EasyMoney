package com.resolvebug.app.easymoney;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class PointsRedeemHistoryFragment extends Fragment {

    private ImageView backButton;
    public AdView bannerAdRedeemHistoryPageBottom;
    public AdView bannerAdRedeemHistoryPageOne;
    public AdView bannerAdRedeemHistoryPageTwo;
    public AdView bannerAdRedeemHistoryPageThree;

    public PointsRedeemHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_points_redeem_history, container, false);
        initialize(view);
        setBannerAd();
        pressBackButton();
        return view;
    }

    private void initialize(View view) {
        backButton = view.findViewById(R.id.backButton);
        bannerAdRedeemHistoryPageBottom = view.findViewById(R.id.bannerAdRedeemHistoryPageBottom);
        bannerAdRedeemHistoryPageOne = view.findViewById(R.id.bannerAdRedeemHistoryPageOne);
        bannerAdRedeemHistoryPageTwo = view.findViewById(R.id.bannerAdRedeemHistoryPageTwo);
        bannerAdRedeemHistoryPageThree = view.findViewById(R.id.bannerAdRedeemHistoryPageThree);
    }

    private void setBannerAd() {
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5CE8DC1CD7BCEE2D6F7B8E22B0538BAB")
                .build();
        bannerAdRedeemHistoryPageBottom.loadAd(request);
        bannerAdRedeemHistoryPageBottom.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdRedeemHistoryPageBottom.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdRedeemHistoryPageBottom.setVisibility(View.GONE);
            }
        });

        bannerAdRedeemHistoryPageOne.loadAd(request);
        bannerAdRedeemHistoryPageOne.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdRedeemHistoryPageOne.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdRedeemHistoryPageOne.setVisibility(View.GONE);
            }
        });

        // two
        bannerAdRedeemHistoryPageTwo.loadAd(request);
        bannerAdRedeemHistoryPageTwo.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdRedeemHistoryPageTwo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdRedeemHistoryPageTwo.setVisibility(View.GONE);
            }
        });

        // three
        bannerAdRedeemHistoryPageThree.loadAd(request);
        bannerAdRedeemHistoryPageThree.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdRedeemHistoryPageThree.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdRedeemHistoryPageThree.setVisibility(View.GONE);
            }
        });
    }

    private void pressBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}

package com.resolvebug.app.easymoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private Button googleSignOutButton;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth firebaseAuth;
    public AdView bannerAdMainPageBottom;
    public AdView bannerAdMainPageOne;
    public AdView bannerAdMainPageTwo;
    public AdView bannerAdMainPageThree;
    public AdView bannerAdMainPageFour;
    public AdView bannerAdMainPageFive;
    private RewardedVideoAd rewardedVideoAd;
    private TextView totalRewardedPoints;
    private int rewardAmount = 0;
    private ImageView referralHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        rewardedVideoAd.setRewardedVideoAdListener(this);
        loadChannelsVideoAd();
        setBannerAd();

        openPointsRedeemHistoryFragment();

        appLogout();

    }

    private void openPointsRedeemHistoryFragment() {
        referralHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddTransactionFragment();
            }
        });
    }

    private void initialize() {
        googleSignOutButton = findViewById(R.id.googleSignOutButton);
        firebaseAuth = FirebaseAuth.getInstance();
        bannerAdMainPageBottom = findViewById(R.id.bannerAdMainPageBottom);
        bannerAdMainPageOne = findViewById(R.id.bannerAdMainPageOne);
        bannerAdMainPageTwo = findViewById(R.id.bannerAdMainPageTwo);
        bannerAdMainPageThree = findViewById(R.id.bannerAdMainPageThree);
        bannerAdMainPageFour = findViewById(R.id.bannerAdMainPageFour);
        bannerAdMainPageFive = findViewById(R.id.bannerAdMainPageFive);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        totalRewardedPoints = findViewById(R.id.totalRewardedPoints);
        referralHistory = findViewById(R.id.referralHistory);
    }

    private void setBannerAd() {
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("5CE8DC1CD7BCEE2D6F7B8E22B0538BAB")
                .build();
        bannerAdMainPageBottom.loadAd(request);
        bannerAdMainPageBottom.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdMainPageBottom.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdMainPageBottom.setVisibility(View.GONE);
            }
        });

// one
        bannerAdMainPageOne.loadAd(request);
        bannerAdMainPageOne.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdMainPageOne.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdMainPageOne.setVisibility(View.GONE);
            }
        });

        // two
        bannerAdMainPageTwo.loadAd(request);
        bannerAdMainPageTwo.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdMainPageTwo.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdMainPageTwo.setVisibility(View.GONE);
            }
        });

        // three
        bannerAdMainPageThree.loadAd(request);
        bannerAdMainPageThree.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdMainPageThree.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdMainPageThree.setVisibility(View.GONE);
            }
        });

        // four
        bannerAdMainPageFour.loadAd(request);
        bannerAdMainPageFour.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdMainPageFour.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdMainPageFour.setVisibility(View.GONE);
            }
        });

        // five
        bannerAdMainPageFive.loadAd(request);
        bannerAdMainPageFive.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdMainPageFive.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdMainPageFive.setVisibility(View.GONE);
            }
        });
    }

    private void appLogout() {
        googleSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
            }
        });
        signOut();
    }

    private void signOut() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {    // user logged out
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void loadChannelsVideoAd() {
        loadRewardedVideoAd("ca-app-pub-3940256099942544/5224354917");  // channel 1
        loadRewardedVideoAd("ca-app-pub-3940256099942544/5224354917");  // channel 2
        loadRewardedVideoAd("ca-app-pub-3940256099942544/5224354917");  // channel 3
        loadRewardedVideoAd("ca-app-pub-3940256099942544/5224354917");  // channel 4
        loadRewardedVideoAd("ca-app-pub-3940256099942544/5224354917");  // channel 5
    }

    private void loadRewardedVideoAd(String adUnit) {
        if (!rewardedVideoAd.isLoaded()) {
            AdRequest request = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("5CE8DC1CD7BCEE2D6F7B8E22B0538BAB")
                    .build();
            rewardedVideoAd.loadAd(adUnit, request);
        }
    }

    public void startChannelVideo(View view) {
        if (rewardedVideoAd.isLoaded()) {
            rewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadChannelsVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        rewardAmount += rewardItem.getAmount();
        totalRewardedPoints.setText(rewardAmount);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    protected void onPause() {
        rewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        rewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        rewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    private void openAddTransactionFragment() {
        Fragment fragment = new PointsRedeemHistoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack("container");
        fragmentTransaction.commit();
    }
}
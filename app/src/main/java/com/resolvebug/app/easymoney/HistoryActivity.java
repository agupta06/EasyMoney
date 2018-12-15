package com.resolvebug.app.easymoney;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class HistoryActivity extends AppCompatActivity {

    private ImageView backButton;
    public AdView bannerAdRedeemHistoryPageBottom;
    public AdView bannerAdRedeemHistoryPageOne;
    public AdView bannerAdRedeemHistoryPageTwo;
    public AdView bannerAdRedeemHistoryPageThree;
    private TextView pageTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initialize();
        setTitleFont();
        setBannerAd();
        pressBackButton();
    }

    private void initialize() {
        backButton = findViewById(R.id.backButton);
        bannerAdRedeemHistoryPageBottom = findViewById(R.id.bannerAdRedeemHistoryPageBottom);
        bannerAdRedeemHistoryPageOne = findViewById(R.id.bannerAdRedeemHistoryPageOne);
        bannerAdRedeemHistoryPageTwo = findViewById(R.id.bannerAdRedeemHistoryPageTwo);
        bannerAdRedeemHistoryPageThree = findViewById(R.id.bannerAdRedeemHistoryPageThree);
        pageTitle = findViewById(R.id.pageTitle);
        MobileAds.initialize(this, "ca-app-pub-7589353131090263~4287498139");   // Correct App ID
    }

    private void setTitleFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        pageTitle.setTypeface(typeface);
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
                finish();
            }
        });
    }

    public void appLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient;
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(HistoryActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }
}

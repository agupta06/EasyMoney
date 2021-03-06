package com.resolvebug.app.easymoney;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener, CashOutDialog.CashOutDialogListener {

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
    private TextView pageTitle;
    private RetrofitApiInterface retrofitApiInterface;
    private UserDetailsPojo userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        getUserDetails("cartergupta@gmail.com");
        loadChannelsVideoAd();
        setBannerAd();
        openPointsRedeemHistoryFragment();
        setTitleFont();
    }

    private void openPointsRedeemHistoryFragment() {
        referralHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoryActivity();
            }
        });
    }

    private void initialize() {
        bannerAdMainPageBottom = findViewById(R.id.bannerAdMainPageBottom);
        bannerAdMainPageOne = findViewById(R.id.bannerAdMainPageOne);
        bannerAdMainPageTwo = findViewById(R.id.bannerAdMainPageTwo);
        bannerAdMainPageThree = findViewById(R.id.bannerAdMainPageThree);
        bannerAdMainPageFour = findViewById(R.id.bannerAdMainPageFour);
        bannerAdMainPageFive = findViewById(R.id.bannerAdMainPageFive);
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");     // Test App ID
        MobileAds.initialize(this, "ca-app-pub-7589353131090263~4287498139");   // Correct App ID
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        totalRewardedPoints = findViewById(R.id.totalRewardedPoints);
        referralHistory = findViewById(R.id.referralHistory);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        pageTitle = findViewById(R.id.pageTitle);
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void loadChannelsVideoAd() {
//        loadRewardedVideoAd("ca-app-pub-3940256099942544/5224354917");  // test ad unit id
        loadRewardedVideoAd("ca-app-pub-7589353131090263/8820148825");  // Correct Rewarded1
        loadRewardedVideoAd("ca-app-pub-7589353131090263/6628389319");  // Correct Rewarded2
        loadRewardedVideoAd("ca-app-pub-7589353131090263/9431357061");  // Correct Rewarded3
        loadRewardedVideoAd("ca-app-pub-7589353131090263/6422050346");  // Correct Rewarded4
        loadRewardedVideoAd("ca-app-pub-7589353131090263/9937078936");  // Correct Rewarded5
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
        totalRewardedPoints.setText("hello");
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

    private void openHistoryActivity() {
        startActivity(new Intent(MainActivity.this, HistoryActivity.class));
    }


    public void openCashOutDialog(View view) {
        CashOutDialog cashOutDialog = new CashOutDialog();
        cashOutDialog.show(getSupportFragmentManager(), "Cash Out Dialog");
    }

    @Override
    public void fetchPaypalEmail(String paypalEmail) {
        Toast.makeText(MainActivity.this, "Paypal Email : " + paypalEmail, Toast.LENGTH_LONG).show();
    }

    public void openReferralDialog(View view) {
        ReferralDialog referralDialog = new ReferralDialog();
        Bundle bundle = new Bundle();
        bundle.putString("referralCode", userDetails.getReferralCode());
        referralDialog.setArguments(bundle);
        referralDialog.show(getSupportFragmentManager(), "Referral Dialog");
    }

    private void getUserDetails(String email) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading ...");
//        progress.setMessage("Please wait till the video is loaded...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        userDetails = new UserDetailsPojo();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApiInterface = retrofit.create(RetrofitApiInterface.class);
        UserDetailsPojo userDetailsPojo = new UserDetailsPojo(email);
        Call<UserDetailsPojo> call = retrofitApiInterface.getUserDetails(userDetailsPojo);
        call.enqueue(new Callback<UserDetailsPojo>() {
            @Override
            public void onResponse(Call<UserDetailsPojo> call, Response<UserDetailsPojo> response) {
                totalRewardedPoints.setText(response.body().getPointsEarned());
                userDetails.setEmail(response.body().getEmail());
                userDetails.setJoiningBonusGiven(response.body().getJoiningBonusGiven());
                userDetails.setAppliedReferralCode(response.body().getAppliedReferralCode());
                userDetails.setPaypalEmail(response.body().getPaypalEmail());
                userDetails.setPointsEarned(response.body().getPointsEarned());
                userDetails.setReferralCode(response.body().getReferralCode());
            }

            @Override
            public void onFailure(Call<UserDetailsPojo> call, Throwable t) {
            }
        });
        progress.dismiss();
    }

}
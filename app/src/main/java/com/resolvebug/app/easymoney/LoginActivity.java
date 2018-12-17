package com.resolvebug.app.easymoney;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.security.SecureRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private SignInButton googleSignInButton;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient googleApiClient;
    public AdView bannerAdLoginPage;
    private RetrofitApiInterface retrofitApiInterface;
    private TextView pageTitle;
private TextInputEditText appliedReferralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        setTitleFont();
        setBannerAd();
        checkExistingUser();
        signIn();
        configureGoogleSignIn();
    }

    private void initialize() {
        mAuth = FirebaseAuth.getInstance();
        googleSignInButton = findViewById(R.id.googleSignInButton);
        bannerAdLoginPage = findViewById(R.id.bannerAdLoginPage);
        pageTitle = findViewById(R.id.pageTitle);
        MobileAds.initialize(this, "ca-app-pub-7589353131090263~4287498139");
        appliedReferralCode = findViewById(R.id.appliedReferralCode);
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
        bannerAdLoginPage.loadAd(request);
        bannerAdLoginPage.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                bannerAdLoginPage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int error) {
                bannerAdLoginPage.setVisibility(View.GONE);
            }
        });
    }

    private void checkExistingUser() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {    // existing user
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
    }

    private void signIn() {
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void configureGoogleSignIn() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(LoginActivity.this, "Google sign in failed", Toast.LENGTH_LONG).show();

                // ...
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Toast.makeText(LoginActivity.this, "firebaseAuthWithGoogle:" + acct.getId(), Toast.LENGTH_LONG).show();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "signInWithCredential:", Toast.LENGTH_LONG).show();

                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "signInWithCredential:failure:" + task.getException(), Toast.LENGTH_LONG).show();

                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_LONG).show();

                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String personEmail = acct.getEmail();
            saveUserDetails(personEmail, getAlphaNumeric(6), appliedReferralCode.getText().toString());
        }
    }

    private void saveUserDetails(String email, String referralCode, String appliedReferralCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApiInterface = retrofit.create(RetrofitApiInterface.class);
        UserDetailsPojo userDetailsPojo = new UserDetailsPojo(email, referralCode, appliedReferralCode);
        Call<UserDetailsPojo> call = retrofitApiInterface.saveUserDetails(userDetailsPojo);
        call.enqueue(new Callback<UserDetailsPojo>() {
            @Override
            public void onResponse(Call<UserDetailsPojo> call, Response<UserDetailsPojo> response) {
                Toast.makeText(LoginActivity.this, "Retrofit Success : ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserDetailsPojo> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getAlphaNumeric(int len) {
        char[] ch = "123456789abcdefghjkmnpqrstuvwxyz".toCharArray();
        char[] c = new char[len];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }
        return new String(c);
    }

}

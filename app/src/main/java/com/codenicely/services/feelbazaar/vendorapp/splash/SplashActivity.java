package com.codenicely.services.feelbazaar.vendorapp.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.wikiedufoundation.wikiedudashboard.R;
import com.codenicely.services.feelbazaar.vendorapp.helper.SharedPrefs;
import com.codenicely.services.feelbazaar.vendorapp.home.HomeActivity;
import com.codenicely.services.feelbazaar.vendorapp.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Context context= this;
        sharedPrefs = new SharedPrefs(context);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            if (sharedPrefs.isLoggedIn()){
                startActivity(new Intent(context, HomeActivity.class));
                finish();
            }else {
                startActivity(new Intent(context,WelcomeActivity.class));
                finish();
            }
            }
        }, 1000);
    }
}

package com.example.rssdemo;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SplashScreenActivity extends Activity {

	
	private final Handler mHandler = new Handler();
	 
    private final Runnable mPendingLauncherRunnable = new Runnable() {
        public void run() {
            
            Intent intent = new Intent(SplashScreenActivity.this, ChannelActivity.class);
            startActivity(intent);
            finish();
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		mHandler.postDelayed(mPendingLauncherRunnable, 5000);
	}

	

}

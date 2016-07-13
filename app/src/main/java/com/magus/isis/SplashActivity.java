package com.magus.isis;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.magus.isis.local.LocalCacheKey;

import org.apache.commons.lang3.StringUtils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends Activity {

    private final Handler mHideHandler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        mHideHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences(LocalCacheKey.USER, MODE_PRIVATE);
                String accessToken = pref.getString(LocalCacheKey.ACCESSTOKEN,null);
                finish();
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
//                if(StringUtils.isBlank(accessToken)){
//                    finish();
//                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
//                    startActivity(intent);
//                }else{
//                    finish();
//                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
            }
        },500);
    }

}

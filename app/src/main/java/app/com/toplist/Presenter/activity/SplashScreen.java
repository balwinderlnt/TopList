package app.com.toplist.Presenter.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import app.com.toplist.R;
import app.com.toplist.Utility.Utility;

/**
 * Created by Balwinder on 7/29/2017.
 */

public class SplashScreen extends Activity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MasterScreen.class);
                startActivity(i);
                finish();
            }
        },3000);
    }


}

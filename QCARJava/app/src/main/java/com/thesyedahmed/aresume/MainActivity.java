package com.thesyedahmed.aresume;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.unity3d.player.UnityPlayer;


public class MainActivity extends AppCompatActivity {
    protected UnityPlayer mUnityPlayer;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Context mContext = this.getApplicationContext();
        mPrefs = mContext.getSharedPreferences("myAppPrefs", 0);
        if(getFirstRun()){
            setRunned();
            Intent intent = new Intent(this, AppIntroductionActivity.class);
            startActivity(intent);
            getWindow().takeSurface(null);
            getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

            mUnityPlayer = new UnityPlayer(this);
            if (mUnityPlayer.getSettings ().getBoolean ("hide_status_bar", true)){
                getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }


            setContentView(mUnityPlayer);
            mUnityPlayer.requestFocus();

        }
        else{
            getWindow().takeSurface(null);
            getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

            mUnityPlayer = new UnityPlayer(this);
            if (mUnityPlayer.getSettings ().getBoolean ("hide_status_bar", true))
                getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(mUnityPlayer);
            mUnityPlayer.requestFocus();
        }
    }

    public boolean getFirstRun() {
        return mPrefs.getBoolean("firstRun", true);
    }

    public void setRunned() {
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putBoolean("firstRun", false);
        edit.commit();
    }

    public void callPhone(final String number){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Uri call = Uri.parse("tel:" + number);
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, call);
                startActivity(dialIntent);
            }
        });
    }

    public void openWebsite(final String url){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = WebPageActivity
                        .newIntent(MainActivity.this, Uri.parse(url));
                startActivity(i);
            }
        });

    }

    public void sendEmail(final String address){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", address, null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }

    public void openProjects(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent projects = new Intent(MainActivity.this, ProjectsActivity.class);
                startActivity(projects);

            }
        });
    }

    @Override protected void onDestroy (){
        mUnityPlayer.quit();
        super.onDestroy();

    }

    // Pause Unity
    @Override protected void onPause(){
        super.onPause();
        mUnityPlayer.pause();



    }

    // Resume Unity
    @Override protected void onResume(){
        super.onResume();
        mUnityPlayer.resume();

    }

    // This ensures the layout will be correct.
    @Override public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.app_intro:
                Intent intro_intent = new Intent(MainActivity.this, AppIntroductionActivity.class);
                startActivity(intro_intent);
                return true;
            case R.id.about:
                Intent about_intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

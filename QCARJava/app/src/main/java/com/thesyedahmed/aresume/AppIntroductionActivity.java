package com.thesyedahmed.aresume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by steakpizza on 8/26/2015.
 */
public class AppIntroductionActivity extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.tutorial0));
        addSlide(SampleSlide.newInstance(R.layout.tutorial1));
        addSlide(SampleSlide.newInstance(R.layout.tutorial2));
        addSlide(SampleSlide.newInstance(R.layout.tutorial3));
        addSlide(SampleSlide.newInstance(R.layout.tutorial4));
        addSlide(SampleSlide.newInstance(R.layout.tutorial5));
        addSlide(SampleSlide.newInstance(R.layout.tutorial6));
        addSlide(SampleSlide.newInstance(R.layout.tutorial7));
        addSlide(SampleSlide.newInstance(R.layout.tutorial8));
        setFadeAnimation();
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }

}

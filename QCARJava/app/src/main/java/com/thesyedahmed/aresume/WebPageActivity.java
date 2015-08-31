package com.thesyedahmed.aresume;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by steakpizza on 8/14/2015.
 */
public class WebPageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, Uri webPageUri){
        Intent i = new Intent(context, WebPageActivity.class);
        i.setData(webPageUri);
        return i;

    }

    @Override
    protected Fragment createFragment(){
        return WebPageFragment.newInstance(getIntent().getData());
    }

    @Override
    public void onBackPressed(){
        if(WebPageFragment.getWebView().canGoBack()){
            WebPageFragment.getWebView().goBack();
        }else{
            super.onBackPressed();
        }
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
                Intent intro_intent = new Intent(this, AppIntroductionActivity.class);
                startActivity(intro_intent);
                return true;
            case R.id.about:
                Intent about_intent = new Intent(this, AboutActivity.class);
                startActivity(about_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

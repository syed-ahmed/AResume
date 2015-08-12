package com.thesyedahmed.aresume;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;

import com.qualcomm.QCARUnityPlayer.DebugLog;
import com.unity3d.player.UnityPlayerNativeActivity;


public class MainActivity extends UnityPlayerNativeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final long delay = 5000;//ms

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                ViewGroup rootView = (ViewGroup) MainActivity.this.findViewById
                        (android.R.id.content);

                // find the first leaf view (i.e. a view without children)
                // the leaf view represents the topmost view in the view stack
                View topMostView = getLeafView(rootView);

                // let's add a sibling to the leaf view
                ViewGroup leafParent = (ViewGroup) topMostView.getParent();
                Button sampleButton = new Button(MainActivity.this);
                sampleButton.setText("Press Me");
                leafParent.addView(sampleButton, new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));

            }
        };

        handler.postDelayed(runnable, delay);

    }

    private View getLeafView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); ++i) {
                View chview = vg.getChildAt(i);
                View result = getLeafView(chview);
                if (result != null)
                    return result;
            }
            return null;
        } else {
            DebugLog.LOGE("Found leaf view");
            return view;
        }
    }
}

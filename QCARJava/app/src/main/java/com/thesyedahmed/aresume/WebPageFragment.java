package com.thesyedahmed.aresume;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by steakpizza on 8/13/2015.
 */
public class WebPageFragment extends Fragment {
    private static final String ARG_URI = "web_page_url";
    private Uri mUri;
    private WebView mWebView;

    public static WebPageFragment newInstance (Uri uri){
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);
        WebPageFragment fragment = new WebPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_web_page, container, false);
        mWebView = (WebView) v.findViewById(R.id.fragment_web_page_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });
        mWebView.loadUrl(mUri.toString());
        return v;
    }
}

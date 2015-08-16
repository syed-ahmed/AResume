package com.thesyedahmed.aresume;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by steakpizza on 8/13/2015.
 */
public class WebPageFragment extends Fragment {
    private static final String ARG_URI = "web_page_url";
    private Uri mUri;
    private static WebView mWebView;
    private ProgressBar mProgressBar;

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
        mProgressBar = (ProgressBar) v.findViewById(R.id.fragment_web_page_progress_bar);
        mProgressBar.setMax(100);
        mWebView = (WebView) v.findViewById(R.id.fragment_web_page_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }

            }

            public void onReceivedTitle(WebView webView, String title) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.getSupportActionBar().setSubtitle(title);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!(Uri.parse(url).getScheme().equals("http") || Uri.parse(url).getScheme().equals("https"))) {
                    try {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        return true;
                    } catch (ActivityNotFoundException e) {
                        return false;
                    }

                }
                return false;
            }
        });

        mWebView.loadUrl(mUri.toString());
        return v;
    }

    public static WebView getWebView() {
        return mWebView;
    }
}

package com.example.induja.attractionsandrestaurants_app02;

/**
 * Created by Induja on 3/31/2018.
 */
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RestaurantWebFragment extends Fragment {
    private static final String TAG = "RESTAURANTWebFragment";


    private WebView mRestListsWebView;
    private int mIndexCurrent = -1;
    private int mRestWebLen;

    int getShownIndex() {
        return mIndexCurrent;
    }

    // Show the Quote string at position newIndex
    void showWebsiteAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mRestWebLen)
            return;
        mIndexCurrent = newIndex;
        mRestListsWebView = (WebView) getActivity().findViewById(R.id.rest_web_view);
        mRestListsWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mRestListsWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mRestListsWebView.loadUrl(SecondActivty.mRestWebURLArray[mIndexCurrent]);

    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, getClass().getSimpleName() + ":onConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        return inflater.inflate(R.layout.restaurant_web, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        mRestListsWebView = (WebView) getActivity().findViewById(R.id.rest_web_view);
        mRestWebLen = SecondActivty.mRestWebURLArray.length;
        showWebsiteAtIndex(mIndexCurrent);
    }


    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }


    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }
}
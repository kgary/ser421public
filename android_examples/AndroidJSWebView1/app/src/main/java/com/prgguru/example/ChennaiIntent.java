package com.prgguru.example;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ChennaiIntent extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WebView browser;
        browser=(WebView)findViewById(R.id.webkit);   
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("http://apps.programmerguru.com/examples/chennai.html");
    }
}

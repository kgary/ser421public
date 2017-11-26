package edu.asu.ser421.samplehybrid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {
    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // Get what was passed to us
        final String name = getIntent().getStringExtra("NAME");

        browser = (WebView)findViewById(R.id.webView);

        browser.setWebContentsDebuggingEnabled(true);

        browser.setWebChromeClient(new WebChromeClient());

        // enable JS
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);

        // need this
        browser.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                browser.evaluateJavascript("foo(\""+name+"\")", null);
            }
        });

        // Make a Java object available in the WebView:
        browser.addJavascriptInterface(new WebAppInterface(this.getApplicationContext()), "Android");

        browser.loadUrl("file:///android_asset/www/general-events.html");
    }
}

class WebAppInterface {
    private Context mContext;

    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void dontShowMe(String name) {
        Toast.makeText(mContext.getApplicationContext(), "dontShowMe "+name, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void showMe(String name) {
        Toast.makeText(mContext.getApplicationContext(), "showMe "+name, Toast.LENGTH_LONG).show();
    }
}
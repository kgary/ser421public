package ser421.asu.edu.sampleapp1webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        WebView browser = (WebView)findViewById(R.id.webView);
        //browser.setWebViewClient(new WebViewClient());
        browser.setHorizontalScrollBarEnabled(true);
        browser.setVerticalScrollBarEnabled(true);
        browser.setWebContentsDebuggingEnabled(true);
        //browser.loadUrl(getString(R.string.wvURL));
        browser.loadUrl("https://en.wikipedia.org/wiki/London");
    }
}

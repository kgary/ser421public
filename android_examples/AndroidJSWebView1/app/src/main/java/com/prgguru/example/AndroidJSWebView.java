package com.prgguru.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AndroidJSWebView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //WebView Object
        WebView browser;
        browser=(WebView)findViewById(R.id.webkit);
        //Enable Javascript
        browser.getSettings().setJavaScriptEnabled(true);
        //Inject WebAppInterface methods into Web page by having Interface 'Android' 
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        browser.loadUrl("http://apps.programmerguru.com/examples/androidjs.html");
    }
    //Class to be injected in Web page
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }
        
        /**
         * Show Toast Message
         * @param toast
         */
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
        
        /**
         * Show Dialog 
         * @param dialogMsg
         */
        public void showDialog(String dialogMsg){
        	AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        	 
            // Setting Dialog Title
            alertDialog.setTitle("JS triggered Dialog");
     
            // Setting Dialog Message
            alertDialog.setMessage(dialogMsg);
     
            // Setting alert dialog icon
            //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
     
            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	Toast.makeText(mContext, "Dialog dismissed!", Toast.LENGTH_SHORT).show();
                }
            });
     
            // Showing Alert Message
            alertDialog.show();
        }
        
        /**
         * Intent - Move to next screen
         */
        public void moveToNextScreen(){
        	 AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
             // Setting Dialog Title
             alertDialog.setTitle("Alert");
             // Setting Dialog Message
             alertDialog.setMessage("Are you sure you want to leave to next screen?");
             // Setting Positive "Yes" Button
             alertDialog.setPositiveButton("YES",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                        	 //Move to Next screen
                        	 Intent chnIntent = new Intent(AndroidJSWebView.this, ChennaiIntent.class);  
                        	 startActivity(chnIntent);  
                         }
                     });
             // Setting Negative "NO" Button
             alertDialog.setNegativeButton("NO",
                     new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             // Cancel Dialog
                             dialog.cancel();
                         }
                     });
             // Showing Alert Message
             alertDialog.show();
        }
    }
}

package com.coreservlets.networking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/** Demonstrates a variety of relatively simple network client and
 *  String parsing tasks.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */
public class NetworkingInitialActivity extends Activity {
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /** Switches to the NistTimeActivity when the associated button is clicked. 
     *  You must also register the new Activity in AndroidManifest.xml. 
     */
    public void switchToNistTimeLookup(View clickedButton) {
        ActivityUtils.goToActivity(this, NistTimeActivity.class);
    }
    
    /** Switches to the MailAddressVerifierActivity when the associated button is clicked. */
    
    public void switchToMailAddressVerifier(View clickedButton) {
        ActivityUtils.goToActivity(this, MailAddressVerifierActivity.class);
    }
    
    /** Switches to the MailAddressVerifierActivity when the associated button is clicked. */
    
    public void switchToFtpMessageViewer(View clickedButton) {
        ActivityUtils.goToActivity(this, FtpMessageActivity.class);
    }
    
    /** Switches to the UrlCheckerActivity when the associated button is clicked. */
    
    public void switchToUrlChecker(View clickedButton) {
        ActivityUtils.goToActivity(this, UrlCheckerActivity.class);
    }
    
    /** Switches to the UrlSearcher1Activity when the associated button is clicked. */
    
    public void switchToUrlSearcher1(View clickedButton) {
        ActivityUtils.goToActivity(this, UrlSearcher1Activity.class);
    }
    
    /** Switches to the UrlSearcher1Activity when the associated button is clicked. */
    
    public void switchToUrlSearcher2(View clickedButton) {
        ActivityUtils.goToActivity(this, UrlSearcher2Activity.class);
    }
    
    /** Switches to the LoanCalculatorActivity when the associated button is clicked. */
    
    public void switchToLoanCalculator(View clickedButton) {
        ActivityUtils.goToActivity(this, LoanCalculatorActivity.class);
    }
}
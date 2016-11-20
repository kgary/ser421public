package com.coreservlets.networking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityUtils {
    /** Switches to the given class (which must be a subclass of Activity). 
     *  You must also register the new Activity in AndroidManifest.xml.
     */
    public static void goToActivity(Context currentActivity,
                                    Class<? extends Activity> newClass) {
        Intent newActivity = new Intent(currentActivity, newClass);
        currentActivity.startActivity(newActivity);
    }
}

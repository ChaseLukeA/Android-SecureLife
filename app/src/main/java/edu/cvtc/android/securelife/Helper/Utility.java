package edu.cvtc.android.securelife.Helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class Utility {

    public static void hideSoftKeyboard(Activity activity) {

        View currentView = activity.getCurrentFocus();

        if (null != currentView) {

            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(currentView.getWindowToken(), 0);
        }
    }

}


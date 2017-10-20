package com.motivaimagine.motivaimagine_trial;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by gpaez on 10/20/2017.
 */

public class CheckNetwork {

    private static final String TAG = CheckNetwork.class.getSimpleName();



    public static boolean isInternetAvailable(Activity context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getApplication().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            Log.d(TAG,"no internet connection");
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                Log.d(TAG," internet connection available...");
                return true;
            }
            else
            {
                Log.d(TAG," internet connection");
                return true;
            }

        }
    }
}



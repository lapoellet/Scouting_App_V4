package org.deltaroboticsftc.scouting_app_v4.Managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Luke Poellet on 5/2/2017.
 */

public class NetworkManager
{

    //Add network check to LoginBackendlessLink
    public static Boolean isTheNetworkActive(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}

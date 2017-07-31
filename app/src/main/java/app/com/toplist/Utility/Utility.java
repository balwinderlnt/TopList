package app.com.toplist.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import app.com.toplist.DBmanger.DataBaseHelper;

public class Utility {
    private static final String TAG = "Utility";

    /**
     * Initializes the application requirementCreating table and
     * instantiating Database
     *
     * @param context
     */
    public static void intiallizeApp(Context context) {
        DataBaseHelper.initializeInstance(context);


    }

    public static boolean isNetworkConnected(Context context) {
        boolean isConnected = false;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();
            if (netInfo != null)
                if(netInfo.isConnected() && netInfo.isAvailable())
                    isConnected  = true;
        }
        return isConnected;
    }







}
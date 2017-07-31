package app.com.toplist.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefManager {
    private SharedPreferences pref;
    private static PrefManager prefManager;

    public PrefManager(Context ctx, String preferenceName) {
        //this.ctx = ctx;
        pref = ctx.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }


    public static PrefManager getPrefManager(Context ctx, String preferenceName) {
        if (prefManager == null) {
            intializePrefManager(ctx, preferenceName);
        }
        return prefManager;
    }

    private static void intializePrefManager(Context ctx, String prefernceName) {
        prefManager = new PrefManager(ctx, prefernceName);
    }


    public void saveString(String key, String value) {
        Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveInt(String key, int value) {
        Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveBoolean(String key, boolean value) {
        Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void saveLong(String key, long value) {
        Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    public void deleteString(String key) {
        Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    public String getString(String key) {
        return pref.getString(key, "");
    }

    public int getInt(String key) {
        return pref.getInt(key, -1);
    }

    public boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public long getLong(String key) {
        return pref.getLong(key, -1);
    }
}

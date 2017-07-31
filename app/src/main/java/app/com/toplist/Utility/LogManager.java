package app.com.toplist.Utility;

import android.util.Log;

public class LogManager {
    private final static boolean DEBUG_MODE = true;

    public static /*synchronized*/ void D(String tag, String msg) {
        if (DEBUG_MODE) {
            Log.d(tag, "" + msg);
        }
    }

    public static void E(String tag, String msg) {
        if (DEBUG_MODE) {
            Log.e(tag, "" + msg);
        }
    }

    public static void V(String tag, String msg) {
        if (DEBUG_MODE) {
            Log.v(tag, "" + msg);
        }
    }

    public static void W(String tag, String msg) {
        if (DEBUG_MODE) {
            Log.w(tag, "" + msg);
        }
    }

}

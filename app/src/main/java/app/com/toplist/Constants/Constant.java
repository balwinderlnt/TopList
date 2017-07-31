package app.com.toplist.Constants;


/**
 * Created by Balwinder on 27/07/2017.
 * Copyright (c) 2017 M800. All rights reserved.
 */

public class Constant {


    public static final String STATUS = "true";
    public static final String FAIL = "false";

    public interface HTTPSTATUS {
        int BAD_REQUEST = 400, /* FAIL */
                UNAUTHORIZED = 401/* ERROR */,
                FORBIDDEN = 403,
                NOT_FOUND = 404,
                OK = 200, /* SUCCESS */
                NOT_EXISTs = 424 /* user not found */;
    }


    public interface Commands {
        int REQUEST_GET_TOP_LIST = 0x1001;
    }
    public interface  REQ_TYPE {
        String GET="GET",
        POST="POST";
    }
    public static final int TIMEOUT_CONNECTION = 39000;


    public interface Receivers {
        String BROADCAST_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    }

}

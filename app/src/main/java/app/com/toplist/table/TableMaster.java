package app.com.toplist.table;

import android.provider.BaseColumns;

public class TableMaster implements BaseColumns {
    public static final String TYPE_TEXT = " TEXT ",
            TYPE_INTEGER = " INTEGER ",
            TYPE_DATETIME = " TEXT ";
    public static final String _id = _ID + TYPE_INTEGER
            + " PRIMARY KEY AUTOINCREMENT ";


    public  interface tblTopList {

        String TABLE_NAME = "tblTopList",
                ARTIST_NAME = "mac",
                SONG_NAME = "authKey",
                IMAGE_URL ="conn_state";

        String CREATE_TABLE = "create TABLE IF NOT EXISTS " + TABLE_NAME
                + "(" + ARTIST_NAME + TYPE_TEXT + ", "
                + SONG_NAME + TYPE_TEXT + ", "
                + IMAGE_URL + TYPE_TEXT + ")";
    }

}







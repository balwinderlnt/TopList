package app.com.toplist.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import app.com.toplist.DBmanger.DataBaseHelper;
import app.com.toplist.Utility.LogManager;


public class DBManager {
    private SQLiteDatabase mSqlDatabase;
    private DataBaseHelper dbHelper;
    private static DBManager mDBManager;

    private DBManager(Context ctx) {
        try {
            DataBaseHelper.initializeInstance(ctx);
            dbHelper = DataBaseHelper.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.D(this.getClass().getName(), e.toString());
        }
    }

    public synchronized static DBManager getInstance(Context ctx) {
        if (mDBManager == null) {
            mDBManager = new DBManager(ctx);
        }
        return mDBManager;
    }

    public SQLiteDatabase openDB() {
        return dbHelper.openDatabase();
    }

    public void closeDB() {
        dbHelper.closeDatabase();
    }

    public synchronized Cursor selectQuery(String table, String[] columns, String selection,
                                           String[] selectionArgs, String groupBy, String having,
                                           String orderBy) {
        return dbHelper.selectQuery(table, columns, selection,
                selectionArgs, groupBy, having, orderBy);
    }

    /**
     * Insert Query Generic Method.
     */
    public long insert(String table, String nullColumnHack,
                       ContentValues values) {
        return dbHelper.insert(table, nullColumnHack, values);
    }

    /**
     * Update Query Generic Method.
     */
    public synchronized boolean updateQuery(String table, ContentValues values,
                                            String whereClause, String[] whereArgs) {
        //openDB();
        return dbHelper.updateQuery(table, values, whereClause,
                whereArgs);
        //	closeDB();

    }


    /**
     * Insert Query Generic Method.
     */
    public synchronized boolean insertQuery(String table, String nullColumnHack,
                                            ContentValues values) {
        //openDB();
        boolean result = dbHelper.insertQuery(table, nullColumnHack, values);
        //closeDB();

        return result;
    }

    public synchronized boolean deleteQuery(String table, String whereClause,
                                            String[] whereArgs) {
        return dbHelper.deleteQuery(table, whereClause, whereArgs);
    }

    public synchronized Cursor rawQuery(String sql, String[] selectionArgs) {
        return dbHelper.rawQuery(sql, selectionArgs);
    }

    public String getStringValue(String table, String column, String selection,
                                 String[] selectionArgs) {
        String value = "";
        //openDB();
        Cursor cursor = dbHelper.selectQuery(table, new String[]{column},
                selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            value = cursor.getString(cursor.getColumnIndex(column));
            cursor.close();
        }
        //closeDB();
        return value;
    }

    public int getIntValue(String table, String[] columns, String selection,
                           String[] selectionArgs) {
        int value = -1;
        //openDB();
        Cursor cursor = dbHelper.selectQuery(table, columns, selection,
                selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            value = cursor.getInt(0);
            cursor.close();
        }
        //closeDB();
        return value;
    }

    public boolean updateStringValue(String table, String column,
                                     String selection, String value, String updateColumn) {
        ContentValues values = new ContentValues();
        values.put(updateColumn, value);
        return updateQuery(table, values, column + "=?",
                new String[]{selection});
    }




}

package app.com.toplist.DBmanger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.concurrent.atomic.AtomicInteger;

import app.com.toplist.Utility.LogManager;
import app.com.toplist.table.TableMaster;

/**
 *@author Balwinder
 * Descr
 */
public class DataBaseHelper {
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static SQLiteDatabase mSqlDatabase;

    private static DataBaseHelper objDatabaseManager;

    private static DatabaseAssistant objDbAssistant;

    private static final String DATABASE_NAME = "topListApp";

    private static int DATABASE_VERSION = 1;


    private static final String TAG = "DataBaseHelper";

    private DataBaseHelper(Context context) {
        objDbAssistant = new DatabaseAssistant(context);
    }

    /**
     * called by application class only
     */
    public static synchronized void initializeInstance(Context context) {
        if (objDatabaseManager == null) {
            objDatabaseManager = new DataBaseHelper(context);
        }
    }

    public static synchronized DataBaseHelper getInstance() {
        if (objDatabaseManager == null) {
            throw new IllegalStateException(
                    DataBaseHelper.class.getSimpleName()
                            + " is not initialized, call initializeInstance(..) method first.");
        }

        return objDatabaseManager;
    }

    /**
     * Call it before any CRUD operation
     */
    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            LogManager.D(TAG, "DB OPENED");
            mSqlDatabase = objDbAssistant.getWritableDatabase();
        }
        return mSqlDatabase;
    }

    /**
     * Call it just after any CRUD operation
     */
    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            LogManager.D(TAG, "DB CLOSED");
            mSqlDatabase.close();
        }
    }

    /**
     * Select Query - Generic method.
     */
    public Cursor selectQuery(String table, String[] columns, String selection,
                              String[] selectionArgs, String groupBy, String having,
                              String orderBy) {
        if (mSqlDatabase == null)
            mSqlDatabase = objDbAssistant.getWritableDatabase();
        return  mSqlDatabase.query(table, columns, selection,
                selectionArgs, groupBy, having, orderBy);


    }

    /**
     * Insert Query Generic Method.
     */
    public boolean insertQuery(String table, String nullColumnHack,
                               ContentValues values) {
        if (mSqlDatabase == null)
            mSqlDatabase = objDbAssistant.getWritableDatabase();

        long returnCode = mSqlDatabase.insert(table, nullColumnHack, values);

        return -1 != returnCode;
    }


    /**
     * Insert Query Generic Method.
     */
    public long insert(String table, String nullColumnHack,
                       ContentValues values) {
        if (mSqlDatabase == null)
            mSqlDatabase = objDbAssistant.getWritableDatabase();
        return mSqlDatabase.insert(table, nullColumnHack, values);
    }


    /**
     * Update Query Generic Method.
     */
    public boolean updateQuery(String table, ContentValues values,
                               String whereClause, String[] whereArgs) {

        if (mSqlDatabase == null)
            mSqlDatabase = objDbAssistant.getWritableDatabase();
        int rowsAffected = 0;

        rowsAffected = mSqlDatabase.update(table, values, whereClause,
                whereArgs);

        return rowsAffected > 0;

    }

    public boolean deleteQuery(String table, String whereClause,
                               String[] whereArgs) {
        if (mSqlDatabase == null)
            mSqlDatabase = objDbAssistant.getWritableDatabase();
        int rowsAffected = 0;

        rowsAffected = mSqlDatabase.delete(table, whereClause, whereArgs);
        LogManager.V(TAG, table + " table del rowsAffected==" + rowsAffected);
        return rowsAffected > 0;

    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        if (mSqlDatabase == null)
            mSqlDatabase = objDbAssistant.getWritableDatabase();

        return mSqlDatabase.rawQuery(sql, selectionArgs);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (mSqlDatabase != null)
            mSqlDatabase.close();
    }

    private class DatabaseAssistant extends SQLiteOpenHelper {

        @Override
        public synchronized void close() {
            super.close();
            if (mSqlDatabase != null)
                mSqlDatabase.close();
        }

        DatabaseAssistant(Context context, String name, CursorFactory factory,
                          int version) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        DatabaseAssistant(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
               db.execSQL(TableMaster.tblTopList.CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            LogManager.E("DataBaseHelper", "insise onUpgrade  old db = " + oldVersion + " New version : "+newVersion);

            try {
                if (oldVersion != newVersion && oldVersion < newVersion) {
                    switch (newVersion) {
                        case 2:

                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        public void udpateDbVersion2()
        {
            //
        }


    }

}



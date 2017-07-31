package app.com.toplist.Model.DAO;

import android.content.Context;

import app.com.toplist.Presenter.DBManager;


public abstract class BaseDAO {

    protected Context mContext;

    protected DBManager mDBManager;

    public BaseDAO(Context context) {
        mContext = context;
        mDBManager = DBManager.getInstance(context);
    }

    public abstract Object getData();

    public abstract void insertData(Object data);


}

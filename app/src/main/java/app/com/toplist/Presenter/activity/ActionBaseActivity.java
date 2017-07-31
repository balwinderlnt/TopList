package app.com.toplist.Presenter.activity;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import app.com.toplist.Presenter.DBManager;
import app.com.toplist.Presenter.communication.IProgressListner;
import app.com.toplist.Presenter.communication.IResultListener;
import app.com.toplist.Presenter.fragment.BaseFragment;
import app.com.toplist.R;
import app.com.toplist.Utility.Utility;


public class ActionBaseActivity extends AppCompatActivity implements
        IProgressListner, OnMenuItemClickListener {

    protected IResultListener iResultListener;


    private ProgressDialog progressDialog;

    protected ActionBar mActionBar;

    protected DBManager dbManager;

    public FragmentTransactionManger getManager() {
        return manager;
    }

    private FragmentTransactionManger manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = DBManager.getInstance(this);
        manager = new FragmentTransactionManger(this);
    }

    @Override
    public void showProgressDialog(String title, String msg) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(msg);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem actionSetting = menu.findItem(R.id.action_refresh);
        actionSetting.setOnMenuItemClickListener(this);
        if (Utility.isNetworkConnected(this))
            actionSetting.setVisible(true);
        else actionSetting.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    protected void initialiseActionBar() {
        if (null == mActionBar) {
            mActionBar = getSupportActionBar();
        }
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setHomeButtonEnabled(false);
    }

    /**
     * Public method which will get called from the child fragment as well as
     * Derived activity classes.
     *
     * @param title
     */
    public void setActionBarTitle(String title) {
        if (null != mActionBar) {
            mActionBar = getSupportActionBar();
        }
        mActionBar.setTitle(title);
    }

    /**
     * Dumps all the previous backstack entries and creates fresh backstack to
     * work with.
     *
     * @param fragment
     */
    public void createFreshBackStack(BaseFragment fragment) {
        manager.createFreshBackStack(fragment);
    }

    /**
     * Pops up the fragment if already exists in the backstack otherwise
     * replaces the current fragment.
     *
     * @param fragment object of which is to be displayed.
     */
    public void replaceFragment(BaseFragment fragment) {
        manager.replaceFragment(fragment);
    }

    /**
     * Pop the last fragment transition from the manager's fragment back stack.
     *
     * @param fragment
     * @param flags
     * @see FragmentManager#popBackStack(String, int);
     */
    public void popBackStack(BaseFragment fragment, int flags) {
        manager.popBackStack(fragment, flags);
    }

    /**
     * @param fragId
     * @return true if fragment with the provided fragId is already present in
     * the back stack false otherwise
     */
    public boolean isInBackStack(String fragId) {
        return manager.isInBackStack(fragId);
    }

    /**
     * @return {@link Integer} of the fragment entries present in the backstack.
     */
    public int getBackStackEntryCount() {
        return manager.getBackStackEntryCount();
    }

    public void getFragmetinStack() {
        manager.getFragmentinStack();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (null != iResultListener) {
            return iResultListener.onMenuItemClickListener(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setResultListener(IResultListener iResultListener) {
        this.iResultListener = iResultListener;
    }

    @Override
    public void onBackPressed() {
        boolean handled = false;
        if (null != iResultListener) {
            handled = iResultListener.onFragmentBackPressed();
        }
        if (!handled) {
            super.onBackPressed();
        }
    }

    public DBManager getDBManager() {
        if (dbManager == null) {
            dbManager = DBManager.getInstance(this);
        }
        return dbManager;
    }

    public void setBottomSheetState(boolean expanded, String title, String msg) {

    }

    public void sendCommandToBleService(int command, String auth, BluetoothDevice mLeDevices, BluetoothGatt gatt) {
        //no code here...method body written in Master Screen
    }

    public String getFragmentAtTop() {
        return manager.getFragmentAtTop();
    }

    public void cleaBackStack() {
        manager.clearBackStack();

    }


}

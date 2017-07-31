package app.com.toplist.Presenter.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.toplist.Presenter.DBManager;
import app.com.toplist.Presenter.activity.ActionBaseActivity;
import app.com.toplist.Presenter.communication.IProgressListner;
import app.com.toplist.Presenter.communication.IResultListener;
import app.com.toplist.Presenter.communication.IUIEventListner;
import app.com.toplist.Utility.PrefManager;


public abstract class BaseFragment extends Fragment implements
        IProgressListner, IResultListener, IUIEventListner {

    protected final ThreadLocal<DBManager> dbManager = new ThreadLocal<>();
    protected PrefManager pref;
    private ProgressDialog progressDialog;
    private CoordinatorLayout.Behavior behavior;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof ActionBaseActivity) {
            dbManager.set(((ActionBaseActivity) getActivity()).getDBManager());
        } else {
            dbManager.set(DBManager.getInstance(getActivity()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return onChildCreateView(inflater, container, savedInstanceState);
    }

    public View addCustomHeaderView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    /**
     * This will get a call from
     * {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} to
     * create a view for child fragments which inherits {@link BaseFragment}
     * <p/>
     * <p/>
     * Child element of {@link BaseFragment} should not override
     * </P>
     * {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return {@link View} object of the child's layout.
     */
    protected abstract View onChildCreateView(LayoutInflater inflater,
                                              ViewGroup container, Bundle savedInstanceState);

    @Override
    public boolean onFragmentBackPressed() {
        return false;
    }

    @Override
    public void showProgressDialog(String title, String msg) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
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
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof ActionBaseActivity) {
            ((ActionBaseActivity) getActivity()).setResultListener(this);
        }

    }

    /**
     * Replace any fragment from the current fragment. Will call
     * {@link ActionBaseActivity#replaceFragment(BaseFragment, boolean)} method
     * internally
     *
     * @param fragment
     */
    protected void replaceFragment(BaseFragment fragment) {
        ((ActionBaseActivity) getActivity()).replaceFragment(fragment);
    }


    protected void createFreshBackStack(BaseFragment fragment) {
        ((ActionBaseActivity) getActivity()).createFreshBackStack(fragment);

    }

    /**
     * Change the title from the actionbar title from fragment. Which will in
     * turn call {@link ActionBaseActivity#setActionBarTitle(String)}
     *
     * @param title
     */
    protected void setActionBarTitle(String title) {
        if (null != getActivity())
            ((ActionBaseActivity) getActivity()).setActionBarTitle(title);
    }

    /**
     * Pop the last fragment transition from the manager's fragment back stack.
     *
     * @param fragment
     * @param flags
     * @see FragmentManager#popBackStack(String, int);
     */
    protected void popBackStack(BaseFragment fragment, int flags) {
        if (null != getActivity()) {
            ((ActionBaseActivity) getActivity()).popBackStack(fragment, flags);
        }
    }

    /**
     * To pass arguments to the current fragments
     */
    public void refreshArguments(Bundle bundle) {
    }


    @Override
    public void handleFailureEvent(int action, Object data) {

    }

    @Override
    public void handleSuccessEvent(int action, Object data, Object packet) {


    }

    @Override
    public void handleRetrialEvent(int action, Object data) {

    }

    public String getTopFragName(BaseFragment fragment) {
        return ((ActionBaseActivity) getActivity()).getFragmentAtTop();

    }

}



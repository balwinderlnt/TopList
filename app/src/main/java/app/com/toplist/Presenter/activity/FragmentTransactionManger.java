package app.com.toplist.Presenter.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import app.com.toplist.Presenter.fragment.BaseFragment;
import app.com.toplist.R;
import app.com.toplist.Utility.LogManager;


/**
 * {@link FragmentTransactionManger} is a wrapper class for handling the
 * operations associated with the {@link Fragment}. Contains public methods for
 * replace , popping up back-stack and checking if the fragment already present in
 * back-stack.
 *
 * @author Balwinder
 */
public class FragmentTransactionManger {
    private static final String TAG = "FragmentTransactionManger";
    private FragmentManager manager;

    /**
     * public constructor to create the object of the {@link FragmentTransactionManger}
     *
     * @param activity an instance of {@link ActionBaseActivity}
     */
    public FragmentTransactionManger(ActionBaseActivity activity) {
        this.manager = activity.getSupportFragmentManager();
    }

    /**
     * Dumps all the previous backstack entries and creates fresh backstack to
     * work with.
     *
     * @param fragment
     */
    public void createFreshBackStack(BaseFragment fragment) {
        if (null != fragment) {
            try {

                clearBackStack();
              //  manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            try {
                String fragId = fragment.getClass().getName();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.content_frame, fragment, fragId);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(fragId);
                ft.commitAllowingStateLoss();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Pops up the fragment if already exists in the backstack otherwise
     * replaces the current fragment.
     *
     * @param fragment object of which is to be displayed.
     */
    public void replaceFragment(BaseFragment fragment) {
        if (fragment != null) {
            String fragId = fragment.getClass().getName();
            FragmentTransaction ft = manager.beginTransaction();
            if (!isInBackStack(fragId)) {
                /***
                 *
                 * fragment not in back stack,create it.
                 * */
                ft.replace(R.id.content_frame, fragment, fragId);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(fragId);
                ft.commitAllowingStateLoss();
            } else {
                BaseFragment baseFragment = (BaseFragment) manager
                        .findFragmentByTag(fragId);
                Bundle bundle = fragment.getArguments();
                if (baseFragment.isVisible()) {
                    baseFragment.refreshArguments(bundle);
                } else {
                    manager.popBackStack(fragId, 0);
                }
            }

        }
    }

    /**
     * Pop the last fragment transition from the manager's fragment back stack.
     *
     * @param fragment
     * @param flags
     * @see FragmentManager#popBackStack(String, int);
     */
    public void popBackStack(BaseFragment fragment, int flags) {
        try {
            String fragId = fragment.getClass().getName();
            if (isInBackStack(fragId)) {
                manager.popBackStack(fragId, flags);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fragId
     * @return true if fragment with the provided fragId is already present in
     * the back stack false otherwise
     */
    public boolean isInBackStack(String fragId) {
        // FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            BackStackEntry backStackEntry = manager.getBackStackEntryAt(i);
            if (TextUtils.equals(fragId, backStackEntry.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return {@link Integer} of the fragment entries present in the backstack.
     */
    public int getBackStackEntryCount() {
        return manager.getBackStackEntryCount();
    }

    public void getFragmentinStack() {
        int count = manager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            BackStackEntry backStackEntry = manager.getBackStackEntryAt(i);
            LogManager.E(TAG, backStackEntry.getName());
        }
    }




    public void clearBackStack() {
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);


        }
    }

    /**
     *get name for top fragment in stack
     */
    public String getFragmentAtTop() {
        String topFragName="";
        if (manager.getBackStackEntryCount() > 0) {
            int index = manager.getBackStackEntryCount() - 1;
            BackStackEntry backStackEntry = manager.getBackStackEntryAt(index);
            topFragName = backStackEntry.getName();
        }
        return  topFragName;
    }
}

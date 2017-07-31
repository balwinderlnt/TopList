package app.com.toplist.Presenter.communication;

import android.view.MenuItem;

/**
 * Interface to establish communication between fragment and activity.
 *
 * @author Balwinder
 */
public interface IResultListener {


    boolean onFragmentBackPressed();

    boolean onMenuItemClickListener(MenuItem item);

}

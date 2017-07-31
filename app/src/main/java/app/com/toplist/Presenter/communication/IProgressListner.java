package app.com.toplist.Presenter.communication;


/**
 * Progress dialog callback interface. This needs to be implemented by
 * {@link ActionBaseActivity} or {@link BaseFragment} and then need to pass it
 * on to the {@link UIManager}'s constructor
 *
 * @author Balwinder
 */
public interface IProgressListner {

    /**
     * This method will get a call when the progress dialog need to be shown
     *
     * @param title of the dialog
     * @param msg   which will be shown in the progress dialog
     */
    void showProgressDialog(String title, String msg);

    /**
     * This method will get triggered when {@link UIManager} needs to hide the
     * progress dialog
     */
    void hideProgressDialog();


}
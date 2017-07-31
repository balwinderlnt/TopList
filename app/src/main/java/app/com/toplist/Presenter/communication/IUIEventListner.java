package app.com.toplist.Presenter.communication;


/**
 * Callback interface to be implemented to
 * establish the communication between application framework and User interface
 */
public interface IUIEventListner {

    /**
     * Callback method which will be called from {@link UIManager} once the
     * network request is successfully executed. This method will only get
     * called for the Success of the network response.
     *
     * @param action int field from {@link UIManager#execute(int, Object)}
     * @param data   Parsed data object of the network response
     * @param packet data object which is sent to the
     *               {@link UIManager#execute(int, Object)} as a object field.
     */
    void handleSuccessEvent(int action, Object data, Object packet);

    /**
     * Callback method to be triggered once the network response is served by
     * the server with the status of the request is failure.
     *
     * @param action action int field from {@link UIManager#execute(int, Object)}
     * @param data   Parsed data object of the network response
     */
    void handleFailureEvent(int action, Object data);


    /**
     * Callback method to be triggered once the network response is served by
     * the server with the status of the request is error(401).
     *
     * @param action action int field from {@link UIManager#execute(int, Object)}
     * @param data   Parsed data object of the network response
     */
    void handleErrorEvent(int action, Object data);

    /**
     * Callback method to be triggered if the network request is unable to
     * process due to unavailability of the network. This can also get triggered
     * if there is null response from the server
     *
     * @param action int field from {@link UIManager#execute(int, Object)}
     * @param data   object which is sent to the
     *               {@link UIManager#execute(int, Object)} as a object field.
     */

    void handleRetrialEvent(int action, Object data);
}

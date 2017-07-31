package app.com.toplist.Presenter;

import android.content.Context;

import app.com.toplist.Presenter.communication.IUIEventListner;


public class Controller implements IUIEventListner {
    private UIManager uiManager;
    private DataManager dataManager;

    public Controller(UIManager uiManager, Context context) {
        this.uiManager = uiManager;
        dataManager = new DataManager(this, context);
    }

    public void execute(int command, Object pojo) {
        dataManager.execute(command, pojo);
    }

    @Override
    public void handleSuccessEvent(int action, Object data, Object packet) {
        uiManager.handleSuccessEvent(action, data, packet);
    }

    @Override
    public void handleFailureEvent(int action, Object data) {
        uiManager.handleFailureEvent(action, data);
    }

    @Override
    public void handleErrorEvent(int action, Object data) {
        uiManager.handleErrorEvent(action, data);
    }

    @Override
    public void handleRetrialEvent(int action, Object data) {
        uiManager.handleRetrialEvent(action, data);
    }
}

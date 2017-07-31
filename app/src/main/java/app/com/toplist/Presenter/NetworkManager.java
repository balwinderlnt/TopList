package app.com.toplist.Presenter;

import android.content.Context;


import app.com.toplist.Model.RequestCreator;
import app.com.toplist.Model.ResponseParser;
import app.com.toplist.Presenter.communication.IUIEventListner;
import app.com.toplist.Utility.Utility;

public class NetworkManager implements IUIEventListner {

    private DataManager dataManager;
    private RequestCreator reqCreator;
    private ResponseParser resParser;
    private Context context;

    public NetworkManager(DataManager dataManager, Context context) {
        this.context = context;
        this.dataManager = dataManager;
        reqCreator = new RequestCreator(this, context);
        resParser = new ResponseParser(context);
    }

    public void execute(int command, Object pojo) {
        if (Utility.isNetworkConnected(context)) {
            reqCreator.execute(command, pojo);
        } else {
            handleRetrialEvent(command, pojo);
        }
    }

    @Override
    public void handleSuccessEvent(int action, Object json, Object packet) {
        // to create seperate parser for differnt action/command
        Object responseObj = resParser.parseSuccessResponse(action, json);
        dataManager.handleSuccessEvent(action, responseObj, packet);
    }

    @Override
    public void handleFailureEvent(int action, Object data) {
            dataManager.handleFailureEvent(action, data);
    }

    @Override
    public void handleErrorEvent(int action, Object data) {
        dataManager.handleErrorEvent(action, data);
    }

    @Override
    public void handleRetrialEvent(int action, Object data) {
        dataManager.handleRetrialEvent(action, data);
    }
}

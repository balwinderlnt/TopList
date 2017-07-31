package app.com.toplist.Model;

import android.content.Context;
import app.com.toplist.Connectivity.NetworkTask;
import app.com.toplist.Constants.Constant;
import app.com.toplist.Constants.URLConstants;
import app.com.toplist.Presenter.NetworkManager;
import app.com.toplist.Presenter.communication.IUIEventListner;


public class RequestCreator implements IUIEventListner {
    private NetworkManager netManager;
    private Context context;

    public RequestCreator(NetworkManager netManager, Context context) {
        this.context = context;
        this.netManager = netManager;
    }

    public void execute(int command, Object pojo) {
        String url = "";
        String[] commnadParam = new String[2];
        switch (command) {
            case Constant.Commands.REQUEST_GET_TOP_LIST:
                url = URLConstants.URL_APP_GET_TOP_LIST;
                commnadParam[0] = url;
                commnadParam[1] = Constant.REQ_TYPE.GET;
                break;
            default:
                break;
        }
        new NetworkTask(this, command, pojo, context).execute(commnadParam);
    }


    @Override
    public void handleSuccessEvent(int action, Object data, Object packet) {
        netManager.handleSuccessEvent(action, data, packet);
    }

    @Override
    public void handleFailureEvent(int action, Object data) {
        netManager.handleFailureEvent(action, data);
    }

    @Override
    public void handleErrorEvent(int action, Object data) {
        netManager.handleErrorEvent(action, data);
    }

    @Override
    public void handleRetrialEvent(int action, Object data) {
        netManager.handleRetrialEvent(action, data);
    }
}





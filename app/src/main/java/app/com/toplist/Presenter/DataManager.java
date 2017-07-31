package app.com.toplist.Presenter;

import android.content.Context;

import app.com.toplist.Constants.Constant;
import app.com.toplist.Model.DAO.TopListDAO;
import app.com.toplist.Presenter.communication.IUIEventListner;


public class DataManager implements IUIEventListner {

    private Context context;
    private Controller controller;
    private NetworkManager networkManager;
    private static final String TAG = "DataManager";

    public DataManager(Controller controller, Context context) {
        this.context = context;
        this.controller = controller;
        networkManager = new NetworkManager(this, context);
    }

    public void execute(int command, Object pojo) {
        networkManager.execute(command, pojo);
    }

    @Override
    public synchronized void handleSuccessEvent(int action, Object data, Object packet) {
        switch (action) {

            case Constant.Commands.REQUEST_GET_TOP_LIST:
                if(data!=null) {
                    TopListDAO dao = new TopListDAO(context);
                    dao.insertData(data);
                }
                break;
            default:
                break;

        }
        controller.handleSuccessEvent(action, data, packet);
    }

    @Override
    public void handleFailureEvent(int action, Object data) {
        controller.handleFailureEvent(action, data);
    }

    @Override
    public void handleErrorEvent(int action, Object data) {
        controller.handleErrorEvent(action, data);
    }

    @Override
    public void handleRetrialEvent(int action, Object data) {
        controller.handleRetrialEvent(action, data);
    }
}

package app.com.toplist.Presenter;

import android.content.Context;

import app.com.toplist.Constants.Constant;
import app.com.toplist.Presenter.communication.IProgressListner;
import app.com.toplist.Presenter.communication.IUIEventListner;
import app.com.toplist.R;


public class UIManager implements IUIEventListner {
    private IUIEventListner eventListener;
    private Controller controller;
    private IProgressListner progListener;
    private Context context;

    public UIManager(IUIEventListner eventListener,
                     IProgressListner progListener, Context context) {
        this.context = context;
        this.eventListener = eventListener;
        this.progListener = progListener;
        controller = new Controller(this, context);
    }

    public void execute(int command, Object pojo) {
        if (null != progListener) {

            switch (command) {
                case Constant.Commands.REQUEST_GET_TOP_LIST:
                    progListener.showProgressDialog(
                            context.getString(R.string.loading),
                            "Getting top music ...");
                    break;
            }
        }
        controller.execute(command, pojo);
    }

    @Override
    public void handleSuccessEvent(int action, Object data, Object packet) {

        if (null != progListener) {
            progListener.hideProgressDialog();
        }
        eventListener.handleSuccessEvent(action, data, packet);
    }

    @Override
    public void handleFailureEvent(int action, Object data) {
        if (null != progListener) {
            progListener.hideProgressDialog();
        }
        eventListener.handleFailureEvent(action, data);
    }

    @Override
    public void handleErrorEvent(int action, Object data) {
        if (null != progListener) {
            progListener.hideProgressDialog();
        }
        eventListener.handleErrorEvent(action, data);
    }

    @Override
    public void handleRetrialEvent(int action, Object data) {
        if (null != progListener) {
            progListener.hideProgressDialog();
        }
        eventListener.handleRetrialEvent(action, data);
    }
}

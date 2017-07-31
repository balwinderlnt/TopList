package app.com.toplist.Connectivity;

import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import app.com.toplist.Constants.Constant;
import app.com.toplist.Model.RequestCreator;
import app.com.toplist.Utility.LogManager;
import app.com.toplist.Utility.Utility;

public class NetworkTask extends AsyncTask<String, Integer, String> {
    private static String TAG = "NetworkTask";
    private RequestCreator requestCreator;
    private int statusCode = -2;
    private int command;
    private Object packet;
    private Context context;

    public NetworkTask(RequestCreator requestCreator, int command,
                       Object packet, Context context) {
        this.requestCreator = requestCreator;
        this.command = command;
        this.packet = packet;
        this.context = context;
    }

    @Override
    protected void onPostExecute(String result) {
        if (statusCode == Constant.HTTPSTATUS.OK) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.has("feed")) {
                        requestCreator.handleSuccessEvent(command, result, packet);
                    } else {
                        requestCreator.handleFailureEvent(command, "Something went wrong. Invalid response from server.");
                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (statusCode == Constant.HTTPSTATUS.BAD_REQUEST) {
            requestCreator.handleFailureEvent(command, "Something went wrong. Invalid response from server.");

        } else if (statusCode == Constant.HTTPSTATUS.UNAUTHORIZED) {
                requestCreator.handleRetrialEvent(command, result);
        } else if (!Utility.isNetworkConnected(context) || statusCode == -5 || (statusCode > 400 && statusCode <= 500)) {
            requestCreator.handleRetrialEvent(command, result);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String response = null;
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(params[1]);
            urlConnection.setConnectTimeout(Constant.TIMEOUT_CONNECTION);
            statusCode = urlConnection.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK)// Retrieve response
            {
                StringBuilder stringBuilder = new StringBuilder("");
                String output;
                InputStream is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));

                while ((output = br.readLine()) != null) {
                    stringBuilder.append(output);
                }
                stringBuilder.trimToSize();
                is.close();
                br.close();
                br = null;
                is = null;

                response = stringBuilder.toString();
                LogManager.D(TAG, "status code : " + statusCode);
                LogManager.D(TAG, " response " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            statusCode = 408; //Connect Timeout
        }

        return response;
    }

}


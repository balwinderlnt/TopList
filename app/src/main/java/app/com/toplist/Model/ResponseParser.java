package app.com.toplist.Model;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;

import app.com.toplist.Constants.Constant;
import app.com.toplist.DTO.responses.TopListDTO;

public class ResponseParser {

    private static final String TAG = "ResponseParser";
    private Context mContext;

    public ResponseParser(Context context) {
        mContext = context;
    }

    public synchronized Object parseSuccessResponse(int action, Object json) {
        Gson gson = new Gson();
        Object parsedData = null;
        switch (action) {
            case Constant.Commands.REQUEST_GET_TOP_LIST:

                try {
                    Type listType;
                    Object jsonResult = null;
                    JSONObject jsonObject = new JSONObject(json.toString());
                    JSONObject jsonData = jsonObject.getJSONObject("feed");
                    TopListDTO topListDTO =null;

                    if (jsonData.has("results")) {
                         jsonResult = jsonData.get("results");
                        if(jsonResult instanceof JSONArray) {
                            listType = new TypeToken<ArrayList<TopListDTO>>() {
                            }.getType();

                            JSONArray array = jsonData.getJSONArray("results");
                            parsedData  = gson.fromJson(array.toString(), listType);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

        }
        if (parsedData == null) {
            parsedData = json;
        }
        return parsedData;
    }


}



package es.sakhi.osama.hotstuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by osama on 8/29/15.
 */
public class CarIgnitionTask extends AsyncTask<Void, Void, Void> {

    public static final int START = 0;
    public static final int STOP = 1;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private int action;
    private JsonObject result;
    private Context context;
    private Callback callback;

    public interface Callback {
        void starting();

        void stopping();

        void failed();
    }

    public CarIgnitionTask(Callback callback, int action) {
        this.action = action;
        this.context = (Context) callback;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {

//        android.os.Debug.waitForDebugger();

//        url already has default vin right now, later we must format it.
        String url;

        if (action == START) {
            url = AppConstants.COMMAND_START;
        } else {
            url = AppConstants.COMMAND_STOP;
        }


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String token = prefs.getString(AppConstants.ACCESS_TOKEN, "");
        String authString = AppConstants.BEARER + " " + token;

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, "{}");
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", authString)
                .addHeader("Accept", "application/json")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String resp = response.body().string();
            JsonParser parser = new JsonParser();

            JsonElement jsonElement = parser.parse(resp);
            result = jsonElement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        boolean inProgress = false;

        if (result != null && !result.isJsonNull()) {
            JsonObject commandResponse = result.getAsJsonObject(AppConstants.COMMAND_RESPONSE);
            if (commandResponse != null && !commandResponse.isJsonNull()) {
                String status = commandResponse.get(AppConstants.STATUS).getAsString();
                inProgress = status != null && status.equals(AppConstants.IN_PROGRESS);
            }
        }
        handleCallback(inProgress);
    }

    private void handleCallback(boolean inProgress) {
        if (inProgress) {
            if (action == START) {
                callback.starting();
            } else if (action == STOP) {
                callback.stopping();
            } else {
                callback.failed();
            }
        } else {
            callback.failed();
        }
    }
}
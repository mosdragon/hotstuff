package es.sakhi.osama.hotstuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by osama on 8/29/15.
 */
public class GetTokenTask extends AsyncTask<Void, Void, Void> {

    public interface Callback {
        void tokenReceived();
    }

    private Context context;
    private JsonObject result;
    private Callback callback;

    public GetTokenTask(Callback callback) {
        android.os.Debug.waitForDebugger();
        this.context = (Context) callback;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();

        String auth = AppConstants.CLIENT_ID + ":" + AppConstants.CLIENT_SECRET;
        String authString = AppConstants.BASIC + " " + Base64.encodeToString(
                auth.getBytes(), Base64.DEFAULT);

        authString = authString.replaceAll("\\n", "");

        Request request = new Request.Builder()
                .url(AppConstants.TOKEN_ENDPOINT)
                .addHeader("Authorization", authString)
                .addHeader("Accept", "application/json")
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
        if (result != null && !result.isJsonNull()) {
            String token = result.get(AppConstants.ACCESS_TOKEN).getAsString();
//            Let token expire 3 minutes before actual expiration. Check if expired before every
//            API call
            int expirationMinutes = result.get(AppConstants.EXPIRES_IN).getAsInt() - 3;

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, expirationMinutes);
            long expirationDate = cal.getTimeInMillis();

            if (context != null) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(AppConstants.ACCESS_TOKEN, token);
                editor.putLong(AppConstants.EXPIRATION_DATE, expirationDate);
                editor.commit();
            }
            if (callback != null) {
                callback.tokenReceived();
            }
        }
    }
}

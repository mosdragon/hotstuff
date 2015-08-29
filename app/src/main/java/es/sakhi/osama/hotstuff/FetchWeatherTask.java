package es.sakhi.osama.hotstuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class FetchWeatherTask extends AsyncTask<Void, Void, Void> {

    public interface Callback {
        void foundTemp(double temp);
    }

    private Context context;
    private JsonObject result;
    private Callback callback;

    public FetchWeatherTask(Callback callback) {
        android.os.Debug.waitForDebugger();
        this.context = (Context) callback;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();


        String url = String.format(AppConstants.WEATHER_ENDPOINT_TEMPLATE, "GA", "Atlanta");


        Request request = new Request.Builder()
                .url(url)
         //       .addHeader("Authorization", authString)
         //       .addHeader("Accept", "application/json")
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
        double temperature = 50;
        if (result != null && !result.isJsonNull()) {

            JsonObject temp_var = result.get("current_observation").getAsJsonObject();
            temperature = temp_var.get("temp_f").getAsDouble();

        }
            if (callback != null) {
                callback.foundTemp(temperature);
            }
        }
}

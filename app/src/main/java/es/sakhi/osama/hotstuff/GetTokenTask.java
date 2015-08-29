package es.sakhi.osama.hotstuff;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

/**
 * Created by osama on 8/29/15.
 */
public class GetTokenTask extends AsyncTask<Void, Void, Void> {

    public interface Callback {
        void resultToken(String token);
    }

    private Context context;
    private JsonObject result;

    public GetTokenTask(Context context) {
        android.os.Debug.waitForDebugger();
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {

        OkHttpClient client = new OkHttpClient();

        String auth = AppConstants.CLIENT_ID + ":" + AppConstants.CLIENT_SECRET;
        String authString = AppConstants.BASIC + " " + Base64.encodeToString(
                auth.getBytes(), Base64.URL_SAFE);

        Request req = new Request.Builder()
                .url(AppConstants.TOKEN_ENDPOINT)
                .addHeader("Authorization", authString)
                .addHeader("Accept", "application/json")
                .build();


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

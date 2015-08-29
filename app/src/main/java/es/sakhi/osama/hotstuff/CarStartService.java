package es.sakhi.osama.hotstuff;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class CarStartService extends IntentService implements GetTokenTask.Callback,
        CarIgnitionTask.Callback {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_START_CAR = "START_CAR";
    private static final String ACTION_STOP_CAR = "STOP_CAR";

    private String action;

    @Override
    public void onCreate() {
        super.onCreate();
//        android.os.Debug.waitForDebugger();
//        pd = new ProgressDialog(getApplicationContext());
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startCar(Context context) {
        Intent intent = new Intent(context, CarStartService.class);
        intent.setAction(ACTION_START_CAR);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void stopCar(Context context) {
        Intent intent = new Intent(context, CarStartService.class);
        intent.setAction(ACTION_STOP_CAR);
        context.startService(intent);
    }

    public CarStartService() {
        super("CarStartService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            action = intent.getAction();
            if (ACTION_START_CAR.equals(action)) {
                handleCarStart();
            } else if (ACTION_STOP_CAR.equals(action)) {
                handleCarStop();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleCarStart() {
        Log.d(this.getClass().toString(), "Car Stopping");
        if (Helpers.isTokenInvalid(this)) {
            Log.d("CarStartService", "Token is invalid");
            GetTokenTask task = new GetTokenTask(this);
            task.execute();
        } else {
            Log.d("CarStartService", "Token is valid");
            tokenReceived();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleCarStop() {
        // TODO: Handle action Stop Car
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(this.getClass().toString(), "Car Stopping");
        if (Helpers.isTokenInvalid(this)) {
            Log.d("CarStartService", "Token is invalid");
            GetTokenTask task = new GetTokenTask(this);
            task.execute();
        } else {
            Log.d("CarStartService", "Token is valid");
            tokenReceived();
        }
    }

    @Override
    public void tokenReceived() {
        Log.d("CarStartService", "GOT TOKEN");
        if (action.equals(ACTION_START_CAR)) {
            (new CarIgnitionTask(this, CarIgnitionTask.START)).execute();
        } else {
            (new CarIgnitionTask(this, CarIgnitionTask.STOP)).execute();
        }
//        Make next request, turn car on or off
    }

    @Override
    public void starting() {
        Log.d("CarStartService", "starting");
        toastMsg("Starting Car");
    }

    @Override
    public void stopping() {
        Log.d("CarStartService", "stopping");
        toastMsg("Stopping Car");
    }

    @Override
    public void failed() {
        Log.d("CarStartService", "stopping");
        if (action == ACTION_START_CAR) {
            toastMsg("Failed to Start Car.");
        } else if (action == ACTION_STOP_CAR) {
            toastMsg("Failed to Stop Car.");
        }
        stopSelf();
    }

    private void toastMsg(final String msg) {
        Handler h = new Handler(getMainLooper());
        // Although you need to pass an appropriate context
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CarStartService.this, msg, Toast.LENGTH_LONG).show();
            }
        });
        stopSelf();
    }
}

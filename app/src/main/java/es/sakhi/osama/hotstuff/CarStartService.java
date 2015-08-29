package es.sakhi.osama.hotstuff;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class CarStartService extends IntentService implements GetTokenTask.Callback {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_START_CAR = "START_CAR";
    private static final String ACTION_STOP_CAR = "STOP_CAR";

    ProgressDialog pd;

    @Override
    public void onCreate() {
        super.onCreate();
        android.os.Debug.waitForDebugger();
        pd = new ProgressDialog(getApplicationContext());
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
            final String action = intent.getAction();
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
        // TODO: Handle action Start Car
//        throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(getBaseContext(), "Car Starting", Toast.LENGTH_LONG).show();

        pd.setTitle("Loading...");
        pd.show();

        GetTokenTask task = new GetTokenTask(this);
        task.execute();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleCarStop() {
        // TODO: Handle action Stop Car
//        throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(this, "Car Stopping", Toast.LENGTH_LONG).show();
        if (Helpers.isTokenInvalid(this)) {
            pd.setTitle("Loading...");
            pd.show();
            GetTokenTask task = new GetTokenTask(this);
            task.execute();
        }
    }

    @Override
    public void tokenReceived() {
        Toast.makeText(this, "GOT TOKEN", Toast.LENGTH_LONG);
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }
}

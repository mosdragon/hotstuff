package es.sakhi.osama.hotstuff;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class CarDefrostService extends IntentService implements FetchWeatherTask.Callback {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "es.sakhi.osama.hotstuff.action.FOO";
    private static final String ACTION_BAZ = "es.sakhi.osama.hotstuff.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "es.sakhi.osama.hotstuff.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "es.sakhi.osama.hotstuff.extra.PARAM2";

    private Context context;

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, CarDefrostService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startDefrostService(Context context) {
        Intent intent = new Intent(context, CarDefrostService.class);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, CarDefrostService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public CarDefrostService() {
        super("CarDefrostService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
                    (new FetchWeatherTask(CarDefrostService.this)).execute();
                }
            });
        }
    }




    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void foundTemp(final double temp) {
        Log.d("temp", "" + temp);
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (temp < 35){
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which){
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    Log.d("CarDefrostService", "Positive");
//                                    dialog.dismiss();//Yes button clicked
//                                    break;
//
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    dialog.dismiss();//No button clicked
//                                    break;
//                            }
//                        }
//                    };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(CarDefrostService.this);
//                    builder.setMessage("The Temperature right now is " + temp + "ºF. Would you like to defrost?").setPositiveButton("Yes", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
                    Toast.makeText(CarDefrostService.this, "Your car will begin defrosting", Toast.LENGTH_LONG).show();
                }
                if (temp > 50){
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which){
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    Log.d("CarACService", "Positive");
//                                    dialog.dismiss();//Yes button clicked
//                                    break;
//
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    dialog.dismiss();//No button clicked
//                                    break;
//                            }
//                        }
//                    };
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(CarDefrostService.this);
//                    builder.setMessage("The Temperature right now is " + temp + "ºF. Would you like to turn on the AC?").setPositiveButton("Yes", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
                    Toast.makeText(CarDefrostService.this, "The current temperature is: " + temp + ". Your car will start without defrosting.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

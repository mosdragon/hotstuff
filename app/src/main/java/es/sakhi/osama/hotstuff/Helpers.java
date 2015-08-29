package es.sakhi.osama.hotstuff;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by osama on 8/29/15.
 */
public class Helpers {

    public static boolean isTokenInvalid(Context context) {

        if (context != null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            String token = prefs.getString(AppConstants.ACCESS_TOKEN, "NO TOKEN");
            long expirationDate = prefs.getLong(AppConstants.EXPIRATION_DATE, 0);

            return (token.equals("NO TOKEN") || expirationDate <= System.currentTimeMillis());

        }

        return false;
    }
}

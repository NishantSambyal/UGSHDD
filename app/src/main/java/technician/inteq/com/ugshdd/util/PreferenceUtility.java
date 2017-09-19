package technician.inteq.com.ugshdd.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nishant Sambyal on 15-Sep-17.
 */

public class PreferenceUtility {

    public static final String LOGIN_PREFERENCE = "login_preferences";
    public static final String CURRENT_IP_ADDRESS = "ip_address";
    public static final String CURRENT_PORT_NUMBER = "port_number";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static SharedPreferences getLoginPreference(Context context) {
        return context.getSharedPreferences(LOGIN_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static String getCurrentPortAndIpAddress(Context context) {
        preferences = getLoginPreference(context);
        return preferences.getString(CURRENT_IP_ADDRESS, "") + ":" + preferences.getString(CURRENT_PORT_NUMBER, "");
    }

    public static boolean setCurrentPortAndIpAddress(Context context, String portNumber, String ipAddress) {
        if ((preferences = getLoginPreference(context)) != null) {
            editor = getLoginPreference(context).edit();
            editor.putString(CURRENT_IP_ADDRESS, ipAddress);
            editor.putString(CURRENT_PORT_NUMBER, portNumber);
            editor.apply();
            return true;
        } else return false;
    }
}

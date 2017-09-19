package technician.inteq.com.ugshdd.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by VIkas Patyal on 4-Apr-17.
 */

public class NetworkUtil {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                System.out.println("connectionType" + "wifi");

                return TYPE_WIFI;
            }
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                System.out.println("connectionType" + "mobile data");

                return TYPE_MOBILE;
            }
        }
        System.out.println("connectionType" + "not connected");

        return TYPE_NOT_CONNECTED;
    }


    public static String hitServer(String url, String urlParameters) throws IOException {
        HttpURLConnection conn = null;
        InputStream iStream = null;
        String result;

        try {
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            if (conn.getResponseCode() == 200) {
                iStream = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        iStream));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                result = sb.toString();
                iStream.close();
            } else {
                throw new IOException("Post failed with error code" + conn.getResponseCode());
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }
}
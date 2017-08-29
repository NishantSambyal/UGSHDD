package technician.inteq.com.ugshdd.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import technician.inteq.com.ugshdd.Controller.TaskController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.activity.PendingCases;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public class ReceiveSMS extends Service {

    static int id = 0;
    IncomingSms sms;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service_Started", "bfsjdhfvbsidohgvisjgs");

        sms = new IncomingSms();
        registerReceiver(new IncomingSms(), new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        return super.onStartCommand(intent, flags, startId);
    }

    public void showNotification(String outletName, String mobile_number, int id) {
        final Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/raw/notification");
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, PendingCases.class), 0);
        Notification notification;
        Resources r = getResources();
        notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher, 252)
                .setContentTitle("UGS HHD")
                .setContentText(outletName)
                .setSubText("This outlet is assigned to you by " + mobile_number)
                .setColor(Color.WHITE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis())
                .setSound(alarmSound)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(sms);
    }

    class IncomingSms extends BroadcastReceiver {

        // Get the object of SmsManager
        final SmsManager sms = SmsManager.getDefault();

        public void onReceive(Context context, Intent intent) {
            // Retrieves a map of extended data from the intent.
            Log.i("SmsReceiver", "senderNum: " + "; message: ");

            final Bundle bundle = intent.getExtras();
            System.out.println("action: " + intent.getAction());
            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    assert pdusObj != null;
                    for (int i = 0; i < pdusObj.length; i++) {
                        //old
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                        // Show Alert
                    /*int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration);
                    toast.show();*/
                        if (message.contains(",")) {
                            String[] msg = message.split(",");
                            if (msg.length == 2) {
                                if (msg[0].contains("-") && msg[0].length() == 9) {
                                    if (TaskController.insertTasks(msg[0], msg[1])) {
                                        showNotification(msg[0], senderNum, id);
                                        id++;
                                    }
                                    Toast.makeText(context, "outlet: " + msg[0] + " is assigned", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }


                    }
                    this.abortBroadcast();
                    // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);

            }
        }
    }
}

package technician.inteq.com.ugshdd.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import technician.inteq.com.ugshdd.Controller.TaskModel;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
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
                                if (TaskModel.insertTasks(msg[0], msg[1]))
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

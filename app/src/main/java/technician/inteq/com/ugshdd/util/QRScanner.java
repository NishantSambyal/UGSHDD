package technician.inteq.com.ugshdd.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.activity.AddActionsActivity;

/**
 * Created by Nishant Sambyal on 21-Jul-17.
 */

public class QRScanner extends Activity implements ZXingScannerView.ResultHandler {
    String outletIntent;
    AlertDialog mainDialog;
    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            outletIntent = (String) bundle.get("outlet");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainDialog != null && mainDialog.isShowing()) {
            mainDialog.dismiss();
        }
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
        mScannerView.setFlash(true);
        mScannerView.setAutoFocus(true);

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        ////0005-XBCC-01-X00_Blk_925_Yishun Ave 5_ABC Building_#01-248_Singapore 460213_XXXX_Chicken Rice_Yishun Ave 5
        String string = rawResult.getText();
        String[] format = string.split("_");
        if (format.length == 10) {
            String[] acc_num = format[0].split("-");
            UGSApplication.outletID = format[0];
            UGSApplication.accountNumber = acc_num[0] + "-" + acc_num[1];
            UGSApplication.street = format[1] + " " + format[2] + " " + format[3];
            UGSApplication.building = format[4];
            UGSApplication.unit_country = format[5] + " " + format[6];
            UGSApplication.stall_no = format[7];
            UGSApplication.food_type = format[8];
            UGSApplication.outlet_reference = format[0];


            if (outletIntent.equalsIgnoreCase(acc_num[0] + "-" + acc_num[1])) {
                startActivity(new Intent(this, AddActionsActivity.class));
                finish();
            } else {
                mScannerView.setFlash(false);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Error !");
                alertDialog.setCancelable(false);
                alertDialog.setMessage("Invalid QR code " + acc_num[0] + "-" + acc_num[1]);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                mainDialog = alertDialog.create();
                mainDialog.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem;

        if (mFlash) {
            menuItem = menu.add(Menu.NONE, R.id.menu_flash, 0, "flash_on");
        } else {
            menuItem = menu.add(Menu.NONE, R.id.menu_flash, 0, "flash_off");
        }
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);


        if (mAutoFocus) {
            menuItem = menu.add(Menu.NONE, R.id.menu_auto_focus, 0, "auto_focus_on");
        } else {
            menuItem = menu.add(Menu.NONE, R.id.menu_auto_focus, 0, "auto_focus_off");
        }
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_NEVER);

        return super.onCreateOptionsMenu(menu);
    }
}

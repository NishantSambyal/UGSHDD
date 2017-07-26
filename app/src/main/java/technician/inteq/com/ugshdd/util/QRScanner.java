package technician.inteq.com.ugshdd.util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
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
    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

    }

    @Override
    public void onResume() {
        super.onResume();
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
            SharedPreferences preferences = getSharedPreferences("qr_scan", MODE_PRIVATE);
            SharedPreferences.Editor mEditor = preferences.edit();
            mEditor.putString("outlet_id", format[0]);
            mEditor.putString("acc_num", acc_num[0] + "-" + acc_num[1]);
            mEditor.putString("street", format[1] + " " + format[2] + " " + format[3]);
            mEditor.putString("building", format[4]);
            mEditor.putString("unit_country_pincode", format[5] + " " + format[6]);
            mEditor.putString("stall_no", format[7]);
            mEditor.putString("food_type", format[8]);
            mEditor.putString("outlet_ref", format[9]);
            startActivity(new Intent(this, AddActionsActivity.class));
            finish();
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

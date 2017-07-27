package technician.inteq.com.ugshdd.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import technician.inteq.com.ugshdd.Controller.TaskModel;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.util.AndroidDatabaseManager;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 04-Jul-17.
 */

public class Dashboard extends Activity implements View.OnClickListener {

    LinearLayout pendingCases, materialRequest, dayEndReport, leaveManagement,
            returnMaterial, materialTransfer, dailyCashReport, technicianRequest;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utility().initializeDelegate(this, R.layout.dashboard, savedInstanceState, new String[]{"Main Menu", ""});
        if (TaskModel.getOutletDetails().size() < 2) {
            for (int i = 0; i <= 10; i++) {
                TaskModel.insertTasks("000" + i + "-0BCC", "1234" + i);
            }
        }


        pendingCases = (LinearLayout) findViewById(R.id.pendingCases);
        materialRequest = (LinearLayout) findViewById(R.id.materialRequest);
        dayEndReport = (LinearLayout) findViewById(R.id.dayEndReport);
        leaveManagement = (LinearLayout) findViewById(R.id.leaveManagement);
        returnMaterial = (LinearLayout) findViewById(R.id.returnMaterial);
        materialTransfer = (LinearLayout) findViewById(R.id.materialTransfer);
        dailyCashReport = (LinearLayout) findViewById(R.id.dailyCashReport);
        technicianRequest = (LinearLayout) findViewById(R.id.technicianRequest);
        pendingCases.setOnClickListener(this);
        dailyCashReport.setOnClickListener(this);
        materialTransfer.setOnClickListener(this);
        leaveManagement.setOnClickListener(this);
        dailyCashReport.setOnClickListener(this);
        technicianRequest.setOnClickListener(this);
        context = this;
        boolean result = checkPermission();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pendingCases:
                Intent iPendingCase = new Intent(Dashboard.this, PendingCases.class);
                startActivity(iPendingCase);
                break;

            case R.id.dailyCashReport:
                Intent idailyCashReport = new Intent(Dashboard.this, DailyCashReport.class);
                startActivity(idailyCashReport);
                break;
            case R.id.materialTransfer:
                Intent iStockManagement = new Intent(Dashboard.this, StockManagementBaseActivity.class);
                startActivity(iStockManagement);
                break;
            case R.id.leaveManagement:
                Intent iLeaveManagement = new Intent(Dashboard.this, LeaveManagementBaseActivity.class);
                startActivity(iLeaveManagement);
                break;
            case R.id.technicianRequest:
                Intent itechnicianRequest = new Intent(Dashboard.this, TechnicalRequestBaseActivity.class);
                startActivity(itechnicianRequest);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.database:
                startActivity(new Intent(Dashboard.this, AndroidDatabaseManager.class));
                break;
            case R.id.logout:
                startActivity(new Intent(Dashboard.this, MainActivity.class));
                finish();
                break;
            case R.id.about:
                about();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.RECEIVE_SMS)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //writeSMSEvent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void about() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(getResources().getDrawable(R.mipmap.tick));
        alertDialog.setTitle("About Version !");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Version : 001 \n Release Date : 26th-July-2017");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}

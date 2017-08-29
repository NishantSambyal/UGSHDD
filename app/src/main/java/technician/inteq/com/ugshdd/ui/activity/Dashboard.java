package technician.inteq.com.ugshdd.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.InventoryItemController;
import technician.inteq.com.ugshdd.Controller.PerformedTaskController;
import technician.inteq.com.ugshdd.Controller.TaskController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;
import technician.inteq.com.ugshdd.ui.dialogFragment.ContactDialog;
import technician.inteq.com.ugshdd.util.AndroidDatabaseManager;

/**
 * Created by Nishant Sambyal on 04-Jul-17.
 */

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    LinearLayout pendingCases, materialRequest, dayEndReport, leaveManagement,
            returnMaterial, materialTransfer, dailyCashReport, technicianRequest;
    Context context;
    Toolbar toolbar;
    ArrayList<InventoryItem> inventoryItems;
    List<PerformedTaskBean> performedTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.option_menu);
        toolbar.setOverflowIcon(drawable);

        if (TaskController.getOutletDetails().size() < 2) {
            for (int i = 1; i <= 10; i++) {
                TaskController.insertTasks("000" + i + "-0BCC", "1234" + i);
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
        materialRequest.setOnClickListener(this);
        checkPermission();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (InventoryItemController.getAllItems().getCount() < 1) {
                    prepareList();
                    try {
                    for (InventoryItem item : inventoryItems) {
                            item.insertIntoItem();
                    }
                        for (PerformedTaskBean bean : performedTaskList) {
                            PerformedTaskController.insertTasks(bean);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pendingCases:
                Intent iPendingCase = new Intent(Dashboard.this, PendingCases.class);
                startActivity(iPendingCase);
                finish();
                break;

            case R.id.materialRequest:
                Intent iMaterialRequest = new Intent(Dashboard.this, MaterialRequestAddItems.class);
                startActivity(iMaterialRequest);
                finish();
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
        menu.findItem(R.id.completed_tasks).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.database:
                startActivity(new Intent(Dashboard.this, AndroidDatabaseManager.class));
                break;
            case R.id.contact:
                ContactDialog dFragment = new ContactDialog();
                dFragment.show(getFragmentManager(), "Dialog Fragment");
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
                    //write SMS Event();
                } else {
                    //code for deny
                }
                break;
        }
    }

    void prepareList() {
        inventoryItems = new ArrayList<>();
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.main_pipe), "New", "Auto Change", "Mainpipe", "687", "80-257-MPS-AUT-1 ", "Kagla Auto Change", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.main_pipe_kagla_used), "Used", "Auto Change", "Mainpipe", "688", "80-257-MPS-AUT-1U ", "Auto Change Kagla Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.main_pipe_itokoki), "New", "Auto Change", "Mainpipe", "4779", "80-257-MPS-AUT-267 ", "Auto Change Itokoki", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.main_pipe_itokoki_used), "Used", "Auto Change", "Mainpipe", "689", "80-257-MPS-AUT-2U ", "Auto Change Itokoki Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.fire_extinguisher), "New", "Fire Extinguisher", "Mainpipe", "934", "80-257-MPS-EXT-1 ", "1kg Fire Extinguisher", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.fire_extinguisher_pipe), "New", "Fire Extinguisher", "Mainpipe", "935", "80-257-MPS-EXT-1U ", "1kg Fire Extinguisher Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.fire_extinguisher_267), "New", "Fire Extinguisher", "Mainpipe", "936", "80-257-MPS-EXT-267 ", "Fire Extinguisher 267.5kg", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.pigtail_pipe), "Used", "Pigtail", "Mainpipe", "633", "89-275-MPS-PIG-1U ", "Pigtail Liquid SPC Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.pigtail_pol), "New", "Pigtail", "Mainpipe", "634", "89-275-MPS-PIG-267 ", "Pigtail POL (Vapour)", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.pigtail_pol_used), "Used", "Pigtail", "Mainpipe", "635", "89-275-MPS-PIG-2U ", "Pigtail POL (Vapour) Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.tee_check), "New", "Tee Check", "Mainpipe", "690", "89-275-MPS-TEE-1 ", "Tee Check No-Return Valve", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.tee_check_used), "Used", "Tee Check", "Mainpipe", "691", "89-275-MPS-TEE-1U ", "Tee Check (No-Return Valve)Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.vapourizer_mainpipe), "New", "Vapourizer", "Mainpipe", "643", "89-275-MPS-VAP-7 ", "Vapourizer Torpedo ?LED & Cable", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.meter_downpipe), "New", "Meter", "Downpipe", "748", "80-256-DPS-MET-4U ", "AL-425 Meter Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.regulator_pipe), "New", "Regulator", "Downpipe", "2543", "80-256-DPS-REG-6 ", "REGULATOR LOW PRESSURE", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.regulator_pipe_used), "New", "Regulator", "Downpipe", "4878", "80-DPS-REG-0012 ", "Regulator BCF", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_downpipe), "New", "Bush", "Downpipe", "767", "89-274-DPS-BUS-1 ", "1/267 x 1/8 MS Bush", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_downpipe_used), "Used", "Bush", "Downpipe", "768", "89-274-DPS-BUS-1U ", "Bush MS ½ x? Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.msbush_gl), "New", "Bush", "Downpipe", "769", "89-274-DPS-BUS-267 ", "Bush GI ½ x¼", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_2), "Used", "Bush", "Downpipe", "770", "89-274-DPS-BUS-2U ", "Bush GI ½ x¼ Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_3), "New", "Bush", "Downpipe", "771", "89-274-DPS-BUS-3 ", "Bush GI ½ x¾", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_4), "Used", "Bush", "Downpipe", "772", "89-274-DPS-BUS-3U ", "Bush GI ½ x¼ Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_5), "New", "Bush", "Downpipe", "773", "89-274-DPS-BUS-4 ", "Bush Brass ¼ x?", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_6), "Used", "Bush", "Downpipe", "774", "89-274-DPS-BUS-4U ", "Bush Brass ¼ x?  Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_7), "New", "Bush", "Downpipe", "775", "89-274-DPS-BUS-5 ", "Bush MS ¼ x¾", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_8), "Used", "Bush", "Downpipe", "776", "89-274-DPS-BUS-5U ", "Bush MS ¼ x¾  Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_9), "New", "Bush", "Downpipe", "777", "89-274-DPS-BUS-6 ", "Bush Brass ¼ x?", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bush_gi_10), "Used", "Bush", "Downpipe", "778", "89-274-DPS-BUS-6U ", "Bush Brass ¼ x? Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.ricecooker), "Used", "Rice Cooker", "Burner", "445", "89-274-COK-RIC-6U ", "Rice Cooker Cooker Bowl Rinnai Japan Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.rice_cooker_automatic), "New", "Rice Cooker", "Burner", "446", "89-274-COK-RIC-7 ", "Rice Cooker Auto", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.rice_cooker_used), "New", "Rice Cooker", "Burner", "447", "89-274-COK-RIC-8 ", "Rice Cooker Electronic Wire", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.rice_cooker_pilot), "New", "Rice Cooker", "Burner", "448", "89-274-COK-RIC-9 ", "Rice Cooker Pilot", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.burner_stand_used), "New", "Stand", "Burner", "485", "89-274-COK-STD-1 ", "Kwali Stand Low 380mm", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.kwali_stand_200), "Used", "Stand", "Burner", "486", "89-274-COK-STD-1U ", "Kwali Stand Low 380mm Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.kwali_stand_new), "New", "Stand", "Burner", "487", "89-274-COK-STD-267 ", "Kwali Stand High 700mm", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.burner_stand), "Used", "Stand", "Burner", "488", "89-274-COK-STD-2U ", "Kwali Stand High 700mm Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.toaster), "New", "Toaster", "Burner", "2444", "89-274-COK-TOA-267 ", "RINNAI TOASTER GLASS", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.toaster_glass), "Used", "Toaster", "Burner", "2445", "89-274-COK-TOA-2U ", "RINNAI TOASTER GLASS USED", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.toaster_used), "New", "", "Burner", "4748", "89-4--0 ", "", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bowl_ring), "New", "Bowl & Ring", "Burner", "4648", "89-4-59-0017 ", "", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.bowl_used), "New", "Bowl & Ring", "Burner", "4649", "89-4-59-0018 ", "", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.high_pressure_stove), "New", "High Pressure Stove", "Burner", "4650", "89-4-63-0001 ", "", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.high_pressure_stove_used), "New", "High Pressure Stove", "Burner", "4651", "89-4-63-0002 ", "", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.ball_valve), "Used", "Ball Valve", "Accessories", "331", "89-275-GEN-MBV-1U ", "1 Ball Valve Used", "267"));
        inventoryItems.add(new InventoryItem(convertIntoByte(R.drawable.spray_paint), "New", "Non Inventory", "Accessories", "337", "GEN-ANI-6 ", "Spray Paint-White", "267"));

        performedTaskList = new ArrayList<>();
        performedTaskList.add(new PerformedTaskBean("Replaced Burner"));
        performedTaskList.add(new PerformedTaskBean("Repaired Gas Valve"));
        performedTaskList.add(new PerformedTaskBean("Cleaned the Stove"));
        performedTaskList.add(new PerformedTaskBean("Replace Pipes"));
        performedTaskList.add(new PerformedTaskBean("Replace cylinder"));
    }

    private byte[] convertIntoByte(int resource) {
        Drawable drawable = getResources().getDrawable(resource);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] bitmapData = stream.toByteArray();
        return bitmapData;
    }

    private void about() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(getResources().getDrawable(R.mipmap.tick));
        alertDialog.setTitle("About Version !");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Version : 003 \n Release Date : 17th-Aug-2017");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}

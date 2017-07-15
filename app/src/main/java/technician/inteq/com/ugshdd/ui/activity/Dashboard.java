package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import technician.inteq.com.ugshdd.Controller.TaskModel;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.util.AndroidDatabaseManager;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

/**
 * Created by Nishant Sambyal on 04-Jul-17.
 */

public class Dashboard extends Activity implements View.OnClickListener {

    LinearLayout pendingCases, materialRequest, dayEndReport, leaveManagement,
            returnMaterial, materialTransfer, dailyCashReport, technicianRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.dashboard, savedInstanceState, new String[]{"Main Menu", ""});
        TaskModel.insertTasks("0005-0BCC", "12345");
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
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pendingCases:
                Intent iPendingCase = new Intent(Dashboard.this, PendingCasesExpandable.class);
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
        menu.findItem(R.id.logout).setVisible(false);
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
        }
        return super.onOptionsItemSelected(item);
    }
}

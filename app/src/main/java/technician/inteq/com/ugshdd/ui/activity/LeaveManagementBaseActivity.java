package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.LeaveRVAdapter;
import technician.inteq.com.ugshdd.model.Leave;
import technician.inteq.com.ugshdd.ui.fragment.leave_management.NewLeaveApplicationFragment;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

public class LeaveManagementBaseActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private RecyclerView leavesRV;
    private List<Leave> leaveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.leave_management, savedInstanceState, new String[]{"Leave Management", ""});
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        leaveList = new ArrayList<>();

        leaveList.add(new Leave("01/07/2017", "casual leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("02/07/2017", "sick leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("05/07/2017", "Earned leave", "01/07/2017", "02/07/2017", "status", "done"));
        leaveList.add(new Leave("08/07/2017", "transfer leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("07/07/2017", "study leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("01/07/2017", "transfer leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("04/07/2017", "casual leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("03/07/2017", "sick leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("02/07/2017", "casual leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("01/07/2017", "casual leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("02/07/2017", "sick leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("05/07/2017", "Earned leave", "01/07/2017", "02/07/2017", "status", "done"));
        leaveList.add(new Leave("08/07/2017", "transfer leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("07/07/2017", "study leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("01/07/2017", "transfer leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("04/07/2017", "casual leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("03/07/2017", "sick leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("02/07/2017", "casual leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("01/07/2017", "casual leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("02/07/2017", "sick leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("05/07/2017", "Earned leave", "01/07/2017", "02/07/2017", "status", "done"));
        leaveList.add(new Leave("08/07/2017", "transfer leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("07/07/2017", "study leave", "01/07/2017", "02/07/2017", "pending", "done"));
        leaveList.add(new Leave("01/07/2017", "transfer leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("04/07/2017", "casual leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("03/07/2017", "sick leave", "01/07/2017", "02/07/2017", "done", "done"));
        leaveList.add(new Leave("02/07/2017", "casual leave", "01/07/2017", "02/07/2017", "pending", "done"));

        leavesRV = (RecyclerView) findViewById(R.id.leave_app_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        leavesRV.setLayoutManager(manager);
        leavesRV.setAdapter(new LeaveRVAdapter(leaveList, this));
    }

    public void click(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.replace(R.id.main_layout, new NewLeaveApplicationFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            ToolbarUtil.setNames(new String[]{"Leave Management", ""});
            fragmentManager.popBackStack();
        } else super.onBackPressed();
    }

}

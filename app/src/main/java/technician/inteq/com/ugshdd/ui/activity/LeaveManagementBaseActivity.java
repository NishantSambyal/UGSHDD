package technician.inteq.com.ugshdd.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import technician.inteq.com.ugshdd.ui.fragment.leave_management.NewLeaveApplicationFragment;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

public class LeaveManagementBaseActivity extends FragmentActivity implements NewLeaveApplicationFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private RecyclerView leavesRV;
    private List<String> leaveList;
    private FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.leave_management, savedInstanceState, new String[]{"Leave Management", ""});
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        leaveList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            leaveList.add("item " + i);
        }
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

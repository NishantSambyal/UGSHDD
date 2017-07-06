package technician.inteq.com.ugshdd.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.fragment.leave_management.LeavesListFragment;
import technician.inteq.com.ugshdd.ui.fragment.leave_management.NewLeaveApplicationFragment;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

public class LeaveManagementBaseActivity extends FragmentActivity implements
        NewLeaveApplicationFragment.OnFragmentInteractionListener,
        LeavesListFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.leave_management, savedInstanceState, new String[]{"Leave Management", ""});
        NewLeaveApplicationFragment newLeaveApplicationFragment = new NewLeaveApplicationFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(newLeaveApplicationFragment.getTag());
        fragmentTransaction.replace(R.id.main_layout, new LeavesListFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package technician.inteq.com.ugshdd.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.fragment.LeavesListFragment;
import technician.inteq.com.ugshdd.ui.fragment.NewLeaveApplicationFragment;

public class LeaveManagemen extends AppCompatActivity implements
        NewLeaveApplicationFragment.OnFragmentInteractionListener,
        LeavesListFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_management);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Leave Management");


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

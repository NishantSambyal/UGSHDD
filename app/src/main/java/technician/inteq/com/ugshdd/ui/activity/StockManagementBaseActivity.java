package technician.inteq.com.ugshdd.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.fragment.stock_management.MaterialTransferList;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

public class StockManagementBaseActivity extends FragmentActivity implements MaterialTransferList.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.activity_stock_management, savedInstanceState, new String[]{"Stock Management", ""});
        MaterialTransferList transferList = new MaterialTransferList();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(transferList.getTag());
        fragmentTransaction.replace(R.id.main_layout, new MaterialTransferList());
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package technician.inteq.com.ugshdd.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.fragment.stock_management.MaterialTransferList;

public class StockManagementActivity extends AppCompatActivity implements MaterialTransferList.OnFragmentInteractionListener {

    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_management);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Stock Management");

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

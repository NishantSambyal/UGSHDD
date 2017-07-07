package technician.inteq.com.ugshdd.ui.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.MyPagerAdapter;
import technician.inteq.com.ugshdd.ui.fragment.stock_management.NewMaterialTransfer;

public class StockManagementBaseActivity extends FragmentActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stock_management);
        fragmentManager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void click(View view) {
        tabLayout.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.replace(R.id.main_layout, new NewMaterialTransfer());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount() == 1) {
            tabLayout.setVisibility(View.VISIBLE);
            fragmentManager.popBackStack();
        } else
            super.onBackPressed();
    }
}

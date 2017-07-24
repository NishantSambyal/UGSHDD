package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.fragment.daily_cash_report.DailyCashReportCashFragment;
import technician.inteq.com.ugshdd.ui.fragment.daily_cash_report.DailyCashReportChequeFragment;
import technician.inteq.com.ugshdd.ui.fragment.daily_cash_report.DailyCashReportTotalAmountFragment;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class NewDailyCashReportBaseActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDelegate(this, R.layout.new_daily_cash_report_base_activity, savedInstanceState, new String[]{"New Daily Cash Report", ""});
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DailyCashReportTotalAmountFragment(), "Total Amount");
        adapter.addFragment(new DailyCashReportChequeFragment(), "Cheque");
        adapter.addFragment(new DailyCashReportCashFragment(), "Cash");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}


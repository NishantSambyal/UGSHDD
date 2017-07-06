package technician.inteq.com.ugshdd.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import technician.inteq.com.ugshdd.ui.fragment.stock_management.TransferItemFragment;


/**
 * Created by Patyal on 7/5/2017.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"Transfer Item", "Accepted Item"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TransferItemFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}

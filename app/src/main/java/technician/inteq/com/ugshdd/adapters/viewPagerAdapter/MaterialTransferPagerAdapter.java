package technician.inteq.com.ugshdd.adapters.viewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import technician.inteq.com.ugshdd.ui.fragment.stock_management.AcceptedItemListFragment;
import technician.inteq.com.ugshdd.ui.fragment.stock_management.TransferItemFragment;


/**
 * Created by Patyal on 7/5/2017.
 */

public class MaterialTransferPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"Transfer Item", "Accepted Item"};

    public MaterialTransferPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new TransferItemFragment();
        } else if (position == 1) {
            fragment = new AcceptedItemListFragment();
        }
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
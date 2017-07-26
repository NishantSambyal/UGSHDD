package technician.inteq.com.ugshdd.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import technician.inteq.com.ugshdd.ui.fragment.pending_case.AddItemFragment;
import technician.inteq.com.ugshdd.ui.fragment.pending_case.ChargeableItemFragment;
import technician.inteq.com.ugshdd.ui.fragment.pending_case.ReturnItemFragment;
import technician.inteq.com.ugshdd.ui.fragment.stock_management.AcceptedItemListFragment;

/**
 * Created by Patyal on 7/24/2017.
 */

public class AddActionPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"Add Item", "Chargeable Item", "Return Item", "Performed Tasks"};

    public AddActionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new AddItemFragment();
        } else if (position == 1) {
            fragment = new ChargeableItemFragment();
        } else if (position == 2) {
            fragment = new ReturnItemFragment();
        } else if (position == 3) {
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

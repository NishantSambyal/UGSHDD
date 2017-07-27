package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.viewPagerAdapter.AddActionPagerAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.ui.dialogFragment.AddItemDialog;
import technician.inteq.com.ugshdd.ui.fragment.pending_case.AddItemFragment;
import technician.inteq.com.ugshdd.ui.fragment.pending_case.ChargeableItemFragment;
import technician.inteq.com.ugshdd.ui.fragment.pending_case.ReturnItemFragment;
import technician.inteq.com.ugshdd.util.Utility;

public class AddActionsActivity extends FragmentActivity implements AddItemDialog.AddItem {

    FloatingActionButton floatingActionButton;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utility().initializeDelegate(this, R.layout.activity_add_actions, savedInstanceState, new String[]{"Add Actions", ""});
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        fragmentManager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new AddActionPagerAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                if (!floatingActionButton.isShown()) {
                    floatingActionButton.show();
                } else {
                    if (position == 3) {
                        floatingActionButton.hide();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemDialog dFragment = new AddItemDialog();
                dFragment.show(getFragmentManager(), "Dialog Fragment");
            }
        });
    }

    @Override
    public void selectedItem(InventoryItem inventoryItem) {
        switch (viewPager.getCurrentItem()) {
            case 0:
                AddItemFragment addItemFragment = (AddItemFragment) fragmentManager.getFragments().get(0);
                addItemFragment.addItem(inventoryItem);
                break;
            case 1:
                ChargeableItemFragment chargeableItemFragment = (ChargeableItemFragment) fragmentManager.getFragments().get(1);
                chargeableItemFragment.addItem(inventoryItem);
                break;
            case 2:
                ReturnItemFragment returnItemFragment = (ReturnItemFragment) fragmentManager.getFragments().get(2);
                returnItemFragment.addItem(inventoryItem);
                break;
            case 3:
                break;
        }
    }
}

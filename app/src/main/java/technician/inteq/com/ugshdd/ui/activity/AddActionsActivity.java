package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.AddActionPagerAdapter;
import technician.inteq.com.ugshdd.ui.dialogFragment.AddItemDialog;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

public class AddActionsActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDelegate(this, R.layout.activity_add_actions, savedInstanceState, new String[]{"Add Actions", ""});
        fragmentManager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new AddActionPagerAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void click(View view) {
        AddItemDialog dFragment = new AddItemDialog();
        dFragment.show(getFragmentManager(), "Dialog Fragment");
    }
}

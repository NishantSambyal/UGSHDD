package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import technician.inteq.com.ugshdd.Controller.TaskModel;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ExpandablePendingCaseAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class PendingCasesExpandable extends Activity {

    RecyclerView recyclerView;
    ExpandablePendingCaseAdapter adapter;
    List<Outlets> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.pending_case, savedInstanceState, new String[]{"Pending cases", ""});
        prepareList();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ExpandablePendingCaseAdapter(this, list);
        adapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                Outlets expandedRecipe = list.get(parentPosition);
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
                Outlets collapsedRecipe = list.get(parentPosition);

            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void prepareList() {
        list = TaskModel.getOutletDetails();

        /*OutletDetail outletDetail = new OutletDetail("32767", "Chicken Rice", "Done");

        Outlets outlet = new Outlets("0001-0BCC", Arrays.asList(outletDetail));



        OutletDetail outletDetail2 = new OutletDetail("32767", "Chicken Rice", "Done");
        Outlets outlet2 = new Outlets("0001-0BCC", Arrays.asList(outletDetail2));


        list = Arrays.asList(outlet, outlet2, outlet, outlet, outlet, outlet, outlet, outlet2, outlet, outlet, outlet, outlet, outlet);

        *//*outletDetail = new OutletDetail( "72657", "Sandwich", "Pending");
        outlet = new Outlets("0002-0SCC",Arrays.asList(outletDetail));
        list = Arrays.asList(outlet);*/
    }
}

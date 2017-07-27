package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ExpandableTechnicalRVAdapter;
import technician.inteq.com.ugshdd.adapters.TechnicalRequestRVAdapter;
import technician.inteq.com.ugshdd.model.technicalRequestBean.TechnicalRequests;
import technician.inteq.com.ugshdd.model.technicalRequestBean.TechnicalRequestsDetails;
import technician.inteq.com.ugshdd.ui.fragment.leave_management.NewLeaveApplicationFragment;
import technician.inteq.com.ugshdd.util.Utility;

public class TechnicalRequestBaseActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private RecyclerView leavesRV;
    private List<TechnicalRequests> technicalRequestList;
    private List<TechnicalRequestsDetails> technicalRequestListsmall;
    private FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Utility().initializeDelegate(this, R.layout.activity_technical_request_base, savedInstanceState, new String[]{"Technical Request", ""});
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        technicalRequestList = new ArrayList<>();
        technicalRequestListsmall = new ArrayList<>();
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "master", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("24343", "02/07/2017", "master", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "05/07/2017", "sasi", "status", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "08/07/2017", "transfer", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "07/07/2017", "master", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "04/07/2017", "master", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "03/07/2017", "sasi", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "02/07/2017", "abby", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "master", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "02/07/2017", "master", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "05/07/2017", "sasi", "status", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "08/07/2017", "transfer", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "07/07/2017", "master", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "04/07/2017", "master", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "03/07/2017", "sasi", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "02/07/2017", "abby", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "master", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "02/07/2017", "master", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "05/07/2017", "sasi", "status", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "08/07/2017", "transfer", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "07/07/2017", "master", "pending", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "04/07/2017", "master", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "03/07/2017", "sasi", "done", "done"));
        technicalRequestListsmall.add(new TechnicalRequestsDetails("2343", "02/07/2017", "abby", "pending", "done"));

        leavesRV = (RecyclerView) findViewById(R.id.leave_app_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        leavesRV.setLayoutManager(manager);
        leavesRV.setAdapter(new TechnicalRequestRVAdapter(technicalRequestListsmall, this));

        if (leavesRV.getTag().equals("small")) {
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "master", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("24343", "02/07/2017", "master", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "05/07/2017", "sasi", "status", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "08/07/2017", "transfer", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "07/07/2017", "master", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("sbby", Arrays.asList(new TechnicalRequestsDetails("2343", "04/07/2017", "master", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "03/07/2017", "sasi", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "02/07/2017", "abby", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "master", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "02/07/2017", "master", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "05/07/2017", "sasi", "status", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "08/07/2017", "transfer", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "07/07/2017", "master", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "04/07/2017", "master", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "03/07/2017", "sasi", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "02/07/2017", "abby", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "master", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "02/07/2017", "master", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "05/07/2017", "sasi", "status", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "08/07/2017", "transfer", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("master", Arrays.asList(new TechnicalRequestsDetails("2343", "07/07/2017", "master", "pending", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "01/07/2017", "abby", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "04/07/2017", "master", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("sasi", Arrays.asList(new TechnicalRequestsDetails("2343", "03/07/2017", "sasi", "done", "done"))));
            technicalRequestList.add(new TechnicalRequests("abby", Arrays.asList(new TechnicalRequestsDetails("2343", "02/07/2017", "abby", "pending", "done"))));
            leavesRV.setAdapter(new ExpandableTechnicalRVAdapter(getApplicationContext(), technicalRequestList));
        }
    }

    public void click(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.replace(R.id.main_layout, new NewLeaveApplicationFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() == 1) {
            Utility.setNames(new String[]{"Technical Request", ""});
            fragmentManager.popBackStack();
        } else super.onBackPressed();
    }
}

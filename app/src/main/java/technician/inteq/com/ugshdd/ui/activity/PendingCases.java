package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.PendingCasesListAdapter;
import technician.inteq.com.ugshdd.model.PendingCasesBean;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class PendingCases extends Activity {

    RecyclerView recyclerView;
    ArrayList<PendingCasesBean> list;
    PendingCasesListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.pending_case, savedInstanceState, new String[]{"Pending cases", ""});
        prepareList();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new PendingCasesListAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    public void prepareList() {
        list = new ArrayList<>();

        PendingCasesBean bean = new PendingCasesBean("0001-0BCC", "32767", "Chicken Rice", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0002-0SCC", "72657", "Sandwich", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0003-0BCC", "65727", "Seafood", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0004-00MC", "57726", "Mixed Rice", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0005-0BCC", "57627", "Sea Food", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0006-0BCC", "65727", "Chicken Rice", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0007-0SCC", "77526", "Fish Soup", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0008-02MC", "87633", "Sandwich", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0009-03MC", "76383", "Chicken Rice", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0010-0BCC", "734377", "Fish Soup", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0011-0SCC", "72657", "Sandwich", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0012-0BCC", "65727", "Seafood", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0013-00MC", "57726", "Mixed Rice", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0014-0BCC", "57627", "Sea Food", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0015-0BCC", "65727", "Chicken Rice", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0016-0SCC", "77526", "Fish Soup", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0017-02MC", "87633", "Sandwich", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0018-03MC", "76383", "Chicken Rice", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0019-0BCC", "734377", "Fish Soup", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0020-0SCC", "72657", "Sandwich", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0021-0BCC", "65727", "Seafood", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0022-00MC", "57726", "Mixed Rice", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0023-0BCC", "57627", "Sea Food", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0024-0BCC", "65727", "Chicken Rice", "Pending");
        list.add(bean);

        bean = new PendingCasesBean("0025-0SCC", "77526", "Fish Soup", "Not Done");
        list.add(bean);

        bean = new PendingCasesBean("0026-02MC", "87633", "Sandwich", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0027-03MC", "76383", "Chicken Rice", "Done");
        list.add(bean);

        bean = new PendingCasesBean("0028-0BCC", "734377", "Fish Soup", "Not Done");
        list.add(bean);
    }
}

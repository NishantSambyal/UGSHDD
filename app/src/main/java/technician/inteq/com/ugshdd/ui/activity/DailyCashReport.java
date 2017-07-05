package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.CashReportAdapter;
import technician.inteq.com.ugshdd.model.DailyCashReportBean;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class DailyCashReport extends AppCompatActivity {

    RecyclerView recyclerView;
    CashReportAdapter adapter;
    ArrayList<DailyCashReportBean> list;
    private ArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_cash_report);
        prepareDemoBean();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CashReportAdapter(this, list, categories);
        recyclerView.setAdapter(adapter);
    }


    public void prepareDemoBean() {
        list = new ArrayList<>();
        categories = new ArrayList<>();
        categories.add("--Select--");
        categories.add("Edit");
        categories.add("Copy");
        categories.add("Paste");
        DailyCashReportBean bean = new DailyCashReportBean("Nishant", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Sambyal", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("AAAA", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("BBBB", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("CCC", "03/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("DDDDD", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("RRREEWW", "04/01/2017");
        list.add(bean);
    }

}

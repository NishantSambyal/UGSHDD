package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.CashReportAdapter;
import technician.inteq.com.ugshdd.model.DailyCashReportBean;
import technician.inteq.com.ugshdd.util.RecyclerTouchListener;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class DailyCashReport extends Activity {

    FloatingActionButton newCashReport;
    RecyclerView recyclerView;
    CashReportAdapter adapter;
    ArrayList<DailyCashReportBean> list;
    private ArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utility().initializeDelegate(this, R.layout.daily_cash_report, savedInstanceState, new String[]{"Daily Cash Report", ""});
        newCashReport = (FloatingActionButton) findViewById(R.id.newCashReport);
        prepareDemoBean();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CashReportAdapter(this, list, categories);
        recyclerView.setAdapter(adapter);
        newCashReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyCashReport.this, NewDailyCashReportBaseActivity.class));
            }
        });
        recyclerView.addOnScrollListener(Utility.addFabBehaviour(newCashReport));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                chooseOptions();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    public void prepareDemoBean() {
        list = new ArrayList<>();
        categories = new ArrayList<>();
        categories.add("");
        categories.add("Edit");
        categories.add("Copy");
        categories.add("Paste");

        DailyCashReportBean bean = new DailyCashReportBean("S0134 Mr Li Jim Lau, Royce", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Mr Kulkarni Arun Anantroo", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("S0044 Castillon Security (S) Pte Ltd, Royce", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Smart Timing Steel Limited", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Sumitomo Electric Industries Ltd, Singapore Branch", "03/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Federal Express Pacific Llc", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("David Bruce Sweeney", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Tan Shyang Song", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("S0044 Wong Ka Yen, Royce", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Shaun Romuald Gomez", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Anthony Stephen Clairmont", "03/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Asahi Printing Singapore Pte Ltd", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("G4S Secure Solutions (Singapore)Pte Ltd", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("G4S Secure Solutions (Singapore)Pte Ltd", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("S0044 Ahmad Muzhafar Bin Abdul Rahman, Royce", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("FMC Technologies Singapore Ptd Ltd", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("The Gold Coin Group", "03/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Rohde & Schwarz Regional Headquarters (S) Pte Ltd", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Federal Express Pacific Llc", "04/01/2017");
        list.add(bean);
        bean = new DailyCashReportBean("Simon Richard Collins", "01/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("S0044 Deborah Jane Hurd, Royce", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("One Source Distribution Pte Ltd", "02/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("The Gold Coin Group", "03/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Denon Private Limited", "04/01/2017");
        list.add(bean);

        bean = new DailyCashReportBean("Federal Express Pacific Llc", "04/01/2017");
        list.add(bean);
    }

    private void chooseOptions() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Select Action");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new String[]{"Edit", "Copy", "Paste"}) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setTextColor(Color.BLACK);
                return view;
            }
        };
        ListView listview;
        try {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View popup = inflater.inflate(android.R.layout.list_content, null);
            listview = (ListView) popup.findViewById(android.R.id.list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(DailyCashReport.this, "Under Development", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.setView(popup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}

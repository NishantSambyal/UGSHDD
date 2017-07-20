package technician.inteq.com.ugshdd.ui.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import technician.inteq.com.ugshdd.Controller.TaskModel;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ExpandablePendingCaseAdapter;
import technician.inteq.com.ugshdd.adapters.PendingCasesListAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class PendingCasesExpandable extends AppCompatActivity {

    RecyclerView recyclerView;
    ExpandablePendingCaseAdapter adapter;
    List<Outlets> list;
    List<Outlets> listPass;
    TextView toolbarTitle;
    TextView toolbarSubtitle;
    LinearLayout empty;
    PendingCasesListAdapter tabletAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_case);
//        new ToolbarUtil().initializeDeligate(this, R.layout.pending_case, savedInstanceState, new String[]{"Pending cases", ""});
        prepareList();
        empty = (LinearLayout) findViewById(R.id.empty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarSubtitle = (TextView) toolbar.findViewById(R.id.toolbar_subtitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.option_menu);
        toolbar.setOverflowIcon(drawable);
        toolbarTitle.setText("Pending cases");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if (list.size() > 0) {
            if (recyclerView.getTag().equals("xlarge")) {
                tabletAdapter = new PendingCasesListAdapter(list);
                recyclerView.setAdapter(tabletAdapter);
            } else {
                adapter = new ExpandablePendingCaseAdapter(this, list);
                recyclerView.setAdapter(adapter);
            }
        } else {
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    private void prepareList() {
        list = TaskModel.getOutletDetails();
        listPass = new ArrayList<>(list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchView.setPadding(getResources().getDimensionPixelSize(R.dimen.search_view_minus), 0, 0, 0);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (list.size() > 0) {
                    if (recyclerView.getAdapter() instanceof ExpandablePendingCaseAdapter) {
                        if (TextUtils.isEmpty(newText)) {
                            recyclerView.setAdapter(new ExpandablePendingCaseAdapter(PendingCasesExpandable.this, list));
                        } else {
                            filter(newText);
                            recyclerView.setAdapter(new ExpandablePendingCaseAdapter(PendingCasesExpandable.this, filter(newText)));
                        }
                    } else {
                        if (TextUtils.isEmpty(newText)) {
                            recyclerView.setAdapter(new PendingCasesListAdapter(list));
                        } else {
                            filter(newText);
                            recyclerView.setAdapter(new PendingCasesListAdapter(filter(newText)));
                        }
                    }
                }


                return true;
            }
        });

        return true;
    }

    public List<Outlets> filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listPass.clear();
        if (charText.length() == 0) {
            listPass.addAll(list);
        } else {
            for (Outlets s : list) {
                if (s.getOutletName().toLowerCase().contains(charText)) {
                    listPass.add(s);
                }
            }
        }
        return listPass;
    }

    public void sendSMS(String no, String msg) {
        Intent intent = new Intent(getApplicationContext(), PendingCasesExpandable.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi, null);
    }
}

package technician.inteq.com.ugshdd.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import technician.inteq.com.ugshdd.Controller.TaskModel;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ExpandablePendingCaseAdapter;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_case);
//        new ToolbarUtil().initializeDeligate(this, R.layout.pending_case, savedInstanceState, new String[]{"Pending cases", ""});
        prepareList();
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ExpandablePendingCaseAdapter(this, list);
        recyclerView.setAdapter(adapter);
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
                if (TextUtils.isEmpty(newText)) {
                    recyclerView.setAdapter(new ExpandablePendingCaseAdapter(PendingCasesExpandable.this, list));
                } else {
                    filter(newText);
                    recyclerView.setAdapter(new ExpandablePendingCaseAdapter(PendingCasesExpandable.this, filter(newText)));
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
}

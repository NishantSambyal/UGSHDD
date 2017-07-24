package technician.inteq.com.ugshdd.ui.activity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import technician.inteq.com.ugshdd.Controller.TaskModel;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ExpandablePendingCaseAdapter;
import technician.inteq.com.ugshdd.adapters.PendingCasesListAdapter;
import technician.inteq.com.ugshdd.adapters.RecyclerTouchListener;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;
import technician.inteq.com.ugshdd.util.QRScanner;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class PendingCases extends AppCompatActivity {

    RecyclerView recyclerView;
    ExpandablePendingCaseAdapter adapter;
    List<Outlets> list;
    List<Outlets> listPass;
    TextView toolbarTitle;
    TextView toolbarSubtitle;
    LinearLayout empty;
    PendingCasesListAdapter tabletAdapter;

    private static void recreateAdapter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_case);
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
                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }

                    @Override
                    public void onLongClick(View view, final int positionList) {
                        String[] popupList;
                        if (list.get(positionList).getChildList().get(0).getIsAcknowledge().equals("0")) {
                            popupList = new String[2];
                            popupList[0] = "Acknowledge";
                            popupList[1] = "Action";
                        } else {
                            popupList = new String[1];
                            popupList[0] = "Action";
                        }
                        ToolbarUtil.chooseOptions(PendingCases.this, new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                if (parent.getItemAtPosition(position).equals("Acknowledge")) {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(PendingCases.this);
                                    dialog.setMessage("Are you sure want to acknowledge this task ?");
                                    dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String outletName = list.get(positionList).getOutletName();
                                            if (TaskModel.acknowledgeTask(outletName)) {
                                                Toast.makeText(PendingCases.this, "\t" + outletName + "\nAcknowledge done", Toast.LENGTH_SHORT).show();
                                                ToolbarUtil.alertDialog.dismiss();
                                                PendingCases.this.recreate();
                                            }
                                        }
                                    });
                                    dialog.show();
                                } else if (parent.getItemAtPosition(position).equals("Action")) {
                                    ToolbarUtil.alertDialog.dismiss();
                                    ToolbarUtil.chooseOptions(PendingCases.this, new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            if (parent.getItemAtPosition(position).equals("Scan with camera")) {
                                                startActivity(new Intent(PendingCases.this, QRScanner.class));
                                            } else if (parent.getItemAtPosition(position).equals("Scan with QR scanner")) {
                                                Toast.makeText(PendingCases.this, "Under Development", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }, "Scan with camera", "Scan with QR scanner");
                                }
                            }
                        }, popupList);
                    }
                }));
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
        menu.findItem(R.id.logout).setVisible(false);
        menu.findItem(R.id.database).setVisible(false);
        menu.findItem(R.id.about).setVisible(false);
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
                            recyclerView.setAdapter(new ExpandablePendingCaseAdapter(PendingCases.this, list));
                        } else {
                            filter(newText);
                            recyclerView.setAdapter(new ExpandablePendingCaseAdapter(PendingCases.this, filter(newText)));
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
        Intent intent = new Intent(getApplicationContext(), PendingCases.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(no, null, msg, pi, null);
    }

    public void back(View view) {
        finish();
    }
}

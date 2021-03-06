package technician.inteq.com.ugshdd.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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

import technician.inteq.com.ugshdd.Controller.ContactController;
import technician.inteq.com.ugshdd.Controller.TaskController;
import technician.inteq.com.ugshdd.Database.InternalValues;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.PendingCasesListAdapter;
import technician.inteq.com.ugshdd.adapters.expandableRVAdapters.ExpandablePendingCaseAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.OutletDetail;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;
import technician.inteq.com.ugshdd.model.contact.AddContact;
import technician.inteq.com.ugshdd.ui.dialogFragment.ContactDialog;
import technician.inteq.com.ugshdd.util.QRScanner.FullScannerActivity;
import technician.inteq.com.ugshdd.util.RecyclerTouchListener;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class PendingCases extends AppCompatActivity implements InternalValues {

    RecyclerView recyclerView;
    ExpandablePendingCaseAdapter adapter;
    List<Outlets> list;
    List<Outlets> listPass;
    TextView toolbarTitle;
    TextView toolbarSubtitle;
    LinearLayout empty;
    PendingCasesListAdapter tabletAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_case);
        context = this;
        prepareList();
        empty = (LinearLayout) findViewById(R.id.empty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarSubtitle = (TextView) toolbar.findViewById(R.id.toolbar_subtitle);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.option_menu);
        toolbar.setOverflowIcon(drawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
        toolbarTitle.setText("Pending cases");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                    public void onLongClick(final View viewOutlet, final int positionList) {
                        final String[] popupList;
                        final int actuallyRequiredPosition = getPosition((String) viewOutlet.getTag());
                        if (list.get(actuallyRequiredPosition).getChildList().get(0).getIsAcknowledge().equals(Acknowledge.UNACKNOWLEDGE.toString())) {
                            popupList = new String[2];
                            popupList[0] = getString(R.string.acknowledge);
                            popupList[1] = getString(R.string.action);
                        } else {
                            popupList = new String[1];
                            popupList[0] = getString(R.string.action);
                        }
                        Utility.chooseOptions(PendingCases.this, new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                if (parent.getItemAtPosition(position).equals(getString(R.string.acknowledge))) {
                                    final AddContact contactNumber = ContactController.getSelectedNumber();
                                    if (contactNumber != null) {
                                        AlertDialog.Builder dialog = new AlertDialog.Builder(PendingCases.this);
                                        dialog.setMessage("Are you sure want to acknowledge this task ?");
                                        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (TaskController.acknowledgeTask((String) viewOutlet.getTag())) {
                                                    Toast.makeText(PendingCases.this, "\t   " + viewOutlet.getTag() + "\nAcknowledge done", Toast.LENGTH_SHORT).show();
                                                    OutletDetail object = list.get(actuallyRequiredPosition).getChildList().get(0);
                                                    sendSMS(contactNumber.getNumber(), "Case ID: " + object.getJobNumber() + " for outlet: " + object.getOutletName() + " has been acknowledged by master");
                                                    Utility.alertDialog.dismiss();
                                                    PendingCases.this.recreate();
                                                }
                                            }
                                        });
                                        dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Utility.alertDialog.dismiss();
                                            }
                                        });
                                        dialog.show();
                                    } else {
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PendingCases.this);
                                        alertDialog.setTitle("No number added");
                                        alertDialog.setCancelable(true);
                                        alertDialog.setMessage("Do you want to add it now..?");
                                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ContactDialog dFragment = new ContactDialog();
                                                dFragment.show(getFragmentManager(), "Dialog Fragment");
                                            }
                                        });
                                        alertDialog.setNeutralButton("Cancel", null);
                                        alertDialog.show();
                                        Utility.alertDialog.dismiss();
                                    }

                                } else if (parent.getItemAtPosition(position).equals(getString(R.string.action))) {
                                    Utility.alertDialog.dismiss();
                                    if (list.get(actuallyRequiredPosition).getChildList().get(0).getIsAcknowledge().equals(Acknowledge.UNACKNOWLEDGE.toString())) {
                                        Toast.makeText(PendingCases.this, "First acknowledge the task", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Utility.chooseOptions(PendingCases.this, new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                if (parent.getItemAtPosition(position).equals(getString(R.string.scan_with_camera))) {
                                                    if (checkPermission()) {
                                                        if (!Utility.isCameraAlreadyInUse()) {
                                                            Intent intent = new Intent(PendingCases.this, FullScannerActivity.class);
                                                            intent.putExtra("outlet", list.get(actuallyRequiredPosition).getChildList().get(0).getOutletName());
                                                            startActivity(intent);
                                                        } else {
                                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(PendingCases.this);
                                                            alertDialog.setTitle("Error !");
                                                            alertDialog.setCancelable(true);
                                                            alertDialog.setMessage("The camera is already used in other application. Please close all other camera applications and try again");
                                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.cancel();
                                                                }
                                                            });
                                                            alertDialog.show();
                                                        }

                                                    }
                                                    Utility.alertDialog.dismiss();

                                                } else if (parent.getItemAtPosition(position).equals(getString(R.string.inbuilt_qr_scanner))) {
                                                    Utility.toast(PendingCases.this, getString(R.string.under_development));
                                                    Utility.alertDialog.dismiss();
                                                }
                                            }
                                        }, getString(R.string.scan_with_camera), getString(R.string.inbuilt_qr_scanner));
                                    }
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
        list = TaskController.getOutletDetails();
        listPass = new ArrayList<>(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.logout).setVisible(false);
        menu.findItem(R.id.database).setVisible(false);
        menu.findItem(R.id.about).setVisible(false);
        menu.findItem(R.id.contact).setVisible(false);
        menu.findItem(R.id.import_csv).setVisible(false);
        menu.findItem(R.id.completed_tasks).setVisible(true).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(PendingCases.this, CompletedTaskActivity.class));
                return true;
            }
        });

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint("e.g 0001-0BCC");
        searchEditText.setHintTextColor(getResources().getColor(R.color.tab_text_color));
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
        startActivity(new Intent(PendingCases.this, Dashboard.class));
        finish();
    }

    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(PendingCases.this, FullScannerActivity.class));
                } else {
                    Utility.toast(context, "Unable to process further\nMust grant permission");
                }
                break;
        }
    }

    int getPosition(String outletName) {
        for (int i = 0; i < list.size(); i++) {
            Outlets outlet = list.get(i);
            if (outlet.getOutletName().equals(outletName)) {
                return i;
            }
        }
        return -1;
    }

    void finishActivity() {
        startActivity(new Intent(PendingCases.this, Dashboard.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }
}

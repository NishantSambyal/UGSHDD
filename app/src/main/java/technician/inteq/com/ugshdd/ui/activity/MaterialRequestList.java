package technician.inteq.com.ugshdd.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.MaterialRequestController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.recyclerViewAdapter.MaterialRequestRecyclerAdapter;
import technician.inteq.com.ugshdd.model.HddUpload;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequestTransaction;
import technician.inteq.com.ugshdd.ui.dialogFragment.EditItemDialog;
import technician.inteq.com.ugshdd.ui.dialogFragment.MaterialRequestHistoryDialog;
import technician.inteq.com.ugshdd.util.NetworkUtil;
import technician.inteq.com.ugshdd.util.PreferenceUtility;
import technician.inteq.com.ugshdd.util.RecyclerTouchListener;
import technician.inteq.com.ugshdd.util.Utility;

import static technician.inteq.com.ugshdd.Controller.MaterialRequestController.deleteTempTable;
import static technician.inteq.com.ugshdd.Controller.MaterialRequestController.saveTheTransaction;
import static technician.inteq.com.ugshdd.util.Utility.toast;

/**
 * Created by Nishant Sambyal on 21-Aug-17.
 */

public class MaterialRequestList extends AppCompatActivity implements EditItemDialog.RefreshItem {

    FloatingActionButton fab;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MaterialRequest> materialRequestList;
    RecyclerView.Adapter materialRequestRecyclerAdapter;
    int positionEdit;
    LinearLayout emptyLayout, buttonPanel;
    Context context;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.material_request_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        emptyLayout = (LinearLayout) findViewById(R.id.empty);
        buttonPanel = (LinearLayout) findViewById(R.id.buttons_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        prepareList();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MaterialRequestList.this, MaterialRequestAddItems.class));
                finish();
            }
        });
        checkPermission();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    @Override
    public void refresh() {
        materialRequestList.clear();
        MaterialRequest.getTempItemFromCursor(materialRequestList);
        materialRequestRecyclerAdapter.notifyItemChanged(positionEdit);
    }


    private void finishActivity() {
        startActivity(new Intent(MaterialRequestList.this, Dashboard.class));
        finish();
    }

    private void save() {
        uploadToServer(saveTheTransaction());
        toast(MaterialRequestList.this, "Transaction saved successfully");
        finishActivity();
    }


    private void uploadToServer(int transactionId) {
        if (NetworkUtil.getConnectivityStatus(this) == NetworkUtil.TYPE_WIFI /*|| NetworkUtil.getConnectivityStatus(this) == NetworkUtil.TYPE_MOBILE*/) {
            if (PreferenceUtility.getCurrentPortAndIpAddress(context).equals("")) {
                new sendData(transactionId).execute();
            } else {
                Toast.makeText(this, "Unable to upload to server..! Add IP Address", Toast.LENGTH_SHORT).show();
            }
        }
        new sendData(transactionId).execute();
    }

    public void back(View view) {
        finishActivity();
    }

    private void showBlankScreen() {
        if (materialRequestList.size() > 0) {
            emptyLayout.setVisibility(View.GONE);
            buttonPanel.setVisibility(View.VISIBLE);
        } else {
            emptyLayout.setVisibility(View.VISIBLE);
            buttonPanel.setVisibility(View.GONE);
        }
    }

    public void cancel(View view) {
        showAlert("Cancel will empty the cart. Are you sure ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTempTable();
                finishActivity();
            }
        });
    }

    public void save(View view) {
        save();
    }

    private void showAlert(String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", listener);
        alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Utility.alertDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.history:
                MaterialRequestHistoryDialog historyDialog = new MaterialRequestHistoryDialog();
                historyDialog.show(getFragmentManager(), "Dialog Fragment");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void prepareList() {
        materialRequestList = new ArrayList<>();
        MaterialRequest.getTempItemFromCursor(materialRequestList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialRequestRecyclerAdapter = new MaterialRequestRecyclerAdapter(this, materialRequestList);
        recyclerView.setAdapter(materialRequestRecyclerAdapter);
        recyclerView.addOnScrollListener(Utility.addFabBehaviour(fab));
        showBlankScreen();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, final int positionList) {
                Utility.chooseOptions(MaterialRequestList.this, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Edit")) {
                            positionEdit = positionList;
                            EditItemDialog dFragment = new EditItemDialog();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("material_request_item", materialRequestList.get(positionList));
                            dFragment.setArguments(bundle);
                            dFragment.show(getFragmentManager(), "Dialog Fragment");
                            Utility.alertDialog.dismiss();

                        } else {
                            showAlert("Are you sure want to delete this item ?", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MaterialRequestController.deleteMaterialRequest(materialRequestList.get(positionList).getColID());
                                    materialRequestRecyclerAdapter.notifyItemRemoved(positionList);
                                    materialRequestList.remove(positionList);
                                    Toast.makeText(MaterialRequestList.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                                    Utility.alertDialog.dismiss();
                                    if (!fab.isShown()) {
                                        fab.show();
                                    }
                                    showBlankScreen();
                                }
                            });
                        }
                    }
                }, "Edit", "Delete");
            }
        }));
    }

    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
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
                    //write SMS Event();
                } else {
                    //code for deny
                }
                break;
        }
    }

    public class sendData extends AsyncTask<Void, Void, Void> {

        int transactionId;

        public sendData(int transactionId) {
            this.transactionId = transactionId;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HddUpload batchDemo = new HddUpload();
            batchDemo.setMaterialRequestsTransactions(MaterialRequest.getMaterialRequestTransaction(new ArrayList<MaterialRequestTransaction>(), String.valueOf(transactionId)));
            batchDemo.setMaterialRequests(MaterialRequest.getMaterialRequest(new ArrayList<MaterialRequest>(), String.valueOf(transactionId)));

            String jsonData = new Gson().toJson(batchDemo);
            System.out.println("vikaspatyaallll" + jsonData);
            try {
                String response = NetworkUtil.hitServer(url, jsonData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

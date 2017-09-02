package technician.inteq.com.ugshdd.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.MaterialRequestController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.recyclerViewAdapter.MaterialRequestRecyclerAdapter;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.ui.dialogFragment.EditItemDialog;
import technician.inteq.com.ugshdd.util.RecyclerTouchListener;
import technician.inteq.com.ugshdd.util.Utility;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_request_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    @Override
    public void refresh() {
        materialRequestRecyclerAdapter.notifyItemChanged(positionEdit);

    }


    private void finishActivity() {
        startActivity(new Intent(MaterialRequestList.this, Dashboard.class));
        finish();
    }

    public void back(View view) {
        finishActivity();
    }

    public void cancel(View view) {

    }

    public void save(View view) {

    }

    private void showAlert(String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(getResources().getDrawable(R.mipmap.tick));
        alertDialog.setCancelable(true);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", listener);
        alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utility.alertDialog.dismiss();
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

        }
        return super.onOptionsItemSelected(item);
    }

    void prepareList() {
        materialRequestList = new ArrayList<>();
        MaterialRequest.getItemFromCursor(materialRequestList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialRequestRecyclerAdapter = new MaterialRequestRecyclerAdapter(this, materialRequestList);
        recyclerView.setAdapter(materialRequestRecyclerAdapter);
        recyclerView.addOnScrollListener(Utility.addFabBehaviour(fab));
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
                                }
                            });
                        }
                    }
                }, "Edit", "Delete");
            }
        }));
    }
}

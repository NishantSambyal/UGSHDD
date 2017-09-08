package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.recyclerViewAdapter.MaterialRequestRecyclerAdapter;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.ui.dialogFragment.MaterialRequestHistoryDialog;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 02-Sep-17.
 */

public class MaterialRequestTransactionSummary extends Activity {

    RecyclerView recyclerView;
    List<MaterialRequest> materialRequestList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utility().initializeDelegate(this, R.layout.material_request_list, savedInstanceState, new String[]{"Requested History", ""});
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        prepareList();
        recyclerView.setAdapter(new MaterialRequestRecyclerAdapter(this, materialRequestList));
        findViewById(R.id.empty).setVisibility(View.GONE);
        findViewById(R.id.fab).setVisibility(View.GONE);
        findViewById(R.id.buttons_view).setVisibility(View.GONE);

    }

    void prepareList() {
        materialRequestList = new ArrayList<>();
        MaterialRequest.getItemFromCursor(materialRequestList, getIntent().getStringExtra(MaterialRequestHistoryDialog.TRANSACTION_ID));
    }

    public void back(View view) {
        finish();
    }
}

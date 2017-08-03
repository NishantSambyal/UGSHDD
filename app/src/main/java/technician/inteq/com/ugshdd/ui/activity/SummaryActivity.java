package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.CaseController;
import technician.inteq.com.ugshdd.Database.InternalValues;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ArrayAdapters.SummaryItemAdapter;
import technician.inteq.com.ugshdd.adapters.ArrayAdapters.SummaryPTAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskHelper;
import technician.inteq.com.ugshdd.util.ExpandableHeightListView;
import technician.inteq.com.ugshdd.util.UGSApplication;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 02-Aug-17.
 */

public class SummaryActivity extends Activity implements InternalValues, View.OnClickListener {

    private static final int SIGNATURE_ACTIVITY = 1;
    private ExpandableHeightListView listViewAdd, listViewChargeable, listViewReturned, listViewPerformedTasks;
    private List<Case> addItemList, chargeableItemList, returnItemList;
    private List<PerformedTaskBean> performedList = new ArrayList<>();
    private Button sign, save, cancel;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utility().initializeDelegate(this, R.layout.summary, savedInstanceState, new String[]{"Summary", ""});
        loadSummary();
        listViewAdd = (ExpandableHeightListView) findViewById(R.id.listView_add);
        listViewChargeable = (ExpandableHeightListView) findViewById(R.id.listView_chargeable);
        listViewReturned = (ExpandableHeightListView) findViewById(R.id.listView_returned);
        listViewPerformedTasks = (ExpandableHeightListView) findViewById(R.id.listView_performedTasks);
        listViewAdd.setAdapter(new SummaryItemAdapter(this, R.layout.summary_single_item, addItemList));
        listViewChargeable.setAdapter(new SummaryItemAdapter(this, R.layout.summary_single_item, chargeableItemList));
        listViewReturned.setAdapter(new SummaryItemAdapter(this, R.layout.summary_single_item, returnItemList));
        listViewPerformedTasks.setAdapter(new SummaryPTAdapter(this, R.layout.summary_pt_single_item, performedList));

        listViewAdd.setExpanded(true);
        listViewChargeable.setExpanded(true);
        listViewReturned.setExpanded(true);
        listViewPerformedTasks.setExpanded(true);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        sign = (Button) findViewById(R.id.sign);
        imageView = (ImageView) findViewById(R.id.sign_view);

        sign.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    public void loadSummary() {
        addItemList = CaseController.loadItems(ItemType.AddItem.toString(), UGSApplication.accountNumber);
        chargeableItemList = CaseController.loadItems(ItemType.ChargeableItem.toString(), UGSApplication.accountNumber);
        returnItemList = CaseController.loadItems(ItemType.ReturnItem.toString(), UGSApplication.accountNumber);
        PerformedTaskHelper.getPerformedTask(UGSApplication.accountNumber, performedList);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String status = bundle.getString("status");
                    byte[] byte_arr = bundle.getByteArray("image");
                    if (status.equalsIgnoreCase("done")) {
                        Toast.makeText(SummaryActivity.this, "Signature capture successful!", Toast.LENGTH_SHORT).show();
                        final Bitmap mCBitmap2 = BitmapFactory.decodeByteArray(byte_arr, 0, byte_arr.length);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(mCBitmap2);
                        sign.setVisibility(View.GONE);
                    }
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                if (imageView.getVisibility() == View.VISIBLE) {
                    finishAffinity();
                    startActivity(new Intent(SummaryActivity.this, Dashboard.class));
                    startActivity(new Intent(SummaryActivity.this, PendingCases.class));
                } else {
                    Utility.toast(SummaryActivity.this, "Signature left");
                }
                break;
            case R.id.cancel:
                finish();
                break;
            case R.id.sign:
                startActivityForResult(new Intent(SummaryActivity.this, SignatureActivity.class), SIGNATURE_ACTIVITY);
                break;
        }
    }
}

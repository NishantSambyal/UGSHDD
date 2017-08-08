package technician.inteq.com.ugshdd.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.CaseController;
import technician.inteq.com.ugshdd.Controller.PerformedTaskController;
import technician.inteq.com.ugshdd.Controller.TaskController;
import technician.inteq.com.ugshdd.Database.InternalValues;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ArrayAdapters.SummaryItemAdapter;
import technician.inteq.com.ugshdd.adapters.ArrayAdapters.SummaryPTAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskHelper;
import technician.inteq.com.ugshdd.util.CreatePDF;
import technician.inteq.com.ugshdd.util.ExpandableHeightListView;
import technician.inteq.com.ugshdd.util.UGSApplication;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 02-Aug-17.
 */

public class SummaryActivity extends Activity implements InternalValues, View.OnClickListener {

    private static final int SIGNATURE_ACTIVITY = 1;
    Context context;
    File file;
    boolean done;
    private ExpandableHeightListView listViewAdd, listViewChargeable, listViewReturned, listViewPerformedTasks;
    private List<Case> addItemList, chargeableItemList, returnItemList;
    private List<PerformedTaskBean> performedList = new ArrayList<>();
    private Button sign, save, cancel;
    private ImageView imageView;
    private byte[] byteArrSignature;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        new Utility().initializeDelegate(this, R.layout.summary, savedInstanceState, new String[]{"Summary", ""});

        loadSummary();
        hideEmptyBlocks();

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

    void hideEmptyBlocks() {
        if (addItemList.size() == 0) {
            findViewById(R.id.card_add_item).setVisibility(View.GONE);
        }
        if (chargeableItemList.size() == 0) {
            findViewById(R.id.card_chargable_item).setVisibility(View.GONE);
        }
        if (returnItemList.size() == 0) {
            findViewById(R.id.card_return_item).setVisibility(View.GONE);
        }
        if (performedList.size() == 0) {
            findViewById(R.id.card_performed_task).setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    name = bundle.getString("name");
                    byteArrSignature = bundle.getByteArray("image");
                    if (bundle.getString("status").equalsIgnoreCase("done")) {
                        Toast.makeText(SummaryActivity.this, "Signature capture successful!", Toast.LENGTH_SHORT).show();
                        final Bitmap mCBitmap2 = BitmapFactory.decodeByteArray(byteArrSignature, 0, byteArrSignature.length);
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
                    saveTasks();
                    if (checkPermission()) {
                        createPDFThread();
                    }

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

    public boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(SummaryActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
                    createPDFThread();

                } else {
                    //code for deny
                }
                break;
        }
    }

    private void createPDFThread() {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Saving data");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                done = createPDF();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (done) {
                            dialog.dismiss();
                            finishAffinity();
                            startActivity(new Intent(SummaryActivity.this, Dashboard.class));
                            startActivity(new Intent(SummaryActivity.this, PendingCases.class));
                            Utility.toast(context, "Transaction done successfully");
                        } else {
                            Utility.toast(context, "Error while creating PDF");
                        }

                    }
                });
            }
        }).start();
    }

    boolean createPDF() {
        Document document = new Document(PageSize.A4);
        File myDir = new File(Environment.getExternalStorageDirectory() + File.separator + "UGS_HHD" + File.separator);
        myDir.mkdirs();
        try {
            file = new File(myDir + File.separator + "HHD_PDF" + new SimpleDateFormat("dd_MM_hh_mm_ss").format(new Date()) + ".pdf");
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            CreatePDF pfd = new CreatePDF(SummaryActivity.this, addItemList, chargeableItemList, returnItemList, performedList);
            pfd.createPDF(document);
            document.close();
            return true;
        } catch (Exception e) {
            Utility.toast(SummaryActivity.this, "" + e);
        }
        return false;

    }

    void saveTasks() {
        TaskController.completeTask(UGSApplication.accountNumber, byteArrSignature, name);
        CaseController.saveOngoingTask(UGSApplication.accountNumber);
        PerformedTaskController.savePerformedTask();
    }
}

package technician.inteq.com.ugshdd.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ArrayAdapters.CompletedCaseAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.util.Utility;

public class CompletedTaskActivity extends AppCompatActivity {

    ListView listView;
    CompletedCaseAdapter adapter;
    List<Case> cases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Case.getCompletedCases(cases);
        setContentView(R.layout.activity_completed_task);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new CompletedCaseAdapter(this, R.layout.completed_task_item, cases));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String[] popupList = {getString(R.string.documents)};
                Utility.chooseOptions(CompletedTaskActivity.this, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        if (parent.getItemAtPosition(position).equals(getString(R.string.documents))) {
                            Utility.alertDialog.dismiss();
                            Utility.toast(CompletedTaskActivity.this, "Under Development\nYou can view PDF in External Storage /UGS_HHD Folder");
                        }
                    }
                }, popupList);
                return true;
            }
        });
    }
}

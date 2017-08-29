package technician.inteq.com.ugshdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
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
    List<String> filenames;
    List<String> filePaths;

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
                            filenames = new ArrayList<>();
                            filePaths = new ArrayList<>();
                            int sno = 1;
                            for (File file : new File(Environment.getExternalStorageDirectory() + File.separator + "UGS_HHD" + File.separator).listFiles()) {
                                if (cases.get(position).getOutlet().equals(file.getName().substring(4, 13))) {
                                    String fg = file.getName().substring(0, file.getName().indexOf(" ")) + "\n\t" + file.getName().substring(file.getName().indexOf(" "), file.getName().length());
                                    Log.e("name: ", fg);
                                    filenames.add(sno + ". " + fg);
                                    filePaths.add(file.getAbsolutePath());
                                    sno++;
                                }
                            }

                            Utility.chooseOptions(CompletedTaskActivity.this, new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                    Intent intent = new Intent(CompletedTaskActivity.this, PDFViewerActivity.class);
                                    intent.putExtra("filepath", filePaths.get(position));
                                    startActivity(intent);
                                }
                            }, filenames.toArray(new String[0]));
                        }
                    }
                }, popupList);
                return true;
            }
        });
    }
}

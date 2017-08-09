package technician.inteq.com.ugshdd.ui.fragment.pending_case;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.PerformedTaskController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskHelper;
import technician.inteq.com.ugshdd.ui.activity.SummaryActivity;
import technician.inteq.com.ugshdd.util.ExpandableHeightListView;
import technician.inteq.com.ugshdd.util.UGSApplication;
import technician.inteq.com.ugshdd.util.Utility;

import static technician.inteq.com.ugshdd.Database.DatabaseValues.COL_OUTLET;
import static technician.inteq.com.ugshdd.Database.DatabaseValues.TABLE_ONGOING_TASKS;
import static technician.inteq.com.ugshdd.Database.DatabaseValues.TABLE_PERFORMED_TASKS_TEMP;

public class PerformedTask extends Fragment {


    ExpandableHeightListView listView;
    List<PerformedTaskBean> taskList;
    List<PerformedTaskBean> selectedTasks;
    Spinner spinner_category;
    String[] categoryArray = {"--select category--", "Category 1", "Category 2", "Category 3", "Category 4"};
    private List<PerformedTaskBean> performedList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PerformedTaskHelper.getPerformedTask(UGSApplication.accountNumber, performedList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performed_task, container, false);

        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTasks = new ArrayList<>();
                PerformedTaskHelper.getPerformedTask(UGSApplication.accountNumber, selectedTasks);
                if (selectedTasks.size() > 0) {
                    startActivity(new Intent(getActivity(), SummaryActivity.class));
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setMessage("No tasks done ?");
                    dialog.setPositiveButton("OK", null);
                    dialog.show();
                }
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.chooseOptions(getActivity(), new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Clear all data")) {
                            SQLiteDatabase db = UGSApplication.getDb();
                            db.delete(TABLE_ONGOING_TASKS, COL_OUTLET + " = ?", new String[]{UGSApplication.accountNumber});
                            db.delete(TABLE_PERFORMED_TASKS_TEMP, COL_OUTLET + " = ?", new String[]{UGSApplication.accountNumber});
                            getActivity().finish();
                        } else {
                            getActivity().finish();
                        }
                    }
                }, "Clear all data", "do later");
            }
        });
        listView = (ExpandableHeightListView) view.findViewById(R.id.listView);
        spinner_category = (Spinner) view.findViewById(R.id.spinner_category);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoryArray);
        spinner_category.setAdapter(spinnerAdapter);
        prepareList();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.select_dialog_multichoice, taskList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckedTextView chk = (CheckedTextView) view;

                for (PerformedTaskBean taskBean : performedList) {
                    if (taskBean.getId().equals(taskList.get(position).getId())) {
                        ((ListView) parent).setItemChecked(position, true);
                    }
                }
                chk.setPadding(15, 3, 15, 3);
                chk.setMinimumHeight((int) getActivity().getResources().getDimension(R.dimen.task_view_min_height));
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(taskList.get(position).getTasks());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getActivity().getResources().getDimension(R.dimen.task_view));
                return view;
            }
        };
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView chk = (CheckedTextView) view;
                if (chk.isChecked()) {
                    try {
                        PerformedTaskController.insertTempTask(new PerformedTaskHelper(UGSApplication.accountNumber, taskList.get(position).getId()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        PerformedTaskController.removeTempTask(taskList.get(position).getId(), UGSApplication.accountNumber);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        in_x = (int) event.getX();
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(in_x - event.getX()) > 50)
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;

                }
                v.onTouchEvent(event);
                return true;
            }
        });*/

        listView.setAdapter(adapter);
        return view;
    }

    void prepareList() {

        taskList = new ArrayList<>();
        PerformedTaskBean.getPerformedTask(taskList);
    }

}

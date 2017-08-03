package technician.inteq.com.ugshdd.ui.fragment.pending_case;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import technician.inteq.com.ugshdd.util.UGSApplication;

public class PerformedTask extends Fragment {


    ListView listView;
    List<PerformedTaskBean> taskList;
    List<PerformedTaskHelper> selectedTasks;
    Spinner spinner_category;
    String[] categoryArray = {"--select category--", "Category 1", "Category 2", "Category 3", "Category 4"};
    int in_x = 0;
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
                try {
                    PerformedTaskController.insertTempTasks(selectedTasks);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getActivity(), SummaryActivity.class));
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        listView = (ListView) view.findViewById(R.id.listView);
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
                Log.e("defined....", "" + taskList.get(position).getId());
                for (PerformedTaskBean taskBean : performedList) {
                    if (taskBean.getId().equals(taskList.get(position).getId())) {
//                        ((view).setSelected(true));
                        ((ListView) parent).setItemChecked(position, true);
                        Log.e("kjsdbvksjbdvksbdvk....", "kjcbkdsbkabdvkjabsdvosbdkvjbsdkvbsdlkbvksjd");
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
                    selectedTasks.add(new PerformedTaskHelper(UGSApplication.accountNumber, taskList.get(position).getId()));
                } else {
                    for (PerformedTaskHelper task : selectedTasks) {
                        if (taskList.get(position).getId().equals(task.getTask())) {
                            selectedTasks.remove(selectedTasks.indexOf(task));
                            break;
                        }
                    }
                }
            }
        });
        listView.setOnTouchListener(new ListView.OnTouchListener() {
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
        });

        listView.setAdapter(adapter);
        return view;
    }

    void prepareList() {
        selectedTasks = new ArrayList<>();
        taskList = new ArrayList<>();
        PerformedTaskBean.getPerformedTask(taskList);
    }

}

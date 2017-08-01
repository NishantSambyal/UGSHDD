package technician.inteq.com.ugshdd.ui.fragment.pending_case;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.ui.activity.SignatureActivity;
import technician.inteq.com.ugshdd.util.Utility;

import static android.app.Activity.RESULT_OK;

public class PerformedTask extends Fragment {

    private static final int SIGNATURE_ACTIVITY = 1;
    ListView listView;
    List<String> taskList;
    Spinner spinner_category;
    String[] categoryArray = {"--select category--", "Category 1", "Category 2", "Category 3", "Category 4"};
    ImageView imageView;
    Button sign;
    int in_x = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performed_task, container, false);

        sign = (Button) view.findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), SignatureActivity.class), SIGNATURE_ACTIVITY);
            }
        });
        view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.toast(getActivity(), "saved successfully");
                getActivity().finish();
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        imageView = (ImageView) view.findViewById(R.id.sign_view);
        listView = (ListView) view.findViewById(R.id.listView);
        spinner_category = (Spinner) view.findViewById(R.id.spinner_category);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoryArray);
        spinner_category.setAdapter(spinnerAdapter);
        addTasks();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.select_dialog_multichoice, taskList);
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    void addTasks() {
        taskList = new ArrayList<>();
        taskList.add("Replaced Burner");
        taskList.add("Repaired Gas Valve");
        taskList.add("Cleaned the Stove");
        taskList.add("Replace Pipes");
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
                    //DatabaseHandler db = new DatabaseHandler(getActivity());
                    byte_arr = bundle.getByteArray("image");
                    //   db.insertSignature(byte_arr);
                    System.out.println("/*/*/*/*  =" + byte_arr);
                    if (status.equalsIgnoreCase("done")) {
                        Toast.makeText(getActivity(), "Signature capture successful!", Toast.LENGTH_SHORT).show();

                        final Bitmap mCBitmap2 = BitmapFactory.decodeByteArray(byte_arr, 0, byte_arr.length);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(mCBitmap2);
                        sign.setVisibility(View.GONE);
                    }
                }
                break;
        }

    }
}

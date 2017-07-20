package technician.inteq.com.ugshdd.ui.fragment.leave_management;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import technician.inteq.com.ugshdd.R;

public class NewLeaveApplicationFragment extends Fragment {

    public NewLeaveApplicationFragment() {
    }

    public static NewLeaveApplicationFragment newInstance(String param1, String param2) {
        NewLeaveApplicationFragment fragment = new NewLeaveApplicationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_leave_application, container, false);
        return view;
    }
}

package technician.inteq.com.ugshdd.ui.fragment.leave_management;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.LeaveRVAdapter;

public class LeavesListFragment extends Fragment {

    private RecyclerView leavesRV;
    private List<String> leaveList;
    private FloatingActionButton fabButton;
    private OnFragmentInteractionListener mListener;

    public LeavesListFragment() {
    }

    public static LeavesListFragment newInstance(String param1, String param2) {
        LeavesListFragment fragment = new LeavesListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leaves_list, container, false);

        leaveList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            leaveList.add("item " + i);
        }
        leavesRV = (RecyclerView) view.findViewById(R.id.leave_app_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        leavesRV.setLayoutManager(manager);
        leavesRV.setAdapter(new LeaveRVAdapter(leaveList, getContext()));
        fabButton = (FloatingActionButton) view.findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewLeaveApplicationFragment newLeaveApplicationFragment = new NewLeaveApplicationFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(newLeaveApplicationFragment.getTag());
                fragmentTransaction.replace(R.id.main_layout, newLeaveApplicationFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}

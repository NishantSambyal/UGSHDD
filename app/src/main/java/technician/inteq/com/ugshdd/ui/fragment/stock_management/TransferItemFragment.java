package technician.inteq.com.ugshdd.ui.fragment.stock_management;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.LeaveRVAdapter;

public class TransferItemFragment extends Fragment {

    private RecyclerView materialListRV;
    private List<String> materialTransferList;
//    private FloatingActionButton floatingActionButton;

    public TransferItemFragment() {
    }

    public static TransferItemFragment newInstance(String param1, String param2) {
        TransferItemFragment fragment = new TransferItemFragment();
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

        View view = inflater.inflate(R.layout.fragment_transfer_item, container, false);
        materialListRV = (RecyclerView) view.findViewById(R.id.material_transfer_list);
        materialTransferList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            materialTransferList.add("nishant" + i);
        }

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        materialListRV.setLayoutManager(manager);
        materialListRV.setAdapter(new LeaveRVAdapter(materialTransferList, getContext()));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}

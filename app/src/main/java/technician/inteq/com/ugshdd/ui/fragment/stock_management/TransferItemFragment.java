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
import technician.inteq.com.ugshdd.adapters.MaterialListRVAdapter;
import technician.inteq.com.ugshdd.model.TransferItem;

public class TransferItemFragment extends Fragment {

    private RecyclerView materialListRV;
    private List<TransferItem> materialTransferList;

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

        materialTransferList.add(new TransferItem("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("04/07/2017", "master", "abby", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("04/07/2017", "master", "abby", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferList.add(new TransferItem("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("04/07/2017", "master", "abby", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"));
        materialTransferList.add(new TransferItem("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        materialListRV.setLayoutManager(manager);
        materialListRV.setAdapter(new MaterialListRVAdapter(materialTransferList, getContext()));
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

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
import java.util.Arrays;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.MaterialListRVAdapter;
import technician.inteq.com.ugshdd.adapters.expandableRVAdapters.ExpandableMaterialListRVAdapter;
import technician.inteq.com.ugshdd.model.TransferItemxllayout;
import technician.inteq.com.ugshdd.model.transferItemBean.TransferItem;
import technician.inteq.com.ugshdd.model.transferItemBean.TransferItemDetail;

public class TransferItemFragment extends Fragment {

    private RecyclerView materialListRV;
    private List<TransferItemxllayout> materialTransferListxllayout;
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
        materialTransferListxllayout = new ArrayList<>();
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("04/07/2017", "master", "abby", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("04/07/2017", "master", "abby", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("04/07/2017", "master", "abby", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"));
        materialTransferListxllayout.add(new TransferItemxllayout("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        materialListRV.setLayoutManager(manager);
        materialListRV.setAdapter(new MaterialListRVAdapter(materialTransferListxllayout, getContext()));


        if (materialListRV.getTag().equals("small")) {
            materialListRV.setLayoutManager(manager);
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("sbby", Arrays.asList(new TransferItemDetail("04/07/2017", "master", "abby", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("04/07/2017", "master", "abby", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("01/07/2017", "master", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("02/07/2017", "master", "abby", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("05/07/2017", "sasi", "master", "02/07/2017", "status", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("01/07/2017", "abby", "sasi", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("08/07/2017", "transfer", "master", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("master", Arrays.asList(new TransferItemDetail("07/07/2017", "master", "abby", "02/07/2017", "pending", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("01/07/2017", "abby", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("04/07/2017", "master", "abby", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("sasi", Arrays.asList(new TransferItemDetail("03/07/2017", "sasi", "sasi", "02/07/2017", "done", "done"))));
            materialTransferList.add(new TransferItem("abby", Arrays.asList(new TransferItemDetail("02/07/2017", "abby", "master", "02/07/2017", "pending", "done"))));


            materialListRV.setAdapter(new ExpandableMaterialListRVAdapter(getContext(), materialTransferList));

        }


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

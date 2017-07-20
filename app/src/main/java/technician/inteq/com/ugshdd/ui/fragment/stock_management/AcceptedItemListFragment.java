package technician.inteq.com.ugshdd.ui.fragment.stock_management;

import android.content.Context;
import android.net.Uri;
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
import technician.inteq.com.ugshdd.adapters.AcceptedItemRVAdapter;
import technician.inteq.com.ugshdd.adapters.ExpandableAccepedItemRVAdapter;
import technician.inteq.com.ugshdd.model.AcceptedItemBean.AcceptedItem;
import technician.inteq.com.ugshdd.model.AcceptedItemBean.AcceptedItemDetails;


public class AcceptedItemListFragment extends Fragment {


    private RecyclerView acceptedItemRV;
    private List<AcceptedItem> acceptedItemList;
    private List<String> acceptedItemListxlarge;

    public AcceptedItemListFragment() {
    }

    public static AcceptedItemListFragment newInstance(String param1, String param2) {
        AcceptedItemListFragment fragment = new AcceptedItemListFragment();
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
        View view = inflater.inflate(R.layout.fragment_accepted_item_list, container, false);
        acceptedItemRV = (RecyclerView) view.findViewById(R.id.accepted_item_list);
        acceptedItemList = new ArrayList<>();
        acceptedItemListxlarge = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            acceptedItemListxlarge.add("item ");
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        acceptedItemRV.setLayoutManager(manager);
        acceptedItemRV.setAdapter(new AcceptedItemRVAdapter(acceptedItemListxlarge, getContext()));


        if (acceptedItemRV.getTag().equals("small")) {
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("02/07/2017", "abby", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("05/07/2017", "master", "02/07/2017", "status", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("08/07/2017", "master", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("07/07/2017", "abby", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("sbby", Arrays.asList(new AcceptedItemDetails("04/07/2017", "abby", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("03/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("02/07/2017", "master", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("02/07/2017", "abby", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("05/07/2017", "master", "02/07/2017", "status", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("08/07/2017", "master", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("07/07/2017", "abby", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("04/07/2017", "abby", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("03/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("02/07/2017", "master", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("02/07/2017", "abby", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("05/07/2017", "master", "02/07/2017", "status", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("08/07/2017", "master", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("master", Arrays.asList(new AcceptedItemDetails("07/07/2017", "abby", "02/07/2017", "pending", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("01/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("04/07/2017", "abby", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("sasi", Arrays.asList(new AcceptedItemDetails("03/07/2017", "sasi", "02/07/2017", "done", "done"))));
            acceptedItemList.add(new AcceptedItem("abby", Arrays.asList(new AcceptedItemDetails("02/07/2017", "master", "02/07/2017", "pending", "done"))));

            acceptedItemRV.setAdapter(new ExpandableAccepedItemRVAdapter(getContext(), acceptedItemList));

        }
        return view;
    }

    public void onButtonPressed(Uri uri) {

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

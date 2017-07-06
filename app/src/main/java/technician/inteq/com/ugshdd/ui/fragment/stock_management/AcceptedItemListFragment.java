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
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.AcceptedItemRVAdapter;


public class AcceptedItemListFragment extends Fragment {


    private RecyclerView acceptedItemRV;
    private List<String> acceptedItemList;

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
        for (int i = 0; i <= 9; i++) {
            acceptedItemList.add("item " + i);
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        acceptedItemRV.setLayoutManager(manager);
        acceptedItemRV.setAdapter(new AcceptedItemRVAdapter(acceptedItemList, getContext()));
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

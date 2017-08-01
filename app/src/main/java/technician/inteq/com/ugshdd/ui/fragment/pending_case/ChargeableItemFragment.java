package technician.inteq.com.ugshdd.ui.fragment.pending_case;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.CaseController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.AddItemAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.util.RecyclerTouchListener;
import technician.inteq.com.ugshdd.util.UGSApplication;
import technician.inteq.com.ugshdd.util.Utility;

public class ChargeableItemFragment extends Fragment {
    private final String type = "2";
    RecyclerView recyclerView;
    ArrayList<InventoryItem> inventoryItems;
    AddItemAdapter addItemAdapter;
    LinearLayout layout;
    List<Case> cases;

    public ChargeableItemFragment() {
        // Required empty public constructor
    }

    public static ChargeableItemFragment newInstance(String param1, String param2) {
        ChargeableItemFragment fragment = new ChargeableItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cases = CaseController.loadItems(type, UGSApplication.accountNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        layout = (LinearLayout) view.findViewById(R.id.empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.item_rv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        addItemAdapter = new AddItemAdapter(cases, getContext());
        recyclerView.setAdapter(addItemAdapter);
        recyclerView.addOnScrollListener(Utility.addFabBehaviour((FloatingActionButton) getActivity().findViewById(R.id.fab)));
        if (cases.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, final int positionList) {
                final String[] popupList = {getString(R.string.edit), getString(R.string.delete)};
                Utility.chooseOptions(getContext(), new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        if (parent.getItemAtPosition(position).equals(getString(R.string.edit))) {
                            Utility.alertDialog.dismiss();
                        } else if (parent.getItemAtPosition(position).equals(getString(R.string.delete))) {
                            CaseController.deletePendingCases(cases.get(positionList).getOutlet(), cases.get(positionList).getId());
                            cases = CaseController.loadItems(type, UGSApplication.accountNumber);
                            recyclerView.setAdapter(new AddItemAdapter(cases, getContext()));
                            Utility.alertDialog.dismiss();
                            if (cases.size() == 0) {
                                recyclerView.setVisibility(View.GONE);
                                layout.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }, popupList);
            }
        }));
        return view;
    }

    public void addItem() {
        cases = CaseController.loadItems(type, UGSApplication.accountNumber);
        recyclerView.setAdapter(new AddItemAdapter(cases, getContext()));
        addItemAdapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
    }

}

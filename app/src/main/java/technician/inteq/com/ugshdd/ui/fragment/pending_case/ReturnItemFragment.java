package technician.inteq.com.ugshdd.ui.fragment.pending_case;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.AddItemAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

public class ReturnItemFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<InventoryItem> inventoryItems;

    public ReturnItemFragment() {
    }

    public static ReturnItemFragment newInstance(String param1, String param2) {
        ReturnItemFragment fragment = new ReturnItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_return_item, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.return_item_rv);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        inventoryItems = new ArrayList<>();
        inventoryItems.add(new InventoryItem(R.drawable.main_pipe, "New", "Auto Change", "Mainpipe", "687", "80-257-MPS-AUT-1 ", "Kagla Auto Change", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.main_pipe, "Used", "Auto Change", "Mainpipe", "688", "80-257-MPS-AUT-1U ", "Auto Change Kagla Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.main_pipe, "New", "Auto Change", "Mainpipe", "4779", "80-257-MPS-AUT-2 ", "Auto Change Itokoki", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.main_pipe, "Used", "Auto Change", "Mainpipe", "689", "80-257-MPS-AUT-2U ", "Auto Change Itokoki Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.fire_extinguisher_pipe, "New", "Fire Extinguisher", "Mainpipe", "934", "80-257-MPS-EXT-1 ", "1kg Fire Extinguisher", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.fire_extinguisher_pipe, "New", "Fire Extinguisher", "Mainpipe", "935", "80-257-MPS-EXT-1U ", "1kg Fire Extinguisher Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.fire_extinguisher_pipe, "New", "Fire Extinguisher", "Mainpipe", "936", "80-257-MPS-EXT-2 ", "Fire Extinguisher 2.5kg", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.pigtail_pipe, "Used", "Pigtail", "Mainpipe", "633", "89-275-MPS-PIG-1U ", "Pigtail Liquid SPC Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.pigtail_pipe, "New", "Pigtail", "Mainpipe", "634", "89-275-MPS-PIG-2 ", "Pigtail POL (Vapour)", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.pigtail_pipe, "Used", "Pigtail", "Mainpipe", "635", "89-275-MPS-PIG-2U ", "Pigtail POL (Vapour) Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.tee_check, "New", "Tee Check", "Mainpipe", "690", "89-275-MPS-TEE-1 ", "Tee Check No-Return Valve", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.tee_check, "Used", "Tee Check", "Mainpipe", "691", "89-275-MPS-TEE-1U ", "Tee Check (No-Return Valve)Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.vapourizer_mainpipe, "New", "Vapourizer", "Mainpipe", "643", "89-275-MPS-VAP-7 ", "Vapourizer Torpedo ?LED & Cable", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.meter_downpipe, "New", "Meter", "Downpipe", "748", "80-256-DPS-MET-4U ", "AL-425 Meter Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.regulator_pipe, "New", "Regulator", "Downpipe", "2543", "80-256-DPS-REG-6 ", "REGULATOR LOW PRESSURE", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.regulator_pipe, "New", "Regulator", "Downpipe", "4878", "80-DPS-REG-0012 ", "Regulator BCF", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "New", "Bush", "Downpipe", "767", "89-274-DPS-BUS-1 ", "1/2 x 1/8 MS Bush", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "Used", "Bush", "Downpipe", "768", "89-274-DPS-BUS-1U ", "Bush MS ½ x? Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "New", "Bush", "Downpipe", "769", "89-274-DPS-BUS-2 ", "Bush GI ½ x¼", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "Used", "Bush", "Downpipe", "770", "89-274-DPS-BUS-2U ", "Bush GI ½ x¼ Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "New", "Bush", "Downpipe", "771", "89-274-DPS-BUS-3 ", "Bush GI ½ x¾", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "Used", "Bush", "Downpipe", "772", "89-274-DPS-BUS-3U ", "Bush GI ½ x¼ Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "New", "Bush", "Downpipe", "773", "89-274-DPS-BUS-4 ", "Bush Brass ¼ x?", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "Used", "Bush", "Downpipe", "774", "89-274-DPS-BUS-4U ", "Bush Brass ¼ x?  Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "New", "Bush", "Downpipe", "775", "89-274-DPS-BUS-5 ", "Bush MS ¼ x¾", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "Used", "Bush", "Downpipe", "776", "89-274-DPS-BUS-5U ", "Bush MS ¼ x¾  Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "New", "Bush", "Downpipe", "777", "89-274-DPS-BUS-6 ", "Bush Brass ¼ x?", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bush_downpipe, "Used", "Bush", "Downpipe", "778", "89-274-DPS-BUS-6U ", "Bush Brass ¼ x? Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.ricecooker, "Used", "Rice Cooker", "Burner", "445", "89-274-COK-RIC-6U ", "Rice Cooker Cooker Bowl Rinnai Japan Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.ricecooker, "New", "Rice Cooker", "Burner", "446", "89-274-COK-RIC-7 ", "Rice Cooker Auto", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.ricecooker, "New", "Rice Cooker", "Burner", "447", "89-274-COK-RIC-8 ", "Rice Cooker Electronic Wire", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.ricecooker, "New", "Rice Cooker", "Burner", "448", "89-274-COK-RIC-9 ", "Rice Cooker Pilot", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.burner_stand, "New", "Stand", "Burner", "485", "89-274-COK-STD-1 ", "Kwali Stand Low 380mm", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.burner_stand, "Used", "Stand", "Burner", "486", "89-274-COK-STD-1U ", "Kwali Stand Low 380mm Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.burner_stand, "New", "Stand", "Burner", "487", "89-274-COK-STD-2 ", "Kwali Stand High 700mm", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.burner_stand, "Used", "Stand", "Burner", "488", "89-274-COK-STD-2U ", "Kwali Stand High 700mm Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.toaster, "New", "Toaster", "Burner", "2444", "89-274-COK-TOA-2 ", "RINNAI TOASTER GLASS", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.toaster, "Used", "Toaster", "Burner", "2445", "89-274-COK-TOA-2U ", "RINNAI TOASTER GLASS USED", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.toaster, "New", "", "Burner", "4748", "89-4--0 ", "", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bowl_ring, "New", "Bowl & Ring", "Burner", "4648", "89-4-59-0017 ", "", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.bowl_ring, "New", "Bowl & Ring", "Burner", "4649", "89-4-59-0018 ", "", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.high_pressure_stove, "New", "High Pressure Stove", "Burner", "4650", "89-4-63-0001 ", "", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.high_pressure_stove, "New", "High Pressure Stove", "Burner", "4651", "89-4-63-0002 ", "", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.ball_valve, "Used", "Ball Valve", "Accessories", "331", "89-275-GEN-MBV-1U ", "1 Ball Valve Used", "2", "267", "535"));
        inventoryItems.add(new InventoryItem(R.drawable.spray_paint, "New", "Non Inventory", "Accessories", "337", "GEN-ANI-6 ", "Spray Paint-White", "2", "267", "535"));

        recyclerView.setAdapter(new AddItemAdapter(inventoryItems, getContext()));
        return view;
    }
}

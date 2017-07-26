package technician.inteq.com.ugshdd.ui.dialogFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.spinner_adapter.ItemsCustomSpinnerAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 25-Jul-17.
 */

public class AddItemDialog extends DialogFragment {

    String[] section_array = {"-Select Section-", "Burner", "Pipe", "Cylinder parts"};
    String[] category_array = {"-Select Category-", "Burner", "Pipe", "Cylinder parts"};
    TextView quantityView;
    int quantity = 1;
    ArrayList<InventoryItem> inventoryItems;
    ImageView imageview;
    TextView itemName, itemDescription, itemRate;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_action_inventory_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Spinner spinner_section = (Spinner) view.findViewById(R.id.spinner_section);
        Spinner spinner_items = (Spinner) view.findViewById(R.id.spinner_items);
        Spinner spinner_category = (Spinner) view.findViewById(R.id.spinner_category);
        imageview = (ImageView) view.findViewById(R.id.item_image);
        quantityView = (TextView) view.findViewById(R.id.quantity);
        itemName = (TextView) view.findViewById(R.id.item_name);
        itemDescription = (TextView) view.findViewById(R.id.item_description);
        itemRate = (TextView) view.findViewById(R.id.item_rate);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText("Add Items");
        ArrayAdapter<String> section_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, section_array);
        ArrayAdapter<String> category_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, category_array);
        prepareList();
        ItemsCustomSpinnerAdapter customAdapter = new ItemsCustomSpinnerAdapter(getActivity(), inventoryItems);

        spinner_section.setAdapter(section_adapter);
        spinner_category.setAdapter(category_adapter);
        spinner_items.setAdapter(customAdapter);
        spinner_items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InventoryItem item = inventoryItems.get(position);

                imageview.setImageResource(item.getItemImage());
                itemName.setText(item.getItem());
                itemDescription.setText(item.getDescription());
                itemRate.setText(item.getRate());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.inc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityView.setText("" + quantity);
            }
        });
        view.findViewById(R.id.dec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    quantityView.setText("" + quantity);
                }
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    void prepareList() {
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

    }
}

package technician.inteq.com.ugshdd.ui.dialogFragment;

import android.app.DialogFragment;
import android.database.Cursor;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.Controller.CaseController;
import technician.inteq.com.ugshdd.Controller.InventoryItemController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.spinner_adapter.ItemsCustomSpinnerAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.util.UGSApplication;
import technician.inteq.com.ugshdd.util.Utility;

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
    TextView itemName, itemDescription, itemRate, itemAmount;
    AddItem addItem;
    InventoryItem item;
    short item_type;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addItem = (AddItem) getActivity();
        item_type = getArguments().getShort("currentType");
    }

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
        itemAmount = (TextView) view.findViewById(R.id.item_amount);
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
                item = inventoryItems.get(position);
                Glide.with(getActivity()).load(item.getItemImage()).into(imageview);
                itemName.setText(item.getItem());
                itemDescription.setText(item.getDescription());
                itemRate.setText(item.getRate());
                itemAmount.setText(item.getRate());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.findViewById(R.id.inc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityView.setText(String.valueOf(quantity));
                itemAmount.setText(String.valueOf(Integer.parseInt(itemRate.getText().toString()) * quantity));
            }
        });
        view.findViewById(R.id.dec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    quantityView.setText(String.valueOf(quantity));
                    itemAmount.setText(String.valueOf(Integer.parseInt(itemRate.getText().toString()) * quantity));
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
                InventoryItem selectedItem = new InventoryItem(item.getItemImage(), item.getStatus(),
                        item.getCategory(), item.getSubCategory(), item.getInternalId(), item.getItem(),
                        item.getDescription(), item.getRate());
                selectedItem.setQuantity(String.valueOf(quantity));

                getDialog().dismiss();
                try {
                    if (CaseController.insert(new Case(UGSApplication.accountNumber, item.getInternalId(), quantity, itemAmount.getText().toString(), item_type))) {
                        addItem.selectedItem();
                        Utility.toast(getActivity(), "Item Added");
                    } else {
                        Utility.toast(getActivity(), "Item already added");
                    }
                } catch (IllegalAccessException e) {
                    Utility.toast(getActivity(), "Error while inserting into database " + e);
                }
            }
        });
        return view;
    }

    void prepareList() {
        inventoryItems = new ArrayList<>();
        Cursor cursor = InventoryItemController.getAllItems();
        if (cursor.moveToFirst()) {
            do {
                inventoryItems.add(InventoryItem.getItem(cursor));
            } while (cursor.moveToNext());
        }
    }


    public interface AddItem {
        void selectedItem();
    }
}

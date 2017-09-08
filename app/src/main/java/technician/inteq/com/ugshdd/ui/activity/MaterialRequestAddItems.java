package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.MaterialRequestController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.MaterialTransferGridAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 21-Aug-17.
 */

public class MaterialRequestAddItems extends Activity {
    public static final String STATE_INVENTORY = "inventory";
    ArrayList<InventoryItem> inventoryItems;
    List<String> categoryArrayList, subCategoryArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    Button cancel;
    Spinner category, subCategory;
    ArrayAdapter subCategoryAdapter;
    MaterialTransferGridAdapter materialTransferGridAdapter;
    String subCategoryItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_request_add_items);
        if (savedInstanceState == null) {
            prepareList();
        } else {
            inventoryItems = savedInstanceState.getParcelableArrayList(STATE_INVENTORY);
        }
        cancel = (Button) findViewById(R.id.cancel);
        category = (Spinner) findViewById(R.id.category);
        subCategory = (Spinner) findViewById(R.id.sub_category);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        int orientation = this.getResources().getConfiguration().orientation;
        int columns = 2;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 3;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categoryArrayList) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                text.setTypeface(null, Typeface.BOLD);
                return view;

            }
        };
        category.setAdapter(categoryAdapter);
        subCategoryAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, subCategoryArrayList) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                text.setTypeface(null, Typeface.BOLD);
                return view;

            }
        };
        subCategory.setAdapter(subCategoryAdapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subCategoryArrayList.clear();
                InventoryItem.getSubCategory(subCategoryArrayList, (String) parent.getItemAtPosition(position));
                subCategoryAdapter.notifyDataSetChanged();
                if (subCategoryArrayList.size() > 0) {
                    refreshInventoryItems(subCategoryArrayList.get(0));
                    subCategory.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        subCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subCategoryItem = (String) parent.getItemAtPosition(position);
                refreshInventoryItems(subCategoryItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        materialTransferGridAdapter = new MaterialTransferGridAdapter(this, inventoryItems);
        recyclerView.setAdapter(materialTransferGridAdapter);
        materialTransferGridAdapter.setItemClickListener(new MaterialTransferGridAdapter.ItemClickListener() {
            @Override
            public void onClickAddToCart(View view, int position) {
                InventoryItem inventoryItem = inventoryItems.get(position);
                inventoryItem.setSelected(true);
                inventoryItem.setQuantity("1");
                materialTransferGridAdapter.notifyItemChanged(position);
            }

            @Override
            public void onClickInc(View view, int position) {
                InventoryItem inventoryItem = inventoryItems.get(position);
                int qty = Integer.parseInt(inventoryItem.getQuantity());
                qty++;
                inventoryItem.setQuantity(String.valueOf(qty));
                materialTransferGridAdapter.notifyItemChanged(position);
            }

            @Override
            public void onClickDec(View view, int position) {
                InventoryItem inventoryItem = inventoryItems.get(position);
                int qty = Integer.parseInt(inventoryItem.getQuantity());
                qty--;
                if (!inventoryItem.getQuantity().equals("1")) {
                    inventoryItem.setQuantity(String.valueOf(qty));
                } else {
                    inventoryItem.setSelected(false);
                    inventoryItem.setQuantity(String.valueOf(qty));
                }
                materialTransferGridAdapter.notifyItemChanged(position);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MaterialRequestAddItems.this, MaterialRequestList.class));
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_INVENTORY, inventoryItems);
    }

    void prepareList() {
        inventoryItems = new ArrayList<>();
        InventoryItem.getCategory(categoryArrayList = new ArrayList<>());
        InventoryItem.getDataFromCursor(inventoryItems, null);
    }

    private void refreshInventoryItems(String subCategory) {
        inventoryItems.clear();
        InventoryItem.getDataFromCursor(inventoryItems, subCategory);
        materialTransferGridAdapter.notifyDataSetChanged();
    }

    public void request(View view) {
        MaterialRequestController.insertRequestedMaterial(inventoryItems);
        startActivity(new Intent(MaterialRequestAddItems.this, MaterialRequestList.class));
        Utility.toast(this, "Items Added in list");
        finish();
    }

}

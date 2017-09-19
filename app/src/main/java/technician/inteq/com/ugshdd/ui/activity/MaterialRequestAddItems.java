package technician.inteq.com.ugshdd.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import technician.inteq.com.ugshdd.Controller.MaterialRequestController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.MaterialTransferGridAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 21-Aug-17.
 */

public class MaterialRequestAddItems extends AppCompatActivity {
    private final String STATE_INVENTORY = "inventory";
    private final String CATEGORY = "category";
    private final String SUB_CATEGORY = "sub_category";
    TextView toolbarTitle;
    TextView toolbarSubtitle;
    ArrayList<InventoryItem> temp = new ArrayList<>();
    private ArrayList<InventoryItem> inventoryItems, inventoryItemsPermanent;
    private ArrayList<String> categoryArrayList = new ArrayList<>(), subCategoryArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button cancel;
    private Spinner category, subCategory;
    private ArrayAdapter subCategoryAdapter;
    private MaterialTransferGridAdapter materialTransferGridAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_request_add_items);
        if (savedInstanceState == null) {
            prepareList();
        } else {
            inventoryItems = savedInstanceState.getParcelableArrayList(STATE_INVENTORY);
            categoryArrayList = savedInstanceState.getStringArrayList(CATEGORY);
            subCategoryArrayList = savedInstanceState.getStringArrayList(SUB_CATEGORY);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarSubtitle = (TextView) toolbar.findViewById(R.id.toolbar_subtitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        cancel = (Button) findViewById(R.id.cancel);
        category = (Spinner) findViewById(R.id.category);
        subCategory = (Spinner) findViewById(R.id.sub_category);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, Utility.calculateNoOfColumns(this)));
        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryArrayList) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                text.setSingleLine(true);
                return view;

            }
        };
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(categoryAdapter);
        subCategoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subCategoryArrayList) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);
                text.setSingleLine(true);
                return view;

            }
        };
        subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
                refreshInventoryItems((String) parent.getItemAtPosition(position));
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
                startActivity(new Intent(MaterialRequestAddItems.this, MaterialRequestList.class));
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_INVENTORY, inventoryItems);
        outState.putStringArrayList(CATEGORY, categoryArrayList);
        outState.putStringArrayList(SUB_CATEGORY, subCategoryArrayList);
    }

    void prepareList() {
        inventoryItems = new ArrayList<>();
        InventoryItem.getCategory(categoryArrayList = new ArrayList<>());
        InventoryItem.getDataFromCursor(inventoryItems, null);
        inventoryItemsPermanent = new ArrayList<>(inventoryItems);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.logout).setVisible(false);
        menu.findItem(R.id.database).setVisible(false);
        menu.findItem(R.id.about).setVisible(false);
        menu.findItem(R.id.contact).setVisible(false);
        menu.findItem(R.id.import_csv).setVisible(false);
        menu.findItem(R.id.completed_tasks).setVisible(false);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint("Search...");
        searchEditText.setHintTextColor(getResources().getColor(R.color.tab_text_color));
        searchView.setPadding(getResources().getDimensionPixelSize(R.dimen.search_view_minus), 0, 0, 0);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().length() > 0) {
                    temp = filter(newText);
                    materialTransferGridAdapter = new MaterialTransferGridAdapter(MaterialRequestAddItems.this, temp);
                    recyclerView.setAdapter(materialTransferGridAdapter);
                } else {
                    materialTransferGridAdapter = new MaterialTransferGridAdapter(MaterialRequestAddItems.this, inventoryItems);
                    recyclerView.setAdapter(materialTransferGridAdapter);
                }


                return false;
            }
        });
        return true;
    }

    public ArrayList<InventoryItem> filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        inventoryItemsPermanent.clear();
        if (charText.length() == 0) {
            inventoryItemsPermanent.addAll(inventoryItems);
        } else {
            for (InventoryItem s : inventoryItems) {
                if (s.getItem().toLowerCase().contains(charText)) {
                    inventoryItemsPermanent.add(s);
                }
            }
        }
        return inventoryItemsPermanent;
    }

}

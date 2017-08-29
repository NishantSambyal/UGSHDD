package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.Controller.InventoryItemController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.MaterialTransferGridAdapter;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.util.RecyclerTouchListener;
import technician.inteq.com.ugshdd.util.SQLiteCursorLoader;

/**
 * Created by Nishant Sambyal on 21-Aug-17.
 */

public class MaterialRequestAddItems extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final String STATE_INVENTORY = "inventory";
    ArrayList<InventoryItem> inventoryItems;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_request_add_items);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        int orientation = this.getResources().getConfiguration().orientation;
        int columns = 2;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 3;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, columns));
        if (savedInstanceState == null) {
            prepareList();
        } else {
            inventoryItems = savedInstanceState.getParcelableArrayList(STATE_INVENTORY);
        }
        final MaterialTransferGridAdapter materialTransferGridAdapter = new MaterialTransferGridAdapter(this, inventoryItems);
        recyclerView.setAdapter(materialTransferGridAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(final View view, final int position) {
                view.findViewById(R.id.addToCart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InventoryItem inventoryItem = inventoryItems.get(position);
                        inventoryItem.setSelected(true);
                        inventoryItem.setQuantity("1");
                        materialTransferGridAdapter.notifyItemChanged(position);
                    }
                });
                view.findViewById(R.id.dec).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                view.findViewById(R.id.inc).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InventoryItem inventoryItem = inventoryItems.get(position);
                        int qty = Integer.parseInt(inventoryItem.getQuantity());
                        qty++;
                        inventoryItem.setQuantity(String.valueOf(qty));
                        materialTransferGridAdapter.notifyItemChanged(position);
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_INVENTORY, inventoryItems);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Cursor cursor = InventoryItemController.getAllItems();
        SQLiteCursorLoader loader = new SQLiteCursorLoader(this, cursor);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        materialTransferGridAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        materialTransferGridAdapter.swapCursor(null);
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
}

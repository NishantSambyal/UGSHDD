package technician.inteq.com.ugshdd.adapters.spinner_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 25-Jul-17.
 */

public class ItemsCustomSpinnerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;
    ArrayList<InventoryItem> inventoryItems;

    public ItemsCustomSpinnerAdapter(Context applicationContext, ArrayList<InventoryItem> inventoryItems) {
        this.context = applicationContext;
        this.inventoryItems = inventoryItems;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return inventoryItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.add_action_inventory_spinner, null);
        InventoryItem items = inventoryItems.get(i);
        ImageView icon = (ImageView) view.findViewById(R.id.item_image);
        TextView names = (TextView) view.findViewById(R.id.item_name);
        TextView description = (TextView) view.findViewById(R.id.description);
        Glide.with(context).load(items.getItemImage()).into(icon);
        names.setText(items.getItem());
        description.setText(items.getDescription());
        return view;
    }
}

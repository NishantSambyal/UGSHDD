package technician.inteq.com.ugshdd.adapters.ArrayAdapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 23-Aug-17.
 */

public class MaterialRequestAdditemArrayAdpter extends ArrayAdapter<InventoryItem> {
    ArrayList<InventoryItem> inventoryItems;
    Context context;
    int resource;

    public MaterialRequestAdditemArrayAdpter(@NonNull Context context, @LayoutRes int resource, ArrayList<InventoryItem> inventoryItems) {
        super(context, resource, inventoryItems);
        this.context = context;
        this.resource = resource;
        this.inventoryItems = inventoryItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        final ItemHolder holder;
        InventoryItem item = inventoryItems.get(position);
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ItemHolder();
            view = inflater.inflate(resource, parent, false);
            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            holder.itemName = (TextView) view.findViewById(R.id.item_name);
            holder.itemRate = (TextView) view.findViewById(R.id.item_rate);
            holder.selection = (LinearLayout) view.findViewById(R.id.selection);
            holder.addToCart = (Button) view.findViewById(R.id.addToCart);
            holder.inc = (Button) view.findViewById(R.id.inc);
            holder.dec = (Button) view.findViewById(R.id.dec);
            holder.quantityTxt = (TextView) view.findViewById(R.id.item_quantity);

            Glide.with(context).load(item.getItemImage()).crossFade().into(holder.imageView);
            holder.itemName.setText(item.getItem());
            holder.itemRate.setText("$ " + item.getRate());
            view.setTag(holder);
        } else {
            holder = (ItemHolder) view.getTag();
            Glide.with(context).load(item.getItemImage()).crossFade().into(holder.imageView);
            holder.itemName.setText(item.getItem());
            holder.itemRate.setText("$ " + item.getRate());

            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.addToCart.setText("changed");
                }
            });
        }

        return view;
    }

    static class ItemHolder {
        ImageView imageView;
        TextView itemName;
        TextView itemRate, quantityTxt;
        Button addToCart, inc, dec;
        LinearLayout selection;
    }
}

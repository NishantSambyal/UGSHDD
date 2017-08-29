package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 28-Aug-17.
 */

public class MaterialTransferGridAdapter extends RecyclerView.Adapter<MaterialTransferGridAdapter.ViewHolder> {
    private ArrayList<InventoryItem> inventoryItems;
    private Context context;

    public MaterialTransferGridAdapter(Context context, ArrayList<InventoryItem> inventoryItems) {
        this.context = context;
        this.inventoryItems = inventoryItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mateial_transfer_add_item_single_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        InventoryItem inventoryItem = inventoryItems.get(position);

        holder.itemName.setText(inventoryItem.getItem());
        holder.itemRate.setText(inventoryItem.getRate());
        holder.item_quantity.setText(inventoryItem.getQuantity());

        if (inventoryItem.isSelected()) {
            holder.addToCart.setVisibility(View.GONE);
            holder.selection.setVisibility(View.VISIBLE);

        } else {
            holder.addToCart.setVisibility(View.VISIBLE);
            holder.selection.setVisibility(View.GONE);
        }

        if (inventoryItem.getItemImage() != null)
            Glide.with(context).load(inventoryItem.getItemImage()).into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return inventoryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, dec, inc;
        TextView itemName, itemRate, item_quantity;
        Button addToCart;
        LinearLayout selection;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemRate = (TextView) itemView.findViewById(R.id.item_rate);
            addToCart = (Button) itemView.findViewById(R.id.addToCart);
            selection = (LinearLayout) itemView.findViewById(R.id.selection);
            item_quantity = (TextView) itemView.findViewById(R.id.item_quantity);
        }
    }
}
package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Patyal on 7/25/2017.
 */

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.AddItemRVViewholder> {

    List<InventoryItem> strings;
    Context context;

    public AddItemAdapter(List<InventoryItem> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @Override
    public AddItemRVViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddItemRVViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AddItemRVViewholder holder, int position) {
        InventoryItem leave = strings.get(position);
        holder.item.setText(leave.getItem());
        holder.description.setText(leave.getDescription());
        holder.quentity.setText(leave.getRate());
        holder.rate.setText(leave.getQuentity());
        holder.amount.setText(leave.getAmount());
        Glide.with(context).load(leave.getItemImage()).into(holder.imageView);
//        holder.imageView.setImageResource(leave.getItemImage());
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class AddItemRVViewholder extends RecyclerView.ViewHolder {
        TextView item, description, quentity, rate, amount;
        View view;
        ImageView imageView;

        public AddItemRVViewholder(View itemView) {
            super(itemView);
            view = itemView;
            item = (TextView) itemView.findViewById(R.id.item);
            description = (TextView) itemView.findViewById(R.id.description);
            quentity = (TextView) itemView.findViewById(R.id.qty);
            rate = (TextView) itemView.findViewById(R.id.rate);
            amount = (TextView) itemView.findViewById(R.id.amount);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

    }
}
package technician.inteq.com.ugshdd.adapters.recyclerViewAdapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 01-Sep-17.
 */

public class MaterialRequestRecyclerAdapter extends RecyclerView.Adapter<MaterialRequestRecyclerAdapter.ViewHolder> {

    Context context;
    List<MaterialRequest> requestList;

    public MaterialRequestRecyclerAdapter(Context context, List<MaterialRequest> requestList) {
        this.context = context;
        this.requestList = requestList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MaterialRequest materialRequest = requestList.get(position);
        holder.item.setText(materialRequest.getInventoryItem().getItem());
        holder.description.setText(materialRequest.getInventoryItem().getDescription());
        holder.quantity.setText(String.valueOf(materialRequest.getQuantity()));
        holder.rate.setText(materialRequest.getInventoryItem().getRate());
        holder.amount.setText(String.valueOf(materialRequest.getAmount()));
        Glide.with(context).load(Uri.fromFile(Utility.getImage(materialRequest.getInventoryItem().getInternalId()))).placeholder(R.drawable.noimagefound).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item, description, quantity, rate, amount;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item);
            description = (TextView) itemView.findViewById(R.id.description);
            quantity = (TextView) itemView.findViewById(R.id.qty);
            rate = (TextView) itemView.findViewById(R.id.rate);
            amount = (TextView) itemView.findViewById(R.id.amount);
            imageView = (ImageView) itemView.findViewById(R.id.image);
    }
    }
}

package technician.inteq.com.ugshdd.adapters;

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
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Patyal on 7/25/2017.
 */

public class AddItemAdapter extends RecyclerView.Adapter<AddItemAdapter.AddItemRVViewholder> {

    private List<Case> caseList;
    private Context context;


    public AddItemAdapter(List<Case> caseList, Context context) {
        this.caseList = caseList;
        this.context = context;

    }

    @Override
    public AddItemRVViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddItemRVViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AddItemRVViewholder holder, int position) {
        Case aCase = caseList.get(position);

        holder.item.setText(aCase.getInventoryItem().getItem());
        holder.description.setText(aCase.getInventoryItem().getDescription());
        holder.quantity.setText(String.valueOf(aCase.getQuantity()));
        holder.rate.setText(aCase.getInventoryItem().getRate());
        holder.amount.setText(String.valueOf(aCase.getAmount()));
        Glide.with(context).load(Uri.fromFile(Utility.getImage(aCase.getInventoryItem().getInternalId()))).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return caseList.size();
    }

    class AddItemRVViewholder extends RecyclerView.ViewHolder {
        TextView item, description, quantity, rate, amount;
        View view;
        ImageView imageView;

        public AddItemRVViewholder(View itemView) {
            super(itemView);
            view = itemView;
            item = (TextView) itemView.findViewById(R.id.item);
            description = (TextView) itemView.findViewById(R.id.description);
            quantity = (TextView) itemView.findViewById(R.id.qty);
            rate = (TextView) itemView.findViewById(R.id.rate);
            amount = (TextView) itemView.findViewById(R.id.amount);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
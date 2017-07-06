package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import technician.inteq.com.ugshdd.R;

/**
 * Created by Patyal on 7/5/2017.
 */

public class AcceptedItemRVAdapter extends RecyclerView.Adapter<AcceptedItemRVAdapter.AcceptedItemViewHolder> {

    private List<String> accptedItemList;
    private Context context;

    public AcceptedItemRVAdapter(List<String> accptedItemList, Context context) {
        this.accptedItemList = accptedItemList;
        this.context = context;
    }

    @Override
    public AcceptedItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AcceptedItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.accepted_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(AcceptedItemViewHolder holder, int position) {
        holder.date.setText(accptedItemList.get(position));
        holder.fromTm.setText(accptedItemList.get(position));
        holder.invWith.setText(accptedItemList.get(position));
        holder.status.setText(accptedItemList.get(position));
        holder.action.setText(accptedItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return accptedItemList.size();
    }

    class AcceptedItemViewHolder extends RecyclerView.ViewHolder {
        TextView date, fromTm, invWith, status, action;

        public AcceptedItemViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            fromTm = (TextView) itemView.findViewById(R.id.from_tm);
            invWith = (TextView) itemView.findViewById(R.id.inv_with);
            status = (TextView) itemView.findViewById(R.id.status);
            action = (TextView) itemView.findViewById(R.id.action);

        }
    }
}

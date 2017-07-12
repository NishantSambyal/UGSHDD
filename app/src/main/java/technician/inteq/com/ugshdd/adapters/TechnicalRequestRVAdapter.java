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
 * Created by Patyal on 7/10/2017.
 */

public class TechnicalRequestRVAdapter extends RecyclerView.Adapter<TechnicalRequestRVAdapter.TechnicalRequestItemViewHolder> {

    private List<String> accptedItemList;
    private Context context;

    public TechnicalRequestRVAdapter(List<String> accptedItemList, Context context) {
        this.accptedItemList = accptedItemList;
        this.context = context;
    }

    @Override
    public TechnicalRequestItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TechnicalRequestItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.technical_request_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(TechnicalRequestItemViewHolder holder, int position) {

        if (position % 2 != 0) {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.list_item_background));
        }

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

    class TechnicalRequestItemViewHolder extends RecyclerView.ViewHolder {
        TextView date, fromTm, invWith, status, action;
        View view;

        public TechnicalRequestItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            date = (TextView) itemView.findViewById(R.id.id);
            fromTm = (TextView) itemView.findViewById(R.id.requested_date);
            invWith = (TextView) itemView.findViewById(R.id.view);
            status = (TextView) itemView.findViewById(R.id.edit);
            action = (TextView) itemView.findViewById(R.id.del);

        }
    }
}

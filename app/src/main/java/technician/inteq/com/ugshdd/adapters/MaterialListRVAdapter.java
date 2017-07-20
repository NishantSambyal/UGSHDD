package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.TransferItemxllayout;


/**
 * Created by Patyal on 7/5/2017.
 */

public class MaterialListRVAdapter extends RecyclerView.Adapter<MaterialListRVAdapter.MaterialListViewHolder> {

    private List<TransferItemxllayout> materialList;
    private Context context;

    public MaterialListRVAdapter(List<TransferItemxllayout> materialList, Context context) {
        this.materialList = materialList;
        this.context = context;
    }

    @Override
    public MaterialListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MaterialListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.material_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MaterialListViewHolder holder, int position) {
       /* if (position % 2 != 0) {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.list_item_background));
        }*/
        TransferItemxllayout transferItem = materialList.get(position);
        holder.date.setText(transferItem.getDate());
        holder.tm.setText(transferItem.getTm());
        holder.alternateTm.setText(transferItem.getAlternateTm());
        holder.invWith.setText(transferItem.getInvWith());
        holder.status.setText(transferItem.getStatus());
        holder.action.setText(transferItem.getAction());

        if (transferItem.getStatus().equals("done")) {
            holder.status.setTextColor(Color.parseColor("#6EC274"));
        } else if (transferItem.getStatus().equals("pending")) {
            holder.status.setTextColor(Color.parseColor("#FF9641"));
        } else {
            holder.status.setTextColor(Color.parseColor("#9e2111"));
        }
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    class MaterialListViewHolder extends RecyclerView.ViewHolder {
        TextView date, tm, alternateTm, invWith, status, action;
        View view;

        public MaterialListViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            date = (TextView) itemView.findViewById(R.id.date);
            tm = (TextView) itemView.findViewById(R.id.tm);
            alternateTm = (TextView) itemView.findViewById(R.id.alternate_tm);
            invWith = (TextView) itemView.findViewById(R.id.inv_with);
            status = (TextView) itemView.findViewById(R.id.status);
            action = (TextView) itemView.findViewById(R.id.action);

        }
    }
}

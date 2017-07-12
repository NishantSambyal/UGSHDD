package technician.inteq.com.ugshdd.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCasesBean;

/**
 * Created by Nishant Sambyal on 11-Jul-17.
 */

public class PendingCasesListAdapter extends RecyclerView.Adapter<PendingCasesListAdapter.PendingCaseViewHolder> {

    ArrayList<PendingCasesBean> list;

    public PendingCasesListAdapter(ArrayList<PendingCasesBean> list) {
        this.list = list;
    }

    @Override
    public PendingCaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PendingCaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_case_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PendingCaseViewHolder holder, int position) {
        PendingCasesBean item = list.get(position);
        holder.outlet.setText(item.getOutlet());
        holder.job_no.setText(item.getJobNo());
        holder.unit_food.setText(item.getUnit_food());
        String action = item.getAction();
        if (action.equals("Done")) {
            holder.action.setTextColor(Color.parseColor("#6EC274"));
        } else if (action.equals("Pending")) {
            holder.action.setTextColor(Color.parseColor("#FF9641"));
        } else {
            holder.action.setTextColor(Color.parseColor("#9e2111"));
        }
        holder.action.setText(action);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PendingCaseViewHolder extends RecyclerView.ViewHolder {

        TextView outlet, job_no, unit_food, action;
        View view;

        public PendingCaseViewHolder(View itemView) {
            super(itemView);
            outlet = (TextView) itemView.findViewById(R.id.outlet);
            job_no = (TextView) itemView.findViewById(R.id.job_no);
            unit_food = (TextView) itemView.findViewById(R.id.unit_food);
            action = (TextView) itemView.findViewById(R.id.action);
            view = itemView;
        }
    }
}

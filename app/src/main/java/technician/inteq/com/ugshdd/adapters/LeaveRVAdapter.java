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
import technician.inteq.com.ugshdd.model.Leave;

/**
 * Created by Patyal on 7/3/2017.
 */

public class LeaveRVAdapter extends RecyclerView.Adapter<LeaveRVAdapter.LeaveRVViewholder> {

    List<Leave> strings;
    Context context;

    public LeaveRVAdapter(List<Leave> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @Override
    public LeaveRVViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeaveRVViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LeaveRVViewholder holder, int position) {
        Leave leave = strings.get(position);

        holder.date.setText(leave.getDate());
        holder.leaveType.setText(leave.getLeaveType());
        holder.fromDate.setText(leave.getFromDate());
        holder.toDate.setText(leave.getToDate());
        holder.status.setText(leave.getStatus());
        holder.action.setText(leave.getAction());

        if (leave.getStatus().equals("done")) {
            holder.status.setTextColor(Color.parseColor("#6EC274"));
        } else if (leave.getStatus().equals("pending")) {
            holder.status.setTextColor(Color.parseColor("#FF9641"));
        } else {
            holder.status.setTextColor(Color.parseColor("#9e2111"));
        }
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class LeaveRVViewholder extends RecyclerView.ViewHolder {
        TextView toDate, fromDate, leaveType, status, action, date;
        View view;


        public LeaveRVViewholder(View itemView) {
            super(itemView);
            view = itemView;
            date = (TextView) itemView.findViewById(R.id.date);
            leaveType = (TextView) itemView.findViewById(R.id.leave_type);
            fromDate = (TextView) itemView.findViewById(R.id.from_date);
            toDate = (TextView) itemView.findViewById(R.id.to_date);
            status = (TextView) itemView.findViewById(R.id.status);
            action = (TextView) itemView.findViewById(R.id.action);
        }

    }
}

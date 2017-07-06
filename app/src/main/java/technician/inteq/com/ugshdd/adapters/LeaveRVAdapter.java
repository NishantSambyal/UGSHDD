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
 * Created by Patyal on 7/3/2017.
 */

public class LeaveRVAdapter extends RecyclerView.Adapter<LeaveRVAdapter.LeaveRVViewholder> {

    List<String> strings;
    Context context;

    public LeaveRVAdapter(List<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @Override
    public LeaveRVViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeaveRVViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LeaveRVViewholder holder, int position) {
        holder.date.setText(strings.get(position));
        holder.leaveType.setText(strings.get(position));
        holder.fromDate.setText(strings.get(position));
        holder.toDate.setText(strings.get(position));
        holder.status.setText(strings.get(position));
        holder.action.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class LeaveRVViewholder extends RecyclerView.ViewHolder {
        TextView toDate, fromDate, leaveType, status, action, date;

        public LeaveRVViewholder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            leaveType = (TextView) itemView.findViewById(R.id.leave_type);
            fromDate = (TextView) itemView.findViewById(R.id.from_date);
            toDate = (TextView) itemView.findViewById(R.id.to_date);
            status = (TextView) itemView.findViewById(R.id.status);
            action = (TextView) itemView.findViewById(R.id.action);
        }

    }
}

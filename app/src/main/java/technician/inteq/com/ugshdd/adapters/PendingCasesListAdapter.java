package technician.inteq.com.ugshdd.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;

/**
 * Created by Nishant Sambyal on 11-Jul-17.
 */

public class PendingCasesListAdapter extends RecyclerView.Adapter<PendingCasesListAdapter.PendingCaseViewHolder> {

    List<Outlets> list;


    public PendingCasesListAdapter(List<Outlets> list) {
        this.list = list;
    }


    @Override
    public PendingCaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PendingCaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_case_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PendingCaseViewHolder holder, final int position) {
        holder.outlet.setText(list.get(position).getOutletName());
        holder.job_no.setText(list.get(position).getChildList().get(0).getJobNumber());
        holder.unit_food.setText(list.get(position).getChildList().get(0).getUnitNumber());
        holder.ack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Clicked", list.get(position).getOutletName() + " is clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PendingCaseViewHolder extends RecyclerView.ViewHolder {

        TextView outlet, job_no, unit_food;
        Button ack;
        public PendingCaseViewHolder(View itemView) {
            super(itemView);
            outlet = (TextView) itemView.findViewById(R.id.outlet);
            job_no = (TextView) itemView.findViewById(R.id.job_no);
//            unit_food = (TextView) itemView.findViewById(R.id.unit_food);
//            ack = (Button) itemView.findViewById(R.id.ack);
        }
    }
}

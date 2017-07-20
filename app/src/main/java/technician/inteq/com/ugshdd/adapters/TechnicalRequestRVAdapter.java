package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.technicalRequestBean.TechnicalRequestsDetails;

/**
 * Created by Patyal on 7/10/2017.
 */

public class TechnicalRequestRVAdapter extends RecyclerView.Adapter<TechnicalRequestRVAdapter.TechnicalRequestItemViewHolder> {

    private List<TechnicalRequestsDetails> technicalRequestsDetails;
    private Context context;

    public TechnicalRequestRVAdapter(List<TechnicalRequestsDetails> technicalRequestsDetails, Context context) {
        this.technicalRequestsDetails = technicalRequestsDetails;
        this.context = context;
    }

    @Override
    public TechnicalRequestItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TechnicalRequestItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.technical_request_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(TechnicalRequestItemViewHolder holder, int position) {

        holder.id.setText(technicalRequestsDetails.get(position).getId());
        holder.requested_date.setText(technicalRequestsDetails.get(position).getRequestDate());
        holder.view.setText(technicalRequestsDetails.get(position).getView());
        holder.edit.setText(technicalRequestsDetails.get(position).getEdit());
        holder.del.setText(technicalRequestsDetails.get(position).getDel());
    }

    @Override
    public int getItemCount() {
        return technicalRequestsDetails.size();
    }

    class TechnicalRequestItemViewHolder extends RecyclerView.ViewHolder {
        TextView id, requested_date, view, edit, del;


        public TechnicalRequestItemViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id);
            requested_date = (TextView) itemView.findViewById(R.id.requested_date);
            view = (TextView) itemView.findViewById(R.id.view);
            edit = (TextView) itemView.findViewById(R.id.edit);
            del = (TextView) itemView.findViewById(R.id.del);

        }
    }
}

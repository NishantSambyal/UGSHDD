package technician.inteq.com.ugshdd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class CashReportAdapter extends RecyclerView.Adapter<CashReportAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView employeeName, DCR_date;
        Spinner action;

        public MyViewHolder(View itemView) {
            super(itemView);
            employeeName = (TextView) itemView.findViewById(R.id.employeeName);
            DCR_date = (TextView) itemView.findViewById(R.id.DCR_date);
            action = (Spinner) itemView.findViewById(R.id.actionSpinner);
        }

    }
}

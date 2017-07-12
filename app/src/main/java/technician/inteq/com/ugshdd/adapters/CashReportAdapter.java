package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.DailyCashReportBean;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class CashReportAdapter extends RecyclerView.Adapter<CashReportAdapter.MyViewHolder> {

    ArrayList<String> categories;
    Context context;
    ArrayAdapter<String> dataAdapter;
    private ArrayList<DailyCashReportBean> list;

    public CashReportAdapter(Context context, ArrayList<DailyCashReportBean> list, ArrayList<String> categories) {
        this.list = list;
        this.categories = categories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cash_report_single_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
        DailyCashReportBean bean = list.get(position);
        holder.employeeName.setText(bean.getEmployeeName());
        holder.DCR_date.setText(bean.getDCR_date());
//        holder.action.setAdapter(dataAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView employeeName, DCR_date;
        Spinner action;

        public MyViewHolder(View itemView) {
            super(itemView);
            employeeName = (TextView) itemView.findViewById(R.id.employeeName);
            DCR_date = (TextView) itemView.findViewById(R.id.DCR_date);
//            action = (Spinner) itemView.findViewById(R.id.actionSpinner);
//            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }

    }
}

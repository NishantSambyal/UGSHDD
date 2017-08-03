package technician.inteq.com.ugshdd.adapters.ArrayAdapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;

/**
 * Created by Nishant Sambyal on 02-Aug-17.
 */

public class SummaryItemAdapter extends ArrayAdapter<Case> {

    Context context;
    List<Case> summaryList;
    int resource;

    public SummaryItemAdapter(Context context, int resource, List<Case> list) {
        super(context, resource, list);
        this.context = context;
        this.summaryList = list;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ItemHolder();
            view = inflater.inflate(resource, parent, false);
            holder.sno = (TextView) view.findViewById(R.id.sno);
            holder.item_name = (TextView) view.findViewById(R.id.item_name);
            holder.item_quantity = (TextView) view.findViewById(R.id.item_quantity);
            holder.amount = (TextView) view.findViewById(R.id.item_amount);
            view.setTag(holder);
        } else {
            holder = (ItemHolder) view.getTag();
        }
        holder.sno.setText(String.valueOf((position + 1) + "."));
        holder.item_name.setText(summaryList.get(position).getInventoryItem().getItem());
        holder.item_quantity.setText(String.valueOf(summaryList.get(position).getQuantity()));
        holder.amount.setText(String.valueOf(summaryList.get(position).getAmount()));
        return view;
    }

    public static class ItemHolder {
        TextView sno, item_name, item_quantity, amount;

    }
}

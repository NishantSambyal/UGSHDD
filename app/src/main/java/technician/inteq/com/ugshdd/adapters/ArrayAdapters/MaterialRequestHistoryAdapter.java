package technician.inteq.com.ugshdd.adapters.ArrayAdapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequestTransaction;

/**
 * Created by Nishant Sambyal on 02-Sep-17.
 */

public class MaterialRequestHistoryAdapter extends ArrayAdapter<MaterialRequestTransaction> {

    List<MaterialRequestTransaction> materialRequestsList;
    int layout;
    Context context;

    public MaterialRequestHistoryAdapter(@NonNull Context context, @LayoutRes int resource, List<MaterialRequestTransaction> materialRequestsList) {
        super(context, resource, materialRequestsList);
        this.materialRequestsList = materialRequestsList;
        this.layout = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemHolder holder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ItemHolder();
            view = inflater.inflate(layout, parent, false);
            holder.dateTime = (TextView) view.findViewById(R.id.task_name);
            holder.sno = (TextView) view.findViewById(R.id.sno);
            view.setTag(holder);
        } else {
            holder = (ItemHolder) view.getTag();
        }
        holder.dateTime.setText(materialRequestsList.get(position).getDateTime());
        holder.sno.setText((position + 1) + ".");
        return view;
    }

    public static class ItemHolder {
        TextView dateTime, sno;
    }
}

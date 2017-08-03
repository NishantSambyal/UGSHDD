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
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;

/**
 * Created by Nishant Sambyal on 03-Aug-17.
 */

public class SummaryPTAdapter extends ArrayAdapter<PerformedTaskBean> {
    Context context;
    List<PerformedTaskBean> performedTaskList;
    int resource;

    public SummaryPTAdapter(Context context, int resource, List<PerformedTaskBean> performedTaskList) {
        super(context, resource, performedTaskList);
        this.context = context;
        this.performedTaskList = performedTaskList;
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
            holder.performedTask = (TextView) view.findViewById(R.id.task_name);
            view.setTag(holder);
        } else {
            holder = (ItemHolder) view.getTag();
        }
        holder.sno.setText(String.valueOf((position + 1) + "."));
        holder.performedTask.setText(performedTaskList.get(position).getTasks());
        return view;
    }

    public static class ItemHolder {
        TextView sno, performedTask;
    }
}

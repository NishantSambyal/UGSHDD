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
 * Created by Patyal on 8/4/2017.
 */

public class CompletedCaseAdapter extends ArrayAdapter<Case> {

    private Context context;
    private List<Case> cases;
    private int resource;

    public CompletedCaseAdapter(Context context, int resource, List<Case> list) {
        super(context, resource, list);

        this.context = context;
        this.cases = list;
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
            holder.outlet = (TextView) view.findViewById(R.id.outlet);
            holder.sno = (TextView) view.findViewById(R.id.sno);
            view.setTag(holder);
        } else {
            holder = (ItemHolder) view.getTag();
        }
        holder.outlet.setText(cases.get(position).getOutlet());
        holder.sno.setText((position + 1) + ".");
        return view;
    }

    @Override
    public int getCount() {
        return cases.size();
    }

    public static class ItemHolder {
        TextView outlet, sno;
    }
}
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
 * Created by Patyal on 7/5/2017.
 */

public class MaterialListRVAdapter extends RecyclerView.Adapter<MaterialListRVAdapter.MaterialListViewHolder> {

    private List<String> materialList;
    private Context context;

    public MaterialListRVAdapter(List<String> materialList, Context context) {
        this.materialList = materialList;
        this.context = context;
    }

    @Override
    public MaterialListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MaterialListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.material_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MaterialListViewHolder holder, int position) {
        holder.date.setText(materialList.get(position));
        holder.tm.setText(materialList.get(position));
        holder.alternateTm.setText(materialList.get(position));
        holder.invWith.setText(materialList.get(position));
        holder.status.setText(materialList.get(position));
        holder.action.setText(materialList.get(position));
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    class MaterialListViewHolder extends RecyclerView.ViewHolder {
        TextView date, tm, alternateTm, invWith, status, action;

        public MaterialListViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            tm = (TextView) itemView.findViewById(R.id.tm);
            alternateTm = (TextView) itemView.findViewById(R.id.alternate_tm);
            invWith = (TextView) itemView.findViewById(R.id.inv_with);
            status = (TextView) itemView.findViewById(R.id.status);
            action = (TextView) itemView.findViewById(R.id.action);

        }
    }
}

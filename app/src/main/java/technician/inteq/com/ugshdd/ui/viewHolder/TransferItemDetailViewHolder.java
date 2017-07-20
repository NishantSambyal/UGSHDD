package technician.inteq.com.ugshdd.ui.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.transferItemBean.TransferItemDetail;

/**
 * Created by Patyal on 7/15/2017.
 */

public class TransferItemDetailViewHolder extends ChildViewHolder {

    TextView date;
    TextView tm;
    TextView alternate_tm;
    TextView status;
    TextView action;

    public TransferItemDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.date);
        tm = (TextView) itemView.findViewById(R.id.tm);
        alternate_tm = (TextView) itemView.findViewById(R.id.alternate_tm);
        status = (TextView) itemView.findViewById(R.id.status);
        action = (TextView) itemView.findViewById(R.id.action);
    }

    public void bind(@NonNull TransferItemDetail transferItem) {
        date.setText(transferItem.getDate());
        tm.setText(transferItem.getTm());
        alternate_tm.setText(transferItem.getAlternateTm());
        status.setText(transferItem.getStatus());
        action.setText(transferItem.getAction());
    }
}

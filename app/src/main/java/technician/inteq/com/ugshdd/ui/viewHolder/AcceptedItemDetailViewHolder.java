package technician.inteq.com.ugshdd.ui.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.AcceptedItemBean.AcceptedItemDetails;

/**
 * Created by Patyal on 7/17/2017.
 **/

public class AcceptedItemDetailViewHolder extends ChildViewHolder {

    TextView date;
    TextView form_tm;
    TextView status;
    TextView action;
    TextView inv_with;

    public AcceptedItemDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.date);
        form_tm = (TextView) itemView.findViewById(R.id.from_tm);
        status = (TextView) itemView.findViewById(R.id.status);
        action = (TextView) itemView.findViewById(R.id.action);
        inv_with = (TextView) itemView.findViewById(R.id.inv_with);
    }

    public void bind(@NonNull AcceptedItemDetails itemDetails) {
        date.setText(itemDetails.getDate());
        form_tm.setText(itemDetails.getFrom_tm());
        status.setText(itemDetails.getStatus());
        action.setText(itemDetails.getAction());
        inv_with.setText(itemDetails.getInv_With());
    }

}

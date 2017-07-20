package technician.inteq.com.ugshdd.ui.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.technicalRequestBean.TechnicalRequestsDetails;

/**
 * Created by Patyal on 7/18/2017.
 */

public class TechnicalRequestsDetailsViewHolder extends ChildViewHolder {
    TextView id;
    TextView requestDate;
    TextView view;
    TextView edit;
    TextView del;

    public TechnicalRequestsDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView) itemView.findViewById(R.id.id);
        requestDate = (TextView) itemView.findViewById(R.id.requested_date);
        view = (TextView) itemView.findViewById(R.id.view);
        edit = (TextView) itemView.findViewById(R.id.edit);
        del = (TextView) itemView.findViewById(R.id.del);
    }

    public void bind(@NonNull TechnicalRequestsDetails requestsDetails) {
        id.setText(requestsDetails.getId());
        requestDate.setText(requestsDetails.getRequestDate());
        view.setText(requestsDetails.getView());
        edit.setText(requestsDetails.getEdit());
        del.setText(requestsDetails.getDel());
    }
}

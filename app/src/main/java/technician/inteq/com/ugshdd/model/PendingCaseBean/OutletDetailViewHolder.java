package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import technician.inteq.com.ugshdd.R;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class OutletDetailViewHolder extends ChildViewHolder {

    TextView jobNumber;
    TextView unitNumber;
    TextView action;

    public OutletDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        jobNumber = (TextView) itemView.findViewById(R.id.job_no);
        unitNumber = (TextView) itemView.findViewById(R.id.unitNumber);
        action = (TextView) itemView.findViewById(R.id.action);
    }

    public void bind(@NonNull OutletDetail outletDetail) {
        jobNumber.setText(outletDetail.getJobNumber());
        unitNumber.setText(outletDetail.getUnitNumber());
        action.setText(outletDetail.getAction());
    }
}

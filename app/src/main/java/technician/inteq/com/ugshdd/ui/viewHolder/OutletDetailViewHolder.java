package technician.inteq.com.ugshdd.ui.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.OutletDetail;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class OutletDetailViewHolder extends ChildViewHolder {

    TextView jobNumber;
    TextView unitNumber;

    public OutletDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        jobNumber = (TextView) itemView.findViewById(R.id.job_no);
        unitNumber = (TextView) itemView.findViewById(R.id.unitNumber);
    }

    public void bind(@NonNull OutletDetail outletDetail) {
        jobNumber.setText(outletDetail.getJobNumber());
        unitNumber.setText(outletDetail.getUnitNumber());
    }
}

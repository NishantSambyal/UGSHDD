package technician.inteq.com.ugshdd.model.PendingCaseBean;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Nishant Sambyal on 13-Jul-17.
 */

public class Outlets implements Parent<OutletDetail> {

    String outletName;
    List<OutletDetail> outletDetailsList;

    public Outlets(String outletName, List<OutletDetail> outletDetailsList) {
        this.outletName = outletName;
        this.outletDetailsList = outletDetailsList;
    }

    @Override
    public List<OutletDetail> getChildList() {
        return outletDetailsList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getOutletName() {
        return outletName;
    }

}

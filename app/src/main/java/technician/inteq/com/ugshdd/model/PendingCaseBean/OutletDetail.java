package technician.inteq.com.ugshdd.model.PendingCaseBean;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class OutletDetail {

    String outletName;
    String jobNumber;
    String unitNumber;
    String isAcknowledge;

    public OutletDetail(String jobNumber, String unitNumber, String isAcknowledge, String outletName) {
        this.jobNumber = jobNumber;
        this.unitNumber = unitNumber;
        this.isAcknowledge = isAcknowledge;
        this.outletName = outletName;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getIsAcknowledge() {
        return isAcknowledge;
    }
}

package technician.inteq.com.ugshdd.model.PendingCaseBean;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class OutletDetail {

    String jobNumber;
    String unitNumber;
    String action;

    public OutletDetail(String jobNumber, String unitNumber, String action) {
        this.jobNumber = jobNumber;
        this.unitNumber = unitNumber;
        this.action = action;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

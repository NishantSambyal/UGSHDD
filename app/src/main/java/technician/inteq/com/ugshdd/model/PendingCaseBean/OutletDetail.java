package technician.inteq.com.ugshdd.model.PendingCaseBean;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class OutletDetail {

    String jobNumber;
    String unitNumber;

    public OutletDetail(String jobNumber, String unitNumber) {
        this.jobNumber = jobNumber;
        this.unitNumber = unitNumber;
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


}

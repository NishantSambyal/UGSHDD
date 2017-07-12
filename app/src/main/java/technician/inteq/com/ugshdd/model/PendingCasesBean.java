package technician.inteq.com.ugshdd.model;

/**
 * Created by Nishant Sambyal on 11-Jul-17.
 */

public class PendingCasesBean {

    String outlet, jobNo, unit_food, action;

    public PendingCasesBean(String outlet, String jobNo, String unit_food, String action) {
        this.outlet = outlet;
        this.jobNo = jobNo;
        this.unit_food = unit_food;
        this.action = action;
    }

    public String getOutlet() {
        return outlet;
    }

    public String getJobNo() {
        return jobNo;
    }

    public String getUnit_food() {
        return unit_food;
    }

    public String getAction() {
        return action;
    }
}

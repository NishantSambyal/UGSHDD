package technician.inteq.com.ugshdd.model;

/**
 * Created by Patyal on 7/11/2017.
 */

public class Leave {

    private String date;
    private String leaveType;
    private String fromDate;
    private String toDate;
    private String status;
    private String action;

    public Leave(String date, String leaveType, String fromDate, String toDate, String status, String action) {
        this.date = date;
        this.leaveType = leaveType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }
}

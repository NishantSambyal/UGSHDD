package technician.inteq.com.ugshdd.model.AcceptedItemBean;

/**
 * Created by Patyal on 7/17/2017.
 */

public class AcceptedItemDetails {

    private String date;
    private String from_tm;
    private String inv_With;
    private String status;
    private String action;

    public AcceptedItemDetails(String date, String from_tm, String inv_With, String status, String action) {
        this.date = date;
        this.from_tm = from_tm;
        this.inv_With = inv_With;
        this.status = status;
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public String getFrom_tm() {
        return from_tm;
    }

    public String getInv_With() {
        return inv_With;
    }

    public String getStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }
}

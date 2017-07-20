package technician.inteq.com.ugshdd.model.transferItemBean;

/**
 * Created by Patyal on 7/11/2017.
 */

public class TransferItemDetail {

    private String date;
    private String tm;
    private String alternateTm;
    private String invWith;
    private String status;
    private String action;

    public TransferItemDetail(String date, String tm, String alternateTm, String invWith, String status, String action) {
        this.date = date;
        this.tm = tm;
        this.alternateTm = alternateTm;
        this.invWith = invWith;
        this.status = status;
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public String getTm() {
        return tm;
    }

    public String getAlternateTm() {
        return alternateTm;
    }

    public String getInvWith() {
        return invWith;
    }

    public String getStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }
}

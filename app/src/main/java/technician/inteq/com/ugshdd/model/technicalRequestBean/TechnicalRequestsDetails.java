package technician.inteq.com.ugshdd.model.technicalRequestBean;

/**
 * Created by Patyal on 7/18/2017.
 */

public class TechnicalRequestsDetails {
    private String id;
    private String requestDate;
    private String view;
    private String edit;
    private String del;

    public TechnicalRequestsDetails(String id, String requestDate, String view, String edit, String del) {
        this.id = id;
        this.requestDate = requestDate;
        this.view = view;
        this.edit = edit;
        this.del = del;
    }

    public String getId() {
        return id;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getView() {
        return view;
    }

    public String getEdit() {
        return edit;
    }

    public String getDel() {
        return del;
    }
}

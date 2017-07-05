package technician.inteq.com.ugshdd.model;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class DailyCashReportBean {

    String employeeName, DCR_date;

    public DailyCashReportBean(String employeeName, String DCR_date) {
        this.employeeName = employeeName;
        this.DCR_date = DCR_date;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDCR_date() {
        return DCR_date;
    }

    public void setDCR_date(String DCR_date) {
        this.DCR_date = DCR_date;
    }
}

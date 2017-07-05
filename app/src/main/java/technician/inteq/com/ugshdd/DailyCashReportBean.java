package technician.inteq.com.ugshdd;

import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class DailyCashReportBean {

    TextView employeeName, DCR_date;
    Spinner action;

    public TextView getEmployeeName() {
        return employeeName;
    }

    public TextView getDCR_date() {
        return DCR_date;
    }

    public Spinner getAction() {
        return action;
    }
}

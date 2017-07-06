package technician.inteq.com.ugshdd.ui.fragment.daily_cash_report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import technician.inteq.com.ugshdd.R;

/**
 * Created by Nishant Sambyal on 06-Jul-17.
 */

public class DailyCashReportCashFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_cash_report_cash_fragment, container, false);
    }
}

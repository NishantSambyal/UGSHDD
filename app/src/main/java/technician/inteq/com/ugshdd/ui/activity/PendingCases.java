package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

/**
 * Created by Nishant Sambyal on 05-Jul-17.
 */

public class PendingCases extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.pending_case, savedInstanceState, new String[]{"Pending cases", ""});

    }
}

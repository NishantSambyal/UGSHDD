package technician.inteq.com.ugshdd.util;

import android.view.Menu;
import android.view.MenuItem;

import com.nbsp.materialfilepicker.ui.FilePickerActivity;

/**
 * Created by Nishant Sambyal on 08-Sep-17.
 */

public class MyFilePickerActivity extends FilePickerActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

}

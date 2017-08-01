package technician.inteq.com.ugshdd.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Nishant Sambyal on 29-Jul-17.
 */

public class InventoryItemController implements DatabaseValues {


    public static Cursor getAllItems() {
        SQLiteDatabase db = UGSApplication.getDb();
        return db.query(TABLE_ITEMS, null, null, null, null, null, null);
    }
}

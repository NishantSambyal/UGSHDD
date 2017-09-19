package technician.inteq.com.ugshdd.Controller;

import android.database.Cursor;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.util.UGSApplication;

import static technician.inteq.com.ugshdd.util.UGSApplication.getDb;

/**
 * Created by Nishant Sambyal on 29-Jul-17.
 */

public class InventoryItemController implements DatabaseValues {


    public static Cursor getAllItems(String subCategory) {
        String selection = COL_ITEM_SUB_CATEGORY + " = ?";
        if (subCategory != null) {
            return UGSApplication.getDb().query(TABLE_ITEMS, null, selection, new String[]{subCategory}, null, null, null);
        }
        return UGSApplication.getDb().query(TABLE_ITEMS, null, null, null, null, null, null);
    }


    public static Cursor getCategory() {
        return getDb().rawQuery("SELECT DISTINCT "
                + COL_CATEGORY + " FROM " + TABLE_ITEMS, null);
    }

    public static Cursor getSubCategory(String category) {
        return getDb().rawQuery("SELECT DISTINCT "
                + COL_ITEM_SUB_CATEGORY + " FROM " + TABLE_ITEMS + " WHERE "
                + COL_CATEGORY + " = '" + category + "'", null);

    }
}

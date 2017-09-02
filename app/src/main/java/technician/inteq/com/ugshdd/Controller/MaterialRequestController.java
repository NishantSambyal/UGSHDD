package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Nishant Sambyal on 29-Aug-17.
 */

public class MaterialRequestController implements DatabaseValues {

    public static void insertRequestedMaterial(List<InventoryItem> itemList) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values;
        for (InventoryItem item : itemList) {
            if (Integer.parseInt(item.getQuantity()) > 0) {
                values = new ContentValues();
                values.put(COL_INTERNAL_ID, item.getInternalId());
                values.put(COL_QUANTITY, item.getQuantity());
                values.put(COL_AMOUNT, Integer.parseInt(item.getRate().trim()) * Integer.parseInt(item.getQuantity().trim()));
                db.insert(TABLE_MATERIAL_REQUEST_TEMP, null, values);
            }
        }
    }

    public static Cursor getAllRequestedMaterial() {
        String query = "SELECT * FROM " + TABLE_MATERIAL_REQUEST_TEMP + " LEFT JOIN " + TABLE_ITEMS
                + " ON " + TABLE_MATERIAL_REQUEST_TEMP + "." + COL_INTERNAL_ID + "=" + TABLE_ITEMS
                + "." + COL_INTERNAL_ID;
        return UGSApplication.getDb().rawQuery(query, null);
    }

    public static void deleteMaterialRequest(String id) {
        UGSApplication.getDb().delete(TABLE_MATERIAL_REQUEST_TEMP, COL_ID + " = ?", new String[]{id});
    }

    public static void updateMaterialRequestItem(MaterialRequest requestedItem) {
        ContentValues values = new ContentValues();
        values.put(COL_AMOUNT, requestedItem.getAmount());
        values.put(COL_QUANTITY, requestedItem.getQuantity());
        UGSApplication.getDb().update(TABLE_MATERIAL_REQUEST_TEMP, values, COL_ID + " = ?", new String[]{requestedItem.getColID()});
    }
}

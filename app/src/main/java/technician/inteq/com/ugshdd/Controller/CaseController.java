package technician.inteq.com.ugshdd.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Nishant Sambyal on 31-Jul-17.
 */

public class CaseController implements DatabaseValues {

    public static Cursor getAllPendingCases(String type, String outlet) {
        SQLiteDatabase db = UGSApplication.getDb();
        String query = "SELECT * FROM " + TABLE_ONGOING_TASKS + " LEFT JOIN " + TABLE_ITEMS
                + " ON " + TABLE_ONGOING_TASKS + "." + COL_INTERNAL_ID + "=" + TABLE_ITEMS
                + "." + COL_INTERNAL_ID + " WHERE " + TABLE_ONGOING_TASKS + "." + COL_ITEM_TYPE + " = '" + type + "' AND " + TABLE_ONGOING_TASKS
                + "." + COL_OUTLET + " = '" + outlet + "'";
        return db.rawQuery(query, null);
    }

    public static boolean insert(Case singleCase) throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        String where = COL_INTERNAL_ID + " = ? AND " + COL_ITEM_TYPE + " =?";
        if (db.query(TABLE_ONGOING_TASKS, null, where, new String[]{singleCase.getInternalId(), String.valueOf(singleCase.getItem_type())}, null, null, null).getCount() > 0) {
            return false;
        }
        return db.insert(TABLE_ONGOING_TASKS, null, singleCase.getContentValues()) != -1;
    }

    public static boolean deletePendingCases(String outlet, short itemId) {
        SQLiteDatabase db = UGSApplication.getDb();
        String where = COL_OUTLET + " = ? AND " + COL_ID + " = ?";
        String whereArgs[] = {outlet, String.valueOf(itemId)};
        db.delete(TABLE_ONGOING_TASKS, where, whereArgs);
        return true;
    }

    public static List<Case> loadItems(String type, String outlet) {
        List<Case> cases = new ArrayList<>();
        Cursor cursor = CaseController.getAllPendingCases(type, outlet);
        if (cursor.moveToFirst()) {
            do {
                Case aCase = new Case(cursor.getShort(0),
                        cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_OUTLET)),
                        cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_INTERNAL_ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseValues.COL_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_AMOUNT)),
                        cursor.getShort(cursor.getColumnIndex(DatabaseValues.COL_ITEM_TYPE)));
                aCase.setInventoryItem(new InventoryItem(cursor.getBlob(cursor.getColumnIndex("itemImage")),
                        cursor.getString(cursor.getColumnIndex("status")),
                        cursor.getString(cursor.getColumnIndex("SubCategory")),
                        cursor.getString(cursor.getColumnIndex("category")),
                        cursor.getString(cursor.getColumnIndex("internalId")),
                        cursor.getString(cursor.getColumnIndex("item")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("rate"))));
                cases.add(aCase);
            } while (cursor.moveToNext());
        }

        return cases;
    }
}

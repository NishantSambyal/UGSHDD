package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
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
        String query = "SELECT * FROM " + TABLE_ONGOING_TASKS + " LEFT JOIN " + TABLE_ITEMS
                + " ON " + TABLE_ONGOING_TASKS + "." + COL_INTERNAL_ID + "=" + TABLE_ITEMS
                + "." + COL_INTERNAL_ID + " WHERE " + TABLE_ONGOING_TASKS + "." + COL_ITEM_TYPE + " = '" + type + "' AND " + TABLE_ONGOING_TASKS
                + "." + COL_OUTLET + " = '" + outlet + "'";
        return UGSApplication.getDb().rawQuery(query, null);
    }

    public static Cursor getAllCompletedCases() {
        String query = "SELECT * FROM " + TABLE_CASES + " WHERE " + COL_IS_COMPLETED + " = 1";
        return UGSApplication.getDb().rawQuery(query, null);
    }

    public static void updateCase(Case cases) throws IllegalAccessException {
        String where = COL_ID + " = ? ";
        ContentValues values = new ContentValues();
        values.put("quantity", cases.getQuantity());
        values.put("amount", cases.getAmount());
        UGSApplication.getDb().update(TABLE_ONGOING_TASKS, values, where, new String[]{String.valueOf(cases.getId())});
    }


    public static boolean insert(Case singleCase) throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        String where = COL_INTERNAL_ID + " = ? AND " + COL_ITEM_TYPE + " = ? ";
        if (db.query(TABLE_ONGOING_TASKS, null, where, new String[]{singleCase.getInternalId(), String.valueOf(singleCase.getItem_type())}, null, null, null).getCount() > 0) {
            return false;
        }
        return db.insert(TABLE_ONGOING_TASKS, null, singleCase.getContentValues()) != -1;
    }

    public static boolean deletePendingCases(String outlet, short itemId) {
        String where = COL_OUTLET + " = ? AND " + COL_ID + " = ?";
        String whereArgs[] = {outlet, String.valueOf(itemId)};
        UGSApplication.getDb().delete(TABLE_ONGOING_TASKS, where, whereArgs);
        return true;
    }

    public static void saveOngoingTask(String outlet) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        String s = "SELECT  * FROM " + TABLE_ONGOING_TASKS + " WHERE " + COL_OUTLET + "= '" + outlet + "'";
        Cursor cursor = db.rawQuery(s, null);
        if (cursor.moveToFirst()) {
            do {
                values.put(COL_OUTLET, cursor.getString(cursor.getColumnIndex(COL_OUTLET)));
                values.put(COL_INTERNAL_ID, cursor.getString(cursor.getColumnIndex(COL_INTERNAL_ID)));
                values.put(COL_QUANTITY, cursor.getString(cursor.getColumnIndex(COL_QUANTITY)));
                values.put(COL_ITEM_TYPE, cursor.getString(cursor.getColumnIndex(COL_ITEM_TYPE)));
                values.put(COL_AMOUNT, cursor.getString(cursor.getColumnIndex(COL_AMOUNT)));
                db.insert(TABLE_COMPLETED_TASKS, null, values);
            } while (cursor.moveToNext());
        }
        db.delete(TABLE_ONGOING_TASKS, COL_OUTLET + " = ?", new String[]{outlet});
        db.delete(TABLE_PERFORMED_TASKS_TEMP, COL_OUTLET + " = ?", new String[]{outlet});
    }

    public static Cursor getCompletedCase(String outlet_no) {
        return UGSApplication.getDb().query(TABLE_COMPLETED_TASKS, null, COL_OUTLET + "=?", new String[]{outlet_no}, null, null, null);
    }

    public static List<Case> loadItems(String type, String outlet) {
        List<Case> cases = new ArrayList<>();
        Cursor cursor = CaseController.getAllPendingCases(type, outlet);
        if (cursor.moveToFirst()) {
            do {
                Case aCase = new Case(cursor.getShort(0),// id of ongoing task
                        cursor.getString(cursor.getColumnIndex(COL_OUTLET)),
                        cursor.getString(cursor.getColumnIndex(COL_INTERNAL_ID)),
                        cursor.getInt(cursor.getColumnIndex(COL_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(COL_AMOUNT)),
                        cursor.getShort(cursor.getColumnIndex(COL_ITEM_TYPE)));
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

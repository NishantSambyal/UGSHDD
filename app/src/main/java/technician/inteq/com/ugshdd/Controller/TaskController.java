package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.PendingCaseBean.OutletDetail;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public class TaskController implements DatabaseValues {

    public static boolean insertTasks(String outlet, String jobNumber) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        values.put(COL_OUTLET, outlet);
        values.put(COL_JOB_NO, jobNumber);
        values.put(COL_UNIT_NO, "unit_no");
        values.put(COL_ACKNOWLEDGE, "0");
        return db.insert(TABLE_CASES, null, values) != -1;
    }

    public static Cursor getAllOutlets() {
        SQLiteDatabase db = UGSApplication.getDb();
        return db.query(TABLE_CASES, null, null, null, null, null, null);
    }

    public static List<Outlets> getOutletDetails() {
        List<Outlets> outletList = new ArrayList<>();
        Outlets outlets;
        OutletDetail outletDetail;
        Cursor cursor = getAllOutlets();
        try {
            if (cursor.moveToFirst()) {
                do {
                    outletDetail = new OutletDetail(cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_JOB_NO)),
                            cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_UNIT_NO)),
                            cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_ACKNOWLEDGE)),
                            cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_OUTLET)));
                    outlets = new Outlets(cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_OUTLET)), Arrays.asList(outletDetail));
                    outletList.add(outlets);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outletList;
    }

    public static boolean acknowledgeTask(String outlet) {
        ContentValues values = new ContentValues();
        values.put(COL_ACKNOWLEDGE, "1");
        return UGSApplication.getDb().update(TABLE_CASES, values, COL_OUTLET + "= ?", new String[]{outlet}) == 1;
    }

    public static boolean completeTask(String outlet, byte[] signature, String customerName) {

        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        values.put(COL_IS_COMPLETED, "1");
        values.put(COL_SIGNATURE, signature);
        values.put(COL_CUSTOMER_NAME, customerName);
        return db.update(TABLE_CASES, values, COL_OUTLET + "= ?", new String[]{outlet}) == 1;
    }




}

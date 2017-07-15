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

public class TaskModel implements DatabaseValues {

    public static boolean insertTasks(String outlet, String jobNumber) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        values.put(COL_OUTLET, outlet);
        values.put(COL_JOB_NO, jobNumber);
        values.put(COL_UNIT_NO, "null");
        return db.insert(TABLE_TASKS, null, values) != -1;
    }

    public static List<Outlets> getOutletDetails() {
        SQLiteDatabase db = UGSApplication.getDb();
        List<Outlets> outletList = new ArrayList<>();
        Outlets outlets;
        OutletDetail outletDetail;
        Cursor cursor = db.query(TABLE_TASKS, null, null, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    outletDetail = new OutletDetail(cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_JOB_NO)),
                            cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_UNIT_NO)));
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

}

package technician.inteq.com.ugshdd.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskBean;
import technician.inteq.com.ugshdd.model.PendingCaseBean.PerformedTaskHelper;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Nishant Sambyal on 02-Aug-17.
 */

public class PerformedTaskController implements DatabaseValues {

    public static boolean insertTasks(PerformedTaskBean bean) throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        return db.insert(TABLE_PERFORMED_TASKS_LIST, null, bean.getContentValues()) != -1;
    }

    public static Cursor getAllTasks() {
        SQLiteDatabase db = UGSApplication.getDb();
        return db.query(TABLE_PERFORMED_TASKS_LIST, null, null, null, null, null, null);
    }

    public static void insertTempTasks(List<PerformedTaskHelper> list) throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        for (PerformedTaskHelper bean : list) {
            db.insert(TABLE_PERFORMED_TASKS_TEMP, null, bean.getContentValues());
        }
    }

    /* public static Cursor getPerformTasks(String outlet) {
         SQLiteDatabase db = UGSApplication.getDb();
         String selection = COL_OUTLET + " = ? ";
         return db.query(TABLE_PERFORMED_TASKS_TEMP, null, selection, new String[]{outlet}, null, null, null);
     }*/
    public static Cursor getPerformTasks(String outlet) {
        SQLiteDatabase db = UGSApplication.getDb();
        String query = "SELECT * FROM " + TABLE_PERFORMED_TASKS_TEMP + " JOIN " + TABLE_PERFORMED_TASKS_LIST
                + " ON " + TABLE_PERFORMED_TASKS_TEMP + "." + COL_TASKS + "=" + TABLE_PERFORMED_TASKS_LIST
                + "." + COL_ID + " WHERE " + TABLE_PERFORMED_TASKS_TEMP + "." + COL_OUTLET + " = '" + outlet + "' ";
        return db.rawQuery(query, null);
    }

}

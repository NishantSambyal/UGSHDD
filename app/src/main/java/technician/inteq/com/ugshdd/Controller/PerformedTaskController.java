package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public static void insertTempTask(PerformedTaskHelper task) throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        db.insert(TABLE_PERFORMED_TASKS_TEMP, null, task.getContentValues());
    }

    public static void removeTempTask(String taskId, String outlet) throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        String selection = COL_TASKS + " =? AND " + COL_OUTLET + " =?";
        db.delete(TABLE_PERFORMED_TASKS_TEMP, selection, new String[]{taskId, outlet});
    }

    public static Cursor getPerformTasks(String outlet) {
        SQLiteDatabase db = UGSApplication.getDb();
        String query = "SELECT * FROM " + TABLE_PERFORMED_TASKS_TEMP + " JOIN " + TABLE_PERFORMED_TASKS_LIST
                + " ON " + TABLE_PERFORMED_TASKS_TEMP + "." + COL_TASKS + "=" + TABLE_PERFORMED_TASKS_LIST
                + "." + COL_ID + " WHERE " + TABLE_PERFORMED_TASKS_TEMP + "." + COL_OUTLET + " = '" + outlet + "' ";
        return db.rawQuery(query, null);
    }

    public static void savePerformedTask() {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        String s = "SELECT * FROM " + TABLE_PERFORMED_TASKS_TEMP;
        Cursor cursor = db.rawQuery(s, null);
        if (cursor.moveToFirst()) {
            do {
                values.put(COL_OUTLET, cursor.getString(cursor.getColumnIndex(COL_OUTLET)));
                values.put(COL_TASKS, cursor.getString(cursor.getColumnIndex(COL_TASKS)));
                db.insert(TABLE_PERFORMED_TASKS, null, values);
            } while (cursor.moveToNext());
        }
    }
}

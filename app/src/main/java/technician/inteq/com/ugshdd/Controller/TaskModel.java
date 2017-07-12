package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
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
        return db.insert(TABLE_TASKS, null, values) != -1;
    }

}

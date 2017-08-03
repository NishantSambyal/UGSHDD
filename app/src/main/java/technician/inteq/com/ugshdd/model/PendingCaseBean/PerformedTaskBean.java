package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.PerformedTaskController;
import technician.inteq.com.ugshdd.Database.DatabaseValues;

/**
 * Created by Nishant Sambyal on 02-Aug-17.
 */

public class PerformedTaskBean implements DatabaseValues {
    private String id, tasks;

    public PerformedTaskBean(String tasks) {
        this.tasks = tasks;
    }

    public PerformedTaskBean(String id, String tasks) {
        this.id = id;
        this.tasks = tasks;
    }

    public static void getPerformedTask(List<PerformedTaskBean> tasks) {
        Cursor cursor = PerformedTaskController.getAllTasks();
        if (cursor.moveToFirst()) {
            do {
                tasks.add(new PerformedTaskBean(cursor.getString(cursor.getColumnIndex(COL_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_TASKS))));
            } while (cursor.moveToNext());
        }
    }

    public String getId() {
        return id;
    }

    public String getTasks() {
        return tasks;
    }

    public ContentValues getContentValues() throws IllegalAccessException, IllegalArgumentException {
        ContentValues values = null;
        values = new ContentValues();

        for (Field field : this.getClass().getDeclaredFields()) {
            String name = field.getName();
            Class<?> type = field.getType();
            if (!Modifier.isStatic(field.getModifiers()) && !Collection.class.isAssignableFrom(type)
                    && field.get(this) != null && !name.equals("id")) {
                if (type.equals(byte[].class)) {
                    byte[] obj = (byte[]) field.get(this);
                    values.put(name, obj);
                } else {

                    String obj = (String) field.get(this);
                    values.put(name, obj);
                }
            }
        }
        return values;
    }
}

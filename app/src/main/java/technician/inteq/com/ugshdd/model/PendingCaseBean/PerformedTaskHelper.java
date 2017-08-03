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

public class PerformedTaskHelper implements DatabaseValues {
    String outlet, tasks;

    public PerformedTaskHelper(String outlet, String task) {
        this.outlet = outlet;
        this.tasks = task;
    }

    public static void getPerformedTask(String outlet, List<PerformedTaskBean> list) {
        Cursor cursor = PerformedTaskController.getPerformTasks(outlet);
        if (cursor.moveToFirst()) {
            do {

                list.add(new PerformedTaskBean(cursor.getString(2), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
    }

    public ContentValues getContentValues() throws IllegalAccessException, IllegalArgumentException {
        ContentValues values = null;
        values = new ContentValues();

        for (Field field : this.getClass().getDeclaredFields()) {
            String name = field.getName();
            Class<?> type = field.getType();
            if (!Modifier.isStatic(field.getModifiers()) && !Collection.class.isAssignableFrom(type)
                    && field.get(this) != null) {
                if (type.equals(String.class)) {
                    String obj = (String) field.get(this);
                    values.put(name, obj);
                } else {
                    String obj = (String) field.get(this);
                    values.put(name, obj);
                }
            }
        }
        return values;
    }

    public String getOutlet() {
        return outlet;
    }

    public String getTask() {
        return tasks;
    }
}

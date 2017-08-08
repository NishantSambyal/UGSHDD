package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.contact.AddContact;
import technician.inteq.com.ugshdd.util.UGSApplication;


/**
 * Created by Nishant Sambyal on 08-Aug-17.
 */

public class ContactController implements DatabaseValues {

    public static boolean insertContact(String number) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        values.put(TABLE_CONTACT, number);
        values.put(COL_IS_ACTIVE, "0");
        return db.insert(TABLE_CONTACT, null, values) != -1;
    }

    public static void getAllContact(List<AddContact> list) {
        SQLiteDatabase db = UGSApplication.getDb();
        Cursor c = db.query(TABLE_CONTACT, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                list.add(new AddContact(c.getString(c.getColumnIndex(COL_ID)),
                        c.getString(c.getColumnIndex(TABLE_CONTACT)), c.getString(c.getColumnIndex(COL_IS_ACTIVE))));
            } while (c.moveToNext());
        }

    }

    public static void deleteNumber(String id) {
        SQLiteDatabase db = UGSApplication.getDb();
        db.delete(TABLE_CONTACT, COL_ID + " = ? ", new String[]{id});
    }

    public static AddContact getSelectedNumber() {
        SQLiteDatabase db = UGSApplication.getDb();
        Cursor cursor = db.query(TABLE_CONTACT, null, COL_IS_ACTIVE + " = ? ", new String[]{"1"}, null, null, null);
        AddContact contact = null;
        if (cursor.moveToFirst()) {
            contact = new AddContact(cursor.getString(cursor.getColumnIndex(COL_ID)),
                    cursor.getString(cursor.getColumnIndex(TABLE_CONTACT)), cursor.getString(cursor.getColumnIndex(COL_IS_ACTIVE)));
        }
        return contact;
    }

    public static void setSelectedNumber(String id) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = new ContentValues();
        values.put(COL_IS_ACTIVE, "0");
        db.update(TABLE_CONTACT, values, null, null);

        values = new ContentValues();
        values.put(COL_IS_ACTIVE, "1");
        db.update(TABLE_CONTACT, values, COL_ID + " = ? ", new String[]{id});
    }


}

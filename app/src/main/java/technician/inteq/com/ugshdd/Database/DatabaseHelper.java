package technician.inteq.com.ugshdd.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseValues {

    private static final String DATABASE_NAME = "UGS_HHD.db";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ";
    String[] SQL_CREATE_TABLES = {SQL_CREATE_TABLE_PENDING_TASKS, SQL_CREATE_TABLE_COMPLETED_TASKS,
            SQL_CREATE_TABLE_ONGOING_TASKS, SQL_CREATE_TABLE_TASK, SQL_CREATE_TABLE_ITEM};

    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, 1);
    }

    public DatabaseHelper(Context context) {
        this(context, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String singleTable : SQL_CREATE_TABLES) {
            db.execSQL(singleTable);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        String[] tableNames;
        int iterator = 0;
        tableNames = new String[c.getCount()];
        while (c.moveToNext()) {
            tableNames[iterator] = c.getString(c.getColumnIndex("name"));
            iterator++;
        }
        for (String SQL_TABLE : tableNames) {
            db.execSQL(SQL_DELETE_ENTRIES + SQL_TABLE);
        }
        onCreate(db);
    }

    public ArrayList<Cursor> getData(String Query) {
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        ArrayList<Cursor> alc = new ArrayList<>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            Cursor c = sqlDB.rawQuery(maxQuery, null);
            Cursor2.addRow(new Object[]{"Success"});
            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {
                alc.set(0, c);
                c.moveToFirst();
                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }
}

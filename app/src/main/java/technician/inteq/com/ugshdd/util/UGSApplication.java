package technician.inteq.com.ugshdd.util;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import technician.inteq.com.ugshdd.Database.DatabaseHelper;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public class UGSApplication extends Application {

    public static String outletID;
    public static String accountNumber;
    public static String street;
    public static String building;
    public static String unit_country;
    public static String stall_no;
    public static String food_type;
    public static String outlet_reference;
    private static SQLiteDatabase db;

    public static SQLiteDatabase getDb() {
        return db;
    }

    public static void setDb(SQLiteDatabase db) {
        UGSApplication.db = db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDb(new DatabaseHelper(getApplicationContext(), null).getWritableDatabase());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        db.close();
    }

}

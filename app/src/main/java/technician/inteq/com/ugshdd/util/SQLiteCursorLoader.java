package technician.inteq.com.ugshdd.util;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

/**
 * Created by Nishant Sambyal on 21-Aug-17.
 */

public class SQLiteCursorLoader extends CursorLoader {

    Cursor cursor;

    public SQLiteCursorLoader(Context context, Cursor cursor) {
        super(context);
        this.cursor = cursor;
    }

    @Override
    public Cursor loadInBackground() {
        return this.cursor;
    }
}

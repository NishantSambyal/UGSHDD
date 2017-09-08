package technician.inteq.com.ugshdd.util;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import technician.inteq.com.ugshdd.Database.DatabaseValues;

/**
 * Created by Nishant Sambyal on 05-Sep-17.
 */

public class ImportCSV implements DatabaseValues {

    public static void importCSV(String filepath, Context context) {
        SQLiteDatabase db = UGSApplication.getDb();
        String filename = (new File(filepath)).getName();
        String exact = filename.substring(filename.length() - 3, filename.length());
        if (!filepath.contains("storage")) {
            final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Wrong Input Process");
            alertDialog.setMessage("Please use a file manager to select CSV file");
            alertDialog.setButton("Dismiss", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
//            if (exact.equals("csv")) {
            FileReader file;
            try {
                file = new FileReader(filepath);
                try {
                    if (db.query(TABLE_ITEMS, null, null, null, null, null, null).getCount() > 0) {
                        db.delete(TABLE_ITEMS, null, null);
                    }
                    BufferedReader buffer = new BufferedReader(file);
                    String line;
                    db.beginTransaction();
                    boolean firstLine = false;
                    while ((line = buffer.readLine()) != null) {
                        if (firstLine) {// skip column line
                            String[] str = line.split(",");
                            ContentValues values = new ContentValues();
                            values.put(COL_INTERNAL_ID, str[0]);
                            values.put(COL_ITEM_NAME, str[1]);
                            values.put(COL_CATEGORY, str[2]);
                            values.put(COL_ITEM_SUB_CATEGORY, str[3]);
                            values.put(COL_DESCRIPTION, str[4]);
                            values.put(COL_C_NO, str[5]);
                            values.put(COL_RATE, str[0]);
//                        values.put(COL_IMAGE_LINK, str[6]);
                            db.insert(TABLE_ITEMS, null, values);
                        }
                        firstLine = true;
                    }
                } catch (Exception e) {
                    Utility.toast(context, "exception: " + e);
                }
                db.setTransactionSuccessful();
                db.endTransaction();
                Utility.toast(context, "File updated successfully");
            } catch (FileNotFoundException e) {
                Utility.toast(context, "File not found: " + e);
                e.printStackTrace();
            }
//            } else {
//                Utility.toast(context, "Please use a file with extension .CSV");
//            }

        }
    }
}

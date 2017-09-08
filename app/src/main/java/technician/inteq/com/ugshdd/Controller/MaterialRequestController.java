package technician.inteq.com.ugshdd.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;
import java.util.List;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.util.UGSApplication;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 29-Aug-17.
 */

public class MaterialRequestController implements DatabaseValues {

    public static void insertRequestedMaterial(List<InventoryItem> itemList) {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values;
        for (InventoryItem item : itemList) {
            if (Integer.parseInt(item.getQuantity()) > 0) {
                values = new ContentValues();
                values.put(COL_INTERNAL_ID, item.getInternalId());
                values.put(COL_QUANTITY, item.getQuantity());
                values.put(COL_AMOUNT, Integer.parseInt(item.getRate().trim()) * Integer.parseInt(item.getQuantity().trim()));
                db.insert(TABLE_MATERIAL_REQUEST_TEMP, null, values);
            }
        }
    }

    public static void saveTheTransaction() {
        SQLiteDatabase db = UGSApplication.getDb();
        Cursor cursor = db.query(TABLE_MATERIAL_REQUEST_TEMP, null, null, null, null, null, null);
        Cursor transactionCursor = db.query(TABLE_MATERIAL_REQUEST, null, null, null, null, null, null);
        int transactionID = 1;
        if (transactionCursor.moveToFirst()) {
            do {
                int newTransID = transactionCursor.getInt(transactionCursor.getColumnIndex(COL_TRANSACTION_ID));
                if (newTransID >= transactionID) {
                    transactionID = newTransID;
                    transactionID++;
                }
            } while (transactionCursor.moveToNext());
        }
        ContentValues values;
        if (cursor.moveToFirst()) {
            do {
                values = new ContentValues();
                values.put(COL_TRANSACTION_ID, transactionID);
                values.put(COL_INTERNAL_ID, cursor.getString(cursor.getColumnIndex(COL_INTERNAL_ID)));
                values.put(COL_QUANTITY, cursor.getString(cursor.getColumnIndex(COL_QUANTITY)));
                values.put(COL_AMOUNT, cursor.getString(cursor.getColumnIndex(COL_AMOUNT)));
                db.insert(TABLE_MATERIAL_REQUEST, null, values);
            } while (cursor.moveToNext());
        }

        values = new ContentValues();
        values.put(COL_TRANSACTION_ID, transactionID);
        values.put(COL_DATE_TIME, Utility.format.format(Calendar.getInstance().getTime()));
        db.insert(TABLE_MATERIAL_REQUEST_TRANSACTIONS, null, values);

        deleteTempTable();
    }

    public static Cursor getAllRequestedMaterialTemp() {
        String query = "SELECT * FROM " + TABLE_MATERIAL_REQUEST_TEMP + " LEFT JOIN " + TABLE_ITEMS
                + " ON " + TABLE_MATERIAL_REQUEST_TEMP + "." + COL_INTERNAL_ID + "=" + TABLE_ITEMS
                + "." + COL_INTERNAL_ID;
        return UGSApplication.getDb().rawQuery(query, null);
    }

    public static Cursor getMaterialRequestTransactions() {
        return UGSApplication.getDb().query(TABLE_MATERIAL_REQUEST_TRANSACTIONS, null, null, null, null, null, null);
    }

    public static Cursor getAllRequestedMaterial(String transactionID) {
        String query = "SELECT * FROM " + TABLE_MATERIAL_REQUEST + " LEFT JOIN " + TABLE_ITEMS
                + " ON " + TABLE_MATERIAL_REQUEST + "." + COL_INTERNAL_ID + "=" + TABLE_ITEMS
                + "." + COL_INTERNAL_ID + " WHERE " + COL_TRANSACTION_ID + "='" + transactionID + "'";
        return UGSApplication.getDb().rawQuery(query, null);
    }

    public static void deleteTempTable() {
        UGSApplication.getDb().delete(TABLE_MATERIAL_REQUEST_TEMP, null, null);
    }

    public static void deleteMaterialRequest(String id) {
        UGSApplication.getDb().delete(TABLE_MATERIAL_REQUEST_TEMP, COL_ID + " = ?", new String[]{id});
    }

    public static void updateMaterialRequestItem(MaterialRequest requestedItem) {
        ContentValues values = new ContentValues();
        values.put(COL_AMOUNT, requestedItem.getAmount());
        values.put(COL_QUANTITY, requestedItem.getQuantity());
        UGSApplication.getDb().update(TABLE_MATERIAL_REQUEST_TEMP, values, COL_ID + " = ?", new String[]{requestedItem.getColID()});
    }
}

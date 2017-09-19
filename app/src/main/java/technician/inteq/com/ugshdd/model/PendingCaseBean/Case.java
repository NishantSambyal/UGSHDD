package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.content.ContentValues;
import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.CaseController;
import technician.inteq.com.ugshdd.Database.DatabaseValues;

/**
 * Created by Nishant Sambyal on 28-Jul-17.
 */

public class Case {

    private String outlet;
    private String internalId;
    private int quantity;
    private String amount;
    private short item_type;
    private short id;
    private InventoryItem inventoryItem;

    public Case(int quantity, String amount, short id) {
        this.quantity = quantity;
        this.amount = amount;
        this.id = id;
    }

    public Case(short id, String outletNumber, String itemID, int quantity, String amount, short itemType) {
        this.id = id;
        this.outlet = outletNumber;
        this.internalId = itemID;
        this.quantity = quantity;
        this.amount = amount;
        this.item_type = itemType;
    }

    public Case(String outletNumber, String itemID, int quantity, String amount, short itemType) {
        this.outlet = outletNumber;
        this.internalId = itemID;
        this.quantity = quantity;
        this.amount = amount;
        this.item_type = itemType;
    }

    public static List<Case> getCompletedCases(List<Case> cases) {
        Cursor cursor = CaseController.getAllCompletedCases();
        if (cursor.moveToFirst()) {
            do {
                cases.add(new Case(cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_OUTLET)), "", 0, "", (short) 0));
            } while (cursor.moveToNext());
        }

        return null;
    }

    public static List<Case> getOutletCompletedCases(List<Case> list, String outletNo) {
        Cursor cursor = CaseController.getCompletedCase(outletNo);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Case(
                        cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_OUTLET)),
                        cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_INTERNAL_ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseValues.COL_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(DatabaseValues.COL_ITEM_TYPE)),
                        cursor.getShort(cursor.getColumnIndex(DatabaseValues.COL_AMOUNT))));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ContentValues getContentValues() throws IllegalAccessException, IllegalArgumentException {
        ContentValues values = null;
        values = new ContentValues();

        for (Field field : this.getClass().getDeclaredFields()) {
            String name = field.getName();
            Class<?> type = field.getType();

            if (!Modifier.isStatic(field.getModifiers()) && !Collection.class.isAssignableFrom(type)
                    && field.get(this) != null && !field.getName().equals("inventoryItem") && !field.getName().equals("id")) {
                if (type.equals(int.class)) {
                    int obj = (int) field.get(this);
                    values.put(name, obj);
                } else if (type.equals(short.class)) {
                    short obj = (short) field.get(this);
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

    public String getInternalId() {
        return internalId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public short getItem_type() {
        return item_type;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public short getId() {
        return id;
    }
}

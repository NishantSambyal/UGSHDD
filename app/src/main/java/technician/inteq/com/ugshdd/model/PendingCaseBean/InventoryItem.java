package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Patyal on 7/25/2017.
 */

public class InventoryItem implements DatabaseValues {
    private byte[] itemImage;
    private String status;
    private String SubCategory;
    private String category;
    private String internalId;
    private String item;
    private String description;
    private String rate;
    private String quantity = "";
    private String amount = "";
    private String type;

    public InventoryItem() {
    }

    public InventoryItem(byte[] itemImage, String status, String subCategory, String category, String internalId, String item, String description, String rate) {
        this.itemImage = itemImage;
        this.status = status;
        this.SubCategory = subCategory;
        this.category = category;
        this.internalId = internalId;
        this.item = item;
        this.description = description;
        this.rate = rate;
    }

    public static String getSql() {
        String sql = "CREATE TABLE " + TABLE_ITEMS + " ("
                + DatabaseValues.COL_ID + " INTEGER PRIMARY KEY";
        for (Field field : InventoryItem.class.getDeclaredFields()) {
            String name = field.getName();
            Class<?> type = field.getType();
            if (!Modifier.isStatic(field.getModifiers()) && Modifier.isPrivate(field.getModifiers())
                    && !Collection.class.isAssignableFrom(type) && !name.equals("id")) {
                if (type.equals(byte[].class)) {
                    sql = sql + ", " + name + " " + DatabaseValues.TYPE_BLOB;
                } else {

                    if (!(name.equals("quantity") || name.equals("amount") || name.equals("type"))) {
                        sql = sql + ", " + name + " " + DatabaseValues.TYPE_TEXT;
                    }

                }
            }
        }
        sql = sql + " )";
        return sql;
    }

    public static InventoryItem getItem(Cursor cursor) {
        InventoryItem obj = new InventoryItem();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                String name = field.getName();
                Class<?> type = field.getType();
                if (!(name.equals("quantity") || name.equals("amount") || name.equals("type"))) {
                    if (!Modifier.isStatic(field.getModifiers())
                            && !Collection.class.isAssignableFrom(type)) {
                        if (type.equals(byte[].class)) {
                            byte[] value = cursor.getBlob(cursor.getColumnIndex(name));
                            field.set(obj, value);
                        } else {
                            String value = cursor.getString(cursor.getColumnIndex(name));
                            field.set(obj, value);
                        }
                    }
                }

            }

        } catch (Exception e) {
            Log.e("InventoryItem", "Error for getItem");
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    public void insertIntoItem() throws IllegalAccessException {
        SQLiteDatabase db = UGSApplication.getDb();
        ContentValues values = this.getContentValues();
        db.insert(TABLE_ITEMS, null, values);
    }

    public ContentValues getContentValues() throws IllegalAccessException, IllegalArgumentException {
        ContentValues values = null;
        values = new ContentValues();

        for (Field field : this.getClass().getDeclaredFields()) {
            String name = field.getName();
            Class<?> type = field.getType();
            if (!(name.equals("quantity") || name.equals("amount") || name.equals("type"))) {
                if (!Modifier.isStatic(field.getModifiers()) && !Collection.class.isAssignableFrom(type)
                        && field.get(this) != null) {
                    if (type.equals(byte[].class)) {
                        byte[] obj = (byte[]) field.get(this);
                        values.put(name, obj);
                    } else {

                        String obj = (String) field.get(this);
                        values.put(name, obj);
                    }
                }
            }
        }
        return values;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public byte[] getItemImage() {
        return itemImage;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public String getRate() {
        return rate;
    }

    public String getInternalId() {
        return internalId;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public String getStatus() {
        return status;
    }

    public String getAmount() {
        return String.valueOf(Double.parseDouble(quantity) * Double.parseDouble(rate));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

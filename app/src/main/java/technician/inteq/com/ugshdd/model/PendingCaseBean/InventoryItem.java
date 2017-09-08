package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.InventoryItemController;
import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.util.UGSApplication;

/**
 * Created by Patyal on 7/25/2017.
 */

public class InventoryItem implements DatabaseValues, Parcelable {


    public static final Parcelable.Creator<InventoryItem> CREATOR = new Parcelable.Creator<InventoryItem>() {
        @Override
        public InventoryItem createFromParcel(Parcel source) {
            return new InventoryItem(source);
        }

        @Override
        public InventoryItem[] newArray(int size) {
            return new InventoryItem[size];
        }
    };

    private byte[] itemImage;
    private String status;
    private String SubCategory;
    private String category;
    private String internalId;
    private String item;
    private boolean selected = false;
    private String description;
    private String rate;
    private String quantity = "0";
    private String type = "";
    private String c_no;

    public InventoryItem() {
    }

    public InventoryItem(Parcel parcel) {
        this.status = parcel.readString();
        this.SubCategory = parcel.readString();
        this.category = parcel.readString();
        this.internalId = parcel.readString();
        this.item = parcel.readString();
        this.selected = parcel.readInt() != 0;
        this.description = parcel.readString();
        this.rate = parcel.readString();
        this.quantity = parcel.readString();
        this.type = parcel.readString();

        byte[] temp = parcel.createByteArray();
        parcel.unmarshall(temp, 0, temp.length);
        this.itemImage = temp;
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
                    && !Collection.class.isAssignableFrom(type) && !name.equals("selected")) {
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
                if (!(name.equals("quantity") || name.equals("amount") || name.equals("type") || name.equals("selected"))) {
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

    public static void getCategory(List<String> categoryList) {
        Cursor cursor = InventoryItemController.getCategory();
        if (cursor.moveToFirst()) {
            do {
                categoryList.add(cursor.getString(cursor.getColumnIndex(COL_CATEGORY)));
            } while (cursor.moveToNext());
        }
    }

    public static void getSubCategory(List<String> subCategoryList, String category) {
        Cursor cursor = InventoryItemController.getSubCategory(category);
        if (cursor.moveToFirst()) {
            do {
                subCategoryList.add(cursor.getString(cursor.getColumnIndex(COL_ITEM_SUB_CATEGORY)));
            } while (cursor.moveToNext());
        }
    }

    public static void getDataFromCursor(List<InventoryItem> itemList, String subCategory) {
        Cursor cursor = InventoryItemController.getAllItems(subCategory);
        if (cursor.moveToFirst()) {
            do {
                itemList.add(InventoryItem.getItem(cursor));
            } while (cursor.moveToNext());
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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
            if (!(name.equals("quantity") || name.equals("amount") || name.equals("type")) || name.equals("selected")) {
                if (!Modifier.isStatic(field.getModifiers()) && !Collection.class.isAssignableFrom(type)
                        && field.get(this) != null) {
                    if (type.equals(byte[].class)) {
                        byte[] obj = (byte[]) field.get(this);
                        values.put(name, obj);
                    } else if (type.equals(boolean.class)) {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        try {

            dest.writeString(status);
            dest.writeString(SubCategory);
            dest.writeString(category);
            dest.writeString(internalId);
            dest.writeString(item);
            dest.writeInt(selected ? 1 : 0);
            dest.writeString(description);
            dest.writeString(rate);
            dest.writeString(quantity);
            dest.writeString(type);
            dest.writeByteArray(itemImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

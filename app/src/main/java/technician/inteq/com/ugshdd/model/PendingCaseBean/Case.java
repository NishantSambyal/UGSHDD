package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.content.ContentValues;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

/**
 * Created by Nishant Sambyal on 28-Jul-17.
 */

public class Case {
    String outlet;
    String internalId;
    int quantity;
    String amount;
    short item_type;
    short id;
    InventoryItem inventoryItem;


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

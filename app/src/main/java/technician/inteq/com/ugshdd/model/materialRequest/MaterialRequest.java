package technician.inteq.com.ugshdd.model.materialRequest;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import technician.inteq.com.ugshdd.Controller.MaterialRequestController;
import technician.inteq.com.ugshdd.Database.DatabaseValues;
import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 29-Aug-17.
 */

public class MaterialRequest implements DatabaseValues, Parcelable {
    public static final Creator<MaterialRequest> CREATOR = new Creator<MaterialRequest>() {

        @Override
        public MaterialRequest createFromParcel(Parcel source) {
            return new MaterialRequest(source);
        }

        @Override
        public MaterialRequest[] newArray(int size) {
            return new MaterialRequest[0];
        }
    };
    private InventoryItem inventoryItem;
    private String amount;
    private String quantity;
    private String colID;

    public MaterialRequest(String amount, String quantity, String colID) {
        this.amount = amount;
        this.quantity = quantity;
        this.colID = colID;
    }

    public MaterialRequest(String colID, String amount, String quantity, InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        this.amount = amount;
        this.quantity = quantity;
        this.colID = colID;
    }


    public MaterialRequest(Parcel parcel) {
        this.inventoryItem = parcel.readParcelable(InventoryItem.class.getClassLoader());
        this.amount = parcel.readString();
        this.quantity = parcel.readString();
    }

    public static void getItemFromCursor(List<MaterialRequest> materialRequestList) {
        Cursor cursor = MaterialRequestController.getAllRequestedMaterial();
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                materialRequestList.add(new MaterialRequest(cursor.getString(0),
                        cursor.getString(cursor.getColumnIndex(COL_AMOUNT)),
                        cursor.getString(cursor.getColumnIndex(COL_QUANTITY)),
                        new InventoryItem(cursor.getBlob(cursor.getColumnIndex("itemImage")),
                                cursor.getString(cursor.getColumnIndex("status")),
                                cursor.getString(cursor.getColumnIndex("SubCategory")),
                                cursor.getString(cursor.getColumnIndex("category")),
                                cursor.getString(cursor.getColumnIndex("internalId")),
                                cursor.getString(cursor.getColumnIndex("item")),
                                cursor.getString(cursor.getColumnIndex("description")),
                                cursor.getString(cursor.getColumnIndex("rate")))));
            }
        }
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public String getAmount() {
        return amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getColID() {
        return colID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(inventoryItem, 23);
        dest.writeString(amount);
        dest.writeString(quantity);
    }
}

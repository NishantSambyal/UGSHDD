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
            return new MaterialRequest[size];
        }
    };

    private InventoryItem inventoryItem;
    private String amount;
    private String quantity;
    private String colID;
    private String transactionID;

    public MaterialRequest(String amount, String quantity, String colID) {
        this.amount = amount;
        this.quantity = quantity;
        this.colID = colID;
    }

    public MaterialRequest(String colID, String amount, String quantity, String transactionID, InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        this.amount = amount;
        this.quantity = quantity;
        this.colID = colID;
        this.transactionID = transactionID;
    }

    public MaterialRequest(String colID, String amount, String quantity, InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        this.amount = amount;
        this.quantity = quantity;
        this.colID = colID;
    }

    public MaterialRequest() {

    }

    public MaterialRequest(Parcel parcel) {
        this();
        this.inventoryItem = InventoryItem.class.cast(parcel.readParcelable(InventoryItem.class.getClassLoader()));
        this.amount = parcel.readString();
        this.quantity = parcel.readString();
    }

    public static void getTempItemFromCursor(List<MaterialRequest> materialRequestList) {
        Cursor cursor = MaterialRequestController.getAllRequestedMaterialTemp();
        if (cursor.moveToFirst()) {
            do {
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
            } while (cursor.moveToNext());
        }
    }

    public static void getMaterialRequestTransactions(List<MaterialRequestTransaction> list) {
        Cursor cursor = MaterialRequestController.getMaterialRequestTransactions();
        if (cursor.moveToFirst()) {
            do {
                list.add(new MaterialRequestTransaction(cursor.getString(cursor.getColumnIndex(COL_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_TRANSACTION_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_DATE_TIME))));
            } while (cursor.moveToNext());
        }
    }

    public static List<MaterialRequestTransaction> getMaterialRequestTransaction(List<MaterialRequestTransaction> list, String transactionID) {
        Cursor cursor = MaterialRequestController.getMaterialRequestTransaction(transactionID);
        if (cursor.moveToFirst()) {
            do {
                list.add(new MaterialRequestTransaction(cursor.getString(cursor.getColumnIndex(COL_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_TRANSACTION_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_DATE_TIME))));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public static List<MaterialRequest> getMaterialRequest(List<MaterialRequest> list, String transactionID) {
        Cursor cursor = MaterialRequestController.getMaterialRequest(transactionID);
        if (cursor.moveToFirst()) {
            do {
                InventoryItem item = new InventoryItem();
                item.setInternalId(cursor.getString(cursor.getColumnIndex(COL_INTERNAL_ID)));
                list.add(new MaterialRequest(cursor.getString(cursor.getColumnIndex(COL_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_AMOUNT)),
                        cursor.getString(cursor.getColumnIndex(COL_QUANTITY)),
                        item));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public static void getItemFromCursor(List<MaterialRequest> materialRequestList, String transactionID) {
        Cursor cursor = MaterialRequestController.getAllRequestedMaterial(transactionID);
        if (cursor.moveToFirst()) {
            do {
                materialRequestList.add(new MaterialRequest(cursor.getString(0),
                        cursor.getString(cursor.getColumnIndex(COL_AMOUNT)),
                        cursor.getString(cursor.getColumnIndex(COL_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(COL_TRANSACTION_ID)),
                        new InventoryItem(cursor.getBlob(cursor.getColumnIndex("itemImage")),
                                cursor.getString(cursor.getColumnIndex("status")),
                                cursor.getString(cursor.getColumnIndex("SubCategory")),
                                cursor.getString(cursor.getColumnIndex("category")),
                                cursor.getString(cursor.getColumnIndex("internalId")),
                                cursor.getString(cursor.getColumnIndex("item")),
                                cursor.getString(cursor.getColumnIndex("description")),
                                cursor.getString(cursor.getColumnIndex("rate")))));
            } while (cursor.moveToNext());
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

    public String getTransactionID() {
        return transactionID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(inventoryItem, flags);
        dest.writeString(amount);
        dest.writeString(quantity);
    }
}

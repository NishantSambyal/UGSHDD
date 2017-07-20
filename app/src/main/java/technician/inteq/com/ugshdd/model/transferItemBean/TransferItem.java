package technician.inteq.com.ugshdd.model.transferItemBean;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Patyal on 7/15/2017.
 */

public class TransferItem implements Parent<TransferItemDetail> {

    String trsnsferItem;
    List<TransferItemDetail> transferItems;

    public TransferItem(String trsnsferItem, List<TransferItemDetail> transferItemDetails) {
        this.trsnsferItem = trsnsferItem;
        this.transferItems = transferItemDetails;
    }

    @Override
    public List<TransferItemDetail> getChildList() {
        return transferItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getTransferItemName() {
        return trsnsferItem;
    }
}

package technician.inteq.com.ugshdd.model.AcceptedItemBean;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Patyal on 7/17/2017.
 */

public class AcceptedItem implements Parent<AcceptedItemDetails> {

    String acceptedItem;
    List<AcceptedItemDetails> acceptedItemDetails;

    public AcceptedItem(String acceptedItem, List<AcceptedItemDetails> acceptedItemDetails) {
        this.acceptedItem = acceptedItem;
        this.acceptedItemDetails = acceptedItemDetails;
    }

    @Override
    public List<AcceptedItemDetails> getChildList() {
        return acceptedItemDetails;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getAcceptedItemName() {
        return acceptedItem;
    }
}

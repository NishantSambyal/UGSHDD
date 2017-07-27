package technician.inteq.com.ugshdd.model.PendingCaseBean;

/**
 * Created by Patyal on 7/25/2017.
 */

public class InventoryItem {
    private int itemImage;
    private String status;
    private String SubCategory;
    private String category;
    private String internalId;
    private String item;
    private String description;
    private String quentity;
    private String rate;
    private String amount;

    public InventoryItem() {
    }


    public InventoryItem(int itemImage, String status, String subCategory, String category, String internalId, String item, String description, String quentity, String rate) {
        this.itemImage = itemImage;
        this.status = status;
        SubCategory = subCategory;
        this.category = category;
        this.internalId = internalId;
        this.item = item;
        this.description = description;
        this.quentity = quentity;
        this.rate = rate;
    }

    public InventoryItem(String status, String subCategory, String category, String internalId, String item, String description, String quentity, String rate, String amount) {
        this.status = status;
        SubCategory = subCategory;
        this.category = category;
        this.internalId = internalId;
        this.item = item;
        this.description = description;
        this.quentity = quentity;
        this.rate = rate;
        this.amount = amount;
    }

    public int getItemImage() {
        return itemImage;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public String getQuentity() {
        return quentity;
    }

    public String getRate() {
        return rate;
    }

    public String getAmount() {
        return amount = String.valueOf(Integer.parseInt(rate) * Integer.parseInt(quentity));
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
}

package technician.inteq.com.ugshdd.model.materialRequest;

/**
 * Created by Nishant Sambyal on 02-Sep-17.
 */

public class MaterialRequestTransaction {

    private String colID;
    private String transactionID;
    private String dateTime;

    public MaterialRequestTransaction(String colID, String transactionID, String dateTime) {
        this.colID = colID;
        this.transactionID = transactionID;
        this.dateTime = dateTime;
    }

    public String getColID() {
        return colID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getDateTime() {
        return dateTime;
    }

}

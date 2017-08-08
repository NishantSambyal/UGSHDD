package technician.inteq.com.ugshdd.model.contact;

/**
 * Created by Nishant Sambyal on 08-Aug-17.
 */

public class AddContact {

    String id;
    String number;
    String isActive;

    public AddContact(String id, String number, String isActive) {
        this.id = id;
        this.number = number;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getIsActive() {
        return isActive;
    }
}

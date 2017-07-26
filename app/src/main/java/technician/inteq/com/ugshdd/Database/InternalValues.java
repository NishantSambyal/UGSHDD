package technician.inteq.com.ugshdd.Database;

/**
 * Created by Nishant Sambyal on 24-Jul-17.
 */

public interface InternalValues {
    enum Acknowledge {
        ACKNOWLEDGE(1), UNACKNOWLEDGE(0);
        int isAcknowledge;

        Acknowledge(int i) {
            this.isAcknowledge = i;
        }

        @Override
        public String toString() {
            return "" + isAcknowledge;
        }
    }
}

package technician.inteq.com.ugshdd.Database;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public interface DatabaseValues {
    //Data Types
    String TYPE_TEXT = " TEXT";
    String TYPE_INTEGER = " INTEGER";
    String TYPE_COMMA_SEP = ", ";

    //Tables Names
    String TABLE_TASKS = "tasks";

    //Column names
    String COL_ID = "id";
    String COL_OUTLET = "outlet";
    String COL_JOB_NO = "job_no";
    String COL_UNIT_NO = "unit_no";
    String COL_ACKNOWLEDGE = "isAcknowledge";

    //Queries to execute
    String SQL_CREATE_TABLE_TASKS = "CREATE TABLE " + TABLE_TASKS + "( " + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_JOB_NO + TYPE_TEXT + TYPE_COMMA_SEP + COL_UNIT_NO + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ACKNOWLEDGE + TYPE_TEXT + ")";

}

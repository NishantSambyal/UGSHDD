package technician.inteq.com.ugshdd.Database;

import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public interface DatabaseValues {
    //Data Types
    String TYPE_TEXT = " TEXT";
    String TYPE_INTEGER = " INTEGER";
    String TYPE_BLOB = " blob";
    String TYPE_COMMA_SEP = ", ";

    //Tables Names
    String TABLE_PENDING_TASKS = "pending_tasks";
    String TABLE_COMPLETED_TASKS = "completed_tasks";
    String TABLE_ONGOING_TASKS = "ongoing_tasks";
    String TABLE_TASKS = "tasks";
    String TABLE_ITEMS = "items";

    //Column names
    String COL_ID = "id";
    String COL_OUTLET = "outlet";
    String COL_JOB_NO = "job_no";
    String COL_UNIT_NO = "unit_no";
    String COL_ACKNOWLEDGE = "isAcknowledge";
    String COL_INTERNAL_ID = "internalId";
    String COL_QUANTITY = "quantity";
    String COL_AMOUNT = "amount";
    String COL_ITEM_TYPE = "item_type";
    String COL_TASK_1 = "Replaced Burner";
    String COL_TASK_2 = "Repaired Gas Valve";
    String COL_TASK_3 = "Cleaned the Stove";
    String COL_TASK_4 = "Replace Pipes";

    //Queries to execute
    String SQL_CREATE_TABLE_PENDING_TASKS = "CREATE TABLE " + TABLE_PENDING_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_JOB_NO + TYPE_TEXT + TYPE_COMMA_SEP + COL_UNIT_NO + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ACKNOWLEDGE + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_COMPLETED_TASKS = "CREATE TABLE " + TABLE_COMPLETED_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_INTERNAL_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_QUANTITY + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ITEM_TYPE + TYPE_TEXT + TYPE_COMMA_SEP + COL_AMOUNT + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_ONGOING_TASKS = "CREATE TABLE " + TABLE_ONGOING_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_INTERNAL_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_QUANTITY + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ITEM_TYPE + TYPE_TEXT + TYPE_COMMA_SEP + COL_AMOUNT + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_TASK = "CREATE TABLE " + TABLE_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_TASK_1 + TYPE_TEXT + TYPE_COMMA_SEP + COL_TASK_2 + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_TASK_3 + TYPE_TEXT + TYPE_COMMA_SEP + COL_TASK_4 + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_ITEM = InventoryItem.getSql();

}

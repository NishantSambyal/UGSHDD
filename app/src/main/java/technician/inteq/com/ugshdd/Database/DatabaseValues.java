package technician.inteq.com.ugshdd.Database;

import technician.inteq.com.ugshdd.model.PendingCaseBean.InventoryItem;

/**
 * Created by Nishant Sambyal on 12-Jul-17.
 */

public interface DatabaseValues {
    //Data Types
    String TYPE_TEXT = " TEXT ";
    String TYPE_INTEGER = " INTEGER";
    String TYPE_BLOB = " blob";
    String TYPE_UNIQUE = " UNIQUE";
    String TYPE_COMMA_SEP = ", ";

    //Tables Names
    String TABLE_CASES = "tasks";
    String TABLE_COMPLETED_TASKS = "completed_tasks";
    String TABLE_ONGOING_TASKS = "ongoing_tasks";
    String TABLE_PERFORMED_TASKS_LIST = "performed_tasks_list";
    String TABLE_PERFORMED_TASKS = "performed_tasks";
    String TABLE_PERFORMED_TASKS_TEMP = "performed_tasks_temp";
    String TABLE_ITEMS = "items";
    String TABLE_CONTACT = "contact";
    String TABLE_MATERIAL_REQUEST_TEMP = "material_request_temp";
    String TABLE_MATERIAL_REQUEST = "material_request";
    String TABLE_MATERIAL_REQUEST_TRANSACTIONS = "material_request_transactions";

    //Column names
    String COL_ID = "_id";
    String COL_OUTLET = "outlet";
    String COL_JOB_NO = "job_no";
    String COL_UNIT_NO = "unit_no";
    String COL_ACKNOWLEDGE = "isAcknowledge";
    String COL_INTERNAL_ID = "internalId";
    String COL_QUANTITY = "quantity";
    String COL_AMOUNT = "amount";
    String COL_ITEM_TYPE = "item_type";
    String COL_IS_ASSIGNED = "is_assigned";
    String COL_IS_COMPLETED = "is_completed";
    String COL_SIGNATURE = "signature";
    String COL_TASKS = "tasks";
    String COL_CUSTOMER_NAME = "customer_name";
    String COL_IS_ACTIVE = "isActive";
    String COL_TRANSACTION_ID = "transaction_id";
    String COL_DATE_TIME = "date_time";
    String COL_ITEM_NAME = "item";
    String COL_CATEGORY = "category";
    String COL_ITEM_SUB_CATEGORY = "SubCategory";
    String COL_DESCRIPTION = "description";
    String COL_C_NO = "c_no";
    String COL_RATE = "rate";
    String COL_IMAGE_LINK = "image_link";
    String COL_STATUS = "upload_status";

    //Queries to execute

    String SQL_CREATE_TABLE_PENDING_TASKS = "CREATE TABLE " + TABLE_CASES + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_JOB_NO + TYPE_TEXT + TYPE_COMMA_SEP + COL_UNIT_NO + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ACKNOWLEDGE + TYPE_TEXT + TYPE_COMMA_SEP + COL_IS_ASSIGNED + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_IS_COMPLETED + TYPE_TEXT + TYPE_COMMA_SEP + COL_CUSTOMER_NAME + TYPE_TEXT + TYPE_COMMA_SEP + COL_SIGNATURE + TYPE_BLOB
            + TYPE_COMMA_SEP + COL_STATUS + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_COMPLETED_TASKS = "CREATE TABLE " + TABLE_COMPLETED_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_INTERNAL_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_QUANTITY + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ITEM_TYPE + TYPE_TEXT + TYPE_COMMA_SEP + COL_AMOUNT + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_ONGOING_TASKS = "CREATE TABLE " + TABLE_ONGOING_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_INTERNAL_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_QUANTITY + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_ITEM_TYPE + TYPE_TEXT + TYPE_COMMA_SEP + COL_AMOUNT + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_TASK = "CREATE TABLE " + TABLE_PERFORMED_TASKS_LIST + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_TASKS + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_TASKS = "CREATE TABLE " + TABLE_PERFORMED_TASKS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_TASKS + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_TASKS_TEMP = "CREATE TABLE " + TABLE_PERFORMED_TASKS_TEMP + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_OUTLET + TYPE_TEXT + TYPE_COMMA_SEP + COL_TASKS + TYPE_TEXT + TYPE_UNIQUE + ")";

    String SQL_CREATE_TABLE_ITEM = InventoryItem.getSql();

    String SQL_CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_CONTACT + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + TABLE_CONTACT + TYPE_TEXT + TYPE_UNIQUE + TYPE_COMMA_SEP + COL_IS_ACTIVE + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_MATERIAL_REQUEST = "CREATE TABLE " + TABLE_MATERIAL_REQUEST + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_TRANSACTION_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_INTERNAL_ID + TYPE_TEXT + TYPE_COMMA_SEP
            + COL_QUANTITY + TYPE_TEXT + TYPE_COMMA_SEP + COL_AMOUNT + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_MATERIAL_REQUEST_TEMP = "CREATE TABLE " + TABLE_MATERIAL_REQUEST_TEMP + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_INTERNAL_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_QUANTITY + TYPE_TEXT
            + TYPE_COMMA_SEP + COL_AMOUNT + TYPE_TEXT + ")";

    String SQL_CREATE_TABLE_MATERIAL_REQUEST_TRANSACTION = "CREATE TABLE " + TABLE_MATERIAL_REQUEST_TRANSACTIONS + "(" + COL_ID + TYPE_INTEGER + " PRIMARY KEY " + TYPE_COMMA_SEP
            + COL_TRANSACTION_ID + TYPE_TEXT + TYPE_COMMA_SEP + COL_DATE_TIME + TYPE_TEXT + ")";
}

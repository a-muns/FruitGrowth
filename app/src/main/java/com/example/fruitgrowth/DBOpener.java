package com.example.fruitgrowth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpener extends SQLiteOpenHelper {

    /**
     * DB Constants
     */
    protected final static String DATABASE_NAME = "FruitGrowth_DB";
    protected final static int VERSION_NUM = 4;

    // Variety Table
    public final static String TABLE_VARIETY = "Variety";
    public final static String VARIETY_ID = "VarietyID";
    public final static String VARIETY_NAME = "Name";
    public final static String VARIETY_TREECOUNT = "TreeCount";
    public final static String VARIETY_TREETYPE = "TreeType";
    public final static String VARIETY_VARIETYID_FK = "Variety_VarietyID";

    // Tree Table
    public final static String TABLE_TREE = "Tree";
    public final static String TREE_ID = "TreeID";
    public final static String TREE_TREENUMBER = "TreeNumber";
    public final static String TREE_TREEID_FK = "Tree_TreeID";

    // Tree_Date_Int Table
    public final static String TABLE_TREEDATEINT = "TreeDateInt";
    public final static String TREEDATEINT_ID = "TreeDateIntID";
    public final static String TREEDATEINT_ID_FK = "TreeDateInt_TreeDateIntID";

    // Date Table
    public final static String TABLE_DATE = "Date";
    public final static String DATE_DATE = "Date_Date";

    // Fruitlet Table
    public final static String TABLE_FRUITLET = "Fruitlet";
    public final static String FRUITLET_ID = "FruitletID";
    public final static String FRUITLET_CLUSTERNUMBER = "ClusterNumber";
    public final static String FRUITLET_FRUITLETNUMBER = "FruitletNumber";
    public final static String FRUITLET_SIZE = "Size";

    // Cluster Table
    public final static String TABLE_CLUSTER = "Cluster";
    public final static String CLUSTER_ID = "ClusterID";
    public final static String CLUSTER_REGION = "Region";
    public final static String CLUSTER_COUNT = "Count";

    /**
     * Constructor
     * @param context
     */
    public DBOpener (Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    /**
     * Create tables and their columns upon DB creation
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_VARIETY + " ( " +
                VARIETY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VARIETY_NAME + " TEXT, " +
                VARIETY_TREECOUNT + " INTEGER, " +
                VARIETY_TREETYPE + " TEXT);");

        // TODO: Date should be refactored, as one tree can have multiple dates
        db.execSQL("CREATE TABLE " + TABLE_TREE + " ( " +
                TREE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VARIETY_VARIETYID_FK + " INTEGER, " +
                " FOREIGN KEY ( " + VARIETY_VARIETYID_FK + " ) " +
                " REFERENCES " + TABLE_VARIETY + " ( " + VARIETY_ID + " ));");

        db.execSQL("CREATE TABLE " + TABLE_DATE + " ( " +
                DATE_DATE + " DATE PRIMARY KEY AUTOINCREMENT, " +
                VARIETY_VARIETYID_FK + " INTEGER);");

        db.execSQL("CREATE TABLE " + TABLE_TREEDATEINT + " ( " +
                TREEDATEINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TREE_TREEID_FK + " INTEGER, " +
                // VarietyID FK here attached to the TreeID
                DATE_DATE + " DATE, " +
                TREE_TREEID_FK + " INTEGER, " +
                " FOREIGN KEY ( " + TREE_TREEID_FK + " ) " +
                " REFERENCES " + TABLE_TREE + " ( " + TREE_TREEID_FK + " ), " +
                " FOREIGN KEY ( " + DATE_DATE + " ) " +
                " REFERENCES " + TABLE_DATE + " ( " + DATE_DATE + " ));");

        db.execSQL("CREATE TABLE " + TABLE_FRUITLET + " ( " +
                FRUITLET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FRUITLET_CLUSTERNUMBER + " INTEGER, " +
                FRUITLET_FRUITLETNUMBER + " INTEGER, " +
                FRUITLET_SIZE + " REAL, " +
                TREEDATEINT_ID + " INTEGER, " +
                " FOREIGN KEY ( " + TREEDATEINT_ID + " ) " +
                " REFERENCES " + TABLE_TREEDATEINT + " ( " + TREEDATEINT_ID_FK + " ));");

        db.execSQL("CREATE TABLE " + TABLE_CLUSTER + " ( " +
                CLUSTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLUSTER_REGION + " TEXT, " +
                CLUSTER_COUNT + " INTEGER, " +
                TREEDATEINT_ID + " INTEGER, " +
                " FOREIGN KEY ( " + TREEDATEINT_ID + " ) " +
                " REFERENCES " + TABLE_TREEDATEINT + " ( " + TREEDATEINT_ID_FK + " ));");
    }

    /**
     * Drop and create new DB on upgrade
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: ** IMPORTANT ** Migrate current data into new database (So users don't lose their data)
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VARIETY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRUITLET);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUSTER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREEDATEINT);
            // Create new db
            onCreate(db);
        }
    }

}
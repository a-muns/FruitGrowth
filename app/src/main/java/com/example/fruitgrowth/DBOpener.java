package com.example.fruitgrowth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpener extends SQLiteOpenHelper {

    /**
     * DB Constants
     */
    protected final static String DATABASE_NAME = "FruitGrowth_DB";
    protected final static int VERSION_NUM = 3;

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
    public final static String TREE_DATE = "Date";
    public final static String TREE_TREEID_FK = "Tree_TreeID";
    public final static String TREE_DATE_FK = "Tree_Date";

    // Fruitlet Table
    public final static String TABLE_FRUITLET = "Fruitlet";
    public final static String FRUITLET_ID = "FruitletID";
    public final static String FRUITLET_TREENUMBER = "TreeNumber";
    public final static String FRUITLET_CLUSTERNUMBER = "ClusterNumber";
    public final static String FRUITLET_FRUITLETNUMBER = "FruitletNumber";
    public final static String FRUITLET_SIZE = "Size";

    // Cluster Table
    public final static String TABLE_CLUSTER = "Cluster";
    public final static String CLUSTER_ID = "ClusterID";
    public final static String CLUSTER_TREENUMBER = "TreeNumber";
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
                TREE_DATE + " DATE, " +
                VARIETY_VARIETYID_FK + " INTEGER, " +
                " FOREIGN KEY ( " + VARIETY_VARIETYID_FK + " ) " +
                " REFERENCES " + TABLE_VARIETY + " ( " + VARIETY_ID + " ));");

        db.execSQL("CREATE TABLE " + TABLE_FRUITLET + " ( " +
                FRUITLET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FRUITLET_TREENUMBER + " INTEGER, " +
                FRUITLET_CLUSTERNUMBER + " INTEGER, " +
                FRUITLET_FRUITLETNUMBER + " INTEGER, " +
                FRUITLET_SIZE + " REAL, " +
                TREE_TREEID_FK + " INTEGER, " +
                TREE_DATE_FK + " DATE, " +
                " FOREIGN KEY ( " + TREE_TREEID_FK + " ) " +
                " REFERENCES " + TABLE_TREE + " ( " + TREE_TREEID_FK + " ), " +
                " FOREIGN KEY ( " + TREE_DATE_FK + " ) " +
                " REFERENCES " + TABLE_TREE + " ( " + TREE_DATE_FK + " ));");

        db.execSQL("CREATE TABLE " + TABLE_CLUSTER + " ( " +
                CLUSTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLUSTER_TREENUMBER + " INTEGER, " +
                CLUSTER_REGION + " TEXT, " +
                CLUSTER_COUNT + " INTEGER, " +
                TREE_TREEID_FK + " INTEGER, " +
                TREE_DATE_FK + " DATE, " +
                " FOREIGN KEY ( " + TREE_TREEID_FK + " ) " +
                " REFERENCES " + TABLE_TREE + " ( " + TREE_TREEID_FK + " ), " +
                " FOREIGN KEY ( " + TREE_DATE_FK + " ) " +
                " REFERENCES " + TABLE_TREE + " ( " + TREE_DATE_FK + " ));");
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
            // Create new db
            onCreate(db);
        }
    }

}
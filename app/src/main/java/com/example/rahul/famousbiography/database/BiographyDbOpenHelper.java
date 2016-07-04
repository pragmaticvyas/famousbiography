package com.example.rahul.famousbiography.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rahul on 03/03/2016.
 */
 public class BiographyDbOpenHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "BIOGRAPHY";

    private static final String DATABASE_NAME = "bio.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_BIO = "celebrities";
    public static final String COLUMN_ID = "famousid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DETAIL = "details";
    public static final String COLUMN_IMAGE = "image";



    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_BIO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DETAIL + " TEXT, " +
                    COLUMN_IMAGE + " TEXT " +
                    ")";

    public BiographyDbOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATE);
        onCreate(db);
    }
}

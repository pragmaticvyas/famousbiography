package com.example.rahul.famousbiography.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rahul.famousbiography.model.FamousPeople;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 08/03/2016.
 */
public class BiographyDataSource extends BiographyDbOpenHelper {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private static  final  String[] allColumn={

            BiographyDbOpenHelper.COLUMN_ID,
            BiographyDbOpenHelper.COLUMN_NAME,
            BiographyDbOpenHelper.COLUMN_IMAGE,
            BiographyDbOpenHelper.COLUMN_DETAIL
    };

    public static final String LOGTAG = "BIOGRAPHYTAG";

    public BiographyDataSource(Context context) {
        super(context);
        dbhelper=new BiographyDbOpenHelper(context);

    }
    public void open() {
        Log.i(LOGTAG, "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    public FamousPeople create(FamousPeople famousPeople){
        ContentValues contentValues=new ContentValues();
        contentValues.put(BiographyDbOpenHelper.COLUMN_NAME,famousPeople.getName());
        contentValues.put(BiographyDbOpenHelper.COLUMN_IMAGE,famousPeople.getPhoto());
        contentValues.put(BiographyDbOpenHelper.COLUMN_DETAIL, famousPeople.getDetails());
        long insertId=database.insert(BiographyDbOpenHelper.TABLE_BIO,null,contentValues);
        famousPeople.setId(insertId);
        return famousPeople;

    }
    public List<FamousPeople> findRecords(){
        List<FamousPeople> famousPeoples=new ArrayList<>();
        Cursor cursor=database
                .query(BiographyDbOpenHelper.TABLE_BIO,allColumn,null,null,null,null,null);

        Log.i("Coulmn","returnd"+cursor.getCount()+"total rows");

        if(cursor.getCount()>0){
            while (cursor.moveToNext())
            {

                FamousPeople famousPeople=new FamousPeople();
                famousPeople.setId(cursor.getLong(cursor.getColumnIndex(BiographyDbOpenHelper.COLUMN_ID)));
                famousPeople.setName(cursor.getString(cursor.getColumnIndex(BiographyDbOpenHelper.COLUMN_NAME)));
                famousPeople.setPhoto(cursor.getString(cursor.getColumnIndex(BiographyDbOpenHelper.COLUMN_IMAGE)));
                famousPeople.setDetails(cursor.getString(cursor.getColumnIndex(BiographyDbOpenHelper.COLUMN_DETAIL)));
                famousPeoples.add(famousPeople);

            }
        }


return famousPeoples;

    }

}

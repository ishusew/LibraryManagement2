package ishara.atigapaha.librarymanagement.Publisher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class publishDbHelper extends SQLiteOpenHelper {
    public publishDbHelper(@Nullable Context context) {
        super(context, "PUBLISHERDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS publisher(NAME VARCHAR(20) PRIMARY KEY UNIQUE  ,ADDRESS VARCHAR(30),PHONE VARCHAR(10))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists publisher");

    }

    public Boolean insertuserdata(String NAME, String ADDRESS, String PHONE)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", NAME);
        contentValues.put("address", ADDRESS);
        contentValues.put("phone", PHONE);
        long result=DB.insert("publisher", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String NAME, String ADDRESS, String PHONE) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", ADDRESS);
        contentValues.put("phone", PHONE);
        Cursor cursor = DB.rawQuery("Select * from publisher where name = ?", new String[]{NAME});
        if (cursor.getCount() > 0) {
            long result = DB.update("publisher", contentValues, "name=?", new String[]{NAME});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String NAME)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from publisher where NAME = ?", new String[]{NAME});
        if (cursor.getCount() > 0) {
            long result = DB.delete("publisher", "NAME=?", new String[]{NAME});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from publisher", null);
        return cursor;

    }

}

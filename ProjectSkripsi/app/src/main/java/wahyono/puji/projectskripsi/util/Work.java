package wahyono.puji.projectskripsi.util;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import wahyono.puji.projectskripsi.MainActivity;
import wahyono.puji.projectskripsi.konfig;

/**
 * Created by ai on 11/02/2018.
 */

public class Work {
    public static String getUrl(Activity a) {
        DBHelper d=new DBHelper(a);
        SQLiteDatabase db=d.getReadableDatabase();
        String s;
        Cursor c=db.query("konfig",new String[]{"alamat"},null,null,null,null,null);
        c.moveToFirst();
        s=c.getColumnName(0);
        c.close();
        d.close();
        return s;
    }

    public static int getMeja(Activity a) {
        DBHelper d=new DBHelper(a);
        SQLiteDatabase db=d.getReadableDatabase();
        int i;
        Cursor c=db.query("konfig",new String[]{"meja"},null,null,null,null,null);
        c.moveToFirst();
        i=c.getInt(0);
        c.close();
        d.close();
        return i;
    }

    public static boolean checkDB(Activity a) {
        DBHelper d=new DBHelper(a);
        SQLiteDatabase db=d.getReadableDatabase();
        boolean b;
        Cursor c=db.query("konfig",new String[]{"meja"},null,null,null,null,null);
        b=!c.moveToFirst();
        c.close();
        d.close();
        return b;
    }

    public static void save(Activity a, String alamat, int nomor) {
        DBHelper d=new DBHelper(a);
        SQLiteDatabase db=d.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("alamat",alamat);
        cv.put("meja",nomor);
        db.insert("konfig",null,cv);
        d.close();
    }
}

package wahyono.puji.newayam.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnHelper extends SQLiteOpenHelper{
    public ConnHelper(Context context) {
        super(context, "puji", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists konfig(" +
                "meja int not null," +
                "akses text not null)");
        db.execSQL("create table if not exists nota(" +
                "n text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table if not exists konfig(" +
                "meja int not null," +
                "akses text not null)");
        db.execSQL("create table if not exists nota(" +
                "n text not null)");
    }
}

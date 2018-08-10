package wahyono.puji.newayam.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Work {
    public static boolean sudahKonfig(Context ctx) {
        ConnHelper ch = new ConnHelper(ctx);
        SQLiteDatabase db = ch.getReadableDatabase();
        Cursor c = db.query("konfig",null,null,null,null,
                null,null);
        boolean b = c.moveToFirst();
        c.close();
        ch.close();
        return b;
    }

    public static int getMeja(Context ctx) {
        ConnHelper ch = new ConnHelper(ctx);
        int m = 0;
        SQLiteDatabase db = ch.getReadableDatabase();
        Cursor c = db.query("konfig", new String[]{"meja"}, null, null,
                null, null, null);
        if(c.moveToNext())m = c.getInt(c.getColumnIndex("meja"));
        c.close();
        db.close();
        ch.close();
        return m;
    }

    public static String getURL(Context ctx) {
        String s = "";
        ConnHelper ch = new ConnHelper(ctx);
        SQLiteDatabase db = ch.getReadableDatabase();
        Cursor c = db.query("konfig", new String[]{"akses"}, null, null,
                null, null, null);
        if(c.moveToFirst())s = c.getString(c.getColumnIndex("akses"));
        c.close();
        db.close();
        ch.close();
        return s;
    }

    public static void setData(Context ctx, String akses, int meja) {
        ConnHelper ch = new ConnHelper(ctx);
        SQLiteDatabase db = ch.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("akses", akses);
        cv.put("meja", meja);
        db.insert("konfig", null, cv);
        db.close();
        ch.close();
    }

    public static void setNota(Context ctx, String nota) {
        ConnHelper ch = new ConnHelper(ctx);
        SQLiteDatabase db = ch.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("n", nota);
        db.insert("nota", null, cv);
        db.close();
        ch.close();
    }

    public static String getNota(Context ctx) {
        String s = "";
        ConnHelper ch = new ConnHelper(ctx);
        SQLiteDatabase db = ch.getReadableDatabase();
        Cursor c = db.query("nota", new String[]{"n"}, null,null,null,
                null,null);
        if(c.moveToFirst())s = c.getString(c.getColumnIndex("n"));
        c.close();
        db.close();
        ch.close();
        return s;
    }

    public static void clearNota(Context ctx) {
        ConnHelper ch = new ConnHelper(ctx);
        SQLiteDatabase db = ch.getWritableDatabase();
        db.delete("nota", null, null);
        db.close();
        ch.close();
    }

    public static String dataAccess(String s) throws IOException {
        URL url = new URL(s);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String buff;
        while ((buff = r.readLine()) != null)
            sb.append(buff);
        String data = sb.toString();
        conn.disconnect();
        return data;
    }
}

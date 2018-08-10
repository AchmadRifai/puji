package wahyono.puji.newayam.server;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Objects;

import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class MasihAccess extends AsyncTask<Void, Void, Boolean> {
    private Activity a;
    private Runnable lagi,tidak;

    public MasihAccess(Activity a, Runnable lagi, Runnable tidak) {
        this.a = a;
        this.lagi = lagi;
        this.tidak = tidak;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean b = false;
        String nota = Work.getNota(a); try {
            String data = Work.dataAccess(Work.getURL(a) + Access.MASIH_PESAN + nota);
            JsonObject o = new JsonParser().parse(data).getAsJsonObject();
            String pesan = o.get("pesan").getAsString();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                b = Objects.equals(pesan, "Dapat");
            }
        } catch (IOException e) {
            Log.i("IOException", e.getMessage());
        } return b;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        if(b)lagi.run();
        else tidak.run();
    }
}

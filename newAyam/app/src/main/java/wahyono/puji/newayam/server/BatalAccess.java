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

public class BatalAccess extends AsyncTask<Void, Void, Boolean> {
    private Activity a;
    private Runnable r;

    public BatalAccess(Activity a, Runnable r) {
        this.a = a;
        this.r = r;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean b = false;
        String nota = Work.getNota(a); try {
            String data = Work.dataAccess(Work.getURL(a) + Access.BATAL_PESAN + nota);
            JsonObject o = new JsonParser().parse(data).getAsJsonObject();
            String pesan = o.get("pesan").getAsString();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                b = Objects.equals(pesan, "Sukses");
            }
        } catch (IOException e) {
            Log.i("IOException", e.getMessage());
        } return b;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean) r.run();
    }
}

package wahyono.puji.newayam.server;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class PesanAccess extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private Activity a;
    private Runnable dapat, gagal, fail;

    public PesanAccess(Activity a, Runnable dapat, Runnable gagal, Runnable fail) {
        this.a = a;
        this.dapat = dapat;
        this.gagal = gagal;
        this.fail =  fail;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s = "", nm = strings[0], hp = strings[1];
        try {
            String data = Work.dataAccess(Work.getURL(a) + Access.PESANAN_BARU +
                    Work.getMeja(a) + "/" + nm + "/" + hp);
            JsonObject o = new com.google.gson.JsonParser().parse(data).getAsJsonObject();
            s = o.get("nota").getAsString();
        } catch (IOException e) {
            Log.i("IOException", e.getMessage());
        } return s;
    }

    @Override
    protected void onPostExecute(String s) {
        if(!s.isEmpty()) {
            if(!s.equals("Error")) {
                Work.setNota(a, s);
                dapat.run();
            } else gagal.run();
        } else fail.run();
    }
}

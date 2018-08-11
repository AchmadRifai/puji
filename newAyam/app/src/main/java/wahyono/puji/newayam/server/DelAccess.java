package wahyono.puji.newayam.server;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class DelAccess extends AsyncTask<String, Void, Boolean> {
    private Activity a;
    private Runnable ya, tidak;

    public DelAccess(Activity a, Runnable ya, Runnable tidak) {
        this.a = a;
        this.ya = ya;
        this.tidak = tidak;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        boolean b = false;
        String nota = Work.getNota(a), menu = strings[0]; try {
            String data = Work.dataAccess(Work.getURL(a) + Access.DEL_ITEM + nota + "/" + menu);
            JsonObject o = new JsonParser().parse(data).getAsJsonObject();
            String pesan = o.get("pesan").getAsString();
            b = "Sukses".equals(pesan);
        } catch (IOException e) {
            Log.i("IOException",e.getMessage());
        } return b;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean) ya.run();
        else tidak.run();
    }
}

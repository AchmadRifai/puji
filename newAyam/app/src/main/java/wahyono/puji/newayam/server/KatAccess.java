package wahyono.puji.newayam.server;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import wahyono.puji.newayam.adapter.KatAdapter;
import wahyono.puji.newayam.beans.Kat;
import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class KatAccess extends AsyncTask<Void, Void, List<Kat>> {
    private Activity a;
    private RecyclerView rv;

    public KatAccess(Activity a, RecyclerView rv) {
        this.a = a;
        this.rv = rv;
    }

    @Override
    protected List<Kat> doInBackground(Void... voids) {
        List<Kat>lk = new LinkedList<>(); try {
            String data = Work.dataAccess(Work.getURL(a) + Access.LIST_KAT);
            JsonArray a = new com.google.gson.JsonParser().parse(data).getAsJsonArray();
            for(int x = 0; x < a.size(); x ++) {
                JsonObject o = a.get(x).getAsJsonObject();
                Kat k = new Kat();
                k.setGbr(o.get("gbr").getAsString());
                k.setKode(o.get("kode").getAsInt());
                k.setNama(o.get("nama").getAsString());
                lk.add(k);
            }
        } catch (IOException e) {
            Log.i("IOException",e.getMessage());
        } return lk;
    }

    @Override
    protected void onPostExecute(List<Kat> lk) {
        GridLayoutManager glm = new GridLayoutManager(a, 2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        KatAdapter ka = new KatAdapter(a, lk);
        rv.setAdapter(ka);
        ka.notifyDataSetChanged();
    }
}

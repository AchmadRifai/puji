package wahyono.puji.newayam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

import wahyono.puji.newayam.adapter.KatAdapter;
import wahyono.puji.newayam.beans.Kat;
import wahyono.puji.newayam.server.BatalAccess;
import wahyono.puji.newayam.server.KatAccess;
import wahyono.puji.newayam.server.MasihAccess;
import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class MainActivity extends AppCompatActivity {
    public boolean mlaku;
    private RecyclerView lstKat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openWar();
        muat();
    }

    private void muat() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mlaku)reload();
            }
        }).start();
    }

    private void reload() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                amot();
            }
        }); try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.cart:
                startActivity(new Intent(this, Cart.class));
                finish();
                return true;
            case R.id.batal:
                batalkanPesanan();
                return true;
        } return super.onOptionsItemSelected(item);
    }

    private void batalkanPesanan() {
        new BatalAccess(this, new Runnable() {
            @Override
            public void run() {
                keluar();
            }
        }).execute();
    }

    private void amot() {
        new KatAccess(this, lstKat).execute();
        new MasihAccess(this, new Runnable() {
            @Override
            public void run() {
            }
        }, new Runnable() {
            @Override
            public void run() {
                keluar();
            }
        }).execute();
    }

    private void keluar() {
        mlaku = false;
        Work.clearNota(this);
        startActivity(new Intent(this, Regist.class));
        finish();
    }

    private void openWar() {
        lstKat = (RecyclerView) findViewById(R.id.lstKat);
        lstKat.setHasFixedSize(true);
        mlaku = true;
    }

    @Override
    public void onBackPressed() {
        mlaku = false;
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

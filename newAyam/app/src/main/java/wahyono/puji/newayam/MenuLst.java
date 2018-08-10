package wahyono.puji.newayam;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import wahyono.puji.newayam.adapter.MenuAdapter;
import wahyono.puji.newayam.beans.Menu;
import wahyono.puji.newayam.server.MasihAccess;
import wahyono.puji.newayam.server.MenuAccess;
import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class MenuLst extends AppCompatActivity {
    private int kode;
    private RecyclerView lstMenu;
    private GridLayoutManager glm;
    public boolean mlaku;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lst);
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

    private void amot() {
        new MenuAccess(this, lstMenu).execute(kode);
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
        mlaku = true;
        kode = getIntent().getExtras().getInt("kat");
        lstMenu = (RecyclerView) findViewById(R.id.lstMenu);
        lstMenu.setHasFixedSize(true);
        glm = new GridLayoutManager(this ,2);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        lstMenu.setLayoutManager(glm);
    }

    @Override
    public void onBackPressed() {
        mlaku = false;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

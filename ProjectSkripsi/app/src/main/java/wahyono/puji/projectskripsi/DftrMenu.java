package wahyono.puji.projectskripsi;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.adapter.MenuAdapter;
import wahyono.puji.projectskripsi.util.Work;
import wahyono.puji.projectskripsi.ws.Api;

public class DftrMenu extends AppCompatActivity {
    private String nota,kode;
    private RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dftr_menu);
        bindingData();
        muat();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,PilihMeja.class);
        i.putExtra("nota",nota);
        startActivity(i);
        finish();
    }

    private void muat() {
        Retrofit r=new Retrofit.Builder().baseUrl(Work.getUrl(this))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        a.getMenu(Integer.parseInt(kode)).enqueue(new Callback<List<wahyono.puji.projectskripsi.ws.Menu>>() {
            @Override
            public void onResponse(Call<List<wahyono.puji.projectskripsi.ws.Menu>> call, Response<List<wahyono.puji.projectskripsi.ws.Menu>> response) {
                List<wahyono.puji.projectskripsi.ws.Menu>l=response.body();
                prosesMuat(l);
            }
            @Override
            public void onFailure(Call<List<wahyono.puji.projectskripsi.ws.Menu>> call, Throwable t) {
                Toast.makeText(DftrMenu.this,"Gagal muat",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prosesMuat(List<wahyono.puji.projectskripsi.ws.Menu> l) {
        int x=2;
        RecyclerView.LayoutManager lm=new GridLayoutManager(this,x);
        rec.setLayoutManager(lm);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.addItemDecoration(new PilihMeja.GridSpacingItemDecoration(x, dpToPx(10), true));
        rec.setAdapter(new MenuAdapter(nota,this,l,kode));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.refreshThis)muat();
        else if(id==R.id.keranjangThis){
            Intent i=new Intent(this,ItemTemp.class);
            i.putExtra("arah",this.getClass().getCanonicalName());
            i.putExtra("nota",nota);
            startActivity(i);
            finish();
        }return true;
    }

    private int dpToPx(int i) {
        Resources r=getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,i,r.getDisplayMetrics()));
    }

    private void bindingData() {
        nota=getIntent().getStringExtra("nota");
        kode=getIntent().getStringExtra("kode");
        rec=(RecyclerView)findViewById(R.id.tayangMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menunecok,menu);
        return true;
    }
}

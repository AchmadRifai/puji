package wahyono.puji.projectskripsi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.adapter.KatAdapter;
import wahyono.puji.projectskripsi.ws.Api;
import wahyono.puji.projectskripsi.ws.Kat;
import wahyono.puji.projectskripsi.ws.Msg;

public class PilihMeja extends AppCompatActivity {
    private RecyclerView rec;
    private String nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_meja);
        toolbare();
        nota=getIntent().getStringExtra("nota");
        rec=(RecyclerView)findViewById(R.id.tayangKat);
        muat();
    }

    @SuppressLint("NewApi")
    private void toolbare() {
        /*Toolbar t=(Toolbar)findViewById(R.id.toolbar);
        setActionBar(t);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menunecok,menu);
        return true;
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

    private void muat() {
        Retrofit r=new Retrofit.Builder().baseUrl(getString(R.string.api_url)).
                addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        a.getKategori().enqueue(new Callback<List<Kat>>() {
            @Override
            public void onResponse(Call<List<Kat>> call, Response<List<Kat>> response) {
                List<Kat>l=response.body();
                muatKat(l);
            }
            @Override
            public void onFailure(Call<List<Kat>> call, Throwable t) {
                Toast.makeText(PilihMeja.this,"Gagal memuat",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void muatKat(List<Kat> l) {
        for(int x=rec.getChildCount()-1;x>=0;x--)rec.removeViewAt(x);
        KatAdapter a=new KatAdapter(this,l,this,nota);
        int x=2;
        RecyclerView.LayoutManager lm=new GridLayoutManager(this,x);
        rec.setLayoutManager(lm);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.addItemDecoration(new GridSpacingItemDecoration(x, dpToPx(10), true));
        rec.setAdapter(a);
    }

    private int dpToPx(int i) {
        Resources r=getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,i,r.getDisplayMetrics()));
    }

    @Override
    public void onBackPressed() {
        batalPesan();
    }

    private void batalPesan() {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setTitle("Batal Pesan?");
        b.setMessage("Apakah anda ingin membatalkan pemesanan?");
        b.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                batalkanPesan();
            }
        });b.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });AlertDialog a=b.create();
        a.show();
    }

    private void batalkanPesan() {
        Retrofit r=new Retrofit.Builder().
                baseUrl(getString(R.string.api_url)).
                addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        a.batalTrans(nota).enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                Msg m=response.body();
                prosesBatal(m);
            }
            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                Toast.makeText(PilihMeja.this,"Pembatalan gagal",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prosesBatal(Msg m) {
        if("Error"!=m.getPesan()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else Toast.makeText(this,"Pembatalan gagal",Toast.LENGTH_LONG).show();
    }

    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount,spacing;
        private boolean edges;

        public GridSpacingItemDecoration(int i, int dpToPx, boolean b) {
            spanCount=i;
            spacing=dpToPx;
            edges=b;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int pos=parent.getChildAdapterPosition(view);
            int col=pos%spanCount;
            if(edges){
                outRect.left=spacing-col*spacing/spanCount;
                if(pos<spanCount)outRect.top=spacing;
                outRect.bottom=spacing;
            }else{
                outRect.left=col*spacing/spanCount;
                outRect.right=spacing-(col+1)*spacing/spanCount;
                if(pos>=spanCount)outRect.top=spacing;
            }
        }
    }
}
package wahyono.puji.projectskripsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.money.CurrencyUnit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.adapter.ItemBrgAdapter;
import wahyono.puji.projectskripsi.ws.Api;
import wahyono.puji.projectskripsi.ws.ItemBrg;
import wahyono.puji.projectskripsi.ws.Msg;

public class ItemTemp extends AppCompatActivity {
    public String nota;
    private String arah;
    private RecyclerView rec;
    private TextView tot;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_temp);
        dataBinding();
        muat();
        evente();
    }

    private void evente() {
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit r=new Retrofit.Builder().baseUrl(getString(R.string.api_url))
                        .addConverterFactory(GsonConverterFactory.create()).build();
                Api a=r.create(Api.class);
                a.validTrans(nota).enqueue(new Callback<Msg>() {
                    @Override
                    public void onResponse(Call<Msg> call, Response<Msg> response) {
                        Msg m=response.body();
                        proses(m);
                    }
                    @Override
                    public void onFailure(Call<Msg> call, Throwable t) {
                        Toast.makeText(ItemTemp.this,"Gagal men-submit",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void proses(Msg m) {
        if("Error"!=m.getPesan()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else Toast.makeText(ItemTemp.this,"Gagal men-submit",Toast.LENGTH_LONG).show();
    }

    public void muat() {
        Retrofit r=new Retrofit.Builder().baseUrl(getString(R.string.api_url)).
                addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        a.getDaftar(nota).enqueue(new Callback<List<ItemBrg>>() {
            @Override
            public void onResponse(Call<List<ItemBrg>> call, Response<List<ItemBrg>> response) {
                List<ItemBrg>l=response.body();
                kalkulasi(l);
            }
            @Override
            public void onFailure(Call<List<ItemBrg>> call, Throwable t) {
                Toast.makeText(ItemTemp.this,"Gagal memuat",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void kalkulasi(List<ItemBrg> l) {
        rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setAdapter(new ItemBrgAdapter(this,l));
        org.joda.money.Money total=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        for(ItemBrg i:l)total=total.plus(org.joda.money.Money.parse(i.getHrg()));
        tot.setText("Total : "+total);
        but.setEnabled(org.joda.money.Money.zero(CurrencyUnit.of("IDR")).isLessThan(total));
    }

    private void dataBinding() {
        nota=getIntent().getStringExtra("nota");
        arah=getIntent().getStringExtra("arah");
        rec=(RecyclerView)findViewById(R.id.myOrder);
        tot=(TextView)findViewById(R.id.totalan);
        but=(Button)findViewById(R.id.submitMyOrder);
    }

    @Override
    public void onBackPressed() {
        try {
            Intent i=new Intent(this,Class.forName(arah));
            i.putExtra("nota",nota);
            startActivity(i);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

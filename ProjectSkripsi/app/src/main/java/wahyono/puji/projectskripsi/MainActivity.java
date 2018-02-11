package wahyono.puji.projectskripsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.util.Work;
import wahyono.puji.projectskripsi.ws.Api;
import wahyono.puji.projectskripsi.ws.BahanNota;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Work.checkDB(this))alihkan();
        getSupportActionBar().hide();
        TextView t=(TextView)findViewById(R.id.textView);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesan();
            }
        });
    }

    private void alihkan() {
        startActivity(new Intent(this,konfig.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    private void pesan(){
        Retrofit r=new Retrofit.Builder().
                baseUrl(Work.getUrl(this)).
                addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        int meja=Work.getMeja(this);
        a.getNota(""+meja).enqueue(new Callback<BahanNota>() {
            @Override
            public void onResponse(Call<BahanNota> call, Response<BahanNota> response) {
                BahanNota b=response.body();
                proses(b);
            }
            @Override
            public void onFailure(Call<BahanNota> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Pemesanan Gagal",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void proses(BahanNota b) {
        if("Error"!=b.getNota()){
            Intent i=new Intent(this,PilihMeja.class);
            i.putExtra("nota",b.getNota());
            startActivity(i);
            finish();
        }else Toast.makeText(this,"Pemesanan Gagal",Toast.LENGTH_LONG).show();
    }
}
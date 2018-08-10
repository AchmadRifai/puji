package wahyono.puji.projectskripsi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.util.Work;
import wahyono.puji.projectskripsi.ws.Api;
import wahyono.puji.projectskripsi.ws.BahanNota;

public class MainActivity extends AppCompatActivity {
    private EditText nm,hp;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Work.checkDB(this))alihkan();
        getSupportActionBar().hide();
        binding();
        aksi();
    }

    private void aksi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    while (!MainActivity.this.isDestroyed()){
                        final String snm=""+nm.getText(),shp=""+hp.getText();
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                submit.setEnabled(!snm.isEmpty()&&!shp.isEmpty());
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesan();
            }
        });
    }

    private void binding() {
        nm=(EditText)findViewById(R.id.namaPelanggan);
        hp=(EditText)findViewById(R.id.noHp);
        submit=(Button)findViewById(R.id.goNota);
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
        a.getNota(""+meja,""+nm.getText(),""+hp.getText()).enqueue(new Callback<BahanNota>() {
            @Override
            public void onResponse(Call<BahanNota> call, Response<BahanNota> response) {
                proses(response.body().getNota());
            }
            @Override
            public void onFailure(Call<BahanNota> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Pemesanan Gagal",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void proses(String b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&b!=null) {
            if(!Objects.equals("Error", b)){
                Intent i=new Intent(this,PilihMeja.class);
                i.putExtra("nota",b);
                startActivity(i);
                finish();
            }else Toast.makeText(this,"Pemesanan Gagal",Toast.LENGTH_LONG).show();
        }else Toast.makeText(this,"Url error",Toast.LENGTH_LONG).show();
    }
}
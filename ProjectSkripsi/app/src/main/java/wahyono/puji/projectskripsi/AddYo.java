package wahyono.puji.projectskripsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.util.Work;
import wahyono.puji.projectskripsi.ws.Api;
import wahyono.puji.projectskripsi.ws.Msg;

public class AddYo extends AppCompatActivity {
    private String nama,nota,gbr,hrg,menu,kat;
    private Money rego;
    private ImageView gbrV;
    private TextView nm,harga,num;
    private Button add,min,apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yo);
        bindingData();
        muat();
        evente();
    }

    private void evente() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=Integer.parseInt(""+num.getText());
                i++;
                num.setText(""+i);
            }
        });min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=Integer.parseInt(""+num.getText());
                i--;
                num.setText(""+i);
            }
        });apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        Retrofit r=new Retrofit.Builder().baseUrl(Work.getUrl(this))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        a.tumpukPesanan(nota,menu,""+num.getText()).enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                Msg m=response.body();
                proses(m);
            }
            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                Toast.makeText(AddYo.this,"gagal Menambah",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void proses(Msg m) {
        if("Error"!=m.getPesan()){
            onBackPressed();
        }else Toast.makeText(AddYo.this,"gagal Menambah",Toast.LENGTH_LONG).show();
    }

    private void muat() {
        nm.setText(nama);
        Glide.with(this).load(Work.getUrl(this)+gbr).into(gbrV);
        num.setText("0");
        harga.setText(""+rego);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,DftrMenu.class);
        i.putExtra("nota",nota);
        i.putExtra("kode",kat);
        startActivity(i);
        finish();
    }

    private void bindingData() {
        nama=getIntent().getStringExtra("nama");
        nota=getIntent().getStringExtra("nota");
        gbr=getIntent().getStringExtra("gbr");
        hrg=getIntent().getStringExtra("hrg");
        menu=getIntent().getStringExtra("menu");
        kat=getIntent().getStringExtra("kat");
        rego=Money.of(CurrencyUnit.of("IDR"),Long.parseLong(hrg));
        gbrV=(ImageView)findViewById(R.id.gbrMenu);
        nm=(TextView)findViewById(R.id.nmMenu);
        harga=(TextView)findViewById(R.id.hrgMenu);
        num=(TextView)findViewById(R.id.numCount);
        add=(Button)findViewById(R.id.plusCount);
        min=(Button)findViewById(R.id.minCount);
        apply=(Button)findViewById(R.id.addToTemp);
    }
}

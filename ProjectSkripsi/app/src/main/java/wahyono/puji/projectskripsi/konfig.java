package wahyono.puji.projectskripsi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import wahyono.puji.projectskripsi.util.Work;

public class konfig extends AppCompatActivity {
    private EditText url,meja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfig);
        getSupportActionBar().hide();
        url=(EditText)findViewById(R.id.urlServer);
        meja=(EditText)findViewById(R.id.noMeja);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setCancelable(false).setMessage("Apa anda ingin keluar?").setTitle("Keluar").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(""!=url.getText().toString()&&""!=meja.getText().toString()){
                    saveData();
                    startActivity(new Intent(konfig.this,MainActivity.class));
                    finish();
                }else Toast.makeText(konfig.this,"Tolong isi semua",Toast.LENGTH_LONG).show();
            }
        }).create().show();
    }

    private void saveData() {
        String alamat=url.getText().toString();
        int nomor=Integer.parseInt(meja.getText().toString());
        Work.save(this,alamat,nomor);
    }
}

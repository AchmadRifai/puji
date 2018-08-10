package wahyono.puji.newayam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wahyono.puji.newayam.util.Work;

public class Konfig extends AppCompatActivity {
    private EditText server, meja;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Work.sudahKonfig(this))alihkan();
        setContentView(R.layout.activity_konfig);
        binding();
        evene();
    }

    private void evene() {
        server.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    validasiForm();
                    return true;
                } return false;
            }
        }); meja.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    validasiForm();
                    return true;
                } return false;
            }
        }); submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Work.setData(Konfig.this, ""+server.getText(),
                        Integer.parseInt(""+meja.getText()));
                alihkan();
            }
        });
    }

    private void validasiForm() {
        submit.setEnabled(""+server.getText() != "" && ""+meja.getText() != "");
    }

    private void binding() {
        server = (EditText) findViewById(R.id.server);
        meja= (EditText) findViewById(R.id.meja);
        submit = (Button) findViewById(R.id.submitConf);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    private void alihkan() {
        if(!Work.getNota(this).isEmpty())
            startActivity(new Intent(this, MainActivity.class));
        else startActivity(new Intent(this, Regist.class));
        finish();
    }
}

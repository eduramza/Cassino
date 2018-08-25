package br.com.uatizapi.cursoandroid.cassino.Activitys;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import br.com.uatizapi.cursoandroid.cassino.R;

public class RegrasActivity extends AppCompatActivity {
    //ATRIBUTOS
    private TextView titulo;
    private Button sair;
    private ScrollView svRegras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regras);

        sair = (Button) findViewById(R.id.bt_voltarR);
        svRegras = (ScrollView) findViewById(R.id.sv_Regras);
        titulo = (TextView) findViewById(R.id.txt_titulo);

        //importando fontes
        Typeface tipoFonte = Typeface.createFromAsset(getAssets(), "fonts/Base 02.ttf");
        titulo.setTypeface(tipoFonte);


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(RegrasActivity.this, MainActivity.class);
                startActivity(intent);*/
                finish();
            }
        });

    }
}

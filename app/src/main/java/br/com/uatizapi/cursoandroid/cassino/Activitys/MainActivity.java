package br.com.uatizapi.cursoandroid.cassino.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.uatizapi.cursoandroid.cassino.R;

public class MainActivity extends AppCompatActivity {
    //ATRIBUTOS
    private Button jogar, regras, sair, recordes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INSTANCIANDO OS COMPONENTES NA TELA
        jogar = (Button) findViewById(R.id.btJogar);
        regras = (Button) findViewById(R.id.btRegras);
        sair = (Button) findViewById(R.id.btSair);
        recordes = (Button) findViewById(R.id.btRecordes);


        //EVENTO DOS BOTÕES
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sair();
            }
        });

        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jogar();
            }
        });

        regras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegrasActivity.class);
                startActivity(intent);
            }
        });

        recordes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordesActivity.class);
                startActivity(intent);
            }
        });
    }

    /******************************************
     * ************METODOS********************
     ****************************************/
    private void Sair(){
        finish();
    }

    private void Jogar(){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }

}

package br.com.uatizapi.cursoandroid.cassino.Activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.uatizapi.cursoandroid.cassino.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //instanciando a classe handler responsavel por criar a thread do delay da splash
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MostrarMain(); //interface chamada após o delay
            }
        }, 3000); //tempo de delay em milisegundos


    }

    /**********************************
     * metodos
     **********************************/
    public void MostrarMain(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

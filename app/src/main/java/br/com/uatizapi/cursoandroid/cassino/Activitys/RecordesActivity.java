package br.com.uatizapi.cursoandroid.cassino.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import br.com.uatizapi.cursoandroid.cassino.R;

public class RecordesActivity extends AppCompatActivity {
    //ATRIBUTOS
    private TextView titulo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordes);

        //listaRecordes = (ListView) findViewById(R.id.lv_recordes);

        //inserindo uma fonte diferente no titulo]
        titulo = (TextView) findViewById(R.id.tv_titulorcd);
        Typeface tipoFonte = Typeface.createFromAsset(getAssets(), "fonts/Base 02.ttf");
        titulo.setTypeface(tipoFonte);


    }


}

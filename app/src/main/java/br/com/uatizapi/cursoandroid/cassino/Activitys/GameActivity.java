package br.com.uatizapi.cursoandroid.cassino.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import br.com.uatizapi.cursoandroid.cassino.R;

public class GameActivity extends AppCompatActivity {
    //ATRIBUTOS
    Button sortear,go, novamente, voltar;
    ImageView lista1, lista2, lista3;
    TextView txt_creditos, txt_pontos;
    private AnimationDrawable im1, im2, im3;
    public String jogador;

    private MediaPlayer media;


    private Random r1 = new Random();
    private Random r2 = new Random();
    private Random r3 = new Random();

    int [] vetorImg = {R.drawable.amarelo, R.drawable.vermelho, R.drawable.azul};
    int a, b, c;

    //VARIAVEIS PARA JOGO
    int creditos = 100;
    int pontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //chamada do método de captura do nome do jogador
        idJogador();

        //INSTANCIANDO OS COMPONENTES
        txt_creditos = (TextView) findViewById(R.id.txt_creditos);
        txt_pontos = (TextView) findViewById(R.id.txt_pontos);
        sortear = (Button) findViewById(R.id.bt_sortear);
        go = (Button) findViewById(R.id.btn_go);
        novamente = (Button) findViewById(R.id.bt_novamente);
        voltar = (Button) findViewById(R.id.bt_voltar);
        lista1 = (ImageView) findViewById(R.id.image1);
        lista2 = (ImageView) findViewById(R.id.image2);
        lista3 = (ImageView) findViewById(R.id.image3);

        novamente.setVisibility(View.GONE);

        txt_creditos.setText("100");
        MovimentarImg();

        //EVENTO DE CLICK NO BOTÃO
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sortear
                ResultadoSorteio();
                //bloquear o botão go
                go.setEnabled(false);

                //VERIFICAR JOGADA
                 if (creditos > 0){

                    creditos = creditos - 10;
                    txt_creditos.setText(String.valueOf(creditos));

                }
                if (creditos == 0){
                    //salvando os pontos no banco
                    String pontos = String.valueOf(pontuacao);//txt_pontos.getText().toString();


                    //ações com os botões
                    go.setVisibility(View.GONE);
                    sortear.setVisibility(View.GONE);
                    novamente.setVisibility(View.VISIBLE);
                }

            }


        });

        sortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovimentarImg();
                go.setEnabled(true);
            }
        });

        novamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JogarNovamente();
            }
        });
    }

    /****************************
     * METODOS
     ***************************/
    private void ResultadoSorteio(){
        //randomizar e jogar as imagens na tela
        a = r1.nextInt(vetorImg.length);
        lista1.setBackgroundResource(vetorImg[a]);

        b = r2.nextInt(vetorImg.length);
        lista2.setBackgroundResource(vetorImg[b]);

        c = r3.nextInt(vetorImg.length);
        lista3.setBackgroundResource(vetorImg[c]);

        //Validando se hove ganho
        if ((a == b) && (a == c)){
            media = MediaPlayer.create(GameActivity.this, R.raw.um_up);
            tocarSom();
            pontuacao = pontuacao + 50;
            txt_pontos.setText(String.valueOf(pontuacao));
        } else if ((a == b) && (a != c) || (b == c) && (a != b) || (a == c) && (a != b)) {
            pontuacao = pontuacao + 25;
            txt_pontos.setText(String.valueOf(pontuacao));
        } else {
            pontuacao = pontuacao - 25;
            txt_pontos.setText(String.valueOf(pontuacao));
        }

    }

    private void tocarSom() {
        if (media != null){
            media.start();
        }
    }

    @Override
    protected void onDestroy() {
        if (media != null){
            media.stop();
            media.release();
            media = null;
        }

        super.onDestroy();
    }

    public void MovimentarImg(){

        //Criando a animação da lista 1
        lista1.setBackgroundResource(R.drawable.roleta1);
        im1 = (AnimationDrawable) lista1.getBackground();
        im1.start();

        //CRIANDO ANIMAÇÃO LISTA 2
        lista2.setBackgroundResource(R.drawable.roleta2);
        im2 = (AnimationDrawable) lista2.getBackground();
        im2.start();

        //CRIANDO ANIMAÇÃO LISTA 3
        lista3.setBackgroundResource(R.drawable.roleta3);
        im3 = (AnimationDrawable) lista3.getBackground();
        im3.start();

    }

    private void JogarNovamente(){
        creditos = 100;
        pontuacao = 0;
        txt_pontos.setText(String.valueOf(pontuacao));
        txt_creditos.setText(String.valueOf(creditos));

        novamente.setVisibility(View.GONE);

        go.setVisibility(View.VISIBLE);
        go.setEnabled(true);
        sortear.setVisibility(View.VISIBLE);

        MovimentarImg();

    }

    //GRAVANDO O NOME DO USUÁRIO TODA VEZ QUE ELE ENTRAR NA TELA DE GAME
    public void idJogador(){
        //configurando o alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);

        alert.setTitle("Jogador");
        alert.setMessage("Nome do jogador");
        alert.setCancelable(false);

        //criando uma caixa de texto dentro do alert dialog
        final EditText texto = new EditText(GameActivity.this);
        alert.setView(texto);

        //ação do alert
        alert.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                jogador = texto.getText().toString();

                if (jogador.isEmpty()){
                    Toast.makeText(GameActivity.this, "Insira o nome do jogador! Sua besta!", Toast.LENGTH_SHORT).show();
                    idJogador(); //recursivida
                } else if (jogador.length() > 1){
                    //GRAVANDO O NOME DO JOGADOR NO BANCO DE DADOS

                    //APÓS A INSERÇÃO DO NOME DO JOGADOR
                    Toast.makeText(GameActivity.this, jogador, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //materializando o alert
        alert.create();
        alert.show();

    }
}

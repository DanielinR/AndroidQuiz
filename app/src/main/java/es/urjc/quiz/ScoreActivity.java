package es.urjc.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity{
    //asigno una nueva variable para el boton de inicio

    Score score;
    Button botonVolverAJugar;
    Button botonSalir;
    TextView total;
    TextView acertadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_score);

        score = ComunicadorScore.getScoreStatic();
        botonSalir = (Button) findViewById(R.id.button_salir);
        botonVolverAJugar = (Button) findViewById(R.id.button_volver_a_jugar);

        total = (TextView) findViewById(R.id.text_puntuacion_total);
        acertadas = (TextView) findViewById(R.id.text_acertadas);

        total.setText(total.getText() + Integer.toString(score.getAciertosArte()*10 + score.getAciertosCiencia()*10 +score.getAciertosDeporte()*10 +
                score.getAciertosEntretenimiento()*10 +score.getAciertosHistoria()*10 +score.getAciertosGeografia()*10));
        acertadas.setText("Aciertos arte: " + score.getAciertosArte() + " \n Aciertos ciencia: " + score.getAciertosCiencia() + " \nAciertos geografia: " + score.getAciertosGeografia() + " \nAciertos historia: " + score.getAciertosHistoria() + "" +
                " \nAciertos entretenimiento: " + score.getAciertosEntretenimiento() + " \nAciertos deporte: " + score.getAciertosDeporte() + " \n");

        botonVolverAJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                score.setACero();
                Intent i = new Intent(ScoreActivity.this, QuestionsActivity.class);
                startActivity(i);

            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                score.setACero();
                Intent i = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(i);

            }
        });


    }
}
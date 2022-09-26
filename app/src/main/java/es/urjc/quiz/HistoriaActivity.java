package es.urjc.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HistoriaActivity extends AppCompatActivity{
    //asigno una nueva variable para el boton de inicio
    FloatingActionButton botonInicioo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_historia);


        //inicializo el boton
        botonInicioo = (FloatingActionButton) findViewById(R.id.floatingActionButton_historia);

        botonInicioo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HistoriaActivity.this, QuestionsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit);
            }
        });
    }
}
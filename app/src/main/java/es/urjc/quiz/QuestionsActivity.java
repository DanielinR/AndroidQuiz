package es.urjc.quiz;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class QuestionsActivity extends AppCompatActivity implements Preguntas4Fragment.fragmentPreguntas4Listener, CheckFragment.fragmentCheckListener,
            SwitchFragment.fragmentSwitchListener , ChipFragment.fragmentChipListener, Imagenes4Fragment.fragmentImagenes4Listener, RadioFragment.fragmentRadioListener,
            ListViewFragment.fragmentListViewListener, ToggleFragment.fragmentToggleListener, FeedbackRespuestaAcertadaFragment.fragmentFeedbackListener, FeedbackRespuestaFalladaFragment.fragmentFeedbackFalladaListener{

    ImageButton botonAtras;
    FragmentContainerView preguntasFragmentos;

    private Preguntas4Fragment fragmentPreguntas4;
    private CheckFragment fragmentPreguntascheck;
    private SwitchFragment fragmentPreguntasswitch;
    private ChipFragment fragmentPreguntaschip;
    private Imagenes4Fragment fragmentPreguntas4Imagenes;
    private RadioFragment fragmentPreguntasRadio;
    private ListViewFragment fragmentPreguntaslistview;
    private ToggleFragment fragmentPreguntastoggle;

    private FeedbackRespuestaAcertadaFragment fragmentFeedbackAcertada;
    private FeedbackRespuestaFalladaFragment fragmentFeedbackFallada;
    Pregunta[] preguntas;
    private  int preguntaActual = -1;

    //score categorias
    public Score score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //pongo el layout
        setContentView(R.layout.activity_questions);

        //creo los fragments
        fragmentPreguntas4 = new Preguntas4Fragment();
        fragmentPreguntascheck = new CheckFragment();
        fragmentPreguntasswitch = new SwitchFragment();
        fragmentPreguntaschip = new ChipFragment();
        fragmentPreguntas4Imagenes = new Imagenes4Fragment();
        fragmentPreguntasRadio = new RadioFragment();
        fragmentPreguntaslistview = new ListViewFragment();
        fragmentPreguntastoggle = new ToggleFragment();
        fragmentFeedbackAcertada = new FeedbackRespuestaAcertadaFragment();
        fragmentFeedbackFallada = new FeedbackRespuestaFalladaFragment();

        //inicializo el score
        score = new Score();

        //inicializo los botones y el navegador de fragmentos
        botonAtras = (ImageButton) findViewById(R.id.boton_atras);
        preguntasFragmentos = (FragmentContainerView) findViewById(R.id.fragment_preguntas);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(QuestionsActivity.this, MainActivity.class);
                startActivity(i);

            }
        });



        //creamos las preguntas con sus respuestas
        Resources res = getResources();
        String[] preguntasCiencia = res.getStringArray(R.array.preguntas_8);

        preguntas = new Pregunta[preguntasCiencia.length/8];//el numero de preguntas actual es igual al de la seccion actual
        for (int i = 0; i < preguntasCiencia.length/8; i++){
            preguntas[i] = new Pregunta(
                    preguntasCiencia[i*8],    //categoria de la pregunta
                    preguntasCiencia[1 + i*8],//titulo de la pregunta
                    preguntasCiencia[2 + i*8],//primera respuesta
                    preguntasCiencia[3 + i*8],
                    preguntasCiencia[4 + i*8],
                    preguntasCiencia[5 + i*8],//cuarta respuesta
                    Integer.parseInt(preguntasCiencia[6 + i*8]),//numero respuesta correcta
                    preguntasCiencia[7 + i*8]//tipo de pregunta


            );
        }




        actualizarPregunta();


    }

    //método que comprueba la pregunta actual e introduce el fragment correspondiente y lo actualiza
    private void actualizarPregunta(){
        preguntaActual++;
        //me aseguro de que halla al menos una pregunta y de que no hallamos respondido todas ya
        if(preguntas.length > 0 && preguntaActual < (preguntas.length)){
            FragmentManager fm = getSupportFragmentManager();
            switch (preguntas[preguntaActual].getTipoDePregunta()){

                case "abc":
                    //transicion sin animacion
                    FragmentTransaction ftabc = fm.beginTransaction();
                    ftabc.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntas4 = new Preguntas4Fragment();
                    ftabc.replace(preguntasFragmentos.getId(), fragmentPreguntas4);
                    fm.executePendingTransactions();
                    ftabc.commit();
                    //introduzco la pregunta
                    fragmentPreguntas4.setPregunta(preguntas[preguntaActual]);

                    break;

                case "check":
                    //transicion sin animacion
                    FragmentTransaction ftcheck = fm.beginTransaction();
                    ftcheck.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntascheck = new CheckFragment();
                    ftcheck.replace(preguntasFragmentos.getId(), fragmentPreguntascheck);
                    fm.executePendingTransactions();
                    ftcheck.commit();
                    //introduzco la pregunta
                    fragmentPreguntascheck.setPregunta(preguntas[preguntaActual]);
                    break;

                case "switch":
                    //transicion sin animacion
                    FragmentTransaction ftswitch = fm.beginTransaction();
                    ftswitch.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntasswitch = new SwitchFragment();
                    ftswitch.replace(preguntasFragmentos.getId(), fragmentPreguntasswitch);
                    fm.executePendingTransactions();
                    ftswitch.commit();
                    //introduzco la pregunta
                    fragmentPreguntasswitch.setPregunta(preguntas[preguntaActual]);
                    break;

                case "chip":
                    //transicion sin animacion
                    FragmentTransaction ftchip = fm.beginTransaction();
                    ftchip.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntaschip = new ChipFragment();
                    ftchip.replace(preguntasFragmentos.getId(), fragmentPreguntaschip);
                    fm.executePendingTransactions();
                    ftchip.commit();
                    //introduzco la pregunta
                    fragmentPreguntaschip.setPregunta(preguntas[preguntaActual]);
                    break;

                case "4imagenes":
                    //transicion sin animacion
                    FragmentTransaction ft4Imagenes = fm.beginTransaction();
                    ft4Imagenes.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntas4Imagenes = new Imagenes4Fragment();
                    ft4Imagenes.replace(preguntasFragmentos.getId(), fragmentPreguntas4Imagenes);
                    fm.executePendingTransactions();
                    ft4Imagenes.commit();
                    //introduzco la pregunta

                    int id1 = getResources().getIdentifier(preguntas[preguntaActual].getRespuesta1(),"drawable",QuestionsActivity.this.getPackageName());
                    int id2 = getResources().getIdentifier(preguntas[preguntaActual].getRespuesta2(),"drawable",QuestionsActivity.this.getPackageName());
                    int id3 = getResources().getIdentifier(preguntas[preguntaActual].getRespuesta3(),"drawable",QuestionsActivity.this.getPackageName());
                    int id4 = getResources().getIdentifier(preguntas[preguntaActual].getRespuesta4(),"drawable",QuestionsActivity.this.getPackageName());

                    preguntas[preguntaActual].setRespuesta1(Integer.toString(id1));
                    preguntas[preguntaActual].setRespuesta2(Integer.toString(id2));
                    preguntas[preguntaActual].setRespuesta3(Integer.toString(id3));
                    preguntas[preguntaActual].setRespuesta4(Integer.toString(id4));
                    fragmentPreguntas4Imagenes.setPregunta(preguntas[preguntaActual]);
                    break;

                case "radio":
                    //transicion sin animacion
                    FragmentTransaction ftRadio = fm.beginTransaction();
                    ftRadio.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntasRadio = new RadioFragment();
                    ftRadio.replace(preguntasFragmentos.getId(), fragmentPreguntasRadio);
                    fm.executePendingTransactions();
                    ftRadio.commit();

                    //introduzco la pregunta
                    fragmentPreguntasRadio.setPregunta(preguntas[preguntaActual]);
                    break;

                case "listview":
                    //transicion sin animacion
                    FragmentTransaction ftListview = fm.beginTransaction();
                    ftListview.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntaslistview = new ListViewFragment();
                    ftListview.replace(preguntasFragmentos.getId(), fragmentPreguntaslistview);
                    fm.executePendingTransactions();
                    ftListview.commit();

                    //introduzco la pregunta
                    fragmentPreguntaslistview.setPregunta(preguntas[preguntaActual]);
                    break;

                case "toggle":
                    //transicion sin animacion
                    FragmentTransaction ftToggle = fm.beginTransaction();
                    ftToggle.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left);
                    fragmentPreguntastoggle = new ToggleFragment();
                    ftToggle.replace(preguntasFragmentos.getId(), fragmentPreguntastoggle);
                    fm.executePendingTransactions();
                    ftToggle.commit();

                    //introduzco la pregunta
                    fragmentPreguntastoggle.setPregunta(preguntas[preguntaActual]);
                    break;


            }
        }else{
            //han acabado las preguntas y muestro los resultados
            ComunicadorScore.setScoreStatic(score);
            Intent i = new Intent(QuestionsActivity.this, ScoreActivity.class);
            startActivity(i);
        }

    }

    @Override
    public void onInputFeedback4Sent(CharSequence input) {
        //cerramos el popup
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ftFeedbackcerrar = fm.beginTransaction();
        ftFeedbackcerrar.setCustomAnimations(R.anim.enter_top_to_bot,R.anim.exit_bot_to_top);
        ftFeedbackcerrar.remove(fragmentFeedbackAcertada);
        fm.executePendingTransactions();
        ftFeedbackcerrar.commit();

        //actualizamos la pregunta cuando el PopUp finaliza
        actualizarPregunta();
    }

    @Override
    public void onInputFeedbackFallada4Sent(CharSequence input) {
        //cerramos el popup
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ftFeedbackcerrar = fm.beginTransaction();
        ftFeedbackcerrar.setCustomAnimations(R.anim.enter_top_to_bot,R.anim.exit_bot_to_top);
        ftFeedbackcerrar.remove(fragmentFeedbackFallada);
        fm.executePendingTransactions();
        ftFeedbackcerrar.commit();

        //actualizamos la pregunta cuando el PopUp finaliza
        actualizarPregunta();
    }

    @Override
    public void onInputPreguntas4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputCheck4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputSwitch4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputChip4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputImagenes4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputRadio4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputListViewSent(CharSequence input) {
        valorarRespuesta(input);
    }

    @Override
    public void onInputToggle4Sent(CharSequence input) {
        valorarRespuesta(input);
    }

    private void valorarRespuesta(CharSequence input){
        FragmentManager fm = getSupportFragmentManager();
        switch(input.toString()){
            //en caso de haber acertado la pregunta
            case "acertada":
                //comprobamos de que categoria era la pregunta acertada
                switch ( preguntas[preguntaActual].getCategoria()){
                    case "arte":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setAciertosArteIncrementar();
                        break;
                    case "ciencia":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setAciertosCienciaIncrementar();
                        break;
                    case "geografia":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setAciertosGeografiaIncrementar();
                        break;
                    case "deporte":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setAciertosDeporteIncrementar();
                        break;
                    case "historia":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setAciertosHistoriaIncrementar();
                        break;
                    case "entretenimiento":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setAciertosEntretenimientoIncrementar();
                        break;

                }
                //devolvemos feedback
                FragmentTransaction ftFeedbackAcertada = fm.beginTransaction();
                ftFeedbackAcertada.setCustomAnimations(R.anim.enter_top_to_bot,R.anim.exit_bot_to_top);
                fragmentFeedbackAcertada = new FeedbackRespuestaAcertadaFragment();
                ftFeedbackAcertada.add(preguntasFragmentos.getId(), fragmentFeedbackAcertada);
                fm.executePendingTransactions();
                ftFeedbackAcertada.commit();

                break;
            case "fallada":
                switch ( preguntas[preguntaActual].getCategoria()){
                    case "arte":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setFallosArteIncrementar();
                        break;
                    case "ciencia":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setFallosCienciaIncrementar();
                        break;
                    case "geografia":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setFallosGeografiaIncrementar();
                        break;
                    case "deporte":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setFallosDeporteIncrementar();
                        break;
                    case "historia":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setFallosHistoriaIncrementar();
                        break;
                    case "entretenimiento":
                        //aumentamos en uno los aciertos de dicha categoria
                        score.setFallosEntretenimientoIncrementar();
                        break;

                }
                //devolvemos feedback
                FragmentTransaction ftFeedbackFallada = fm.beginTransaction();
                ftFeedbackFallada.setCustomAnimations(R.anim.enter_top_to_bot,R.anim.exit_bot_to_top);
                fragmentFeedbackFallada = new FeedbackRespuestaFalladaFragment();
                ftFeedbackFallada.add(preguntasFragmentos.getId(), fragmentFeedbackFallada);
                fm.executePendingTransactions();
                ftFeedbackFallada.commit();
                break;

        }

    }


}

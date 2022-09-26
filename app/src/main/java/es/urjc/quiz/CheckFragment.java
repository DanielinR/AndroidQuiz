package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class CheckFragment extends Fragment {

    public interface fragmentCheckListener{
        void onInputCheck4Sent(CharSequence input);
    }

    private CheckFragment.fragmentCheckListener listener;
    TextView preguntaTexto;
    CheckBox checkRespuesta1;
    CheckBox checkRespuesta2;
    CheckBox checkRespuesta3;
    Button botonConfirmar;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_checkbox, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas_check);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text_check);
        //inicializo los botones de respuestas
        checkRespuesta1 = (CheckBox) root.findViewById(R.id.respuesta_1_check);
        checkRespuesta2 = (CheckBox) root.findViewById(R.id.respuesta_2_check);
        checkRespuesta3 = (CheckBox) root.findViewById(R.id.respuesta_3_check);
        botonConfirmar = (Button) root.findViewById(R.id.confirmar_check);



        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean correcta = true;
                //en este caso las preguntas tienen las dos respuestas en la respuesta 4 y la respuesta correcta
                int respuesta1 = Integer.parseInt(preguntaDeEsta.getRespuesta4());
                int respuesta2 = preguntaDeEsta.getRespuestaCorrecta();

                //comprobamos que la pregunta marcada se encuentre entre las respuestas
                if(checkRespuesta1.isChecked() && (respuesta1 != 1 && respuesta2 != 1)){
                    correcta = false;
                }
                if(checkRespuesta2.isChecked() && (respuesta1 != 2 && respuesta2 != 2)){
                    correcta = false;
                }
                if(checkRespuesta3.isChecked() && (respuesta1 != 3 && respuesta2 != 3)){
                    correcta = false;
                }

                /*
                String debug;
                if(correcta)
                    debug = "acertada";
                else
                    debug = "fallada";
                Log.println(Log.DEBUG,null,debug + " comprobacion no hay respuesta marcada que no sea");

                 */

                //comprobamos que ha marcado todas las respuestas necesarias
                if(respuesta1 == 1 && !checkRespuesta1.isChecked()){
                    correcta = false;
                }
                if(respuesta1 == 2 && !checkRespuesta2.isChecked()){
                    correcta = false;
                }
                if(respuesta1 == 3 && !checkRespuesta3.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 1 && !checkRespuesta1.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 2 && !checkRespuesta2.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 3 && !checkRespuesta3.isChecked()){
                    correcta = false;
                }
                desactivarBotones();
                if(correcta){
                    listener.onInputCheck4Sent("acertada");
                }else{
                    listener.onInputCheck4Sent("fallada");
                }
            }
        });


        actualizarRespuesta();


        return root;
    }
    private void desactivarBotones(){

        botonConfirmar.setEnabled(false);
        checkRespuesta1.setEnabled(false);
        checkRespuesta2.setEnabled(false);
        checkRespuesta3.setEnabled(false);

    }
    private void actualizarRespuesta(){
        //Log.println(Log.DEBUG,null,preguntaTexto.);
        preguntaTexto.setText(preguntaDeEsta.getTitulo());
        checkRespuesta1.setText(preguntaDeEsta.getRespuesta1());
        checkRespuesta2.setText(preguntaDeEsta.getRespuesta2());
        checkRespuesta3.setText(preguntaDeEsta.getRespuesta3());

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof CheckFragment.fragmentCheckListener) {
            listener = (CheckFragment.fragmentCheckListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentCheckListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
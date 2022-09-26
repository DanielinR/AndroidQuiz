package es.urjc.quiz;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Preguntas4Fragment extends Fragment {


    public interface fragmentPreguntas4Listener{
        void onInputPreguntas4Sent(CharSequence input);
    }

    private fragmentPreguntas4Listener listener;
    TextView preguntaTexto;
    Button botonRespuesta1;
    Button botonRespuesta2;
    Button botonRespuesta3;
    Button botonRespuesta4;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_4_opciones, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas4_opciones);
        if(preguntaDeEsta != null){
            int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
            currentLayout.setBackgroundResource(id);
        }

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text);
        //inicializo los botones de respuestas
        botonRespuesta1 = (Button) root.findViewById(R.id.respuesta_1);
        botonRespuesta2 = (Button) root.findViewById(R.id.respuesta_2);
        botonRespuesta3 = (Button) root.findViewById(R.id.respuesta_3);
        botonRespuesta4 = (Button) root.findViewById(R.id.respuesta_4);



        botonRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(1, root);
            }
        });
        botonRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(2, root);
            }
        });
        botonRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(3, root);
            }
        });

        botonRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(4, root);
            }
        });

        actualizarRespuesta();

        // Inflate the layout for this fragment
        return root;
    }

    private void actualizarRespuesta(){
            //Log.println(Log.DEBUG,null,preguntaTexto.);
            preguntaTexto.setText(preguntaDeEsta.getTitulo());
            botonRespuesta1.setText(preguntaDeEsta.getRespuesta1());
            botonRespuesta2.setText(preguntaDeEsta.getRespuesta2());
            botonRespuesta3.setText(preguntaDeEsta.getRespuesta3());
            botonRespuesta4.setText(preguntaDeEsta.getRespuesta4());

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    private void comprobarRespuesta(int nBoton, View view){

        desactivarBotones();
        if (preguntaDeEsta.getRespuestaCorrecta() == nBoton){
            listener.onInputPreguntas4Sent("acertada");

        }else{

            listener.onInputPreguntas4Sent("fallada");
        }

    }

    private void desactivarBotones(){

        botonRespuesta1.setEnabled(false);
        botonRespuesta2.setEnabled(false);
        botonRespuesta3.setEnabled(false);
        botonRespuesta4.setEnabled(false);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof fragmentPreguntas4Listener) {
            listener = (fragmentPreguntas4Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentPreguntas4Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
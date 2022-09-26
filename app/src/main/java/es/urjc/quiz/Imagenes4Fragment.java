package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class Imagenes4Fragment extends Fragment {


    public interface fragmentImagenes4Listener{
        void onInputImagenes4Sent(CharSequence input);
    }

    private fragmentImagenes4Listener listener;
    TextView preguntaTexto;
    ImageButton imagenRespuesta1;
    ImageButton imagenRespuesta2;
    ImageButton imagenRespuesta3;
    ImageButton imagenRespuesta4;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_4_imagenes, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas4_imagenes);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text);
        //inicializo los botones de respuestas
        imagenRespuesta1 = (ImageButton) root.findViewById(R.id.respuesta_1);
        imagenRespuesta2 = (ImageButton) root.findViewById(R.id.respuesta_2);
        imagenRespuesta3 = (ImageButton) root.findViewById(R.id.respuesta_3);
        imagenRespuesta4 = (ImageButton) root.findViewById(R.id.respuesta_4);



        imagenRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(1, root);
            }
        });
        imagenRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(2, root);
            }
        });
        imagenRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(3, root);
            }
        });

        imagenRespuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarRespuesta(4, root);
            }
        });

        actualizarRespuesta();

        // Inflate the layout for this fragment
        return root;
    }
    private void desactivarBotones(){

        imagenRespuesta1.setEnabled(false);
        imagenRespuesta2.setEnabled(false);
        imagenRespuesta3.setEnabled(false);
        imagenRespuesta4.setEnabled(false);


    }
    private void actualizarRespuesta(){
            //Log.println(Log.DEBUG,null,preguntaTexto.);

        preguntaTexto.setText(preguntaDeEsta.getTitulo());
        imagenRespuesta1.setImageResource(Integer.parseInt(preguntaDeEsta.getRespuesta1()));
        imagenRespuesta2.setImageResource(Integer.parseInt(preguntaDeEsta.getRespuesta2()));
        imagenRespuesta3.setImageResource(Integer.parseInt(preguntaDeEsta.getRespuesta3()));
        imagenRespuesta4.setImageResource(Integer.parseInt(preguntaDeEsta.getRespuesta4()));


    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    private void comprobarRespuesta(int nBoton, View view){
        desactivarBotones();
        if (preguntaDeEsta.getRespuestaCorrecta() == nBoton){

            listener.onInputImagenes4Sent("acertada");

        }else{

            listener.onInputImagenes4Sent("fallada");
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof fragmentImagenes4Listener) {
            listener = (fragmentImagenes4Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentImagenes4Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
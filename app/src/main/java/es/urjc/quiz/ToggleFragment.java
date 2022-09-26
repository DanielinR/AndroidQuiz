package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class ToggleFragment extends Fragment {


    public interface fragmentToggleListener{
        void onInputToggle4Sent(CharSequence input);
    }

    private fragmentToggleListener listener;
    TextView preguntaTexto;
    ToggleButton toggleRespuesta;
    Button botonConfirmar;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_toggle, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas_toggle);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text_toggle);
        //inicializo los botones de respuestas
        toggleRespuesta = (ToggleButton) root.findViewById(R.id.respuesta_1_toggle);
        botonConfirmar = (Button) root.findViewById(R.id.confirmar_toggle);


        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desactivarBotones();
                if(toggleRespuesta.isChecked() && preguntaDeEsta.getRespuestaCorrecta() == 1){
                    listener.onInputToggle4Sent("acertada");
                }else if(!toggleRespuesta.isChecked() && preguntaDeEsta.getRespuestaCorrecta() == 2){
                    listener.onInputToggle4Sent("acertada");
                }else {
                    listener.onInputToggle4Sent("fallada");
                }
            }
        });

        actualizarRespuesta();

        // Inflate the layout for this fragment
        return root;
    }
    private void desactivarBotones(){

        toggleRespuesta.setEnabled(false);
        botonConfirmar.setEnabled(false);

    }
    private void actualizarRespuesta(){
            //Log.println(Log.DEBUG,null,preguntaDeEsta.getTitulo());
            preguntaTexto.setText(preguntaDeEsta.getTitulo());

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof fragmentToggleListener) {
            listener = (fragmentToggleListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentToggleListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
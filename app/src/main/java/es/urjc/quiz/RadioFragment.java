package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class RadioFragment extends Fragment {

    public interface fragmentRadioListener{
        void onInputRadio4Sent(CharSequence input);
    }

    private RadioFragment.fragmentRadioListener listener;
    TextView preguntaTexto;
    RadioButton radioRespuesta1;
    RadioButton radioRespuesta2;
    RadioButton radioRespuesta3;
    Button botonConfirmar;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_radio, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas_radio);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text_radio);
        //inicializo los botones de respuestas
        radioRespuesta1 = (RadioButton) root.findViewById(R.id.respuesta_1_radio);
        radioRespuesta2 = (RadioButton) root.findViewById(R.id.respuesta_2_radio);
        radioRespuesta3 = (RadioButton) root.findViewById(R.id.respuesta_3_radio);
        botonConfirmar = (Button) root.findViewById(R.id.confirmar_radio);

        radioRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioRespuesta2.setChecked(false);
                radioRespuesta3.setChecked(false);
            }
        });
        radioRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioRespuesta1.setChecked(false);
                radioRespuesta3.setChecked(false);
            }
        });
        radioRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioRespuesta1.setChecked(false);
                radioRespuesta2.setChecked(false);
            }
        });

        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desactivarBotones();
                //en este caso las preguntas tienen las dos respuestas en la respuesta 4 y la respuesta correcta
                int respuesta1 = Integer.parseInt(preguntaDeEsta.getRespuesta4());
                int respuesta2 = preguntaDeEsta.getRespuestaCorrecta();

                //comprobamos que la pregunta marcada se encuentre entre las respuestas
                if(radioRespuesta1.isChecked() && (respuesta1 == 1 || respuesta2 == 1)){
                    listener.onInputRadio4Sent("acertada");
                }
                else if(radioRespuesta2.isChecked() && (respuesta1 == 2 || respuesta2 == 2)){
                    listener.onInputRadio4Sent("acertada");
                }
                else if(radioRespuesta3.isChecked() && (respuesta1 == 3 || respuesta2 == 3)){
                    listener.onInputRadio4Sent("acertada");
                }
                else{
                    listener.onInputRadio4Sent("fallada");
                }

            }
        });


        actualizarRespuesta();


        return root;
    }
    private void desactivarBotones(){

        radioRespuesta1.setEnabled(false);
        radioRespuesta2.setEnabled(false);
        radioRespuesta3.setEnabled(false);
        botonConfirmar.setEnabled(false);

    }
    private void actualizarRespuesta(){
        //Log.println(Log.DEBUG,null,preguntaTexto.);
        preguntaTexto.setText(preguntaDeEsta.getTitulo());
        radioRespuesta1.setText(preguntaDeEsta.getRespuesta1());
        radioRespuesta2.setText(preguntaDeEsta.getRespuesta2());
        radioRespuesta3.setText(preguntaDeEsta.getRespuesta3());

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof RadioFragment.fragmentRadioListener) {
            listener = (RadioFragment.fragmentRadioListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentRadioListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
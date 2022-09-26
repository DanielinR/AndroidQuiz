package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class SwitchFragment extends Fragment {

    public interface fragmentSwitchListener{
        void onInputSwitch4Sent(CharSequence input);
    }

    private SwitchFragment.fragmentSwitchListener listener;
    TextView preguntaTexto;
    Switch switchRespuesta1;
    Switch switchRespuesta2;
    Switch switchRespuesta3;
    Button botonConfirmar;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_switch, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas_switch);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text_switch);
        //inicializo los botones de respuestas
        switchRespuesta1 = (Switch) root.findViewById(R.id.respuesta_1_switch);
        switchRespuesta2 = (Switch) root.findViewById(R.id.respuesta_2_switch);
        switchRespuesta3 = (Switch) root.findViewById(R.id.respuesta_3_switch);
        botonConfirmar = (Button) root.findViewById(R.id.confirmar_switch);



        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desactivarBotones();
                boolean correcta = true;
                //en este caso las preguntas tienen las dos respuestas en la respuesta 4 y la respuesta correcta
                int respuesta1 = Integer.parseInt(preguntaDeEsta.getRespuesta4());
                int respuesta2 = preguntaDeEsta.getRespuestaCorrecta();

                //comprobamos que la pregunta marcada se encuentre entre las respuestas
                if(switchRespuesta1.isChecked() && (respuesta1 != 1 && respuesta2 != 1)){
                    correcta = false;
                }
                if(switchRespuesta2.isChecked() && (respuesta1 != 2 && respuesta2 != 2)){
                    correcta = false;
                }
                if(switchRespuesta3.isChecked() && (respuesta1 != 3 && respuesta2 != 3)){
                    correcta = false;
                }


                //comprobamos que ha marcado todas las respuestas necesarias
                if(respuesta1 == 1 && !switchRespuesta1.isChecked()){
                    correcta = false;
                }
                if(respuesta1 == 2 && !switchRespuesta2.isChecked()){
                    correcta = false;
                }
                if(respuesta1 == 3 && !switchRespuesta3.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 1 && !switchRespuesta1.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 2 && !switchRespuesta2.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 3 && !switchRespuesta3.isChecked()){
                    correcta = false;
                }

                if(correcta){
                    listener.onInputSwitch4Sent("acertada");
                }else{
                    listener.onInputSwitch4Sent("fallada");
                }
            }
        });


        actualizarRespuesta();


        return root;
    }
    private void desactivarBotones(){

        switchRespuesta1.setEnabled(false);
        switchRespuesta2.setEnabled(false);
        switchRespuesta3.setEnabled(false);
        botonConfirmar.setEnabled(false);

    }    private void actualizarRespuesta(){
        //Log.println(Log.DEBUG,null,preguntaTexto.);
        preguntaTexto.setText(preguntaDeEsta.getTitulo());
        switchRespuesta1.setText(preguntaDeEsta.getRespuesta1());
        switchRespuesta2.setText(preguntaDeEsta.getRespuesta2());
        switchRespuesta3.setText(preguntaDeEsta.getRespuesta3());

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof SwitchFragment.fragmentSwitchListener) {
            listener = (SwitchFragment.fragmentSwitchListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentSwitchListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
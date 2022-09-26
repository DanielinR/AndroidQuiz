package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;

public class ChipFragment extends Fragment {

    public interface fragmentChipListener{
        void onInputChip4Sent(CharSequence input);
    }

    private ChipFragment.fragmentChipListener listener;
    TextView preguntaTexto;
    Chip chipRespuesta1;
    Chip chipRespuesta2;
    Chip chipRespuesta3;
    Button botonConfirmar;
    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_chip, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas_chip);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text_chip);
        //inicializo los botones de respuestas
        chipRespuesta1 = (Chip) root.findViewById(R.id.respuesta_1_chip);
        chipRespuesta2 = (Chip) root.findViewById(R.id.respuesta_2_chip);
        chipRespuesta3 = (Chip) root.findViewById(R.id.respuesta_3_chip);
        botonConfirmar = (Button) root.findViewById(R.id.confirmar_chip);



        botonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean correcta = true;
                //en este caso las preguntas tienen las dos respuestas en la respuesta 4 y la respuesta correcta
                int respuesta1 = Integer.parseInt(preguntaDeEsta.getRespuesta4());
                int respuesta2 = preguntaDeEsta.getRespuestaCorrecta();

                //comprobamos que la pregunta marcada se encuentre entre las respuestas
                if(chipRespuesta1.isChecked() && (respuesta1 != 1 && respuesta2 != 1)){
                    correcta = false;
                }
                if(chipRespuesta2.isChecked() && (respuesta1 != 2 && respuesta2 != 2)){
                    correcta = false;
                }
                if(chipRespuesta3.isChecked() && (respuesta1 != 3 && respuesta2 != 3)){
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
                if(respuesta1 == 1 && !chipRespuesta1.isChecked()){
                    correcta = false;
                }
                if(respuesta1 == 2 && !chipRespuesta2.isChecked()){
                    correcta = false;
                }
                if(respuesta1 == 3 && !chipRespuesta3.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 1 && !chipRespuesta1.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 2 && !chipRespuesta2.isChecked()){
                    correcta = false;
                }
                if(respuesta2 == 3 && !chipRespuesta3.isChecked()){
                    correcta = false;
                }
                desactivarBotones();
                if(correcta){
                    listener.onInputChip4Sent("acertada");
                }else{
                    listener.onInputChip4Sent("fallada");
                }
            }
        });

        chipRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipRespuesta2.setChecked(false);
                chipRespuesta3.setChecked(false);
            }
        });
        chipRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipRespuesta1.setChecked(false);
                chipRespuesta3.setChecked(false);
            }
        });
        chipRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chipRespuesta1.setChecked(false);
                chipRespuesta2.setChecked(false);
            }
        });


        actualizarRespuesta();


        return root;
    }

    private void desactivarBotones(){

        chipRespuesta1.setEnabled(false);
        chipRespuesta2.setEnabled(false);
        chipRespuesta3.setEnabled(false);
        botonConfirmar.setEnabled(false);

    }
    private void actualizarRespuesta(){
        //Log.println(Log.DEBUG,null,preguntaTexto.);
        preguntaTexto.setText(preguntaDeEsta.getTitulo());
        chipRespuesta1.setText(preguntaDeEsta.getRespuesta1());
        chipRespuesta2.setText(preguntaDeEsta.getRespuesta2());
        chipRespuesta3.setText(preguntaDeEsta.getRespuesta3());

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof ChipFragment.fragmentChipListener) {
            listener = (ChipFragment.fragmentChipListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentChipListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
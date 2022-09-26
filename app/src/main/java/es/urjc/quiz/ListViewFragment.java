package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ListViewFragment extends Fragment {


    public interface fragmentListViewListener{
        void onInputListViewSent(CharSequence input);
    }

    private fragmentListViewListener listener;
    TextView preguntaTexto;
    ListView listaRespuestas;
    ArrayList<String> lista;

    private  Pregunta preguntaDeEsta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_preguntas_listview, container, false);

        //introduzco la imagen correspondiente al monigote de la categoria de la pregunta
        ConstraintLayout currentLayout = (ConstraintLayout) root.findViewById(R.id.layout_preguntas_listview);
        int id = getResources().getIdentifier(preguntaDeEsta.getCategoria(),"drawable",root.getContext().getPackageName());
        currentLayout.setBackgroundResource(id);

        //incializo el texto que contiene la pregunta
        preguntaTexto = (TextView) root.findViewById(R.id.pregunta_text_listview);
        //inicializo los botones de respuestas
        listaRespuestas = (ListView) root.findViewById(R.id.respuestas_listview);


        listaRespuestas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                comprobarRespuesta((position + 1), root);

            }
        });

        actualizarRespuesta(root);

        // Inflate the layout for this fragment
        return root;
    }
    private void desactivarBotones(){

        listaRespuestas.setEnabled(false);


    }
    private void actualizarRespuesta(View view){
        //Log.println(Log.DEBUG,null,preguntaTexto.);
        preguntaTexto.setText(preguntaDeEsta.getTitulo());

        //inicializo variables
        lista = new ArrayList<String>();
        String[] aux;

        aux = preguntaDeEsta.getRespuesta1().split(",");
        lista.add(aux[0]);
        lista.add(aux[1]);

        aux = preguntaDeEsta.getRespuesta2().split(",");
        lista.add(aux[0]);
        lista.add(aux[1]);

        aux = preguntaDeEsta.getRespuesta3().split(",");
        lista.add(aux[0]);
        lista.add(aux[1]);

        aux = preguntaDeEsta.getRespuesta4().split(",");
        lista.add(aux[0]);
        lista.add(aux[1]);

        //introduzco las respuestas en la ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, lista);
        listaRespuestas.setAdapter(adapter);

    }

    public void setPregunta(Pregunta pregunta){
        preguntaDeEsta = pregunta;
    }

    private void comprobarRespuesta(int nBoton, View view){
        desactivarBotones();
        if (preguntaDeEsta.getRespuestaCorrecta() == nBoton){


            listener.onInputListViewSent("acertada");

        }else{

            listener.onInputListViewSent("fallada");
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof fragmentListViewListener) {
            listener = (fragmentListViewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListViewListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
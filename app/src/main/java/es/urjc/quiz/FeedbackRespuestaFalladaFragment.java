package es.urjc.quiz;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class FeedbackRespuestaFalladaFragment extends Fragment {


    public interface fragmentFeedbackFalladaListener{
        void onInputFeedbackFallada4Sent(CharSequence input);
    }

    private fragmentFeedbackFalladaListener listener;
    ImageView imagenPopup;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_popup_fallada, container, false);

        (new Handler()).postDelayed(this::metodoAux, 2000);

        // Inflate the layout for this fragment
        return root;
    }

    private void metodoAux(){
        listener.onInputFeedbackFallada4Sent("nextFallada");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la activity implementa este interfaz
        if (context instanceof fragmentFeedbackFalladaListener) {
            listener = (fragmentFeedbackFalladaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentFeedbackListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
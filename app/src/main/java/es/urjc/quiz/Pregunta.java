package es.urjc.quiz;

import android.util.Log;

public class Pregunta {
    private String categoria;
    private String titulo;
    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    private int respuestaCorrecta;
    private String tipoDePregunta;

    public Pregunta(String categoria_, String titulo_, String respuesta1_,String respuesta2_,String respuesta3_,String respuesta4_,int respuestaCorrecta_, String tipoDePregunta_){
        categoria = categoria_;
        respuesta1 = respuesta1_;
        respuesta2 = respuesta2_;
        respuesta3 = respuesta3_;
        respuesta4 = respuesta4_;
        titulo = titulo_;
        respuestaCorrecta = respuestaCorrecta_;
        tipoDePregunta = tipoDePregunta_;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getTipoDePregunta() {
        return tipoDePregunta;
    }

    public void setTipoDePregunta(String tipoDePregunta) {
        this.tipoDePregunta = tipoDePregunta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

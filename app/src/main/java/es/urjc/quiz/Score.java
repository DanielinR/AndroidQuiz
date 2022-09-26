package es.urjc.quiz;

public class Score {
    private   int aciertosCiencia = 0;
    private  int aciertosDeporte = 0;
    private  int aciertosHistoria = 0;
    private  int aciertosGeografia = 0;
    private  int aciertosEntretenimiento = 0;
    private  int aciertosArte = 0;

    private  int fallosCiencia = 0;
    private  int fallosDeporte = 0;
    private  int fallosHistoria = 0;
    private  int fallosGeografia = 0;
    private  int fallosEntretenimiento = 0;
    private  int fallosArte = 0;

    public void setACero(){
        aciertosCiencia = 0;
        aciertosDeporte = 0;
        aciertosHistoria = 0;
        aciertosGeografia = 0;
        aciertosEntretenimiento = 0;
        aciertosArte = 0;

        fallosCiencia = 0;
        fallosDeporte = 0;
        fallosHistoria = 0;
        fallosGeografia = 0;
        fallosEntretenimiento = 0;
        fallosArte = 0;

    }

    public void setAciertosCienciaIncrementar() {
        this.aciertosCiencia++;
    }

    public void setAciertosDeporteIncrementar() {
        this.aciertosDeporte++;
    }

    public void setAciertosHistoriaIncrementar() {
        this.aciertosHistoria++;
    }

    public void setAciertosGeografiaIncrementar() {
        this.aciertosGeografia++;
    }

    public void setAciertosEntretenimientoIncrementar() {
        this.aciertosEntretenimiento++;
    }

    public void setAciertosArteIncrementar() {
        this.aciertosArte++;
    }

    public void setFallosCienciaIncrementar() {
        this.fallosCiencia++;
    }

    public void setFallosDeporteIncrementar() {
        this.fallosDeporte++;
    }

    public void setFallosHistoriaIncrementar() {
        this.fallosHistoria++;
    }

    public void setFallosGeografiaIncrementar() {
        this.fallosGeografia++;
    }

    public void setFallosEntretenimientoIncrementar() {
        this.fallosEntretenimiento++;
    }

    public void setFallosArteIncrementar() {
        this.fallosArte++;
    }

    public int getAciertosCiencia() {
        return aciertosCiencia;
    }

    public int getAciertosDeporte() {
        return aciertosDeporte;
    }

    public int getAciertosHistoria() {
        return aciertosHistoria;
    }

    public int getAciertosGeografia() {
        return aciertosGeografia;
    }

    public int getAciertosEntretenimiento() {
        return aciertosEntretenimiento;
    }

    public int getAciertosArte() {
        return aciertosArte;
    }

    public int getFallosCiencia() {
        return fallosCiencia;
    }

    public int getFallosDeporte() {
        return fallosDeporte;
    }

    public int getFallosHistoria() {
        return fallosHistoria;
    }

    public int getFallosGeografia() {
        return fallosGeografia;
    }

    public int getFallosEntretenimiento() {
        return fallosEntretenimiento;
    }

    public int getFallosArte() {
        return fallosArte;
    }
}

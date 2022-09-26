package es.urjc.quiz;

public class ComunicadorScore {
    private static Score scoreStatic = null;

    public static Score getScoreStatic() {
        return scoreStatic;
    }

    public static void setScoreStatic(Score scoreStatic) {
        ComunicadorScore.scoreStatic = scoreStatic;
    }
}

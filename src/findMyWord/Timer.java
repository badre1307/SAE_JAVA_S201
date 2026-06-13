package findMyWord;

public class Timer {

    private long debut;
    private long fin;

    public void top() {
        debut = System.currentTimeMillis();
    }

    public void stop() {
        fin = System.currentTimeMillis();
    }

    public long recupererTemps() {
        return (fin - debut) / 1000;
    }
}

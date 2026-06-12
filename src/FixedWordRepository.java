
public class FixedWordRepository implements WordRepository {
    private final String motS;
    private final Word mot;
    @Override
    public Word getWord() {
        return mot;
    }
    public FixedWordRepository(String s) {
        motS = s;
        this.mot = new Word(motS);
    }

}

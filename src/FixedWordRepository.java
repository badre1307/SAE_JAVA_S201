
public class FixedWordRepository implements WordRepository {
    private String mot; 
    public String getWord() {
        return mot;
    }
    public FixedWordRepository(String s) {
        mot = s;
    }

}

package findMyWord;
import words.WordSet;
import java.io.IOException;
import words.JsonWordSet;


public class RandomWordRepository extends WordRepository {
    private final WordSet listeMot;

    public RandomWordRepository() throws IOException {
        this.listeMot = new JsonWordSet("data/mots.json");
    }

    @Override
    public Word getWord() {
        return new Word(this.listeMot.random());
    }
}

import words.WordSet;

import java.io.IOException;

import words.JsonWordSet;
public class RandomWordRepository implements WordRepository {
    private WordSet liste_mot;
    @Override
    public Word getWord() {
        Word mot = new Word(liste_mot.random());
        return mot;
    }

    public RandomWordRepository() throws IOException {
        this.liste_mot = new JsonWordSet("data/mots.json");
    }
}

import words.WordSet;

import java.io.IOException;

import words.JsonWordSet;
public class RandomWordRepository implements WordRepository {
    private WordSet liste_mot = new JsonWordSet("data/mots.json");

    public String getWord() {
        
        return liste_mot.random();

    }

}
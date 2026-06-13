import words.*;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    try {
        WordRepository repo = new RandomWordRepository();
        Game nvjeu = new Game(repo);
  }
  catch (IOException e) {
    System.out.println("problème avec le chemin du fichier mots.json : " + e.getMessage());
  }
}
}
import words.*;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    try {
        Game nvjeu = new Game();
  }
  catch (IOException e) {
    System.out.println("problème avec le chemin du fichier mots.json : " + e.getMessage());
  }
}
}
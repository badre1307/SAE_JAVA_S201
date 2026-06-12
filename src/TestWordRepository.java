import java.io.IOException;

public class TestWordRepository {
     // faire les tests rapidement
      public static void main(String[] args) throws IOException {


            WordRepository mot = new FixedWordRepository("abdel"); 
            System.out.println("Mot fixe : " + mot.getWord());          


    }
}
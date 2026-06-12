
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            WordRepository fdp = new RandomWordRepository();
            WordRepository fdp2 = new FixedWordRepository("ntm"); 
            System.out.println("mot aléatoire : " + fdp.getWord());  
            System.out.println("Mot fixe : " + fdp2.getWord());          
        }
        catch (Exception e) {
            System.out.println("y'a un probleme avec le fichier mots.json");
            System.out.println(e.getMessage());
        }

    }
}
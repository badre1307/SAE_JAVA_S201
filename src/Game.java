

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private boolean enCours = true;
    private Word dernierMotSaisi;
    private Word motSecret;
    private final String nomJoueur;
    private int nombreEssai = 0;
    private final static int nombreEssaiMax = 6;

    public Game() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Saisissez votre nom : ");
        this.nomJoueur = scan.nextLine(); 
        System.out.println( " bienvenu : " + nomJoueur + " !");

  
        WordRepository repo = new RandomWordRepository();
            this.motSecret = repo.getWord(); 
            // pour le test : 
            System.out.println(motSecret.getMot());

            while (enCours) {
                //avoir le dernier mot saisi : 
                System.out.println("saisissez votre première tentative : ");
                String mot_saisi = scan.nextLine();
                dernierMotSaisi = new Word(mot_saisi);
                // incrémenter les essais et arrêter la partie après 6 essais ou après avoir trouvé le bon mot : 
                nombreEssai += 1;
                System.out.println(nombreEssai);
                if (nombreEssai == nombreEssaiMax ) {
                    System.out.println("tu as usé tous tes essais fdp");
                    this.enCours = false;
                    scan.close();
                }
                else if (dernierMotSaisi.getMot().matches(motSecret.getMot())) {
                    System.out.println("t'as trouvé le bon mot"); 
                    this.enCours = false;
                }
                

        }
            while (enCours) {
                
        }
  


       


    }



}
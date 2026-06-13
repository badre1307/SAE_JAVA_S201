import java.io.IOException;
import java.util.Scanner;

public class Game {
    private boolean enCours = true;
    private Word dernierMotSaisi;
    private Word motSecret;
    private final String nomJoueur;
    private int nombreEssai = 0;
    private static final int nombreEssaiMax = 6;
    private int points;

    public Game(WordRepository w) throws IOException {
        this.motSecret = w.getWord();

        // À enlever pour le rendu final
        System.out.println(motSecret.getMot());

        System.out.println("========================================");
        System.out.println("FIND MY WORD - BUT1");
        System.out.println("========================================");
        System.out.println();

        Scanner scan = new Scanner(System.in);

        System.out.print("Saisissez votre nom : ");
        this.nomJoueur = scan.nextLine();

        System.out.println();
        System.out.println("Bonjour " + nomJoueur + " !");
        System.out.println();

        System.out.println("[ ] [ ] [ ] [ ] [ ] -> ****** ****** ****** ****** ******");
        System.out.println();

        String[] historique = new String[nombreEssaiMax];

        while (enCours) {
            System.out.print("Tentative " + (nombreEssai + 1) + " : ");
            String motSaisi = scan.nextLine();
            System.out.println();

            try {
                dernierMotSaisi = new Word(motSaisi);
            } catch (IllegalArgumentException e) {
                System.out.println("Mot invalide.");
                System.out.println("Rappel : le mot doit contenir 5 lettres et aucune lettre répétée.");
                System.out.println();
                continue;
            }

            nombreEssai++;

            String[] analyse = new String[Word.getMaxLength()];

            for (int i = 0; i < Word.getMaxLength(); i++) {
                if (dernierMotSaisi.getMot().charAt(i) == motSecret.getMot().charAt(i)) {
                    analyse[i] = "OK";
                } else if (motSecret.getMot().contains(String.valueOf(dernierMotSaisi.getMot().charAt(i)))) {
                    analyse[i] = "PRESENT";
                } else {
                    analyse[i] = "ABSENT";
                }
            }

            String ligneHistorique = "";

            for (int i = 0; i < Word.getMaxLength(); i++) {
                ligneHistorique += "[ " + dernierMotSaisi.getMot().charAt(i) + " ]";
            }

            ligneHistorique += " -> ";

            for (int i = 0; i < analyse.length; i++) {
                ligneHistorique += analyse[i] + " ";
            }

            historique[nombreEssai - 1] = ligneHistorique;

            System.out.println("Historique des essais :");

            for (int i = 0; i < nombreEssai; i++) {
                System.out.println(historique[i]);
            }

            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println();

            if (dernierMotSaisi.getMot().equals(motSecret.getMot())) {
                this.points = nombreEssaiMax - nombreEssai + 1;

                System.out.println("Bravo " + nomJoueur + " !");
                System.out.println("Vous avez trouvé le mot en " + nombreEssai + " essai(s).");
                System.out.println("Vous avez acquis : " + this.points + " point(s).");
                System.out.println();

                this.enCours = false;
            } else if (nombreEssai == nombreEssaiMax) {
                this.points = 0;

                System.out.println("Vous avez utilisé tous vos essais.");
                System.out.println("Partie perdue.");
                System.out.println("Points acquis : " + this.points);
                System.out.println("Mot secret : " + motSecret.getMot());
                System.out.println();

                this.enCours = false;
            }
        }

        scan.close();
    }
}
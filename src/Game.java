import java.io.IOException;
import java.util.Scanner;

public class Game {
    private boolean enCours = true;
    private Word dernierMotSaisi;
    private Word motSecret;
    private final String nomJoueur;
    private int nombreEssai;
    private static final int nombreEssaiMax = 6;
    private int points;
    private int totalPoints;
    private int mancheActuelle = 1;
    private int nombreManches;
    private long tempsSec;
    private String[] historiquePartie;

    public Game(WordRepository w) throws IOException {
        

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

        boolean mancheValide = false;
        while (!mancheValide) {
            System.out.print("Combien de manches voulez-vous jouer? 1, 5, 10 ou 20 : ");
            this.nombreManches = Integer.parseInt(scan.nextLine());
            if (nombreManches == 1 || nombreManches == 5 || nombreManches == 10 || nombreManches == 20) {
                mancheValide = true;
            }
            else {
                System.out.println("Entrée invalide.");
                System.out.println("");
            }
        }

        this.historiquePartie = new String[nombreManches];

        System.out.println("");
        

        

        String[] historique = new String[nombreEssaiMax];

        Timer timer = new Timer();
        while(mancheActuelle != nombreManches+1) {
            this.motSecret = w.getWord();

            // À enlever pour le rendu final
            System.out.println(motSecret.getMot());
            enCours = true;

            System.out.println("Manche " + mancheActuelle + "/" + nombreManches);
            System.out.println("");
            System.out.println("[ ] [ ] [ ] [ ] [ ] -> ****** ****** ****** ****** ******");
            System.out.println();
            nombreEssai = 0;
            timer.top();

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
                    timer.stop();
                    this.tempsSec = timer.recupererTemps();
                    this.points = nombreEssaiMax - nombreEssai + 1;
                    this.totalPoints += this.points;
                    

                    System.out.println("Bravo " + nomJoueur + " !");
                    System.out.println("Vous avez trouvé le mot en " + nombreEssai + " essai(s).");
                    System.out.println("Vous avez obtenu : " + this.points + " point(s) à cette manche.");
                    System.out.println("Total des points : " + this.totalPoints + " point(s).");
                    if (tempsSec >= 60) {
                        long minutes = tempsSec / 60;
                        long secondes = tempsSec % 60;
                        System.out.println("Temps pour cette manche : " 
                            + minutes + " minutes " 
                            + secondes + " secondes");
                    } else {
                        System.out.println("Temps pour cette manche : " 
                            + tempsSec + " secondes");
                    }
                    System.out.println();

                    this.enCours = false;
                } else if (nombreEssai == nombreEssaiMax) {
                    timer.stop();
                    this.tempsSec = timer.recupererTemps();
                    this.points = 0;

                    System.out.println("Vous avez utilisé tous vos essais.");
                    System.out.println("Partie perdue.");
                    System.out.println("Points obtenu : " + this.points);
                    System.out.println("Total des points : " + this.totalPoints + " point(s).");
                    if (tempsSec >= 60) {
                        long minutes = tempsSec / 60;
                        long secondes = tempsSec % 60;
                        System.out.println("Temps pour cette manche : " 
                            + minutes + " minutes " 
                            + secondes + " secondes");
                    } else {
                        System.out.println("Temps pour cette manche : " 
                            + tempsSec + " secondes");
                    }
                    System.out.println("Mot secret : " + motSecret.getMot());
                    System.out.println();

                    this.enCours = false;
                }

                historiquePartie[mancheActuelle-1] = "Joueur : " + nomJoueur + " | Mot : " + motSecret.getMot() + " | Manche : " + mancheActuelle + " | Résultat : victoire | Essais : " + nombreEssai + " | Points : " + points + " | Temps : " + tempsSec + " secondes" ;
            }



            mancheActuelle++;
            
        }

        

        boolean attente = true;
        while(attente) {
            System.out.print("Voulez vous voir un historique des manches? oui - non : git");
            String reponse = scan.nextLine();
            if(reponse.equalsIgnoreCase("oui")) {
                System.out.println("");
                System.out.println("Voici l'historique de vos manches : ");
                System.out.println("");
                for(int i = 0; i<nombreManches; i++) {
                    System.out.println(historiquePartie[i]);
                }
                attente = false;
            }

            else if (reponse.equalsIgnoreCase("non")) {
                attente = false;
            }

            else {
                System.out.println("");
                System.out.println("Réponse invalide.");
                System.out.println("");
            }
        }
        



        scan.close();
    }
}
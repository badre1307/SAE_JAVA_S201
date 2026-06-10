public class Word {
    private static int tailleMot = 5;
    private String mot;

    public boolean isValide() {

        if (mot.length() != tailleMot || !this.mot.matches("[a-zA-Z]+")) {
            return false;
        }

        char[] tab = new char[5];

        for (int i = 0; i < tailleMot; i++) {
            tab[i] = mot.charAt(i);
            int count = 0;

            for (int n = 0; n < tab.length; n++) {
                if (tab[n] == mot.charAt(i)) {
                    count += 1;

                    if (count >= 2) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public int getTaille Mot() {
        return tailleMot;
    }

    public String getMot() {
        return this.mot;
    }

    public void setMot(String s) {
        this.mot = s;
    }
}
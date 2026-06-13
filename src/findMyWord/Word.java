package findMyWord;

public class Word {
    private static final int tailleMot = 5;
    private String mot;

    public Word() {
        this.mot = "tales";
    }

    public Word(String m) {
        if (m == null) {
            this.mot = null;
        } else {
            this.mot = m.toLowerCase();
        }

        String erreur = this.raisonInvalide();

        if (erreur != null) {
            throw new IllegalArgumentException(erreur);
        }
    }

    public Word(Word w) {
        this.mot = w.mot;
    }

    public boolean isValide() {
        return this.raisonInvalide() == null;
    }

    public String raisonInvalide() {
        if (this.mot == null) {
            return "Erreur : le mot ne peut pas être null.";
        }

        if (this.mot.length() != tailleMot) {
            return "Erreur : le mot doit contenir exactement 5 lettres.";
        }

        if (!this.mot.matches("[a-zA-Z]+")) {
            return "Erreur : le mot doit contenir uniquement des lettres.";
        }

        for (int i = 0; i < this.mot.length(); i++) {
            for (int j = i + 1; j < this.mot.length(); j++) {
                if (this.mot.charAt(i) == this.mot.charAt(j)) {
                    return "Erreur : le mot ne doit pas contenir de lettre répétée.";
                }
            }
        }

        return null;
    }

    public static int getTailleMot() {
        return tailleMot;
    }

    public static int getMaxLength() {
        return tailleMot;
    }

    public String getMot() {
        return this.mot;
    }

    public void setMot(String s) {
        String init = this.mot;

        if (s == null) {
            this.mot = null;
        } else {
            this.mot = s.toLowerCase();
        }

        String erreur = this.raisonInvalide();

        if (erreur != null) {
            this.mot = init;
            throw new IllegalArgumentException(erreur);
        }
    }

    @Override
    public String toString() {
        String lparl = "";

        for (int i = 0; i < Word.tailleMot; i++) {
            lparl += "[ " + this.mot.charAt(i) + " ] ";
        }

        return lparl;
    }
}
package findMyWord;

public class Word {
    private static final int tailleMot = 5;
    private String mot;

    public Word() {
        this.mot = "tales";
    }

    public Word(String m) {
        this.mot = m.toLowerCase();

        if (!this.isValide()) {
            throw new IllegalArgumentException("mot invalide : " + m);
        }
    }

    public Word(Word w) {
        this.mot = w.mot;
    }

    public boolean isValide() {
        if (this.mot == null || this.mot.length() != tailleMot || !this.mot.matches("[a-zA-Z]+")) {
            return false;
        }

        char[] tab = new char[tailleMot];

        for (int i = 0; i < tailleMot; i++) {
            tab[i] = this.mot.charAt(i);
            int count = 0;

            for (int n = 0; n < tab.length; n++) {
                if (tab[n] == this.mot.charAt(i)) {
                    count++;

                    if (count >= 2) {
                        return false;
                    }
                }
            }
        }

        return true;
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
            throw new IllegalArgumentException("Mot null");
        }

        this.mot = s.toLowerCase();

        if (!this.isValide()) {
            this.mot = init;
            throw new IllegalArgumentException("mot invalide : " + s);
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
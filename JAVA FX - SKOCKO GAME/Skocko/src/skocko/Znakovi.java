package skocko;

public enum Znakovi {
    KONJ('\u265E'),
    TREF('\u2663'),
    PIK('\u2660'),
    HERC('\u2665'),
    KARO('\u2666'),
    ZVEZDA('\u2605');

    private char znak;

    Znakovi(char p) {
        znak = p;
    }

    public static Znakovi f(int i) {
        switch (i) {
            case 0:
                return Znakovi.KONJ;
            case 1:
                return Znakovi.TREF;
            case 2:
                return Znakovi.PIK;
            case 3:
                return Znakovi.HERC;
            case 4:
                return Znakovi.KARO;
            default:
                return Znakovi.ZVEZDA;
        }
    }

    public char vrednost() {
        return this.znak;
    }
}

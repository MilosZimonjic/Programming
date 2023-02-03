package skocko;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.*;

public class Logika {

    public Znakovi[] tacnaKomb;
    public String[] unicode;
    public int poeni;
    /*PRVO SU MI POLJA BILA STATICKA, PA SE POZIVANJEM FUNKCIJE generisiNiz
      napravi random kombinacija za jedno igranje, a posto sam uveo new Game,
      onda u konstruktoru Logika svaki put napravim novu random kombinaciju */

    public Logika() {
        /*      ovu sam kombinaciju koristio za testiranje
                tacnaKomb = new Znakovi[]{Znakovi.HERC, Znakovi.KARO, Znakovi.PIK, Znakovi.KARO};
                                                                                                  */
        tacnaKomb = generisiNiz();
        unicode = new String[]{
                Character.toString(tacnaKomb[0].vrednost()),
                Character.toString(tacnaKomb[1].vrednost()),
                Character.toString(tacnaKomb[2].vrednost()),
                Character.toString(tacnaKomb[3].vrednost()),
        };
        poeni = 35;
    }

    private static Znakovi[] generisiNiz() {
        Random rand = new Random();
        Znakovi[] z = new Znakovi[4];
        for (int i = 0; i < 4; i++)
            z[i] = Znakovi.f(rand.nextInt(6));

        return z;

    }

    public boolean nizOdSvih0(int[] a) {
        for (int broj : a)
            if (broj != 0)
                return false;

        return true;
    }

    public int[] stanjeKombinacije(Label[] labela, int pozicija) {
        /*Konstruisem niz a na sledeci nacin:
         * 0 - znaci pogodjen clan
         * 1 - nije na pravom mestu
         * 2 - nema ga
         */

        /* IDEJA:
            Uporedjujem

        * */

        int[] a = new int[4];
        int[] nizIndeksa = new int[4];
        int l = 0;


        for (int i = 0; i < 4; i++) {
            if (labela[pozicija + i].getText().equals(this.unicode[i]))
                a[i] = 0;
            else {
                a[i] = 2;
                nizIndeksa[l] = i;        //pravim niz od indexa tamo gde su dvojke
                l++;
            }
        }

        List<String> pokusaj = new ArrayList<>();
        List<String> tacna = new ArrayList<>();

        /* pamtim indexe gde su dvojke (u nizu a) pomocu nizIndexa[i]
         *  pravim liste - pokusaj(sastoji se od onoga sto pise u labelama)
         *               - tacnu(sastoji se od znakova tacne kombinacije)
         * u koje ubacujem clanove sa indexima gde su dvojke(tj koristim nizIndexa[i] */

        for (int i = 0; i < l; i++) {
            pokusaj.add(labela[pozicija + nizIndeksa[i]].getText());
            tacna.add(unicode[nizIndeksa[i]]);
        }

        /*idem po listi pokusaj i uporedjujem sa tacnom
         * i ako nadjem pogodak na tom indexu postavim a[nizindexa[i]] = 1
         *  i GLAVNI DEO: OBRISEM IZ TACNE TAJ ELEMENT - da ne bi doslo do ponavljanja!!!*/

        for (int i = 0; i < l; i++) {
            if (pomocnaFUnkcija(tacna, pokusaj.get(i))) {
                a[nizIndeksa[i]] = 1;
                tacna.remove(pokusaj.get(i)); //zbog ove komande spravim liste DA BIH MOGAO DA BRISEM!!!
            }
        }
        Arrays.sort(a);
        return a;
    }

    //trazi objekat u listi
    public static boolean pomocnaFUnkcija(List<String> lista, String objekt) {
        for (String s : lista)
            if (s.equals(objekt))
                return true;
        return false;
    }

    /*npr dobijem stanjeNiz = {0,0,0,0} - funkcija oboji krugove: crveno crveno crveno crveno
     *                       = {0,0,1,1} - funkcija oboji krugove: crveno crveno zuto zuto
     *                       = {0,0,1,2} - funkcija oboji krugove: crveno crveno zuto,
     * a ovaj poslednji je po deafultu siv ;) */
    public void upisiUKruzice(Circle[] c, int pozicija, int[] stanjeNiz) {

        for (int i = 0; i < 4; i++, pozicija++)
            if (stanjeNiz[i] == 0)
                c[pozicija].setFill(Paint.valueOf("#FE0000"));
            else if (stanjeNiz[i] == 1)
                c[pozicija].setFill(Paint.valueOf("#F2FE00"));

    }


}

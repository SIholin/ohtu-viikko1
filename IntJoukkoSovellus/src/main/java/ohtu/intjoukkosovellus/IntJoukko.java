
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int ALOITUSKOKO = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kokoero;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] jononLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        jononLuvut = new int[ALOITUSKOKO];
        for (int i = 0; i < jononLuvut.length; i++) {
            jononLuvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kokoero = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        jononLuvut = new int[kapasiteetti];
        for (int i = 0; i < jononLuvut.length; i++) {
            jononLuvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kokoero = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        muuttujienTarkastaja(kapasiteetti, kasvatuskoko);
        jononLuvut = new int[kapasiteetti];
        for (int i = 0; i < jononLuvut.length; i++) {
            jononLuvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kokoero = kasvatuskoko;

    }
    public void muuttujienTarkastaja(int kapasiteetti, int kasvatuskoko) {
         if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
    }
    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            jononLuvut[0] = luku;
            alkioidenLkm++;
            return true;
        } 
        if (!kuuluu(luku)) {
            return josLukuEiKuulu(luku);
        }
        return false;
    }
    public boolean josLukuEiKuulu(int luku) {
        jononLuvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % jononLuvut.length == 0) {
                int[] taulukkoOld = new int[jononLuvut.length];
                taulukkoOld = jononLuvut;
                kopioiTaulukko(jononLuvut, taulukkoOld);
                jononLuvut = new int[alkioidenLkm + kokoero];
                kopioiTaulukko(taulukkoOld, jononLuvut);
            }
            return true;
    }
    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == jononLuvut[i]) {
                on++;
            }
        }
        return on > 0;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == jononLuvut[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                jononLuvut[kohta] = 0;
                break;
            }
        }
        return poistaminenOnnistuuko(kohta);
    }

    public boolean poistaminenOnnistuuko(int kohta) {
        if (kohta != -1) {
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
               int apu = jononLuvut[j];
                jononLuvut[j] = jononLuvut[j + 1];
                jononLuvut[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;   
        }
        return false;
    }
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + jononLuvut[0] + "}";
        } else {
            return tulostuksenMuotoilu();
        }
    }
    public String tulostuksenMuotoilu() {
        String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += jononLuvut[i];
                tuotos += ", ";
            }
            tuotos += jononLuvut[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
    }
    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = jononLuvut[i];
        }
        return taulu;
    }
   public static IntJoukko rungonMuodostus(IntJoukko a, IntJoukko b, String tapahtuma) {
       IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        if (tapahtuma.equals("y")) {
            return yhdisteenToiminta(aTaulu, bTaulu, x);
        } else if (tapahtuma.equals("l")) {
            return leikkauksenToiminta(bTaulu, bTaulu, x);
        } else {
            return erotuksenToiminta(bTaulu, bTaulu, x);
        }
        
   } 
   public static IntJoukko yhdisteenToiminta(int[] aJono, int[] bJono, IntJoukko c) {
       for (int i = 0; i < aJono.length; i++) {
            c.lisaa(aJono[i]);
        }
        for (int i = 0; i < bJono.length; i++) {
            c.lisaa(bJono[i]);
        }
        return c;
   }
    public static IntJoukko yhdiste(IntJoukko joukko1, IntJoukko joukko2) {
        String yhdiste = "y";
        return rungonMuodostus(joukko1, joukko2, yhdiste);
        
    }
    public static IntJoukko leikkauksenToiminta(int[] taulu1, int[] taulu2, IntJoukko y) {
         for (int i = 0; i < taulu1.length; i++) {
            for (int j = 0; j < taulu2.length; j++) {
                if (taulu1[i] == taulu2[j]) {
                    y.lisaa(taulu2[j]);
                }
            }
        }
        return y;
    }

    public static IntJoukko leikkaus(IntJoukko ensimmainen, IntJoukko toinen) {
        String leikkaus = "l";
        return rungonMuodostus(ensimmainen, toinen, leikkaus);
    }
    public static IntJoukko erotuksenToiminta(int[] tauluA, int[] tauluB, IntJoukko z) {
         for (int i = 0; i < tauluA.length; i++) {
            z.lisaa(tauluA[i]);
        }
        for (int i = 0; i < tauluB.length; i++) {
            z.poista(i);
        }
        return z;
    }
    public static IntJoukko erotus (IntJoukko eka, IntJoukko toka) {
        String erotus = "e";
        return rungonMuodostus(eka, toka, erotus);
       
    }
        
}
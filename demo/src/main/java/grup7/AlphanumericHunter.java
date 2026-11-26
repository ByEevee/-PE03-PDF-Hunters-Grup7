package grup7;

public class AlphanumericHunter {

    private static final char PRIMERA_LLETRA = 'c';
    private static final char[] SEGONA_LLETRA_CONJUNT = {'a', 'e', 'i', 'o', 'u', 't', 'r'};
    private static final String PATRO = "c?NNNN";
    private static final String RUTA_PDF = "demo/documents/02_projecte_secret.pdf";

    public record Resultat(long combinacionsPossibles, long intents, double tempsSegons, double intentsPerSegon, String contrasenyaTrobada) {
    }

    public static Resultat executarMissio2() {
        long combinacionsPossibles = (long) SEGONA_LLETRA_CONJUNT.length * 10000L;
        long intents = 0;
        String trobada = null;
        long inici = System.nanoTime();

        outer:
        for (char segonaLletra : SEGONA_LLETRA_CONJUNT) {
            for (int n = 0; n <= 9999; n++) {
                String candidata = "" + PRIMERA_LLETRA + segonaLletra + String.format("%04d", n);
                intents++;
                if (PDFChecker.comprovarPassword(RUTA_PDF, candidata)) {
                    trobada = candidata;
                    break outer;
                }
            }
        }

        long fi = System.nanoTime();
        double tempsSegons = (fi - inici) / 1_000_000_000.0;
        double intentsPerSegon = tempsSegons > 0 ? intents / tempsSegons : intents;

        Resultat resultat = new Resultat(combinacionsPossibles, intents, tempsSegons, intentsPerSegon, trobada);
        mostrarResultat(resultat);
        return resultat;
    }

    private static void mostrarResultat(Resultat resultat) {
        System.out.println("=== MISSIÓ 2: ALPHANUMERIC HUNTER ===");
        System.out.println("Patró: " + PATRO);
        System.out.println("Combinacions possibles: " + resultat.combinacionsPossibles());
        if (resultat.contrasenyaTrobada() != null) {
            System.out.println("Contrasenya trobada: " + resultat.contrasenyaTrobada());
        } else {
            System.out.println("Contrasenya no trobada");
        }
        System.out.println("Intents realitzats: " + resultat.intents());
        System.out.printf("Temps emprat: %.3f segons%n", resultat.tempsSegons());
        System.out.printf("Velocitat mitjana: %.2f intents/segon%n", resultat.intentsPerSegon());
    }
}
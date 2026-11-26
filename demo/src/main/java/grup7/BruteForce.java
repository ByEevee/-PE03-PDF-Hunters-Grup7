package grup7;

public class BruteForce {

    private static final String RUTA_PDF = "demo/documents/01_caixa_forta.pdf";
    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 9999;

    public record Resultat(String contrasenya, int intents, double tempsSegons) {
    }

    public Resultat executarMissio1() {
        return executarMissio1(RUTA_PDF);
    }

    public Resultat executarMissio1(String rutaPdf) {
        System.out.println("=== MISSIÓ 1: PIN BREAKER ===");

        long inici = System.nanoTime();
        int intents = 0;
        String contrasenyaTrobada = null;

        for (int i = MIN_PIN; i <= MAX_PIN; i++) {
            String pin = String.format("%04d", i);
            intents++;

            if (PDFChecker.comprovarPassword(rutaPdf, pin)) {
                contrasenyaTrobada = pin;
                break;
            }
        }

        double tempsSegons = (System.nanoTime() - inici) / 1_000_000_000.0;

        if (contrasenyaTrobada != null) {
            System.out.println("Contrasenya trobada: " + contrasenyaTrobada);
        } else {
            System.out.println("Contrasenya no trobada.");
        }
        System.out.println("Intents: " + intents);
        System.out.printf("Temps: %.4f s%n", tempsSegons);

        return new Resultat(contrasenyaTrobada, intents, tempsSegons);
    }
}
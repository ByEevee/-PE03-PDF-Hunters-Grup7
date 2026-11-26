package grup7;

import java.util.List;
import java.util.function.Function;

public class PasswordVariants {


    private static final String RUTA_PDF = "demo/documents/04_arxiu_ultrasecret.pdf";
    private static final String RUTA_DICCIONARI = "demo/resources/diccionari.txt";


    private static final List<Function<String, String>> REGLES_TRANSFORMACIO =
            List.of(PasswordVariants::capitalitzarInicial);

    public record Resultat(int paraulesDisponibles, long variantsGenerades, long variantsComprovades, double tempsSegons, String contrasenyaTrobada) {
    }

    public Resultat executarMissio4() {
        return executarMissio4(RUTA_PDF, RUTA_DICCIONARI);
    }

    public Resultat executarMissio4(String rutaPdf, String rutaDiccionari) {
        System.out.println("=== MISSIÓ 4: FINAL BOSS ===");

        List<String> paraules = DictionaryAttack.carregarDiccionari(rutaDiccionari);
        if (paraules == null || paraules.isEmpty()) {
            return new Resultat(0, 0, 0, 0.0, null);
        }

        int paraulesDisponibles = paraules.size();
        long variantsGenerades = (long) paraulesDisponibles * 100L * REGLES_TRANSFORMACIO.size();
        System.out.println("Paraules disponibles: " + paraulesDisponibles);
        System.out.println("Variants generades: " + variantsGenerades);

        long inici = System.nanoTime();
        long variantsComprovades = 0;
        String contrasenyaTrobada = null;

        outer:
        for (String paraula : paraules) {
            for (Function<String, String> regla : REGLES_TRANSFORMACIO) {
                String base = regla.apply(paraula);
                for (int n = 0; n <= 99; n++) {
                    String candidata = base + String.format("%02d", n);
                    variantsComprovades++;
                    if (PDFChecker.comprovarPassword(rutaPdf, candidata)) {
                        contrasenyaTrobada = candidata;
                        break outer;
                    }
                }
            }
        }

        double tempsSegons = (System.nanoTime() - inici) / 1_000_000_000.0;

        if (contrasenyaTrobada != null) {
            System.out.println("Contrasenya trobada: " + contrasenyaTrobada);
        } else {
            System.out.println("Contrasenya no trobada.");
        }
        System.out.println("Variants comprovades: " + variantsComprovades);
        System.out.printf("Temps: %.4f s%n", tempsSegons);

        return new Resultat(paraulesDisponibles, variantsGenerades, variantsComprovades, tempsSegons, contrasenyaTrobada);
    }

    private static String capitalitzarInicial(String paraula) {
        if (paraula.isEmpty()) {
            return paraula;
        }
        return Character.toUpperCase(paraula.charAt(0)) + paraula.substring(1);
    }
}
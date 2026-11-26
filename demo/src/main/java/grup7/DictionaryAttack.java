package grup7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DictionaryAttack {

    private static final String RUTA_PDF = "demo/documents/03_contrasenya_director.pdf";
    private static final String RUTA_DICCIONARI = "demo/resources/diccionari.txt";

    public record Resultat(String contrasenya, int paraulesDisponibles, int intents, double tempsSegons) {
    }

    public Resultat executarMissio3() {
        return executarMissio3(RUTA_PDF, RUTA_DICCIONARI);
    }

    public Resultat executarMissio3(String rutaPdf, String rutaDiccionari) {
        System.out.println("=== MISSIÓ 3: DICTIONARY ATTACK ===");

        List<String> paraules = carregarDiccionari(rutaDiccionari);
        if (paraules == null || paraules.isEmpty()) {
            return new Resultat(null, 0, 0, 0.0);
        }

        int paraulesDisponibles = paraules.size();
        System.out.println("Paraules disponibles: " + paraulesDisponibles);

        long inici = System.nanoTime();
        int intents = 0;
        String contrasenyaTrobada = null;

        for (String paraula : paraules) {
            intents++;
            if (PDFChecker.comprovarPassword(rutaPdf, paraula)) {
                contrasenyaTrobada = paraula;
                break;
            }
        }

        double tempsSegons = (System.nanoTime() - inici) / 1_000_000_000.0;

        if (contrasenyaTrobada != null) {
            System.out.println("Contrasenya trobada: " + contrasenyaTrobada);
        } else {
            System.out.println("Contrasenya no trobada al diccionari.");
        }
        System.out.println("Intents: " + intents);
        System.out.printf("Temps: %.4f s%n", tempsSegons);

        return new Resultat(contrasenyaTrobada, paraulesDisponibles, intents, tempsSegons);
    }

    public static List<String> carregarDiccionari(String rutaDiccionari) {
        Path path = Path.of(rutaDiccionari);

        if (!Files.exists(path)) {
            System.out.println("Error: el fitxer del diccionari no existeix (" + rutaDiccionari + ").");
            return null;
        }

        if (!Files.isReadable(path)) {
            System.out.println("Error: el fitxer del diccionari no es pot llegir (" + rutaDiccionari + ").");
            return null;
        }

        List<String> paraules = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                String neta = linia.trim();
                if (!neta.isEmpty()) {
                    paraules.add(neta);
                }
            }
        } catch (IOException e) {
            System.out.println("Error en llegir el diccionari '" + rutaDiccionari + "': " + e.getMessage());
            return null;
        }

        if (paraules.isEmpty()) {
            System.out.println("Error: el diccionari està buit (" + rutaDiccionari + ").");
            return null;
        }

        return paraules;
    }
}

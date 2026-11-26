package grup7;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    public record Resultat(String estrategia, long intents, double tempsSegons) {
        public double intentsPerSegon() {
            return tempsSegons > 0 ? intents / tempsSegons : 0;
        }
    }

    private final List<Resultat> resultats = new ArrayList<>();

    public void afegeix(BruteForce.Resultat r) {
        if (r.contrasenya() == null && r.intents() == 0) return;
        resultats.add(new Resultat("Força bruta PIN", r.intents(), r.tempsSegons()));
    }

    public void afegeix(AlphanumericHunter.Resultat r) {
        resultats.add(new Resultat("Cerca restringida c?NNNN", r.intents(), r.tempsSegons()));
    }

    public void afegeix(DictionaryAttack.Resultat r) {
        if (r.contrasenya() == null && r.intents() == 0) return;
        resultats.add(new Resultat("Atac de diccionari", r.intents(), r.tempsSegons()));
    }

    public void afegeix(PasswordVariants.Resultat r) {
        if (r.contrasenyaTrobada() == null && r.variantsComprovades() == 0) return;
        resultats.add(new Resultat("Diccionari amb variants", r.variantsComprovades(), r.tempsSegons()));
    }

    public boolean teResultats() {
        return !resultats.isEmpty();
    }

    public void mostraTaulaComparativa() {
        System.out.println("=== ESTADÍSTIQUES ===");

        if (resultats.isEmpty()) {
            System.out.println("Encara no s'ha executat cap missió en aquesta sessió.");
            return;
        }

        System.out.printf("%-25s %12s %12s %15s%n",
                "Estratègia", "Intents", "Temps (s)", "Intents/s");

        for (Resultat r : resultats) {
            System.out.printf("%-25s %12d %12.3f %15.2f%n",
                    r.estrategia(), r.intents(), r.tempsSegons(), r.intentsPerSegon());
        }
    }
}
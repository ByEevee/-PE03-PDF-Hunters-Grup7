package grup7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.iniciar();
    }

    private void iniciar() {
        BruteForce bruteForce = new BruteForce();
        DictionaryAttack dictionaryAttack = new DictionaryAttack();
        PasswordVariants passwordVariants = new PasswordVariants();
        Statistics statistics = new Statistics();
        boolean sortir = false;

        try (Scanner scanner = new Scanner(System.in)) {
            while (!sortir) {
                mostrarMenu();
                String entrada = scanner.nextLine().trim();

                switch (entrada) {
                    case "1":
                        statistics.afegeix(bruteForce.executarMissio1());
                        break;
                    case "2":
                        statistics.afegeix(AlphanumericHunter.executarMissio2());
                        break;
                    case "3":
                        statistics.afegeix(dictionaryAttack.executarMissio3());
                        break;
                    case "4":
                        statistics.afegeix(passwordVariants.executarMissio4());
                        break;
                    case "5":
                        statistics.mostraTaulaComparativa();
                        break;
                    case "0":
                        sortir = true;
                        System.out.println("Sortint de PDF HUNTERS...");
                        break;
                    default:
                        System.out.println("Opció no vàlida. Introdueix un número del menú (0-5).");
                        break;
                }

                System.out.println();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("===== PDF HUNTERS =====");
        System.out.println("1. PIN BREAKER (Missió 1: PIN de 4 dígits)");
        System.out.println("2. ALPHANUMERIC HUNTER (Missió 2: patró c?NNNN)");
        System.out.println("3. DICTIONARY ATTACK (Missió 3: diccionari)");
        System.out.println("4. FINAL BOSS (Missió 4: diccionari + variants)");
        System.out.println("5. ESTADÍSTIQUES (Taula comparativa)");
        System.out.println("0. SORTIR");
        System.out.print("Tria una opció: ");
    }
}
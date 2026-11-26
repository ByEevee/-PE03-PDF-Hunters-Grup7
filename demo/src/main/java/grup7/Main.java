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
        boolean sortir = false;
        
        try (Scanner scanner = new Scanner(System.in)) {
                while (!sortir) {
                    mostrarMenu();
                    String entrada = scanner.nextLine().trim();

                    switch (entrada) {
                        case "1":
                            bruteForce.executarMissio1();
                            break;
                        case "2":
                            AlphanumericHunter.executarMissio2();
                            break;
                        case "3":
                            dictionaryAttack.executarMissio3();                            
                            break;
                        case "4":
                            System.out.println("=== MISSIÓ 4: FINAL BOSS ===");
                            System.out.println("Pendent d'implementar (diccionari + variants sobre documents/04_arxiu_ultrasecret.pdf).");
                            break;
                        case "5":
                            System.out.println("=== ESTADÍSTIQUES ===");
                            System.out.println("Pendent d'implementar (comparativa de les missions executades).");
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
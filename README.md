<div align="center">

# 🔓 PDF Hunters

### PE03 · Recuperació de contrasenyes de documents PDF

Pràctica avaluable 03 de **Programació de Processos i Serveis** · DAM 2 · **Grup 7**

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Apache PDFBox](https://img.shields.io/badge/Apache%20PDFBox-3.0.8-D22128?style=for-the-badge&logo=apache&logoColor=white)

</div>

---

## 📝 Descripció

Aplicació de consola en **Java** que recupera contrasenyes de documents **PDF protegits** mitjançant diferents estratègies de cerca i n'analitza el rendiment.

Presenta un **menú interactiu** des d'on s'executen quatre missions sobre documents PDF diferents i se'n consulten les estadístiques.

---

## 🎯 Missions

| # | Missió | Estratègia | Document |
|:-:|--------|------------|----------|
| 1 | **PIN Breaker** | Força bruta de totes les combinacions de `0000` a `9999` | `01_caixa_forta.pdf` |
| 2 | **Alphanumeric Hunter** | Cerca restringida amb el patró `c?NNNN` *(2a lletra ∈ `{a,e,i,o,u,t,r}`, 4 dígits)* | `02_projecte_secret.pdf` |
| 3 | **Dictionary Attack** | Atac de diccionari amb les paraules de `resources/diccionari.txt` | `03_contrasenya_director.pdf` |
| 4 | **Final Boss** | Variants del diccionari *(inicial majúscula + 2 dígits de `00` a `99`)* | `04_arxiu_ultrasecret.pdf` |
| 📊 | **Estadístiques** | Taula comparativa: intents, temps i velocitat de cada missió | — |

---

## ⚙️ Execució

> L'aplicació s'executa des de la carpeta `demo/`, on es resolen les rutes a `documents/` i `resources/`.

```bash
cd demo
mvn compile
mvn exec:java
```

Un cop iniciada, trieu una opció del menú:

```text
===== PDF HUNTERS =====
1. PIN BREAKER (Missió 1: PIN de 4 dígits)
2. ALPHANUMERIC HUNTER (Missió 2: patró c?NNNN)
3. DICTIONARY ATTACK (Missió 3: diccionari)
4. FINAL BOSS (Missió 4: diccionari + variants)
5. ESTADÍSTIQUES (Taula comparativa)
0. SORTIR
```

Maven descarrega automàticament la dependència d'**Apache PDFBox 3.0.8** definida al `pom.xml`.

---

## 🧩 Classes principals

| Classe | Responsabilitat |
|--------|-----------------|
| `Main` | Menú interactiu que integra les quatre missions i les estadístiques |
| `PDFChecker` | Comprova si una contrasenya obre un PDF *(base compartida per totes les missions)* |
| `BruteForce` | Missió 1 — força bruta d'un PIN de 4 dígits |
| `AlphanumericHunter` | Missió 2 — cerca restringida amb el patró `c?NNNN` |
| `DictionaryAttack` | Missió 3 — atac de diccionari |
| `PasswordVariants` | Missió 4 — diccionari amb generació de variants |
| `Statistics` | Recull i mostra la taula comparativa de les missions |

---

## 📁 Estructura del projecte

```text
PE03/
├── README.md
└── demo/
    ├── pom.xml
    ├── documents/
    │   ├── 01_caixa_forta.pdf
    │   ├── 02_projecte_secret.pdf
    │   ├── 03_contrasenya_director.pdf
    │   └── 04_arxiu_ultrasecret.pdf
    ├── resources/
    │   └── diccionari.txt
    └── src/
        └── main/
            └── java/
                └── grup7/
                    ├── Main.java
                    ├── PDFChecker.java
                    ├── BruteForce.java
                    ├── AlphanumericHunter.java
                    ├── DictionaryAttack.java
                    ├── PasswordVariants.java
                    └── Statistics.java
```

---

## 👥 Equip

| Integrant |
|-----------|
| **Ivan Carrasco** |
| **Oriol Sardà** |
| **Gerard Pedrosa** |

<div align="center">

🛠️ **Java 17** · **Maven** · **Apache PDFBox 3.0.8**

</div>

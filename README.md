# iut_sd2_ScrappingProject
Voici un exemple de fichier README pour le projet **iut_sd2_ScrappingProject** hébergé sur GitHub :

---

# **iut_sd2_ScrappingProject**

## **Description**
Le projet **iut_sd2_ScrappingProject** consiste à scraper des données de sites web afin d'en extraire des informations spécifiques. Ce projet fait partie de la formation en **Système de Développement** de l'IUT. Il illustre l'utilisation de techniques de scraping avec Java et des bibliothèques comme **Jsoup** pour récupérer des données sur des pages web, les traiter et les exporter dans un format structuré (comme un fichier CSV).

---

## **Fonctionnalités**
- **Scraping de données :**
  - Extraction d'informations à partir de pages web spécifiques en utilisant Jsoup.
  - Analyse de contenu HTML pour récupérer les données pertinentes telles que les scores, les statistiques, et autres informations spécifiques.

- **Exportation des résultats :**
  - Les données extraites sont enregistrées dans un fichier **CSV** pour un traitement et une analyse ultérieurs.
  
- **Navigation entre les pages :**
  - Le projet prend en charge la navigation entre plusieurs pages, suivant les liens "next" pour collecter des données de manière séquentielle.

- **Personnalisation :**
  - Facilité de modification pour s'adapter à différents sites web ou types de données à extraire.

---

## **Installation et Prérequis**

### **Prérequis :**
- **Java Development Kit (JDK)** version 8 ou supérieure.
- **Bibliothèque Jsoup** pour le parsing HTML : [Télécharger Jsoup](https://jsoup.org/download).

### **Installation :**
1. **Cloner le projet depuis GitHub :**
   ```bash
   git clone https://github.com/TommyRahita/iut_sd2_ScrappingProject.git
   cd iut_sd2_ScrappingProject
   ```

2. **Ajouter la bibliothèque Jsoup au classpath :**
   - Téléchargez **Jsoup** depuis le site officiel, puis ajoutez le fichier JAR à votre projet.
   
3. **Compiler le programme :**
   ```bash
   javac -cp jsoup-<version>.jar src/*.java
   ```

4. **Exécuter le programme :**
   ```bash
   java -cp .:jsoup-<version>.jar src.MainClass
   ```

---

## **Structure du projet**

```bash
iut_sd2_ScrappingProject/
├── src/                     # Dossier contenant les fichiers source Java
│   ├── MainClass.java        # Classe principale du projet
│   └── Scraper.java          # Classe pour gérer le scraping des données
├── lib/                     # Bibliothèque externe (Jsoup)
├── data/                    # Dossier pour stocker les fichiers CSV exportés
├── README.md                # Ce fichier
└── pom.xml                  # Fichier de configuration Maven (si applicable)
```

---

## **Exemple de sortie (CSV)**
Après l'exécution du programme, un fichier CSV sera généré dans le dossier **data**. Un exemple de ce fichier pourrait ressembler à ceci :

| Date       | Match           | Équipe Gagnante | Équipe Perdante | Score Gagnant | Score Perdant | Meilleur Joueur (Points) | Points | Meilleur Joueur (Rebonds) | Rebonds |
|------------|-----------------|-----------------|-----------------|---------------|---------------|--------------------------|--------|---------------------------|---------|
| 2025-01-01 | Lakers vs Nets  | Lakers          | Nets            | 110           | 98            | LeBron James             | 30     | Anthony Davis             | 15      |
| 2025-01-01 | Bulls vs Celtics| Bulls           | Celtics         | 120           | 115           | Zach LaVine              | 40     | Nikola Vucevic            | 12      |

---

## **Personnalisation**

- **URL de scraping :**
  - Vous pouvez modifier l'URL de départ dans le code afin de scraper des données sur différents sites ou sur différentes périodes. Exemple de changement dans le fichier source :
    ```java
    String url = "https://www.exemple.com";
    ```

- **Nombre de pages à scraper :**
  - Modifiez le paramètre `maxPages` dans le code pour définir combien de pages seront analysées.

---

## **Limitations**
1. **Changement de structure HTML :**  
   Le programme dépend de la structure actuelle des pages web. Si la structure du site change, le code devra être ajusté pour s'adapter aux nouvelles balises HTML.
   
2. **Respect des conditions d'utilisation :**  
   Veuillez vérifier que vous avez l'autorisation de scraper le site cible et respectez les conditions d'utilisation et les limitations techniques du site.

3. **Blocage potentiel par le site :**  
   Un trop grand nombre de requêtes en peu de temps peut entraîner un blocage temporaire par le site web cible. Il est conseillé d'ajouter des délais entre les requêtes pour éviter ce problème.

---

## **Contributeurs**

- **Développeur Principal :** [Tommy Rahita](https://github.com/TommyRahita)
- **Contact :** tommy.rahita@exemple.com

---

## **Licence**
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser, de le modifier et de le redistribuer.

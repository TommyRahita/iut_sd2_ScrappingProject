# **iut_sd2_ScrappingProject**

## **Description**
**iut_sd2_ScrappingProject** est un projet de scraping web développé en Java dans le cadre de l'IUT SD2. Ce projet utilise des techniques de scraping pour extraire des données à partir de pages web spécifiques. L'objectif est d'analyser et de traiter ces données pour en extraire des informations utiles.

---

## **Fonctionnalités**
- **Scraping web :**  
  Ce projet extrait des informations spécifiques depuis des pages web, en utilisant la bibliothèque [Jsoup](https://jsoup.org/). Les données collectées peuvent inclure des détails sur des matchs, des événements ou tout autre élément pertinent en fonction de la configuration du projet.

- **Enregistrement des données :**  
  Après avoir collecté les données, le programme les enregistre dans un format structuré (par exemple, CSV) pour faciliter l'analyse et l'utilisation ultérieure.

---

## **Installation et Configuration sur Eclipse IDE**

### **1. Cloner le projet**
Pour récupérer le projet sur votre machine, vous devez d'abord le cloner depuis GitHub. Exécutez la commande suivante dans votre terminal ou utilisez l'interface Eclipse pour cloner le projet :

```bash
git clone https://github.com/TommyRahita/iut_sd2_ScrappingProject.git
```

### **2. Ouvrir le projet dans Eclipse**
1. Lancez **Eclipse IDE**.
2. Allez dans **File** > **Import**.
3. Sélectionnez **Git** > **Projects from Git**.
4. Sélectionnez **Clone URI** et entrez l'URL de votre dépôt GitHub :  
   `https://github.com/TommyRahita/iut_sd2_ScrappingProject.git`
5. Après avoir cloné le projet, sélectionnez **Import as existing Eclipse projects** et cliquez sur **Finish**.

### **3. Ajouter les dépendances**
Le projet utilise la bibliothèque [Jsoup](https://jsoup.org/), une dépendance essentielle pour le scraping des pages web. Voici comment ajouter Jsoup dans votre projet Eclipse :

#### **Via Maven** :
Si vous utilisez Maven pour gérer les dépendances dans votre projet, ajoutez la dépendance suivante dans votre fichier `pom.xml` :

```xml
<dependencies>
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.14.3</version>
    </dependency>
</dependencies>
```

#### **Via JAR** :
1. Téléchargez la dernière version de [Jsoup](https://jsoup.org/download).
2. Dans Eclipse, faites un clic droit sur votre projet > **Build Path** > **Add External Archives**.
3. Sélectionnez le fichier `.jar` que vous avez téléchargé et ajoutez-le au classpath de votre projet.

### **4. Exécuter le projet**
1. Après avoir configuré les dépendances, vous pouvez exécuter le projet directement dans Eclipse :
   - Faites un clic droit sur le fichier contenant la méthode `main` (par exemple, `NBAScraper.java`).
   - Sélectionnez **Run As** > **Java Application**.

### **5. Vérification**
Une fois que vous avez exécuté le projet, le scraping commencera et les données collectées seront enregistrées dans un fichier (par exemple, `scraped_data.csv`) dans le répertoire de votre projet.

---

## **Structure du Projet**
Le projet est structuré de la manière suivante :

```
iut_sd2_ScrappingProject/
│
├── src/
│   └── scraping/
│       └── NBAScraper.java      # Le script principal de scraping
│
├── lib/
│   └── jsoup-1.x.x.jar          # La bibliothèque Jsoup (si vous ne l'utilisez pas avec Maven)
│
└── resources/
    └── scraped_data.csv         # Fichier de sortie des données collectées
```

---

## **Dépendances**
- **Jsoup :** Bibliothèque Java pour le parsing HTML.
- **Java 8+ :** Le projet nécessite Java 8 ou une version supérieure.

---

## **Personnalisation du projet**
Vous pouvez personnaliser les paramètres du scraping en modifiant les variables dans le fichier Java principal, telles que :
- L'URL de départ pour le scraping (`initialURL`).
- Le nombre de pages à scraper (`maxDates`).

---

## **Problèmes connus**
- Le site web cible peut changer sa structure HTML, ce qui nécessite des ajustements dans le code pour que le scraping fonctionne toujours.
- Le programme peut rencontrer des limitations d'accès si trop de requêtes sont envoyées en peu de temps. Il est recommandé d'ajouter des délais entre les requêtes si vous scrapez un grand nombre de pages.

---

## **Licence**
Ce projet est sous licence MIT. Vous êtes libre de l'utiliser, de le modifier et de le redistribuer.

---

## **Contributeurs**
- Développeur : **[Tommy RAHITA Andrew DESVALCY]**
---

## **Questions et Support**
Si vous avez des questions ou besoin de support concernant ce projet, n'hésitez pas à ouvrir une *issue* sur le [dépôt GitHub](https://github.com/TommyRahita/iut_sd2_ScrappingProject/issues).

---

Ce fichier README fournit les étapes nécessaires pour configurer et exécuter le projet sur Eclipse IDE. N'oubliez pas de personnaliser les paramètres de scraping et d'ajouter les dépendances pour que tout fonctionne correctement !

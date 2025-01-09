package scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {
    // Limiter les pages à scraper (de 1 à 60)
    private static final int START_PAGE = 1;
    private static final int END_PAGE = 5;

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("scraped_data.csv")) {
            // Écrire l'en-tête du fichier CSV
            writer.write("Site,Page,Titre,Prix,Auteur,Note,Nombre de Notes\n");

            // Scraping pour chaque site
            for (int page = START_PAGE; page <= END_PAGE; page++) {
                String decitre = "https://www.decitre.fr/rechercher/result/index?p=" + page + "&q=policier";
                System.out.println("Scraping page " + page + " : " + decitre);
                scrapePage(decitre, "Decitre", page, writer);
            }
            for (int page = START_PAGE; page <= END_PAGE; page++) {
                String chapitre = "https://www.chapitre.com/livres/litterature/polars-thrillers-26950shop?etat=&page=" + page;
                System.out.println("Scraping page " + page + " : " + chapitre);
                scrapePage(chapitre, "Chapitre", page, writer);
            }
            for (int page = START_PAGE; page <= END_PAGE; page++) {
                String ammareal = "https://www.ammareal.fr/765-romans-policiers-et-polars?q=page-2&page=" + page;
                System.out.println("Scraping page " + page + " : " + ammareal);
                scrapePage(ammareal, "Ammareal", page, writer);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier CSV : " + e.getMessage());
        }
    }

    // Fonction pour scraper une page spécifique et écrire dans le fichier CSV
    private static void scrapePage(String url, String siteName, int page, FileWriter writer) {
        try {
            // Se connecter à l'URL et récupérer le document HTML
            Document doc = Jsoup.connect(url).get();

            Elements productTitles, productPrices, productAuthors, productRating = null, productNumberOfRates = null;

            if (url.contains("decitre")) {
                productTitles = doc.select(".product-title");
                productPrices = doc.select(".final-price");
                productAuthors = doc.select(".authors");
                productRating = doc.select(".star-container");
                productNumberOfRates = doc.select(".nb-rates");
            } else if (url.contains("chapitre")) {
                productTitles = doc.select(".title");
                productPrices = doc.select(".buy");
                productAuthors = doc.select(".author");
                productNumberOfRates = doc.select(".nb-rates");
            } else if (url.contains("ammareal")) {
                productTitles = doc.select(".product-miniature__name");
                productPrices = doc.select(".product-miniature__price");
                productAuthors = doc.select(".product-miniature__origin");
            } else {
                System.err.println("Site inconnu : " + url);
                return;
            }

            // Extraire les informations et écrire dans le fichier CSV
            for (int i = 0; i < productTitles.size(); i++) {
                String title = i < productTitles.size() ? productTitles.get(i).text().trim() : "Titre non disponible";
                String price = i < productPrices.size() ? productPrices.get(i).text().trim() : "Prix non disponible";
                String author = i < productAuthors.size() ? productAuthors.get(i).text().trim() : "Auteur non disponible";

                // Obtenir et convertir les évaluations
                double totalRating = 0;
                totalRating += convertRating(getSafeRating(productRating, i, 1, 1));
                totalRating += convertRating(getSafeRating(productRating, i, 3, 1));
                totalRating += convertRating(getSafeRating(productRating, i, 5, 1));
                totalRating += convertRating(getSafeRating(productRating, i, 7, 1));
                totalRating += convertRating(getSafeRating(productRating, i, 9, 1));

                // Récupérer le nombre de notes et le convertir en entier
                int numberOfRates = 0;
                if (productNumberOfRates != null && productNumberOfRates.size() > i) {
                    String numberOfRatesText = productNumberOfRates.get(i).text().trim();
                    numberOfRates = extractNumber(numberOfRatesText);
                }

                System.out.println("Titre: " + title);
                System.out.println("Prix: " + price);
                System.out.println("Auteur: " + author);
                System.out.println("Note: " + totalRating);
                System.out.println("Nombre de notes: " + numberOfRates);
                System.out.println("------------------------------------------------");

                // Écrire les données dans le fichier CSV
                writer.write(String.format("%s,%d,\"%s\",\"%s\",\"%s\",%.1f,%d\n",
                        siteName, page, title, price, author, totalRating, numberOfRates));
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération de l'URL : " + url);
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour récupérer les évaluations de manière sécurisée
    public static String getSafeRating(Elements productRating, int index1, int index2, int index3) {
        try {
            return productRating.get(index1).childNode(index2).childNode(index3).attr("data-rating-part");
        } catch (Exception e) {
            return "0"; // Retourne "0" si les nœuds ou l'attribut n'existent pas
        }
    }

    // Méthode pour convertir les évaluations
    public static double convertRating(String rating) {
        switch (rating) {
            case "100":
                return 1.0;
            case "50":
                return 0.5;
            case "0":
                return 0.0;
            default:
                return 0.0; // Par défaut, retourne 0 si l'évaluation n'est pas reconnue
        }
    }

    // Méthode pour extraire le nombre à partir d'une chaîne de texte
    public static int extractNumber(String text) {
        try {
            return Integer.parseInt(text.replaceAll("\\D+", ""));
        } catch (NumberFormatException e) {
            return 0; // Retourne 0 si aucun nombre n'est trouvé
        }
    }
}

package scraping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class NBAScraper {
    public static void main(String[] args) {
        String baseURL = "https://www.basketball-reference.com";
        String initialURL = "https://www.basketball-reference.com/boxscores/?month=1&day=1&year=2025"; // Départ spécifique
        String outputFile = "scraped_data.csv";

        try (FileWriter writer = new FileWriter(outputFile)) {
            // Écrire les en-têtes dans le fichier CSV
            writer.write("Date,Match,Victoire,Défaite,Score Gagnant,Score Perdant,Meilleur Joueur (Points),Points,Meilleur Joueur (Rebonds),Rebonds\n");

            String currentURL = initialURL;
            int maxDates = 10; // Limite à 10 dates
            int dateCount = 0; // Compteur de dates analysées

            // Parcourir les pages jusqu'à ce qu'il n'y ait plus de bouton "next" ou que le nombre de dates soit atteint
            while (currentURL != null && dateCount < maxDates) {
                System.out.println("Scraping la page : " + currentURL);
                currentURL = scrapePage(currentURL, baseURL, writer);
                dateCount++; // Incrémenter le compteur de dates
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier CSV : " + e.getMessage());
        }
    }

    private static String scrapePage(String url, String baseURL, FileWriter writer) {
        try {
            // Extraire la date depuis l'URL
            String[] urlParts = url.split("\\?|&|=");
            String year = "", month = "", day = "";

            for (int i = 0; i < urlParts.length; i++) {
                if (urlParts[i].equals("year")) {
                    year = urlParts[i + 1];
                } else if (urlParts[i].equals("month")) {
                    month = urlParts[i + 1];
                } else if (urlParts[i].equals("day")) {
                    day = urlParts[i + 1];
                }
            }

            String pageDate = String.format("%s-%s-%s", year, month, day);

            // Charger la page
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000)
                    .get();

            // Sélectionner les blocs de matchs
            Elements games = doc.select("div.game_summary.expanded.nohover");

            for (Element game : games) {
                // Récupérer les équipes et scores
                Element winnerRow = game.selectFirst("tr.winner");
                Element loserRow = game.selectFirst("tr.loser");

                if (winnerRow != null && loserRow != null) {
                    String winningTeam = winnerRow.selectFirst("td a").text();
                    String losingTeam = loserRow.selectFirst("td a").text();
                    String winningScore = winnerRow.select("td.right").first().text();
                    String losingScore = loserRow.select("td.right").first().text();

                    // Récupérer les statistiques des meilleurs joueurs (points et rebonds)
                    Element statsTable = game.selectFirst("table.stats");
                    String topScorer = "";
                    String topScorerPoints = "";
                    String topRebounder = "";
                    String topRebounds = "";

                    if (statsTable != null) {
                        Elements rows = statsTable.select("tr");

                        for (Element row : rows) {
                            if (row.select("strong").text().equals("PTS")) { // Vérifie si la ligne contient les points
                                topScorer = row.select("a").text(); // Nom du joueur
                                topScorerPoints = row.select("td.right").text(); // Points
                            } else if (row.select("strong").text().equals("TRB")) { // Vérifie si la ligne contient les rebonds
                                topRebounder = row.select("a").text(); // Nom du joueur
                                topRebounds = row.select("td.right").text(); // Rebonds
                            }
                        }
                    }

                    // Validation pour éviter les champs vides
                    topScorerPoints = topScorerPoints.isEmpty() ? "0" : topScorerPoints;
                    topRebounds = topRebounds.isEmpty() ? "0" : topRebounds;

                    // Écrire les données dans le fichier CSV
                    writer.write(String.format("%s,%s vs %s,%s,%s,%s,%s,%s,%s,%s\n",
                            pageDate, winningTeam, losingTeam, winningTeam, losingTeam, winningScore, losingScore, topScorer, topScorerPoints, topRebounder, topRebounds));
                }
            }

            // Récupérer le lien "next" pour continuer le scraping
            Element nextButton = doc.selectFirst("div.prevnext a.button2.next");
            return (nextButton != null) ? baseURL + nextButton.attr("href") : null;

        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération de l'URL : " + url);
            e.printStackTrace();
            return null;
        }
    }
}
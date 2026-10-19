import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * API cible (refactoring fourni precedemment) :
 * FormatRapport.generer(List<Document>)
 * SortieRapport.ecrire(String) throws IOException
 * NotificationService.notifier(String)
 * RapportService.genererRapport(List<Document>) throws IOException
 */
class RapportServiceTest {

    @Test
    void genereEtNotifieAvecDeFaussesDependances() throws IOException {
        // Arrange : dependances injectees (aucun disque, aucun SMTP)
        FormatRapport fauxFormat = docs -> "contenu-teste";
        StringBuilder sortieMemoire = new StringBuilder();
        SortieRapport fausseSortie = contenu -> sortieMemoire.append(contenu);
        List<String> notifications = new ArrayList<>();
        NotificationServices fauxNotificateur = notifications::add;

        RapportService service = new RapportService(
                fauxFormat, fausseSortie, fauxNotificateur);

        // Act
        service.genererRapport(List.of());

        // Assert
        assertEquals("contenu-teste", sortieMemoire.toString());
        assertEquals(List.of("Rapport genere"), notifications);
    }

    @Test
    void formatCsvUtiliseLesVraisDocumentsDeLaMediatheque() {
        Livre livre = new Livre("Clean Code", 2008, "Robert Martin", 464);
        Dvd dvd = new Dvd("Interstellar", 2014, 169);
        FormatRapport csv = new FormatCSV();

        String resultat = csv.generer(List.of(livre, dvd));

        assertEquals("Clean Code;" + livre.getId() + "\n"
                + "Interstellar;" + dvd.getId() + "\n", resultat);
    }

    @Test
    void formatTexteRespecteLePolymorphismeDocument() {
        Livre livre = new Livre("Java", 2020, "Auteur", 200);
        Revue revue = new Revue("Science", 2024, 12);
        FormatRapport texte = new FormatTexte();

        String resultat = texte.generer(List.of(livre, revue));

        assertEquals("-" + livre.descriptionCourte() + "\n"
                + "-" + revue.descriptionCourte() + "\n", resultat);
    }

    @Test
    void echecEcriturePropageIOExceptionEtNeNotifiePas() {
        FormatRapport fauxFormat = docs -> "contenu";
        SortieRapport sortieEnErreur = contenu -> {
            throw new IOException("Disque indisponible");
        };
        List<String> notifications = new ArrayList<>();
        NotificationServices fauxNotificateur = notifications::add;
        RapportService service = new RapportService(
                fauxFormat, sortieEnErreur, fauxNotificateur);

        IOException erreur = assertThrows(IOException.class,
                () -> service.genererRapport(List.of()));

        assertEquals("Disque indisponible", erreur.getMessage());
        assertTrue(notifications.isEmpty(),
                "Pas de notification si le rapport n'a pas ete ecrit");
    }

    @Test
    void leFormatEstInterchangeableSansModifierLeService() throws IOException {
        Livre livre = new Livre("SOLID", 2026, "Auteur", 150);
        List<Document> documents = List.of(livre);
        StringBuilder csvRecu = new StringBuilder();
        StringBuilder texteRecu = new StringBuilder();
        NotificationServices aucuneNotification = message -> {};

        new RapportService(new FormatCSV(), csvRecu::append, aucuneNotification)
                .genererRapport(documents);
        new RapportService(new FormatTexte(), texteRecu::append, aucuneNotification)
                .genererRapport(documents);

        assertEquals("SOLID;" + livre.getId() + "\n", csvRecu.toString());
        assertEquals("-" + livre.descriptionCourte() + "\n", texteRecu.toString());
    }
}

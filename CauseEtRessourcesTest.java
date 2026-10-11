import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class CauseEtRessourcesTest {

    @Test void cause_chainee() {
        Path absent = Path.of("fichier-qui-n-existe-pas.txt");
        CatalogueCorrompuException ex = assertThrows(
                CatalogueCorrompuException.class,
                () -> ChargeurCatalogue.chargerTitres(absent));
        assertTrue(ex.getMessage().contains("fichier-qui-n-existe-pas.txt"));  // message
        assertInstanceOf(IOException.class, ex.getCause());   // cause conservee
    }

    @Test void rapport_ferme_meme_si_exception() throws MediathequeException {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        EmpruntManager m = new EmpruntManager(c);
        RapportEmprunt rapport = new RapportEmprunt();

        assertThrows(DocumentIndisponibleException.class, () -> {
            try (rapport) {
                m.emprunter("Clean Code");
                rapport.enregistrer("Clean Code");
                m.emprunter("Clean Code");    // leve : close() sera appele
            }
        });

        assertTrue(rapport.estFerme());
        assertEquals(1, rapport.getTitres().size());
    }
}
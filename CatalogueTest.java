import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class CatalogueTest {
@Test
void empruntNominal() throws DocumentIndisponibleException {
 Livre l = new Livre("Clean Code", 2008, "R. Martin", 464);
 l.emprunter();
 assertFalse(l.estDisponible());
 l.rendre();
 assertTrue(l.estDisponible());
}

 @Test
 void double_emprunt_leve_une_exception_metier() throws DocumentIndisponibleException {
     Livre l = new Livre("Refactoring", 1999, "M. Fowler", 448);
     l.emprunter();
     DocumentIndisponibleException ex =
             assertThrows(DocumentIndisponibleException.class, l::emprunter);
     assertTrue(ex.getMessage().contains("Refactoring"));
 }

 @Test
 void recherche_ignore_la_casse() {
     Catalogue<Document> c = new Catalogue<>();
     c.ajouter(new Dvd("Inception", 2010, 148));
     assertTrue(c.rechercherParTitre("INCEPTION").isPresent());
 }
 }
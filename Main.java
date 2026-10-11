
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        catalogue.ajouter(new Dvd("Inception", 2010, 148));
        catalogue.ajouter(new Revue("Science", 2026, 412));
        catalogue.afficherTout();

        Bibliothecaire biblio = new Bibliothecaire("Sara", catalogue);
        biblio.accueillir();
        biblio.chercher("INCEPTION")
                .ifPresent(d -> System.out.println("Trouve : " + d.descriptionCourte()));

        EmpruntManager manager = new EmpruntManager(catalogue);
        String[] demandes = {"Clean Code", "Clean Code", "Science", "Fantome"};

        for (String titre : demandes) {
            try {
                manager.emprunter(titre);
                System.out.println("OK, emprunte : " + titre);
            } catch (DocumentIndisponibleException e) {   // subclasses first...
                System.out.println("Indisponible -> " + e.getMessage());
            } catch (DocumentIntrouvableException e) {
                System.out.println("Introuvable  -> " + e.getMessage());
            } catch (MediathequeException e) {            // ...parent last (required by the compiler)
                System.out.println("Autre erreur -> " + e.getMessage());
            }
        }

        try {
            ChargeurCatalogue.chargerTitres(Path.of("fichier-qui-n-existe-pas.txt"));
        } catch (CatalogueCorrompuException e) {
            System.out.println(e.getMessage());
            System.out.println("Cause : " + e.getCause().getClass().getSimpleName());
        }

        CompteurHashSet<String> fragile = new CompteurHashSet<>();
        fragile.addAll(List.of("a", "b", "c"));
        CompteurSet<String> solide = new CompteurSet<>(new HashSet<>());
        solide.addAll(List.of("a", "b", "c"));
        System.out.println("Heritage    : " + fragile.getNbAjouts());   // 6
        System.out.println("Composition : " + solide.getNbAjouts());    // 3
    }
}
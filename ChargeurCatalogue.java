import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ChargeurCatalogue {

    /** Lit un fichier contenant un titre par ligne. */
    public static List<String> chargerTitres(Path chemin)
            throws CatalogueCorrompuException {
        try (var lecteur = Files.newBufferedReader(chemin)) {   // fermeture garantie
            return lecteur.lines().toList();
        } catch (IOException e) {
            throw new CatalogueCorrompuException(
                    "Echec du chargement : " + chemin, e);     // message utile + cause
        }
    }
}
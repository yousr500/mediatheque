import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SortieFichier implements SortieRapport {

    private final Path chemin;

    public SortieFichier(Path chemin) {

        this.chemin = chemin;
    }

    @Override
    public void ecrire(String contenu) throws IOException {

        Path parent = chemin.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(
                chemin,
                contenu,
                StandardCharsets.UTF_8
        );
    }
}
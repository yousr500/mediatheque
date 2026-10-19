import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RapportService {

    private final FormatRapport format;
    private final SortieRapport sortie;
    private final NotificationServices notification;

    public RapportService(
            FormatRapport format,
            SortieRapport sortie,
            NotificationServices notification
    ) {

        this.format = Objects.requireNonNull(format);
        this.sortie = Objects.requireNonNull(sortie);
        this.notification = Objects.requireNonNull(notification);
    }

    public void genererRapport(
            List<Document> documents
    ) throws IOException {

        // 1. Génération du contenu
        String contenu = format.generer(documents);

        // 2. Sauvegarde du rapport
        sortie.ecrire(contenu);

        // 3. Notification
        notification.notifier("Rapport genere");
    }
}
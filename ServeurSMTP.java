/**
 * Simulation d'un serveur SMTP pour l'atelier SOLID (seance 4).
 * Aucun email reel n'est envoye : cette classe represente le detail
 * technique utilise par NotificationEmail.
 */
public class ServeurSMTP {

    private final String hote;

    public ServeurSMTP(String hote) {
        if (hote == null || hote.isBlank()) {
            throw new IllegalArgumentException("Le serveur SMTP est obligatoire");
        }
        this.hote = hote;
    }

    public void envoyer(String destinataire, String message) {
        if (destinataire == null || destinataire.isBlank()) {
            throw new IllegalArgumentException("Le destinataire est obligatoire");
        }
        if (message == null) {
            throw new IllegalArgumentException("Le message ne peut pas etre null");
        }

        // Simulation : pas de connexion reseau pendant l'atelier.
        System.out.println("[SMTP simule] Serveur : " + hote);
        System.out.println("[SMTP simule] Destinataire : " + destinataire);
        System.out.println("[SMTP simule] Message : " + message);
    }
}

public class NotificationEmail implements NotificationServices {

    private final ServeurSMTP serveur;
    private final String destinataire;

    public NotificationEmail(
            ServeurSMTP serveur,
            String destinataire
    ) {
        this.serveur = serveur;
        this.destinataire = destinataire;
    }

    @Override
    public void notifier(String message) {

        serveur.envoyer(destinataire, message);
    }
}
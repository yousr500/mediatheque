
public class DocumentIndisponibleException extends MediathequeException {
    public DocumentIndisponibleException(String titre) {
        super("Document deja emprunte : " + titre);
    }

    // my addition: for documents that can never be borrowed (e.g. a Revue)
    public DocumentIndisponibleException(String titre, String motif) {
        super("Document indisponible : " + titre + " (" + motif + ")");
    }
}
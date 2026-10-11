public class DocumentIntrouvableException extends MediathequeException {
    public DocumentIntrouvableException(String titre) {
        super("Aucun document au titre : " + titre);
    }

    // my addition: lets us chain a cause (needed for step 5)
    public DocumentIntrouvableException(String titre, Throwable cause) {
        super("Aucun document au titre : " + titre, cause);
    }
}
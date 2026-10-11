public class EmpruntManager {
    private final Catalogue<Document> catalogue;

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
    }

    public void emprunter(String titre) throws MediathequeException {
        // 1. delegate the search to the catalogue; absent → business exception
        Document d = catalogue.rechercherParTitre(titre)
                .orElseThrow(() -> new DocumentIntrouvableException(titre));

        // 2. pattern matching: tests the type AND declares the variable 'e' in one step
        if (!(d instanceof Empruntable e)) {
            throw new DocumentIndisponibleException(titre, "consultation sur place uniquement");
        }

        // 3. borrow (throws DocumentIndisponibleException if already borrowed)
        e.emprunter();
    }
}
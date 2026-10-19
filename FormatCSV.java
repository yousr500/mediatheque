import java.util.List;

public class FormatCSV implements FormatRapport {

    @Override
    public String generer(List<Document> documents) {

        StringBuilder contenu = new StringBuilder();

        for (Document d : documents) {

            contenu.append(d.getTitre())
                    .append(";")
                    .append(d.getId())
                    .append("\n");
        }

        return contenu.toString();
    }
}
import java.util.List;

public class FormatTexte implements FormatRapport {

    @Override
    public String generer(List<Document> documents) {

        StringBuilder contenu = new StringBuilder();

        for (Document d : documents) {

            contenu.append("-")
                    .append(d.descriptionCourte())
                    .append("\n");
        }

        return contenu.toString();
    }
}
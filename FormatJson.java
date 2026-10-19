import java.util.List;

public class FormatJson implements FormatRapport {

    @Override
    public String generer(List<Document> documents) {

        StringBuilder json = new StringBuilder();

        json.append("[\n");

        for (int i = 0; i < documents.size(); i++) {

            Document d = documents.get(i);

            json.append("  {\n");

            json.append("    \"id\": ")
                    .append(d.getId())
                    .append(",\n");

            json.append("    \"titre\": \"")
                    .append(escapeJson(d.getTitre()))
                    .append("\"\n");

            json.append("  }");

            if (i < documents.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]");

        return json.toString();
    }

    private String escapeJson(String texte) {

        if (texte == null) {
            return "";
        }

        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < texte.length(); i++) {

            char c = texte.charAt(i);

            switch (c) {
                case '"':
                    resultat.append("\\\"");
                    break;

                case '\\':
                    resultat.append("\\\\");
                    break;

                case '\n':
                    resultat.append("\\n");
                    break;

                case '\r':
                    resultat.append("\\r");
                    break;

                case '\t':
                    resultat.append("\\t");
                    break;

                default:
                    if (c < 0x20) {
                        resultat.append(
                                String.format("\\u%04x", (int) c)
                        );
                    } else {
                        resultat.append(c);
                    }
            }
        }

        return resultat.toString();
    }
}
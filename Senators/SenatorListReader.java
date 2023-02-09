import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SenatorListReader {
    public static List<Senator> readSenatorList (InputStream in) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(in);
        ArrayList<Senator> senators = new ArrayList<>();
        for (int i = 0; i < tree.size(); i++) {
            String f = tree.get(i).get("first").asText();
            String l = tree.get(i).get("last").asText();
            String s = tree.get(i).get("state").asText();
            String p = tree.get(i).get("party").asText();
            URL pU;
            try {
                pU = new URL(tree.get(i).get("photo_url").asText());
            }
            catch(Exception NullPointerException) {
                pU = null;
            }
            Senator sen = new Senator(f,l,s,p,pU);
            senators.add(sen);
        }
        return senators;
    }
}

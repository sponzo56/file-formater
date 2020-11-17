import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class JsonDatas implements IOutputDatas {

    private String inputFile;
    private ArrayList<Reference> references;
    private ArrayList<Erreur> erreurs;

    public JsonDatas(String inputFile, ArrayList<Reference> references, ArrayList<Erreur> erreurs) {
        this.inputFile = inputFile;
        this.references = references;
        this.erreurs = erreurs;
    }

    public JSONObject createDataObject() {
        JSONArray refsArr = new JSONArray();
        if (this.references != null) {
            for (Reference r : this.references) {
                JSONObject ref = new JSONObject();
                ref.put("numReference", r.getRefNumber());
                ref.put("size", r.getSize());
                ref.put("price", r.getPrice());
                ref.put("type", r.getColor());

                refsArr.add(ref);
            }
        }

        JSONArray errsArr = new JSONArray();
        if (this.erreurs != null) {
            for (Erreur e : this.erreurs) {
                JSONObject err = new JSONObject();
                err.put("line", e.getLine());
                err.put("message", e.getMessage());
                err.put("value", e.getLineData());

                errsArr.add(err);
            }
        }


        JSONObject jsonData = new JSONObject();
        jsonData.put("inputFile", this.inputFile);
        jsonData.put("references", refsArr);
        jsonData.put("errors", errsArr);

        return jsonData;
    }
}

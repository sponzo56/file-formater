import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ChangeFileFormat {
    public static void main(String[] args) throws IOException, FileNotFoundException{
        File file = new File(args[0]);
        BufferedReader br = null;

        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            ArrayList<Reference> refs = new ArrayList<>();
            ArrayList<Erreur> erreurs = new ArrayList<>();
            int lineNumber = 1;
            /*
            Tant qu'il reste des lignes dans le fichier input, si la ligne est valide on créé une référence
            sinon on créé une erreur. Ces dernières s'accumulent dans des tableaux dédiés.
             */
            while ((line = br.readLine()) != null) {
                String[] champs = line.split(Reference.delimiter);
                if (champs.length == Reference.nbChamps) {
                    Reference ref = new Reference(champs[0], champs[1], champs[2], champs[3]);
                    String validation = ref.isValidRef();
                    if (validation.equals("Valid")) {
                        refs.add(ref);
                    } else {
                        Erreur err = new Erreur(line, lineNumber, validation);
                        erreurs.add(err);
                    }
                    lineNumber++;
                }
            }

            if (args[1].toLowerCase().equals("json")) {
                JsonDatas jsonDatas = new JsonDatas(args[0], refs, erreurs);
                createFormatedFile(jsonDatas, args[2]);
            } else if (args[1].toLowerCase().equals("xml")) {
                XmlDatas xmlDatas = new XmlDatas(args[0], refs, erreurs);
                createFormatedFile(xmlDatas, args[2]);
            }
        } catch (FileNotFoundException f){
            System.err.printf("Le fichier %s n'a pas été trouvé", file.toString());
        } catch (IOException e) {
            System.err.println("Impossible de lire le contenu du fichier " + file.toString());
        }
    }

    /*
    Utilisation de l'injection de dépendance par interface (IOutputDatas) dans les paramètres d'une méthode statique
    Permet de gérer les 2 types de fichiers en input
     */
    public static void createFormatedFile(IOutputDatas datas, String outputFile) throws IOException {
        try ( FileWriter output = new FileWriter(outputFile)){

            if (datas.getClass().getSimpleName().equals("JsonDatas")) {
                JSONObject obj = (JSONObject) datas.createDataObject();
                output.write(obj.toJSONString());
                output.flush();
                output.close();
            } else if (datas.getClass().getSimpleName().equals("XmlDatas")){
                Marshaller obj = (Marshaller) datas.createDataObject();
                obj.marshal(datas, output);
            }
            } catch (JAXBException jaxbException) {
                jaxbException.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

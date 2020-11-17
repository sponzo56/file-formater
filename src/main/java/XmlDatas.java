
//import jakarta.xml.bind.JAXBContext;
//import jakarta.xml.bind.JAXBException;
//import jakarta.xml.bind.Marshaller;
//import jakarta.xml.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;

@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDatas implements IOutputDatas {

    private String inputFile;

    @XmlElementWrapper(name = "references")
    @XmlElement(name = "reference")
    private ArrayList<Reference> references;

    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
    private ArrayList<Erreur> errors;

    public XmlDatas() {
    }

    public XmlDatas(String inputFile, ArrayList<Reference> references, ArrayList<Erreur> errors) {
        this.inputFile = inputFile;
        this.references = references;
        this.errors = errors;
    }

    public Object createDataObject() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlDatas.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            return jaxbMarshaller;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}

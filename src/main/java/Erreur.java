import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"line", "message"})
public class Erreur {

    @XmlValue
    private String lineData;
    @XmlAttribute
    private int line;
    @XmlAttribute
    private String message;

    public Erreur() {
    }

    public Erreur(String lineData, int line, String message) {
        this.lineData = lineData;
        this.line = line;
        this.message = message;
    }

    public String getLineData() {
        return lineData;
    }

    public void setLineData(String lineData) {
        this.lineData = lineData;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

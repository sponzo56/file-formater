import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@XmlType(propOrder = {"color", "price", "size", "refNumber"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Reference {

    /*
    Propriétés finales et propres aux références de type Reference
    Pour d'autres potentielles références futures, il faudra créer de nouveaux types et certainement
    créer une interface qu'on utilisera pour implementer ces derniers.
     */
    protected final static String delimiter = ";";
    protected final static int nbChamps = 4;

    private final static int refNumberSize = 10;
    private final static char refNumberType = 'D';
    private final static String[] colors = {"R", "G", "B"};
    private final static char priceType = 'F';
    private final static char sizeType = 'I';

    @XmlAttribute
    private String refNumber;
    @XmlAttribute
    private String color;
    @XmlAttribute
    private String price;
    @XmlAttribute
    private String size;

    public Reference() {
    }

    public Reference(String refNumber, String color, String price, String size) {
        this.refNumber = refNumber;
        this.color = color;
        this.price = price;
        this.size = size;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    /*
    Méthode vérifiant la validité d'une référence de type Reference
     */
    public String isValidRef() {
        String errorMessage = "**";
        if (this.refNumber.length() != Reference.refNumberSize) errorMessage += "Incorrect size for reference number**";
        if (Reference.refNumberType == 'D') {
            for (int i = 0; i < this.refNumber.length(); i++) {
                if (!Character.isDigit(this.refNumber.charAt(i))) errorMessage += "Reference number must only contain digits**";
            }
        }
        List<String> colorss = Arrays.asList(Reference.colors);
        if (!colorss.contains(this.color)) errorMessage += "Incorrect value for color**";
        if (Reference.priceType == 'F') {
            String floatPattern = "^([+-]?\\d*\\.?\\d*)$";
            Pattern pattern = Pattern.compile(floatPattern);
            Matcher matcher = pattern.matcher(this.price);
            if (!matcher.matches()) errorMessage += "Illegal format for price**";
        }
        if (Reference.sizeType == 'I') {
            String intPattern = "^\\d+$";
            Pattern pattern = Pattern.compile(intPattern);
            Matcher matcher = pattern.matcher(this.size);
            if (!matcher.matches()) errorMessage += "Illegal format for size**";
        }
        if (errorMessage == "**") return "Valid";
        else return errorMessage;
    }
}

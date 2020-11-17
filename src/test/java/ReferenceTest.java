import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class ReferenceTest extends TestCase {

    @Test
    public void testIsValidRef1() {
        Reference ref = new Reference("1234567890", "R", "22.20", "50");
        Assert.assertEquals("Valid", ref.isValidRef());
    }

    @Test
    public void testIsValidRef2() {
        Reference  ref = new Reference("1234567800", "Z", "10", "20");
        Assert.assertEquals("**Incorrect value for color**", ref.isValidRef());
    }

    @Test
    public void testIsValidRef3() {
        Reference ref = new Reference("12345678", "G", "10", "20");
        Assert.assertEquals("**Incorrect size for reference number**", ref.isValidRef());
    }

    @Test
    public void testIsValidRef4() {
        Reference ref = new Reference("12345678", "X", "10", "20");
        Assert.assertEquals("**Incorrect size for reference number**Incorrect value for color**", ref.isValidRef());
    }

    @Test
    public void testIsValidRef5() {
        Reference ref = new Reference("0000000000", "B", "10", "20.50");
        Assert.assertEquals("**Illegal format for size**", ref.isValidRef());
    }
}
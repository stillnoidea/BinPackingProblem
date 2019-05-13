package Products;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void compareTo() {
        Product p1 = new Product("p1", 5);
        Product p2 = new Product("p2", 5);
        Product p3 = new Product("p2", 6);
        assertEquals(0,p1.compareTo(p2));
        assertEquals(1,p1.compareTo(p3));
        assertEquals(-1,p3.compareTo(p2));
    }
}
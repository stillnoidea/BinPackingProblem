package BagPackingAlgorithm;

import Products.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {


    @Test
    public void addProduct() {
        Bag b1 = new Bag(500);
        Bag b2 = new Bag(500);
        Product p1= new Product("p1", 100);
        Product p2= new Product("p2", 1000);
        Product p3= new Product("p3", 500);
        assertFalse(b1.addProduct(p2));
        assertTrue(b1.addProduct(p3));
        assertTrue(b2.addProduct(p1));
        assertFalse(b2.addProduct(p3));
    }
}
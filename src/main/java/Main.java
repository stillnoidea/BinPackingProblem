import BagPackingAlgorithm.PackingAlgorithm;
import Products.ProductsMap;

import java.io.File;

public class Main {
    private static final String FILE_NAME = "product_data.csv";
    private static final String FIRST_FIT = "First Fit algorithm: ";
    private static final String MY_ALGORITHM = "My algorithm: ";

    public static void main(String[] args) {
        //getting data from file
        ProductsMap map = new ProductsMap();


        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        map.getMapFromFile(file);

        //setting example products list
        int[] prod = {56967011, 241714011, 56967011, 81851011, 241714011, 65344011};

        //initializing algorithm and running it for Fist Fit and my own solution
        PackingAlgorithm alg = new PackingAlgorithm(prod, 3000);
        System.out.println(FIRST_FIT);
        alg.run(true);
        System.out.println(MY_ALGORITHM);
        alg.run(false);
    }
}

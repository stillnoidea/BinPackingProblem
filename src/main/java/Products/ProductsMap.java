package Products;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProductsMap {
    public static HashMap<Integer, Product> productsMap = new HashMap<>();
    private static final String INVALID_ID = "Id is invalid: ";
    private static final String PRODUCT_NAME = "product name: ";
    private static final String THE_SAME_ID = "Two products with the same id: ";
    private static final String NEW_PRODUCT = "new product ";
    private static final String NEW_LINE = "\n";
    private static final String COMMA = ", ";
    private static final String FILE_NOT_FOUND = "Not found file with products";
    private static final String NULL_IN_PRODUCTS = "Null in line of products value in csv file";

    public boolean getMapFromFile(File file) {
        boolean isValid = false;
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                double weight = Double.parseDouble(nextLine[2]);
                String weightUnit = nextLine[3];

                if (!isIdValid(id)) {
                    if (id < 0) {
                        throw new InvalidProductsValue(INVALID_ID + id + COMMA + PRODUCT_NAME + name);
                    } else {
                        throw new InvalidProductsValue(THE_SAME_ID + nextLine[0] + NEW_LINE + NEW_PRODUCT + nextLine[1]);
                    }
                }
                if (isWeightUnitValid(weightUnit) || isWeightValid(weight)) {
                    Product product = new Product(name, getWeightInGrams(weight, weightUnit));
                    productsMap.put(id, product);
                    isValid = true;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND + file);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(NULL_IN_PRODUCTS);
            e.printStackTrace();
        } catch (InvalidProductsValue e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private boolean isIdValid(int newId) {
        boolean isValid = false;
        if (newId > 0) {
            try {
                isValid = productsMap.get(newId) == null;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    private boolean isWeightValid(double newWeight) {
        return newWeight > 0;
    }

    private boolean isWeightUnitValid(String newUnit) {
        boolean isValid = true;
        try {
            Units.valueOf(newUnit);
        } catch (IllegalArgumentException e) {
            isValid = false;
        }
        return isValid;
    }

    private double getWeightInGrams(double weight, String weightUnit) {
        Enum unit = Units.valueOf(weightUnit);
        return weight * ((Units) unit).toGram;
    }

}

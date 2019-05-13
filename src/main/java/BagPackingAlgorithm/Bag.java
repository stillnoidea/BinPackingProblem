package BagPackingAlgorithm;

import Products.Product;

import java.util.ArrayList;

public class Bag {
    private double maxWeight;
    private double actualWeight;
    private ArrayList<Product> products = new ArrayList<>();

    Bag(double newMaxWeight) {
        maxWeight = newMaxWeight;
        actualWeight = 0;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    ArrayList<Product> getProducts() {
        return products;
    }


    boolean addProduct(Product newProduct) {
        //checking if there is enough space in bag to put product, if yes, adding product to bag's products list and returning true, if not returning false

        boolean isSuccess = false;
        if (actualWeight + newProduct.getWeight() <= maxWeight) {
            products.add(newProduct);
            actualWeight += newProduct.getWeight();
            isSuccess = true;
        }
        return isSuccess;
    }

}

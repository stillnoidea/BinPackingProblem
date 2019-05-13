package BagPackingAlgorithm;

import Products.Product;
import Products.ProductsMap;

import java.util.ArrayList;
import java.util.Collections;


public class PackingAlgorithm {
    private int bagsAmount;
    private int maxBagWeight;
    private ArrayList<Bag> bags = new ArrayList<>();
    private ArrayList<Product> productsToPack = new ArrayList<>();
    private int[] productsList;
    private static final String BAGS_AMOUNT="Bags amount: ";

    public PackingAlgorithm(int[] newProductsToPack, int maxBagWeight) {
        int productsAmount = newProductsToPack.length;
        this.maxBagWeight = maxBagWeight;
        this.productsToPack.ensureCapacity(productsAmount);

        //copying list of products id to class field
        productsList = new int[productsAmount];
        System.arraycopy(newProductsToPack, 0, productsList, 0, productsAmount);

        //initializing working ArrayList with products from list of products id
        initialiseProductsArray();

        //setting min bags amount based on sum of products weights
        bagsAmount = getMinBagsAmount();
        //initializing bags in number of bagsAmount
        initializeBags();
    }

    private void initialiseProductsArray() {

        for (int productId : productsList) {

            //getting every product from HashMap with products read from file, by id from productsList
            Product product = ProductsMap.productsMap.get(productId);
            product.setId(productId);
            productsToPack.add(product);
        }

        //sorting ArrayList with products, so the heaviest would be first
        Collections.sort(productsToPack);
    }

    private void initializeBags() {
        for (int i = 0; i < bagsAmount; i++) {
            bags.add(new Bag(maxBagWeight));
        }
    }

    public ArrayList<Bag> getBags() {
        return bags;
    }

    public int getMaxBagWeight() {
        return maxBagWeight;
    }

    public void setMaxBagWeight(int maxBagWeight) {
        this.maxBagWeight = maxBagWeight;
    }

    private int getProductsWeight() {
        //counting sum of products weights from productsList

        double weight = 0.0;
        for (Product product : productsToPack) {
            weight += product.getWeight();
        }
        return (int) weight;
    }

    private int getMinBagsAmount() {
        //counting a minimum amount of bags, simply dividing a sum of products weights by a maximal bag weight, and adding one,
        //so if the result would be f.e. 2.35, after casting to int be 2 plus 1, so bags would have minimal enough space
        //to fit all products

        return (getProductsWeight() / maxBagWeight) + 1;
    }

    private void firstFit() {
        //implementation of the first fit algorithm, which put the next product in the first bag that have enough space for it
        //after putting any product n any bag, remove product from working ArrayList
        //if there is too less bags, add new one

        for (int j = 0; j < productsToPack.size(); j++) {
            Product product = productsToPack.get(j);
            int i = 0;
            for (; i < bagsAmount; i++) {
                if (bags.get(i).addProduct(product)) {
                    productsToPack.remove(product);
                    j--;
                    break;
                }
            }
            if (i == bagsAmount) {
                bags.add(new Bag(maxBagWeight));
                bagsAmount++;
            }
        }

    }

    private void putProductInEveryBag(ArrayList<Product> productsToPack) {
        //part of my own implementation of algorithm for bin packing problem,
        //put first products (the heavier ones), one in every available bag,
        //then removing added product from working ArrayList

        for (int i = 0; i < bagsAmount; i++) {
            Product product = productsToPack.get(0);
            if (bags.get(i).addProduct(product)) {
                productsToPack.remove(0);
            }
        }
    }

    private void myFit() {
        //part of my own implementation of algorithm for bin packing problem,
        //after using putProductInEveryBag method, put next product in the first bag with enough space for it,
        //but after adding new product to any bag, reverse bags order
        //if there is too less bags, add new one

        boolean isDescending = false;
        initializeBags();
        putProductInEveryBag(productsToPack);

        while (productsToPack.size() != 0) {

            for (int j = 0; j < productsToPack.size(); j++) {
                Product product = productsToPack.get(j);

                if (isDescending) {
                    Collections.reverse(bags);
                }
                int i = 0;
                for (; i < bagsAmount; i++) {
                    if (bags.get(i).addProduct(product)) {
                        productsToPack.remove(product);
                        isDescending = !isDescending;
                        j--;
                        break;
                    }
                }
                if (i == bagsAmount) {
                    bags.add(new Bag(maxBagWeight));
                    bagsAmount++;
                }
            }
        }
    }

    private void printBags() {
        //printing result of algorithm, first print amount of bags, then every bag with its products

        System.out.println(BAGS_AMOUNT + bagsAmount);

        for (Bag bag : bags) {
            ArrayList<Product> products = bag.getProducts();
            for (int i = 0; i < products.size(); i++) {
                int productId = products.get(i).getId();
                System.out.print(i + ". " + productId + " ");
            }
            System.out.println();
        }
    }

    private void clearData() {
        //clearing bags and products if something left, reinitializing working ArrayList, setting bags amount to the minimal

        bags.clear();
        productsToPack.clear();
        initialiseProductsArray();
        bagsAmount = getMinBagsAmount();

    }

    public void run(boolean isFirstFit) {
        //running algorithm, chose by boolean parameter
        if (isFirstFit) {
            firstFit();
            printBags();
            clearData();
        } else {
            myFit();
            printBags();
            clearData();
        }
    }


}

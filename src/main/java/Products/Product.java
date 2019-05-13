package Products;

public class Product implements Comparable {
    private String name;
    private double weight = 100;
    private int id;

    public Product(String name, double weight) {
        if (weight > 0) this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight > 0) this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (((Product) o).weight - this.weight);
    }
}
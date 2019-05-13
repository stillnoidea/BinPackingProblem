package Products;

public enum Units {
    KG(1000),
    GR(1),
    DAG(10),
    LB(453.5924),
    OZ(28.3495);
    double toGram;

    Units(double i) {
        toGram = i;
    }
}

import java.util.Iterator;

public class Item implements Comparable<Item> {
    private double value;
    private double weight;
    private double relativeBenefit;

    public Item() {}

    public Item(double value, double weight) {
        this.weight = weight;
        this.value = value;
        relativeBenefit = value / weight;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public double getRelativeBenefit() {
        return relativeBenefit;
    }

    @Override
    public Item clone() {
        return new Item(this.value, this.weight);
    }
    @Override
    public int compareTo(Item toItem) {
        Double thisItem = this.getRelativeBenefit();
        Double compareToItem = toItem.getRelativeBenefit();
        return compareToItem.compareTo(thisItem);
    }
}

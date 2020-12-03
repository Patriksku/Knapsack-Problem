import java.util.ArrayList;

public class Knapsack implements Comparable<Knapsack>{
    private double weight;
    private double value;
    private double capacity;
    private double spaceLeft;
    private ArrayList<Item> bag = new ArrayList<>();

    public Knapsack(double capacity) {
        this.weight = 0;
        this.value = 0;
        this.spaceLeft = capacity;
        this.capacity = capacity;
    }

    //Getters
    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getSpaceLeft() {
        return spaceLeft;
    }

    // Returns true if item fits into the knapsack.
    public boolean itemFits(Item item) {
        return item.getWeight() + getWeight() <= getCapacity();
    }

    public ArrayList<Item> getBag() {
        return bag;
    }


    public void addItem(Item item) {
        if (getWeight() + item.getWeight() > capacity) {
            System.out.println("You went over the capacity for this Knapsack.");
        } else {
            this.weight += item.getWeight();
            this.value += item.getValue();
            this.bag.add(item);
            this.spaceLeft -= item.getWeight();
        }
    }

    // Fine print of Knapsack information.
    public String toString() {
        StringBuilder itemInfo = new StringBuilder();
        itemInfo.append("Amount of items: ").append(bag.size()).append("\n");
        itemInfo.append("--------------------------------------------------------").append("\n");

        for (int i = 0; i < bag.size(); i++) {
            itemInfo.append("Item ").append(i + 1).append(": ").append("value -> ").append(bag.get(i).getValue()).append(" weight -> ").append(bag.get(i).getWeight()).append("\n");
        }

        String result = itemInfo.toString();

        return "Weight and Capacity: " + getWeight() + "/" + getCapacity() + "\n" +
                result;
    }

    @Override
    public int compareTo(Knapsack toKnapsack) {
        Double thisItem = this.getSpaceLeft();
        Double compareToItem = toKnapsack.getSpaceLeft();
        return thisItem.compareTo(compareToItem);
    }
}

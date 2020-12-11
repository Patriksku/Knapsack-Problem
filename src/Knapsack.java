import java.util.ArrayList;

public class Knapsack implements Comparable<Knapsack>{
    public double weight;
    public double value;
    public double capacity;
    public double spaceLeft;
    public ArrayList<Item> bag = new ArrayList<>();

    public Knapsack(double capacity) {
        this.weight = 0;
        this.value = 0;
        this.spaceLeft = capacity;
        this.capacity = capacity;
    }
    public Knapsack(double capacity, ArrayList<Item> items) {
        this.weight = 0;
        this.value = 0;
        this.spaceLeft = capacity;
        this.capacity = capacity;
        bag = items;

        for(int i = 0; i < items.size();i++){
            weight += items.get(i).getWeight();
            value += items.get(i).getValue();
        }
    }

    public Knapsack(Knapsack knapsack) {
        this.weight = knapsack.weight;
        this.value = knapsack.value;
        this.spaceLeft = knapsack.spaceLeft;
        this.capacity = knapsack.capacity;
        this.bag = knapsack.bag;
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

    public void removeItem(Item item){
        bag.remove(item);
        weight -= item.getWeight();
        value -= item.getValue();
        spaceLeft += item.getWeight();
    }

    @Override
    public Knapsack clone() {
        Knapsack cloneKnapsack = new Knapsack(this.capacity);
        ArrayList<Item> cloneBag = new ArrayList<>();

        for (Item item : getBag()) {
            cloneBag.add(item.clone());
        }

        cloneKnapsack.weight = this.weight;
        cloneKnapsack.value = this.value;
        cloneKnapsack.capacity = this.capacity;
        cloneKnapsack.spaceLeft = this.spaceLeft;
        cloneKnapsack.bag = cloneBag;

        return cloneKnapsack;
    }

    @Override
    public int compareTo(Knapsack toKnapsack) {
        Double thisItem = this.getSpaceLeft();
        Double compareToItem = toKnapsack.getSpaceLeft();
        return thisItem.compareTo(compareToItem);
    }
}

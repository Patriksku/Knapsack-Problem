import java.util.ArrayList;
import java.util.Random;

public class KnapsackNeighborhood {


    public SackAndItem search(ArrayList<Item> items, ArrayList<Knapsack> knapsacks, int iterations) {
        int currentIterations = 0;
        boolean foundBetter = false;



        ArrayList<ArrayList<Item>> sameValueItems = new ArrayList<ArrayList<Item>>();
        ArrayList<ArrayList<Knapsack>> sameValueSacks = new ArrayList<ArrayList<Knapsack>>();


        for (int o = 0; o < iterations; o++) {
            System.out.println("souttwrwerwe");

            for (int i = 0; i < knapsacks.size(); i++) { // Loop through knapsacks
                for (int j = 0; j < knapsacks.get(i).getBag().size(); j++) { // Loop through the current knapsack's items [j].


                    for (int k = i; k < knapsacks.size(); k++) { // Loop through knapsacks again, start at same pos as first for-loop.
                        if (knapsacks.get(i) != knapsacks.get(k)) { // If both knapsacks are not equal.
                            for (int h = j; h < knapsacks.get(k).getBag().size(); h++) { // Loop through the current knapsack's items [h].

                                // Create new objects as clones from both the knapsacks and the items.
                                ArrayList<Knapsack> tempKnapsack = cloneKnapSacks(knapsacks);
                                ArrayList<Item> tempItem = new ArrayList<Item>(items);

                                System.out.println("\n item "+j+" From bag "+i+" bag weight: "+tempKnapsack.get(i).getBag().get(j).getWeight()+"\n bag "+i+" space left: "+tempKnapsack.get(i).getSpaceLeft()+"\n item "+h+" From bag "+k+" bag weight: "+tempKnapsack.get(k).getBag().get(h).getWeight()+"\n bag "+k+" space left: "+tempKnapsack.get(k).getSpaceLeft()+ "\n "+o);

                                // Swaps the current item in the first knapsack with the current item in the
                                // second knapsack if possible.
                                if (trySwap(tempKnapsack.get(i),tempKnapsack.get(k),j,h)) {
                                    System.out.println("Swap");

                                    // Loop through all items.
                                    for (int g = 0; g < tempItem.size(); g++) {

                                        // Try if current item fits in the first knapsack after the swap.
                                        if (tempKnapsack.get(i).itemFits(tempItem.get(g))) {
                                            // If so, add the item to the knapsack, and remove it from the items
                                            // list.
                                            tempKnapsack.get(i).addItem(tempItem.get(g));
                                            tempItem.remove(tempItem.get(g));
                                            g--;
                                            System.out.println("Added item");

                                            // Try if the current item fits in the second knapsack instead.
                                        } else if (tempKnapsack.get(k).itemFits(tempItem.get(g))) {
                                            tempKnapsack.get(k).addItem(tempItem.get(g));
                                            tempItem.remove(tempItem.get(g));
                                            g--;
                                            System.out.println("Added item");
                                        }

                                        // Check if the total value of the changed knapsacks is better than
                                        // Before the swap.
                                        if (evalSacks(tempKnapsack) > evalSacks(knapsacks)) {
                                            // Replace old knapsacks and set of items with new ones.
                                            knapsacks = tempKnapsack;
                                            items = tempItem;
                                            foundBetter = true;
                                            System.out.println("Found better combo");

                                            // Else if the changed knapsacks have the same value as pre-swap,
                                            // And no better combo has been found throughout this iteration.
                                        } else if (evalSacks(tempKnapsack) == evalSacks(knapsacks) && foundBetter == false) {
                                            // Add this same-valued combo designated sets for same-valued knapsacks and items.
                                            sameValueSacks.add(tempKnapsack);
                                            sameValueItems.add(tempItem);
                                            System.out.println("found same value combo");
                                        }

                                        System.out.println(evalSacks(knapsacks));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // If we have not found a better combination, but have at least found one combination with the same value.
            if (!foundBetter && sameValueItems.size() > 0) {
                Random random = new Random();
                System.out.println(sameValueItems.size());
                int ranIndex = random.nextInt(sameValueItems.size());
                System.out.println(ranIndex);

                // Choose a random set of items and knapsacks of same value as the starting point for next iteration.
                items = sameValueItems.get(ranIndex);
                knapsacks = sameValueSacks.get(ranIndex);
            }

            // Empty the lists of same-valued combos.
            sameValueItems = new ArrayList<ArrayList<Item>>();
            sameValueSacks = new ArrayList<ArrayList<Knapsack>>();
            foundBetter = false;
        }

        // Create and return the best valued combo of knapsacks and items found with the help of neighborhood search.
        SackAndItem sackAndItem = new SackAndItem();
        sackAndItem.items = items;
        sackAndItem.sacks = knapsacks;
        return sackAndItem;
    }

    // Swaps the specified items between the specified knapsacks if it is possible.
    public boolean trySwap(Knapsack k1, Knapsack k2, int k1swap, int k2swap) {

        // If a swap does not exceed the capacity limits of the knapsacks.
        if (k1.getCapacity() >= (k1.getWeight()-k1.getBag().get(k1swap).getWeight())+k2.getBag().get(k2swap).getWeight()
                && k2.getCapacity() >= (k2.getWeight()-k2.getBag().get(k2swap).getWeight())+k1.getBag().get(k1swap).getWeight()) {

            // Make the swap.
            Item temp = k1.getBag().get(k1swap);
            k1.removeItem(temp);
            k1.addItem(k2.getBag().get(k2swap));
            k2.removeItem(k2.getBag().get(k2swap));
            k2.addItem(temp);
            return true;
        }
        return false;
    }

    // Returns the total value of this set of knapsacks.
    public int evalSacks(ArrayList<Knapsack> sacks) {
        int totalValue = 0;
        for (int i = 0; i< sacks.size(); i++) {
            totalValue += sacks.get(i).getValue();
        }
        return totalValue;
    }

    public int evalSacksGreedy(ArrayList<Knapsack> sacks) {
        int totalValue = 0;
        for(int i = 0; i< sacks.size(); i++){
            totalValue += sacks.get(i).getValue();
        }
        return totalValue;
    }

    // Clones and returns the specified knapsack as a new object.
    public ArrayList<Knapsack> cloneKnapSacks(ArrayList<Knapsack> sacks) {
        ArrayList<Knapsack> cloneSack = new ArrayList<Knapsack>();
        for (int i = 0; sacks.size() > i; i++){
            cloneSack.add(new Knapsack(sacks.get(i)));
        }
        return cloneSack;
    }
}

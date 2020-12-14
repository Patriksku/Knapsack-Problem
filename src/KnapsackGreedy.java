import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The algorithm now chooses the knapsack that has the MOST space left... Pretty good performance.
 * It would maybe be smarter, to loop through the knapsacks in the reversed order,
 * and choosing the one which would have less space left, if not working, choose the next one, etc?
 */
public class KnapsackGreedy {

    // The greedy knapsack-problem algorithm for multiple knapsacks.
    // Puts the best relative-benefit item into the knapsack with the least space left.
    // Terminates when no more items could be added.
    public SackAndItem greedyAlgorithmLessSpace(ArrayList<Knapsack> listOfKnapsacks, ArrayList<Item> listOfItems) {
        // Loop through all items until termination criteria reached.
        while (true) {

            // Termination criteria = A full round of going through the items left, without
            // any item being able to be added to the Knapsacks.
            boolean itemsAdded = false;

            for (int i = 0; i < listOfItems.size(); i++) {
                Item thisItem = listOfItems.get(i);
                Collections.sort(listOfKnapsacks); // Sorts the knapsacks in ascending space left order.

                // Loops through all knapsacks.
                for (int knapNr = 0; knapNr < listOfKnapsacks.size(); knapNr++) {


                    // If item fits the largest knapsack, add it, and remove it from the itemList.
                    if (listOfKnapsacks.get(knapNr).itemFits(thisItem)) {
                        listOfKnapsacks.get(knapNr).addItem(thisItem);
                        listOfItems.remove(thisItem);
                        itemsAdded = true; // Flag that an item has been added and break.
                        i--;
                        break;
                    }
                }
            }

            // If no item has been added to a single knapsack throughout the whole iteration of all items,
            // Terminate the algorithm.
            if (!itemsAdded || listOfItems.size() == 0) {
                break;
            }
        }


        // Prints info about items that have not been assigned to any Knapsack.
        System.out.println("**************************************************");
        System.out.println("Items left after the algorithm has ran: "+listOfItems.size()+"\n");
        for(int i = 0; i<listOfItems.size();i++) {
            System.out.println("item: " + i + ": value -> " + listOfItems.get(i).getValue() + " weight -> " + listOfItems.get(i).getWeight());
        }
        System.out.println("**************************************************");
        System.out.println();

        // Return all the knapsacks.
        SackAndItem sackAndItem = new SackAndItem();
        sackAndItem.sacks = listOfKnapsacks;
        sackAndItem.items = listOfItems;
        return sackAndItem;
    }

    public int evalSacksGreedy(ArrayList<Knapsack> sacks) {
        int totalValue = 0;
        for(int i = 0; i< sacks.size(); i++){
            totalValue += sacks.get(i).getValue();
        }
        return totalValue;
    }

   /* // Returns the index of the knapsack that has the most capacity left to utilize.
    // ---Maybe not efficient!!! Try random as well???
    private int chooseKnapsack(ArrayList<Knapsack> listOfKnapsacks) {
        int bestKnapsack = 0;

        // If there is only one knapsack, return it.
        if (listOfKnapsacks.size() == 1) {
            return bestKnapsack;
        }

        // Loops through the list of knapsacks, and saves the index of the knapsack
        // that has the most capacity left to utilize.
        for (int i = 0; i < listOfKnapsacks.size() - 1; i++) {
            if (listOfKnapsacks.get(i).getSpaceLeft() > listOfKnapsacks.get(i + 1).getSpaceLeft()) {
                bestKnapsack = i;
            } else {
                bestKnapsack = (i + 1);
            }
        }

        // Returns index of the knapsack that has the most capacity left to utilize.
        return bestKnapsack;
    }*/



}

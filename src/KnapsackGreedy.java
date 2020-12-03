import java.util.ArrayList;
import java.util.Collections;

/**
 * The algorithm now chooses the knapsack that has the MOST space left... Pretty good performance.
 * It would maybe be smarter, to loop through the knapsacks in the reversed order,
 * and choosing the one which would have less space left, if not working, choose the next one, etc?
 */
public class KnapsackGreedy {

    // The greedy knapsack-problem algorithm for multiple knapsacks.
    // Puts the best relative-benefit item into the knapsack with the most space left.
    // Terminates when no more items could be added.
    private ArrayList<Knapsack> greedyAlgorithmMostSpace(ArrayList<Knapsack> listOfKnapsacks, ArrayList<Item> listOfItems) {

        // Loop through all items until termination criteria reached.
        while (true) {

            // Termination criteria = A full round of going through the items left, without
            // any item being able to be added to the Knapsacks.
            boolean itemsAdded = false;

            for (int i = 0; i < listOfItems.size(); i++) {
                Item thisItem;
                Item nextItem;
                Collections.sort(listOfKnapsacks); // Sorts the knapsacks in descending space left order.

                // Choose item and neighbour.

                // If we have an item in front of us as a neighbor
                if (i < listOfItems.size() - 1) {
                    thisItem = listOfItems.get(i);
                    nextItem = listOfItems.get(i + 1);

                    // Else if this is the last item in the list, compare it with the first item in the list instead.
                } else {
                    thisItem = listOfItems.get(i);
                    nextItem = listOfItems.get(0);
                }

                // If this item has a better relative benefit than the neighbour.
                if (thisItem.getRelativeBenefit() >= nextItem.getRelativeBenefit()) {

                    // If item fits the largest knapsack, add it, and remove it from the itemList.
                    if (listOfKnapsacks.get(0).itemFits(thisItem)) {
                        listOfKnapsacks.get(0).addItem(thisItem);
                        listOfItems.remove(thisItem);
                        itemsAdded = true; // Flag that an item has been added and break.

                        // If item does not fit in the largest knapsack, try fitting the neighbour
                        // Instead.
                    } else {
                        if (listOfKnapsacks.get(0).itemFits(nextItem)) {
                            listOfKnapsacks.get(0).addItem(nextItem);
                            listOfItems.remove(nextItem);
                            itemsAdded = true; // Flag that an item has been added and break.
                        }
                    }


                    // Else if the neighbour item has a better relative benefit than this item.
                } else {

                    // If item fits the largest knapsack, add it, and remove it from the itemList.
                    if (listOfKnapsacks.get(0).itemFits(nextItem)) {
                        listOfKnapsacks.get(0).addItem(nextItem);
                        listOfItems.remove(nextItem);
                        i--; // This makes sure that we start from "thisItem" again on the next iteration.
                        itemsAdded = true; // Flag that an item has been added.

                        // If item does not fit in the largest knapsack, try fitting the neighbour
                        // Instead.
                    } else {
                        if (listOfKnapsacks.get(0).itemFits(thisItem)) {
                            listOfKnapsacks.get(0).addItem(thisItem);
                            listOfItems.remove(thisItem);
                            i--; // This makes sure that we start from "thisItem" again on the next iteration.
                            itemsAdded = true; // Flag that an item has been added.
                        }
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
        System.out.println("Items left after the algorithm has ran: " + listOfItems.size() + "\n");
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println("item: " + i + ": value -> " + listOfItems.get(i).getValue() + " weight -> " + listOfItems.get(i).getWeight());
        }
        System.out.println("**************************************************");
        System.out.println();

        // Return all the knapsacks.
        return listOfKnapsacks;
    }

    // The greedy knapsack-problem algorithm for multiple knapsacks.
    // Puts the best relative-benefit item into the knapsack with the least space left.
    // Terminates when no more items could be added.
    private ArrayList<Knapsack> greedyAlgorithmLessSpace(ArrayList<Knapsack> listOfKnapsacks, ArrayList<Item> listOfItems) {

        // Loop through all items until termination criteria reached.
        while (true) {

            // Termination criteria = A full round of going through the items left, without
            // any item being able to be added to the Knapsacks.
            boolean itemsAdded = false;

            for (int i = 0; i < listOfItems.size(); i++) {
                Item thisItem;
                Item nextItem;
                Collections.sort(listOfKnapsacks); // Sorts the knapsacks in ascending space left order.

                // Choose item and neighbour.

                // If we have an item in front of us as a neighbor
                if (i < listOfItems.size() - 1) {
                    thisItem = listOfItems.get(i);
                    nextItem = listOfItems.get(i + 1);

                    // Else if this is the last item in the list, compare it with the first item in the list instead.
                } else {
                    thisItem = listOfItems.get(i);
                    nextItem = listOfItems.get(0);
                }

                // Loops through all knapsacks.
                for (int knapNr = 0; knapNr < listOfKnapsacks.size(); knapNr++) {

                    // If this item has a better relative benefit than the neighbour.
                    if (thisItem.getRelativeBenefit() >= nextItem.getRelativeBenefit()) {

                        // If item fits the largest knapsack, add it, and remove it from the itemList.
                        if (listOfKnapsacks.get(knapNr).itemFits(thisItem)) {
                            listOfKnapsacks.get(knapNr).addItem(thisItem);
                            listOfItems.remove(thisItem);
                            itemsAdded = true; // Flag that an item has been added and break.
                            break;

                            // If item does not fit in the largest knapsack, try fitting the neighbour
                            // Instead.
                        } else {
                            if (listOfKnapsacks.get(knapNr).itemFits(nextItem)) {
                                listOfKnapsacks.get(knapNr).addItem(nextItem);
                                listOfItems.remove(nextItem);
                                itemsAdded = true; // Flag that an item has been added and break.
                                break;
                            }
                        }


                        // Else if the neighbour item has a better relative benefit than this item.
                    } else {

                        // If item fits the largest knapsack, add it, and remove it from the itemList.
                        if (listOfKnapsacks.get(0).itemFits(nextItem)) {
                            listOfKnapsacks.get(0).addItem(nextItem);
                            listOfItems.remove(nextItem);
                            i--; // This makes sure that we start from "thisItem" again on the next iteration.
                            itemsAdded = true; // Flag that an item has been added and break.

                            // If item does not fit in the largest knapsack, try fitting the neighbour
                            // Instead.
                        } else {
                            if (listOfKnapsacks.get(0).itemFits(thisItem)) {
                                listOfKnapsacks.get(0).addItem(thisItem);
                                listOfItems.remove(thisItem);
                                i--; // This makes sure that we start from "thisItem" again on the next iteration.
                                itemsAdded = true; // Flag that an item has been added and break.
                            }
                        }
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
        System.out.println("Items left after the algorithm has ran: " + listOfItems.size() + "\n");
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println("item: " + i + ": value -> " + listOfItems.get(i).getValue() + " weight -> " + listOfItems.get(i).getWeight());
        }
        System.out.println("**************************************************");
        System.out.println();

        // Return all the knapsacks.
        return listOfKnapsacks;
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


    public static void main(String[] args) {
        KnapsackGreedy knapsackGreedy = new KnapsackGreedy();

        ArrayList<Knapsack> listOfKnapsacks = new ArrayList<>();
        ArrayList<Item> listOfItems = new ArrayList<>();

        //init and add knapsacks
        Knapsack knapsack1 = new Knapsack(15);
        Knapsack knapsack2 = new Knapsack(10);
        Knapsack knapsack3 = new Knapsack(6);
        listOfKnapsacks.add(knapsack1);
        listOfKnapsacks.add(knapsack2);
        listOfKnapsacks.add(knapsack3);

        //init and add items
        Item i1 = new Item(10, 7);
        Item i2 = new Item(1, 1);
        Item i3 = new Item(3, 5);
        Item i4 = new Item(8, 8);
        Item i5 = new Item(5, 4);
        Item i6 = new Item(4, 5);
        Item i7 = new Item(2, 1);
        listOfItems.add(i1);
        listOfItems.add(i2);
        listOfItems.add(i3);
        listOfItems.add(i4);
        listOfItems.add(i5);
        listOfItems.add(i6);
        listOfItems.add(i7);

        // Sort in descending relative benefit order.
        Collections.sort(listOfItems);

        // Prints relative benefit of items in descending order.
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println(listOfItems.get(i).getRelativeBenefit());
        }

        // Run the algorithm
        /*ArrayList<Knapsack> result = knapsackGreedy.greedyAlgorithmMostSpace(listOfKnapsacks, listOfItems);*/

        // greedyAlgorithmLessSpace will only work if the return in the compareTo inside of Knapsack.java
        // is reversed! That is --> return thisItem.compareTo(compareToItem);
        ArrayList<Knapsack> result = knapsackGreedy.greedyAlgorithmLessSpace(listOfKnapsacks, listOfItems);

        // Print Knapsack information.
        for (Knapsack knapsack : result) {
            System.out.println(knapsack.toString());
        }

        System.out.println("-*-*-*-");
    }
}

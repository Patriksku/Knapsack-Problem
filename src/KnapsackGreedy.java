import java.util.ArrayList;
import java.util.Collections;

/**
 * The algorithm now chooses the knapsack that has the MOST space left... Pretty good performance.
 * It would maybe be smarter, to loop through the knapsacks in the reversed order,
 * and choosing the one which would have less space left, if not working, choose the next one, etc?
 */
public class KnapsackGreedy {

    // The greedy knapsack-problem algorithm for multiple knapsacks.
    // Puts the best relative-benefit item into the knapsack with the least space left.
    // Terminates when no more items could be added.
    private SackAndItem greedyAlgorithmLessSpace(ArrayList<Knapsack> listOfKnapsacks, ArrayList<Item> listOfItems) {
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
        Knapsack knapsack1 = new Knapsack(180);
        Knapsack knapsack2 = new Knapsack(159);
        Knapsack knapsack3 = new Knapsack(180);
        listOfKnapsacks.add(knapsack1);
        listOfKnapsacks.add(knapsack2);
        listOfKnapsacks.add(knapsack3);

        //init and add items
        Item i1 = new Item(10, 71);
        Item i2 = new Item(12, 16);
        Item i3 = new Item(33, 53);
        Item i4 = new Item(82, 84);
        Item i5 = new Item(54, 43);
        Item i6 = new Item(45, 25);
        Item i7 = new Item(23, 15);
        Item i12 = new Item(10, 72);
        Item i22 = new Item(14, 16);
        Item i32 = new Item(5, 52);
        Item i42 = new Item(58, 83);
        Item i52 = new Item(65, 44);
        Item i62 = new Item(74, 55);
        Item i72 = new Item(25, 15);
        Item i13 = new Item(10, 73);
        Item i23 = new Item(13, 16);
        Item i33 = new Item(34, 53);
        Item i43 = new Item(86, 86);
        Item i53 = new Item(57, 42);
        Item i63 = new Item(45, 52);
        Item i73 = new Item(25, 15);
        Item i14 = new Item(10, 73);
        Item i24 = new Item(16, 16);
        Item i34 = new Item(39, 56);
        Item i44 = new Item(82, 81);
        Item i54 = new Item(53, 42);
        Item i64 = new Item(44, 52);
        Item i74 = new Item(25, 15);

        listOfItems.add(i1);
        listOfItems.add(i2);
        listOfItems.add(i3);
        listOfItems.add(i4);
        listOfItems.add(i5);
        listOfItems.add(i6);
        listOfItems.add(i7);
        listOfItems.add(i12);
        listOfItems.add(i22);
        listOfItems.add(i32);
        listOfItems.add(i42);
        listOfItems.add(i52);
        listOfItems.add(i62);
        listOfItems.add(i72);
        listOfItems.add(i13);
        listOfItems.add(i23);
        listOfItems.add(i33);
        listOfItems.add(i43);
        listOfItems.add(i53);
        listOfItems.add(i63);
        listOfItems.add(i73);
        listOfItems.add(i14);
        listOfItems.add(i24);
        listOfItems.add(i34);
        listOfItems.add(i44);
        listOfItems.add(i54);
        listOfItems.add(i64);
        listOfItems.add(i74);



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
        SackAndItem result = knapsackGreedy.greedyAlgorithmLessSpace(listOfKnapsacks, listOfItems);

        // Print Knapsack information.
       for (Knapsack knapsack : result.sacks) {
            System.out.println(knapsack.toString());
        }

        System.out.println("-*-*-*-");

        System.out.println("Neighborhood search");

        KnapsackNeighborhood search = new KnapsackNeighborhood();

        System.out.println("Greedy Value: "+search.evalSacksGreedy(result.sacks));

        SackAndItem sackAndItem = search.search(listOfItems, listOfKnapsacks, 4);
        System.out.println("Neighborhood search :"+ search.evalSacks(sackAndItem.sacks));



    }
}

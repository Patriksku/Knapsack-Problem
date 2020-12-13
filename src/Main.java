import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        KnapsackGreedy knapsackGreedy = new KnapsackGreedy();

        ArrayList<Knapsack> listOfKnapsacks = new ArrayList<>();
        ArrayList<Item> listOfItems = new ArrayList<>();

        //Brute-force testing
        Random rand = new Random();
        int min = 1;
        int max = 40;

        for (int i = 0; i < 70; i++) {
            int val = rand.nextInt(max - min) + min;
            int w = rand.nextInt(max - min) + min;
            listOfItems.add(new Item(val, w));
        }

        // Manual testing

        //init and add items + knapsacks
        Knapsack knapsack1 = new Knapsack(17);
        Knapsack knapsack2 = new Knapsack(26);
        Knapsack knapsack3 = new Knapsack(32);
        Knapsack knapsack12 = new Knapsack(65);
        Knapsack knapsack22 = new Knapsack(52);
        Knapsack knapsack32 = new Knapsack(32);
        Knapsack knapsack13 = new Knapsack(17);
        Knapsack knapsack23 = new Knapsack(82);
        Knapsack knapsack33 = new Knapsack(32);

        listOfKnapsacks.add(knapsack1);
        listOfKnapsacks.add(knapsack2);
        listOfKnapsacks.add(knapsack3);
        listOfKnapsacks.add(knapsack12);
        listOfKnapsacks.add(knapsack22);
        listOfKnapsacks.add(knapsack32);
        listOfKnapsacks.add(knapsack13);
        listOfKnapsacks.add(knapsack23);
        listOfKnapsacks.add(knapsack33);

        /*Item i1 = new Item(10, 7);
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
        listOfItems.add(i7);*/

        // Sort in descending relative benefit order.
        Collections.sort(listOfItems);

        // Prints relative benefit of items in descending order.
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println(listOfItems.get(i).getRelativeBenefit() + " "  + listOfItems.get(i).getValue() + " " + listOfItems.get(i).getWeight());
        }

        // Run the algorithm
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

        System.out.println("sputnik");
        for (int i = 0; i < listOfItems.size(); i++) {
            System.out.println(listOfItems.get(i).getRelativeBenefit());
        }
        SackAndItem sackAndItem = search.search(listOfItems, listOfKnapsacks, 200);

        System.out.println("Greedy Value: "+search.evalSacksGreedy(result.sacks));
        System.out.println("Neighborhood search :"+ search.evalSacks(sackAndItem.sacks));

    }
}

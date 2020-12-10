import java.util.ArrayList;
import java.util.Random;

public class KnapsackNeighborhood {


    public SackAndItem search(ArrayList<Item> items, ArrayList<Knapsack> knapsacks, int iterations) {
        int currentIterations = 0;
        boolean foundBetter = false;

        ArrayList<Item> bestItems = items;
        ArrayList<Knapsack> bestKnappsackCombo = knapsacks;

        ArrayList<ArrayList<Item>> sameValueItems = new ArrayList<ArrayList<Item>>();
        ArrayList<ArrayList<Knapsack>> sameValueSacks = new ArrayList<ArrayList<Knapsack>>();


        for (int o = 0; o < iterations; o++){
            System.out.println("souttwrwerwe");
            for (int i = 0; i < knapsacks.size(); i++) {
                for (int j = 0; j < knapsacks.get(i).getBag().size(); j++) {


                    for (int k = i; k < knapsacks.size(); k++){
                        if (knapsacks.get(i) != knapsacks.get(k)) {
                            for (int h = j; h < knapsacks.get(k).getBag().size(); h++) {

                                ArrayList<Knapsack> tempKnapsack = cloneKnapSacks(bestKnappsackCombo);
                                ArrayList<Item> tempItem = new ArrayList<Item>(bestItems);
                                System.out.println("\n item "+j+" From bag "+i+" bag weight: "+tempKnapsack.get(i).getBag().get(j).getWeight()+"\n bag "+i+" space left: "+tempKnapsack.get(i).getSpaceLeft()+"\n item "+h+" From bag "+k+" bag weight: "+tempKnapsack.get(k).getBag().get(h).getWeight()+"\n bag "+k+" space left: "+tempKnapsack.get(k).getSpaceLeft()+ "\n "+o);

                                if(trySwap(tempKnapsack.get(i),tempKnapsack.get(k),j,h)){
                                    System.out.println("Swap");
                                    for(int g = 0; g < tempItem.size(); g++){
                                        if(tempKnapsack.get(i).itemFits(tempItem.get(g))){
                                            tempKnapsack.get(i).addItem(tempItem.get(g));
                                            tempItem.remove(tempItem.get(g));
                                            g--;
                                            System.out.println("Added item");
                                        }else if(tempKnapsack.get(k).itemFits(tempItem.get(g))){
                                            tempKnapsack.get(k).addItem(tempItem.get(g));
                                            tempItem.remove(tempItem.get(g));
                                            g--;
                                            System.out.println("Added item");
                                        }

                                        if(evalSacks(tempKnapsack) > evalSacks(bestKnappsackCombo)){
                                            bestKnappsackCombo =tempKnapsack;
                                            bestItems = tempItem;
                                            foundBetter = true;
                                            System.out.println("Found better combo");
                                        } else if(evalSacks(tempKnapsack) == evalSacks(bestKnappsackCombo) && foundBetter == false){

                                            sameValueSacks.add(tempKnapsack);
                                            sameValueItems.add(tempItem);
                                            System.out.println("found same value combo");
                                        }

                                        System.out.println(evalSacks(bestKnappsackCombo));

                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(!foundBetter){
                Random random = new Random();
                int ranIndex = random.nextInt(sameValueItems.size());
                System.out.println(ranIndex);
                items = sameValueItems.get(ranIndex);
                knapsacks = sameValueSacks.get(ranIndex);
                bestItems = sameValueItems.get(ranIndex);
                bestKnappsackCombo = sameValueSacks.get(ranIndex);

            }else {
                items = bestItems;
                knapsacks = bestKnappsackCombo;
            }
            sameValueItems = new ArrayList<ArrayList<Item>>();
            sameValueSacks = new ArrayList<ArrayList<Knapsack>>();
            foundBetter = false;

        }
        SackAndItem sackAndItem = new SackAndItem();
        sackAndItem.items = items;
        sackAndItem.sacks = knapsacks;
        return sackAndItem;
    }


    public boolean trySwap(Knapsack k1, Knapsack k2, int k1swap, int k2swap){
        if(k1.getCapacity() >= (k1.getWeight()-k1.getBag().get(k1swap).getWeight())+k2.getBag().get(k2swap).getWeight()
                && k2.getCapacity() >= (k2.getWeight()-k2.getBag().get(k2swap).getWeight())+k1.getBag().get(k1swap).getWeight()){
            Item temp = k1.getBag().get(k1swap);
            k1.removeItem(temp);
            k1.addItem(k2.getBag().get(k2swap));
            k2.removeItem(k2.getBag().get(k2swap));
            k2.addItem(temp);
            return true;
        }
        return false;

    }

    public int evalSacks(ArrayList<Knapsack> sacks){
        int totalValue = 0;
        for(int i = 0; i< sacks.size(); i++){
            totalValue += sacks.get(i).getValue();
        }
        return totalValue;
    }

    public int evalSacksGreedy(ArrayList<Knapsack> sacks){
        int totalValue = 0;
        for(int i = 0; i< sacks.size(); i++){
            totalValue += sacks.get(i).getValue();
        }
        return totalValue;
    }


    public ArrayList<Knapsack> cloneKnapSacks(ArrayList<Knapsack> sacks){
        ArrayList<Knapsack> cloneSack = new ArrayList<Knapsack>();
        for(int i = 0; sacks.size() > i; i++){
            cloneSack.add(new Knapsack(sacks.get(i)));
        }
        return cloneSack;
    }
}

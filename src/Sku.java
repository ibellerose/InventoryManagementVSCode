import java.util.ArrayList;

public class Sku {
    private String skuName ;
    private ArrayList<String> finishedGoodName;
    private ArrayList<Integer> finishedGoodCount;

    Sku(){
        skuName = "";
        finishedGoodName = new ArrayList<String>();
        finishedGoodCount = new ArrayList<Integer>();
    }

    Sku(String newName){
        skuName = newName;
        finishedGoodName = new ArrayList<String>();
        finishedGoodCount = new ArrayList<Integer>();
    }

    void setName(String initName){
        skuName = initName;
        finishedGoodName = new ArrayList<String>();
        finishedGoodCount = new ArrayList<Integer>();
    }

    void addFinishedGoodName(String newName){
        finishedGoodName.add(newName);
    }

    void addFinishedGoodCount(int newCount){
        finishedGoodCount.add(newCount);
    }

    String getName(){
        return skuName;
    }

    String getFinishedGoodName(int index){
        return finishedGoodName.get(index);
    }

    int getFinishedGoodCount(int index){
        return finishedGoodCount.get(index);
    }

    int getTotal(){
        return finishedGoodName.size();
    }
}

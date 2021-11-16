import java.util.ArrayList;

public class Sku {
    private String skuName ;
    private ArrayList<String> finishedGoodNames;
    private ArrayList<Integer> finishedGoodCount;

    Sku(){
        skuName = "";
        finishedGoodNames = new ArrayList<String>();
        finishedGoodCount = new ArrayList<Integer>();
    }

}

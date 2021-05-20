package base;
import pages.US.SingleSearchResult;
import java.util.ArrayList;


public class Utils {
    public static void addElementsToCart(ArrayList<SingleSearchResult> searchResults, int n_elems){
        SingleSearchResult e;
        for(int i = 0; i < Math.min(n_elems, searchResults.size()-1); i++) {
            e = searchResults.get(i);
            e.addToCart(i);
            e.closeCartAddingPage();
        }
    }

}

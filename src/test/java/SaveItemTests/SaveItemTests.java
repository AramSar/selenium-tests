package SaveItemTests;

import base.BaseTest;
import base.Utils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.US.*;

import java.util.ArrayList;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

public class SaveItemTests extends BaseTest {

    private pages.US.Home home;
    private SearchResults elems;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        home = countryPage.selectUS().closeModal();
        home.addSearchQuery("flash drive");
        this.elems = home.enterSearch();
    }

    @Test
    public void testAddingToSavedFromSearchResults() throws InterruptedException {
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < Math.min(3, this.elems.searchResults.size()); i++){
            SingleSearchResult e = this.elems.searchResults.get(i);
            e.save(i);
            names.add(e.name);
            assertEquals(e.getSaveText(), "Saved");
        }

        SavedItemsDd dropdownSavedItems = home.clickSavedItems();
        for(String name: names){
            assertTrue(dropdownSavedItems.namesOfSavedItems.contains(name));
        }

        SavedItems savedList = dropdownSavedItems.seeAllList();
        for(String name: names){
            assertTrue(savedList.namesOfElements.contains(name));
        }

    }

    @Test
    public void testAddingToSavedFromCart() throws InterruptedException {
        Utils.addElementsToCart(this.elems.searchResults,4);
        Cart cart = home.clickCart();

        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < Math.min(3, cart.cartElements.size()); i++){
            SingleCartElement e = cart.cartElements.get(i);
            e.save(i);
            names.add(e.name);
            assertEquals(e.getSaveText(), "We’ve moved this to your saved items.");
        }

        SavedItemsDd dropdownSavedItems = home.clickSavedItems();
        for(String name: names){
            assertTrue(dropdownSavedItems.namesOfSavedItems.contains(name));
        }

        SavedItems savedList = dropdownSavedItems.seeAllList();
        for(String name: names){
            assertTrue(savedList.namesOfElements.contains(name));
        }

    }
}

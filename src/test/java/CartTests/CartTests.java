package CartTests;
import base.BaseTest;
import base.Utils;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.US.*;

import java.util.ArrayList;


public class CartTests  extends BaseTest {

    private pages.US.Home home;
    private SearchResults elems;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        home = countryPage.selectUS().closeModal();
        home.addSearchQuery("flash drive");
        this.elems = home.enterSearch();
    }

    @Test
    public void testAddingToCart() throws InterruptedException {
        assertFalse(Cart.isBadgeVisible(home.getDriver()));

        for(int i = 0; i < Math.min(3, this.elems.searchResults.size()); i++){
            SingleSearchResult e = this.elems.searchResults.get(i);
            e.addToCart(i);
            assertEquals(e.getAddedText(), "Added to cart");

            e.closeCartAddingPage();
            assertTrue(Cart.isBadgeVisible(home.getDriver()));
            int nInCart = Cart.getCartBadge(home.getDriver());
            assertEquals(nInCart, i+1);
        }
    }

    @Test
    public void testCartStatus() throws InterruptedException {
        SingleSearchResult e;
        ArrayList<String> names = new ArrayList<>();
        double priceSum = 0;

        for(int i = 0; i < Math.min(4, this.elems.searchResults.size()-1); i++) {
            e = this.elems.searchResults.get(i);
            e.addToCart(i);
            e.closeCartAddingPage();

            names.add(e.name);
            priceSum += e.price;
        }

        Cart cart = home.clickCart();
        assertTrue(cart.totalPrice - priceSum < 0.000001);

        for(SingleCartElement cartElem: cart.cartElements){
            assertTrue(names.contains(cartElem.name));
        }
    }


    @Test
    public void testIncreasingItemAmount() throws InterruptedException {
        Utils.addElementsToCart(this.elems.searchResults, 4);
        Cart cart = home.clickCart();

        double currTotal = cart.totalPrice;
        int currInCart = Cart.getCartBadge(home.getDriver());
        SingleCartElement elem = cart.cartElements.get(0);
        int oldAmount = elem.amount;
        int newAmount = 3;

        cart.addAmount(cart.cartElements.get(0), newAmount);

        assertTrue(currTotal+(elem.price*(newAmount-oldAmount)) - cart.totalPrice < 0.000001);
        int nInCart = Cart.getCartBadge(home.getDriver());
        System.out.println(nInCart);
        assertEquals(nInCart, currInCart+(newAmount-oldAmount));

    }

    @Test
    public void testRemoveItem() throws InterruptedException {
        Utils.addElementsToCart(this.elems.searchResults, 3);
        Cart cart = home.clickCart();

        double currTotal = cart.totalPrice;
        int currInCart = Cart.getCartBadge(home.getDriver());
        SingleCartElement elem = cart.cartElements.get(0);
        int oldAmount = elem.amount;

        cart.reomveElementFromCart(elem);
        assertTrue(currTotal - (elem.price*oldAmount) - cart.totalPrice < 0.000001);

        String remText = cart.getRemovalText();
        assertEquals(remText, "We’ve removed this item from your cart.");

        int nInCart = Cart.getCartBadge(home.getDriver());
        assertEquals(nInCart, currInCart-oldAmount);

    }
}


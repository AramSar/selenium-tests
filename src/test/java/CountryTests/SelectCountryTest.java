package CountryTests;

import base.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import pages.Mexico.Home;
import pages.US.ModalPage;


public class SelectCountryTest extends BaseTest {

        @Test
        public void testUS(){
            ModalPage res = countryPage.selectUS();
            assertEquals(res.url, "https://www.bestbuy.com/?intl=nosplash");
        }

        @Test
        public void testMexico(){
            Home res = countryPage.selectMexico();
            assertEquals(res.url, "https://www.bestbuy.com.mx/");
        }

        @Test
        public void testCanada(){
            pages.Canada.Home res = countryPage.selectCanada();
            assertEquals(res.url, "https://www.bestbuy.ca/en-ca");
        }
}

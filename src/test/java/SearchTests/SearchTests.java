package SearchTests;
import base.BaseTest;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.US.SearchResults;
import pages.US.SingleSearchResult;

public class SearchTests extends BaseTest {

    private pages.US.Home home;

    @BeforeMethod
    public void beforeMethod(){
        home = countryPage.selectUS().closeModal();
    }

    @Test
    public void testAddingQuery() throws InterruptedException {
        home.addSearchQuery("flash drive");
        assertEquals(home.getQuery(), "flash drive");
    }

    @Test
    public void testGettingResults() throws InterruptedException {
        home.addSearchQuery("flash drive");
        SearchResults e = home.enterSearch();
        String a;
        for(SingleSearchResult s: e.searchResults){
            a = s.name.toLowerCase();
            assertTrue(a.contains("flash") || a.contains("drive") || a.contains("usb"));
        }
    }
}

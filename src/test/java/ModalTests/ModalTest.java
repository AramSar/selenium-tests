package ModalTests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.US.ModalPage;

import static org.testng.Assert.*;


public class ModalTest extends BaseTest {
    private ModalPage page;

    @BeforeMethod
    public void beforeMethod(){
        page = countryPage.selectUS();
    }

    @Test
    public void testExistenceOfModal(){
        assertTrue(page.isVisible());
    }

    @Test
    public void testClosingModal(){
        page.closeModal();
        assertFalse(page.isVisible());
    }

    @Test
    public void testInputEmail(){
        page.inputEmail("test@test.test");
        assertEquals(page.getEmailInput(), "test@test.test");
    }

    @Test
    public void testSignUp(){
        page.inputEmail("test@test.test");
        page.clickEmailSignUp();
        assertTrue(page.successVisibility());
    }

}

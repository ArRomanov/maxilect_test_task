package tests;

import factories.PagesFactory;
import models.AddressShippingModel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.DuckCardPage;
import pages.MainPage;

import java.util.Random;

import static org.testng.Assert.assertTrue;

public class BuyDucksWithoutLoginTests extends BaseTest {

    private PagesFactory pages;

    @Override
    @BeforeClass
    public void initApplication(){
        pages = new PagesFactory("chrome");
    }

    @Test(description = "Покупка уток без авторизации")
    public void buyFromDifferentCountriesAndCurrency() {
        MainPage mainPage = pages.mainPage();
        DuckCardPage duckCardPage = mainPage.searchDuckByName("green");
        duckCardPage.addToCart(1);
        CartPage cartPage = duckCardPage.goToCart();
        cartPage.fillFormAndSave(
                new AddressShippingModel()
                        .setFirstName("kek")
                        .setLastName("lol")
                        .setAddress("Address")
                        .setCity("City")
                        .setPostcode("123456")
                        .setEmail(new Random().nextInt(9999) + "@123")
                        .setPhone("+123"));
        cartPage.confirmOrder();
        assertTrue(cartPage.isOrderSuccess());
    }

    @Override
    @AfterClass
    public void quitBrowser(){
        pages.destroyInstance();
    }
}

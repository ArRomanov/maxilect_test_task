package tests;

import org.testng.annotations.*;
import pages.CartPage;
import pages.DuckCardPage;
import pages.MainPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BuyDucksTests extends BaseTest {

    @BeforeMethod
    public void clearCart() {
        pages.mainPage().goToCart().clearCart();

    }

    @DataProvider
    public static Object[][] getCountriesAndCurrency() {
        return new Object[][]{
                {"Albania", "US Dollars"},
                {"Anguilla", "Euros"},
        };
    }

    @Test(dataProvider = "getCountriesAndCurrency", description = "Покупка уток из разных стран и разными валютами")
    public void buyFromDifferentCountriesAndCurrency(String country, String currency) {
        MainPage mainPage = pages.mainPage();
        mainPage.setCountryAndCurrency(country, currency);
        DuckCardPage duckCardPage = mainPage.searchDuckByName("green");
        duckCardPage.addToCart(1);
        CartPage cartPage = duckCardPage.goToCart();
        assertTrue(cartPage.isThisCountry(country));
        assertTrue(cartPage.isThisCurrency(currency));
        cartPage.confirmOrder();
        assertTrue(cartPage.isOrderSuccess());
    }

    @Test(description = "Покупка уток разного размера")
    public void buyDifferentSizeDucks() {
        String smallSize = "Small";
        String largeSize = "Large";
        MainPage mainPage = pages.mainPage();
        DuckCardPage duckCardPage = mainPage.searchDuckByName("yellow");
        duckCardPage.setSize(smallSize);
        duckCardPage.addToCart(1);
        duckCardPage.setSize(largeSize);
        duckCardPage.addToCart(1);
        CartPage cartPage = duckCardPage.goToCart();
        cartPage.checkDuckSizeInCart(smallSize);
        cartPage.checkDuckSizeInCart(largeSize);
        cartPage.confirmOrder();
        assertTrue(cartPage.isOrderSuccess());
    }

    @Test(description = "Покупка уток разного типа")
    public void buyDifferentDucks() {
        MainPage mainPage = pages.mainPage();
        DuckCardPage duckCardPage = mainPage.searchDuckByName("yellow");
        duckCardPage.setSize("Small");
        duckCardPage.addToCart(1);
        mainPage.searchDuckByName("green").addToCart(1);
        CartPage cartPage = duckCardPage.goToCart();
        cartPage.checkDuckTypeInCart("Yellow Duck");
        cartPage.checkDuckTypeInCart("Green Duck");
        cartPage.confirmOrder();
        assertTrue(cartPage.isOrderSuccess());
    }

    @Test(description = "Покупка много одинаковых уток")
    public void buyManyDucks() {
        MainPage mainPage = pages.mainPage();
        DuckCardPage duckCardPage = mainPage.searchDuckByName("green");
        duckCardPage.addToCart(10);
        CartPage cartPage = duckCardPage.goToCart();
        int actualDuckCount = cartPage.getFullCountDuckFromCart();
        assertEquals(actualDuckCount, 10, "Количество уток в корзине не соответствует ожидаемому");
        cartPage.confirmOrder();
        assertTrue(cartPage.isOrderSuccess());
    }
}

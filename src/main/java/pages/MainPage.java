package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private String mainUrl = "http://litecart.stqa.ru";

    private String loginField = "//input[@name='email']";
    private String passwordField = "//input[@name='password']";
    private String loginButton = "//button[@name='login']";
    private String searchField = "//input[@name='query']";
    private String changeCountryLink = "//a[text()='Change']";
    private String regionalSettingsTitle = "//h1[text()='Regional Settings']";
    private String currencyCodeSelect = "//select[@name='currency_code']";
    private String countryCodeSelect = "//select[@name='country_code']";
    private String saveRegionalSettingsBtn = "//button[@name='save']";
    private String goToCartButton = "//a[contains(text(),'Checkout')]";

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get(mainUrl);
    }

    /**
     * Авторизация
     *
     * @param login    - логин
     * @param password - пароль
     */
    public void auth(String login, String password) {
        setTextInField(loginField, login);
        setTextInField(passwordField, password);
        clickLMB(loginButton);
    }

    /**
     * Поиск товара через строку поиска
     *
     * @param duckName - название утки
     * @return - page object DuckCardPage
     */
    public DuckCardPage searchDuckByName(String duckName) {
        setTextInField(searchField, duckName + Keys.ENTER);
        return new DuckCardPage(driver);
    }

    /**
     * Установка страны и валюты
     *
     * @param country  - страна
     * @param currency - валюта
     */
    public void setCountryAndCurrency(String country, String currency) {
        clickLMB(changeCountryLink);
        waitForAnyElement(regionalSettingsTitle, 3);
        selectDropDownByText(currencyCodeSelect, currency);
        selectDropDownByText(countryCodeSelect, country);
        clickLMB(saveRegionalSettingsBtn);
    }

    /**
     * Перейти в корзину
     *
     * @return - page object CartPage
     */
    public CartPage goToCart() {
        clickLMB(goToCartButton);
        return new CartPage(driver);
    }
}

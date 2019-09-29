package pages;

import models.AddressShippingModel;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private String firstNameField = "//input[@name='firstname']";
    private String lastNameField = "//input[@name='lastname']";
    private String firstAddressField = "//input[@name='address1']";
    private String cityField = "//input[@name='city']";
    private String postcodeField = "//input[@name='postcode']";
    private String emailField = "//input[@name='email']";
    private String phoneField = "//input[@name='phone']";
    private String saveChangeButton = "//button[@value='Save Changes']";
    private String countryCodeSelect = "//select[@name='country_code']";
    private String priceTitle = "//tr[@class='footer']/td[2]/strong";
    private String confirmOrderButton = "//button[@name='confirm_order']";
    private String successOrderTitle = "//h1[text()='Your order is successfully completed!']";
    private String removeItemButton = "//button[@name='remove_cart_item']";
    private String backFromCartButton = "//a[text()='<< Back']";
    private String duckCountField = "//input[@name='quantity']";
    private String fillFormWarning = "//div[@class='warning']";

    private String duckSizeTitle = "//p[text()='Size: %s']";
    private String duckNameTitle = "//strong[text()='%s']";

    CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Заполнить форму адреса
     *
     * @param model - AddressShippingModel
     */
    public void fillFormAndSave(AddressShippingModel model) {
        waitForAnyElement(firstNameField, 3);
        setTextInField(firstNameField, model.getFirstName());
        setTextInField(lastNameField, model.getLastName());
        setTextInField(firstAddressField, model.getAddress());
        setTextInField(cityField, model.getCity());
        setTextInField(postcodeField, model.getPostcode());
        setTextInField(emailField, model.getEmail());
        setTextInField(phoneField, model.getPhone());
        clickLMB(saveChangeButton);
    }

    /**
     * Проверка выбранной страны
     *
     * @param country - страна
     * @return - boolean
     */
    public boolean isThisCountry(String country) {
        return getElementText(countryCodeSelect).contains(country);
    }

    /**
     * Проверить валюту в цене товара
     *
     * @param currency - валюта
     * @return - boolean
     */
    public boolean isThisCurrency(String currency) {
        String currencySign = "no currency";
        if (currency.equals("Euros")) {
            currencySign = "€";
        } else if (currency.equals("US Dollars")) {
            currencySign = "$";
        }
        return getElementText(priceTitle).contains(currencySign);
    }

    /**
     * Подтвердить заказ
     */
    public void confirmOrder() {
        waitForAnyElementDisappear(fillFormWarning, 3);
        waitForAnyElementHasNotAttribute(confirmOrderButton, "disabled", "disabled");
        clickByJS(confirmOrderButton);
    }

    /**
     * Проверить успешность заказа
     *
     * @return - boolean
     */
    public boolean isOrderSuccess() {
        waitForAnyElement(successOrderTitle, 3);
        return isElementDisplayed(successOrderTitle);
    }

    /**
     * Очистить корзину
     */
    public void clearCart() {
        while (true) {
            try {
                clickLMB(removeItemButton);
                driver.navigate().refresh();
            } catch (NoSuchElementException e) {
                clickLMB(backFromCartButton);
                break;
            }
        }
    }

    /**
     * Проверить в корзине утку с определенным размером
     *
     * @param duckSize - размер утки (Small, Medium, Large)
     */
    public void checkDuckSizeInCart(String duckSize) {
        waitForAnyElement(String.format(duckSizeTitle, duckSize), 3);
    }

    /**
     * Проверить в корзине утку с определенным названием
     *
     * @param duckName - название утки
     */
    public void checkDuckTypeInCart(String duckName) {
        waitForAnyElement(String.format(duckNameTitle, duckName), 3);
    }

    /**
     * Получить общее количество уток в корзине
     *
     * @return количество уток в корзине
     */
    public int getFullCountDuckFromCart() {
        int actualFullCount = 0;
        List<WebElement> elementList = getElementList(duckCountField);
        for (WebElement element : elementList) {
            int count = Integer.parseInt(element.getAttribute("value"));
            actualFullCount += count;
        }
        return actualFullCount;
    }
}

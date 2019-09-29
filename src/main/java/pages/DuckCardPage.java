package pages;

import org.openqa.selenium.WebDriver;

public class DuckCardPage extends BasePage {

    private String duckCountField = "//input[@name='quantity']";
    private String addCartButton = "//button[@name='add_cart_product']";
    private String addingToCartFlag = "//div[contains(@style,'background: rgba(0, 136, 204, 0.5)')]";
    private String goToCartButton = "//a[contains(text(),'Checkout')]";
    private String duckSizeSelect = "//select[@name='options[Size]']";

    DuckCardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Добавить товар в корзину
     *
     * @param duckCount - количество товара
     */
    public void addToCart(int duckCount) {
        setTextInField(duckCountField, String.valueOf(duckCount));
        clickLMB(addCartButton);
        waitForAnyElementDisappear(addingToCartFlag, 5);
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

    /**
     * Установить размер утки
     *
     * @param size - размер утки (Small, Nedium, Large)
     */
    public void setSize(String size) {
        selectDropDownByValue(duckSizeSelect, size);
    }
}

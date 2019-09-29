package factories;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class PagesFactory {

    private WebDriver driver;

    public PagesFactory(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = WebDriverFactory.getChromeDriver();
                break;
            case "firefox":
                driver = WebDriverFactory.getFirefoxDriver();
                break;
            default:
                Logger.getAnonymousLogger().warning("Неизвестное имя браузера, по умолчанию запустится Chrome browser");
                driver = WebDriverFactory.getChromeDriver();
        }
    }

    public void destroyInstance() {
        driver.quit();
    }

    public MainPage mainPage() {
        return new MainPage(driver);
    }

    @Attachment(value = "SCREENSHOT", type = "image/png")
    public byte[] attachScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        return Files.toByteArray(screenshot);
    }
}

package tests;

import factories.PagesFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public PagesFactory pages;

    @BeforeClass
    public void initApplication() {
        pages = new PagesFactory("chrome");
        pages.mainPage().auth("test", "test");
    }

    @AfterClass
    public void quitBrowser() {
        pages.destroyInstance();
    }
}

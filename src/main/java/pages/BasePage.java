package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtil waitUtil;

    protected BasePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        waitUtil = new WaitUtil(this.driver);
    }

    public abstract boolean isLoaded();
}

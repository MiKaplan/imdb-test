package pages;

import driver.Browser;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

public abstract class BasePage {

    protected final Browser browser;
    protected final WaitUtil waitUtil;

    protected BasePage(final Browser browser) {
        this.browser = browser;
        PageFactory.initElements(browser.getDriver(), this);
        waitUtil = new WaitUtil(browser.getDriver());
    }

    public abstract boolean isLoaded();
}

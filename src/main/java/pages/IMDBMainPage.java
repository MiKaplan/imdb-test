package pages;

import driver.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class IMDBMainPage extends BasePage {

    @FindBy(id = "imdbHeader")
    private WebElement header;

    @FindBy(id = "imdbHeader-navDrawerOpen")
    private WebElement menuButton;

    public IMDBMainPage(final Browser browser) {
        super(browser);
    }

    @Override
    public boolean isLoaded() {
        return waitUtil.waitUntilElementIsVisible(header).isDisplayed();
    }

    public MenuPage clickOnMenuButton() {
        waitUtil.waitUntilElementIsClickable(menuButton).click();
        return new MenuPage(browser);
    }
}

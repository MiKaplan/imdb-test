package pages;

import driver.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MenuPage extends BasePage {

    @FindBy(className = "navlinkcat__targetWrapper")
    private WebElement logo;

    @FindBy(linkText = "Top 250 Movies")
    private WebElement topMoviesButton;

    protected MenuPage(final Browser browser) {
        super(browser);
    }

    @Override
    public boolean isLoaded() {
        return waitUtil.waitUntilElementIsVisible(logo).isDisplayed();
    }

    public TopMoviesPage clickOnTopMoviesButton() {
        waitUtil.waitUntilElementIsClickable(topMoviesButton).click();
        return new TopMoviesPage(browser);
    }
}

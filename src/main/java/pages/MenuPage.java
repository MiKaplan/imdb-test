package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MenuPage extends BasePage {

    @FindBy(className = "navlinkcat__targetWrapper")
    private WebElement logo;

    @FindBy(linkText = "Top 250 Movies")
    private WebElement topMoviesButton;

    protected MenuPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return waitUtil.waitUntilElementIsVisible(logo).isDisplayed();
    }

    public TopMoviesPage clickOnTopMoviesButton() {
        waitUtil.waitUntilElementIsClickable(topMoviesButton).click();
        return new TopMoviesPage(driver);
    }
}

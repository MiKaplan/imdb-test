package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public final class MoviePage extends BasePage {

    @FindBy(css = "[data-testid='hero__pageTitle']")
    private WebElement name;

    @FindBy(css = "[data-testid='hero-rating-bar__aggregate-rating__score'] span")
    private WebElement rating;

    @FindBy(css = "[data-testid='award_top-rated']")
    private WebElement position;

    @FindBy(css = "ul li a[class='ipc-link ipc-link--baseAlt ipc-link--inherit-color']")
    private List<WebElement> years;

    protected MoviePage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return waitUtil.waitUntilElementIsVisible(name).isDisplayed();
    }

    public String getName() {
        return name.getText();
    }

    public boolean getYear(final String expectedYear) {
        return years.stream()
                .anyMatch(year -> year.getText().equals(expectedYear));
    }

    public int getPosition() {
        return Integer.parseInt(position.getText().replaceAll("\\D", ""));
    }

    public double getRating() {
        return Double.parseDouble(rating.getText());
    }
}

package pages;

import driver.Browser;
import models.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public final class TopMoviesPage extends BasePage {

    @FindBy(className = "lister")
    private WebElement list;

    @FindBy(css = "tbody tr")
    private List<WebElement> moviesList;

    protected TopMoviesPage(final Browser browser) {
        super(browser);
    }

    @Override
    public boolean isLoaded() {
        return waitUtil.waitUntilElementIsVisible(list).isDisplayed();
    }

    public MoviePage clickOnMovieByPosition(final int position) {
        getTitleElement(moviesList.get(position - 1)).click();
        return new MoviePage(browser);
    }

    public List<Movie> getMoviesFromList(final int numberOfMovies) {
        final List<Movie> movieModels = new ArrayList<>();
        moviesList.stream().limit(Math.min(moviesList.size(), numberOfMovies)).
                forEach(movie -> movieModels.add(new Movie(
                        getPosition(movie),
                        getTitle(movie),
                        getYear(movie),
                        getRating(movie))
                ));
        return movieModels;
    }

    private int getPosition(final WebElement element) {
        final String title = getFullTitle(element).getText();
        return Integer.parseInt(title.substring(0, title.indexOf('.')));
    }

    private String getTitle(final WebElement element) {
        return getTitleElement(element).getText();
    }

    private String getYear(final WebElement element) {
        return getFullTitle(element).findElement(By.cssSelector("span")).getText().replaceAll("\\D", "");
    }

    private double getRating(final WebElement element) {
        return Double.parseDouble(element.findElement(By.cssSelector("[class='ratingColumn imdbRating'] strong"))
                .getText().replaceAll(",", "."));
    }

    private WebElement getTitleElement(final WebElement element) {
        return getFullTitle(element).findElement(By.cssSelector("a"));
    }

    private WebElement getFullTitle(final WebElement element) {
        return element.findElement(By.className("titleColumn"));
    }
}

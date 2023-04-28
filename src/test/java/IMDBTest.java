import config.PropertyConfig;
import driver.Browser;
import models.Movie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.IMDBMainPage;
import pages.MenuPage;
import pages.MoviePage;
import pages.TopMoviesPage;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class IMDBTest {

    private Browser browser;
    private SoftAssert softAssert;
    private IMDBMainPage mainPage;

    @BeforeMethod
    void preCondition() {
        softAssert = new SoftAssert();
        browser = new Browser(new PropertyConfig());
        mainPage = new IMDBMainPage(browser);
    }

    @Test(description = "Check top 5 movies and contains 'Godfather'")
    void checkTop5ContainsGodFatherMovie() {
        assertTrue(mainPage.isLoaded(), "IMDB page is not loaded");
        final MenuPage menuPage = mainPage.clickOnMenuButton();
        assertTrue(menuPage.isLoaded(), "Menu is not loaded");
        final TopMoviesPage topMoviesPage = menuPage.clickOnTopMoviesButton();
        assertTrue(topMoviesPage.isLoaded(), "Top 250 movies is not loaded");
        final List<Movie> topMovieList = topMoviesPage.getTopMovies(5);
        final Movie godFather = topMovieList.stream()
                .filter(movie -> movie.getTitle().equals("Крестный отец"))
                .findFirst().orElseThrow();
        final MoviePage moviePage = topMoviesPage.clickOnMovieByPosition(godFather.getPosition());
        softAssert.assertTrue(moviePage.isLoaded(), "Name is not loaded");
        softAssert.assertEquals(godFather.getTitle(), moviePage.getName(), "Wrong Title");
        softAssert.assertTrue(moviePage.getYear(godFather.getYear()), "Wrong Year");
        softAssert.assertEquals(godFather.getPosition(), moviePage.getPosition(), "Wrong Position");
        softAssert.assertEquals(godFather.getRating(), moviePage.getRating(), "Wrong Rating");
        softAssert.assertAll();
    }

    @Test(description = "Check top 250 movies and check data random movie")
    void checkRandomMovieFromTop() {
        assertTrue(mainPage.isLoaded(), "IMDB page is not loaded");
        final MenuPage menuPage = mainPage.clickOnMenuButton();
        assertTrue(menuPage.isLoaded(), "Menu is not loaded");
        final TopMoviesPage topMoviesPage = menuPage.clickOnTopMoviesButton();
        assertTrue(topMoviesPage.isLoaded(), "Top 250 movies is not loaded");
        final Movie randomMovie = topMoviesPage.getMoviesFromList()
                .get(new Random().nextInt(250));
        final MoviePage moviePage = topMoviesPage.clickOnMovieByPosition(randomMovie.getPosition());
        softAssert.assertTrue(moviePage.isLoaded(), "Name is not loaded");
        softAssert.assertEquals(randomMovie.getTitle(), moviePage.getName(), "Wrong Title");
        softAssert.assertTrue(moviePage.getYear(randomMovie.getYear()), "Wrong Year");
        softAssert.assertEquals(randomMovie.getPosition(), moviePage.getPosition(), "Wrong Position");
        softAssert.assertEquals(randomMovie.getRating(), moviePage.getRating(), "Wrong Rating");
        softAssert.assertAll();
    }

    @AfterMethod
    void postCondition() {
        browser.getDriver().quit();
    }
}

import config.PropertyConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.IMDBMainPage;
import pages.MenuPage;
import pages.MoviePage;
import pages.TopMoviesPage;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class IMDBTest {

    private WebDriver driver;
    private IMDBMainPage mainPage;

    @BeforeEach
    void preCondition() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.get(new PropertyConfig().getBaseUrl());
        mainPage = new IMDBMainPage(driver);
    }

    @Test
    @DisplayName("Check top 5 movies and contains 'Godfather'")
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
        assertAll("Check godfather film contains correct data",
                () -> assertTrue(moviePage.isLoaded(), "Name is not loaded"),
                () -> assertEquals(godFather.getTitle(), moviePage.getName(), "Wrong Title"),
                () -> assertTrue(moviePage.getYear(godFather.getYear()), "Wrong Year"),
                () -> assertEquals(godFather.getPosition(), moviePage.getPosition(), "Wrong Position"),
                () -> assertEquals(godFather.getRating(), moviePage.getRating(), "Wrong Rating"));
    }

    @Test
    @DisplayName("Check top 250 movies and check data random movie")
    void checkRandomMovieFromTop() {
        assertTrue(mainPage.isLoaded(), "IMDB page is not loaded");
        final MenuPage menuPage = mainPage.clickOnMenuButton();
        assertTrue(menuPage.isLoaded(), "Menu is not loaded");
        final TopMoviesPage topMoviesPage = menuPage.clickOnTopMoviesButton();
        assertTrue(topMoviesPage.isLoaded(), "Top 250 movies is not loaded");
        final Movie randomMovie = topMoviesPage.getMoviesFromList()
                .get(new Random().nextInt(250));
        final MoviePage moviePage = topMoviesPage.clickOnMovieByPosition(randomMovie.getPosition());
        assertAll("Check that random movie contains correct data",
                () -> assertTrue(moviePage.isLoaded(), "Name is not loaded"),
                () -> assertEquals(randomMovie.getTitle(), moviePage.getName(), "Wrong Title"),
                () -> assertTrue(moviePage.getYear(randomMovie.getYear()), "Wrong Year"),
                () -> assertEquals(randomMovie.getPosition(), moviePage.getPosition(), "Wrong Position"),
                () -> assertEquals(randomMovie.getRating(), moviePage.getRating(), "Wrong Rating"));
    }

    @AfterEach
    void postCondition() {
        driver.quit();
    }
}

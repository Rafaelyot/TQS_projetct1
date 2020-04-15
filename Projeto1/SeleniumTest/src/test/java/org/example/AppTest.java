package org.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import org.example.config.SeleniumConfig;
import org.example.pages.AddCoordinatesPage;
import org.example.pages.AirPage;
import org.example.pages.HistoricalPage;
import org.example.pages.Navbar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class AppTest {
    private SeleniumConfig config;
    private Navbar navbar;
    private AirPage page;
    private HistoricalPage historicalPage;
    private AddCoordinatesPage addCoordinatesPage;

    @BeforeEach
    public void setup() {
        this.config = new SeleniumConfig();
        this.navbar = new Navbar(config);
        this.page = new AirPage(config);
        this.historicalPage = new HistoricalPage(config);
        this.addCoordinatesPage = new AddCoordinatesPage(config);
    }


    @AfterEach
    public void tearDown() {
        this.config.close();
    }


    public String getCurrentDate(int hours) { //ignoring minutes,seconds and mseconds
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        Calendar expected = Calendar.getInstance();
        expected.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), 0, 0);
        expected.set(Calendar.MILLISECOND, 0);
        expected.add(Calendar.HOUR, hours);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        return sdf.format(expected.getTime());
    }


    @Test
    public void searchBarTest() throws InterruptedException {
        navbar.navigate();
        navbar.clickSearchButton();
        navbar.clickSearchBar();
        navbar.typeSearchBar("ansiao");
        TimeUnit.SECONDS.sleep(2);

        assertThat(navbar.getElementByID("39.91177,-8.43568").getText(), is("Ansião, Leiria, Portugal (Ansiao)"));

    }


    @Test
    public void clickOnSearchBarResult() throws InterruptedException {
        navbar.navigate();
        navbar.clickSearchButton();
        navbar.clickSearchBar();
        navbar.typeSearchBar("ansiao");
        TimeUnit.SECONDS.sleep(5);

        AirPage page = navbar.clickOnSearchResult(navbar.getElementByID("39.91177,-8.43568"));

        assertThat(page.getTitle().getText(), is("Ansião, Leiria, Portugal (Ansiao)"));
        assertThat(page.getDate().getText(), is(this.getCurrentDate(0)));
    }

    @Test
    public void checkPollutants() throws InterruptedException {
        navbar.navigate();
        navbar.clickSearchButton();
        navbar.clickSearchBar();
        navbar.typeSearchBar("ansiao");
        TimeUnit.SECONDS.sleep(5);

        AirPage page = navbar.clickOnSearchResult(navbar.getElementByID("39.91177,-8.43568"));

        page.getO3Label().click();

        String title = page.getO3Title().getAttribute("innerText");

        assertThat(title, is("Ozone"));
    }

    //@Test
    //public void checkHistoricalData() throws InterruptedException {
    //    historicalPage.navigate();
    //    AirPage page = historicalPage.clickCharPoint(historicalPage.getCharPoint());
//
    //    //System.out.println(page.getTitle().getText());
    //    WebElement date = page.getDate();
    //    System.out.println(date.getText());
    //}

    @Test
    public void checkAddCoordinates() {
        this.addCoordinatesPage.navigate();
        this.addCoordinatesPage.getLatitude().clear();
        this.addCoordinatesPage.typeOnLatitudeBox("39.91177");
        this.addCoordinatesPage.getLongitude().clear();
        this.addCoordinatesPage.typeOnLongitudeBox("-8.43562");

        AirPage page = this.addCoordinatesPage.goToAirPage(this.addCoordinatesPage.getSubmitButton());

        assertThat(page.getTitle().getText(), is("Undefined"));
        assertThat(page.getDate().getText(), is(this.getCurrentDate(0)));
    }


}

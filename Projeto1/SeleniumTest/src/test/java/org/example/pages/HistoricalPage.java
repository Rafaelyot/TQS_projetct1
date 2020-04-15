package org.example.pages;

import org.example.config.SeleniumConfig;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class HistoricalPage {
    private SeleniumConfig config;

    private WebElement charPoint;

    public HistoricalPage(SeleniumConfig config) {
        this.config = config;
        PageFactory.initElements(this.config.getDriver(), this);

    }

    public void navigate() {
        this.config.navigateTo("http://localhost:3000/admin/historical");
    }


    public AirPage clickCharPoint(WebElement element) {
        config.clickElement(element);

        AirPage page = new AirPage(config);
        PageFactory.initElements(config.getDriver(), page);

        return page;
    }

    public WebElement getCharPoint() {
        return this.config.getDriver().findElement(By.cssSelector(".chartjs-render-monitor"));
    }

}

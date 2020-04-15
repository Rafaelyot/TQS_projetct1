package org.example.pages;

import org.example.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AirPage {

    private SeleniumConfig config;

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(css = "#O3 > span")
    private WebElement O3Label;

    @FindBy(xpath = "//div[@id='root']/div/div[2]/div[3]/div/div/div/div/div/p[2]/h3")
    private WebElement date;

    public AirPage(SeleniumConfig config) {
        this.config = config;
        PageFactory.initElements(this.config.getDriver(), this);
    }

    public void navigate() {
        this.config.navigateTo("http://localhost:3000/admin/air");
    }

    public WebElement getTitle() {
        return this.title;
    }

    public WebElement getO3Label() {
        return this.O3Label;
    }

    public WebElement getO3Title() {
        return this.config.getDriver().findElement(new By.ByCssSelector(".author:nth-child(1) > .description:nth-child(6)"));
    }

    public WebElement getDate() {
        return this.date;
    }


}

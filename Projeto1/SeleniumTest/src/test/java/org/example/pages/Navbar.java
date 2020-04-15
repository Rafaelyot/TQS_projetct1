package org.example.pages;

import org.example.config.SeleniumConfig;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class Navbar {

    private SeleniumConfig config;

    @FindBy(css = ".icon-zoom-split")
    private WebElement searchButton;

    @FindBy(id = "inlineFormInputGroup")
    private WebElement searchBar;

    public Navbar(SeleniumConfig config) {
        this.config = config;
        PageFactory.initElements(this.config.getDriver(), this);
    }

    public void navigate() {
        this.config.navigateTo("http://localhost:3000/admin/air");
    }

    public void clickSearchButton() {
        this.searchButton.click();
    }

    public void clickSearchBar() {
        this.searchBar.click();
    }

    public void typeSearchBar(String keys) {
        this.searchBar.sendKeys(keys);
    }

    public WebElement getElementByID(String ID) {

        return this.config.getDriver().findElement(By.id(ID));
    }

    public AirPage clickOnSearchResult(WebElement element) throws InterruptedException {
        config.clickElement( element);

        AirPage page = new AirPage(config);
        PageFactory.initElements(config.getDriver(),page);

        return page;
    }


}

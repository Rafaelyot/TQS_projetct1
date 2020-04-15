package org.example.pages;

import org.example.config.SeleniumConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddCoordinatesPage {


    @FindBy(id = "latitude")
    private WebElement latitude;

    @FindBy(id = "longitude")
    private WebElement longitude;

    @FindBy(id = "submeter")
    private WebElement submitButton;

    private SeleniumConfig config;

    public AddCoordinatesPage(SeleniumConfig config) {
        this.config = config;
        PageFactory.initElements(this.config.getDriver(), this);
    }

    public void navigate() {
        this.config.navigateTo("http://localhost:3000/admin/add-coordinates");
    }

    public WebElement getLatitude() {
        return this.latitude;
    }

    public WebElement getLongitude() {
        return this.longitude;
    }

    public void clickSubmitButton() {
        this.submitButton.click();
    }

    public AirPage goToAirPage(WebElement element) {
        config.clickElement(element);
        AirPage page = new AirPage(this.config);
        PageFactory.initElements(config.getDriver(), page);
        return page;
    }

    public void typeOnLatitudeBox(String keys) {
        this.latitude.sendKeys(keys);
    }

    public void typeOnLongitudeBox(String keys) {
        this.longitude.sendKeys(keys);
    }

    public WebElement getSubmitButton() {
        return this.submitButton;
    }

}

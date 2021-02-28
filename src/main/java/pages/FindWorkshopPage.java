package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

import java.util.List;

public class FindWorkshopPage {

    private final WebDriver driver;

    @FindBy(xpath = "//h1[contains(.,'Find Your Workshop')]")
    WebElement findWorkshop;

    @FindBy(xpath = "//span[@class='buttonText-3DCCO'][contains(.,'Studio')]")
    WebElement studio;

    @FindBy(xpath = "//input[@id='location-search']")
    WebElement locationSearch;

    @FindBy(xpath = "//span[contains(.,'Filters')]")
    WebElement filter;

    @FindBy(xpath = "//a[contains(@tabindex,'-1')]")
    List<WebElement> searchResultNames;

    @FindBy(xpath = "//span[@class='distance-OhP63']")
    List<WebElement> searchResultDistances;


    public FindWorkshopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        WaitUtil.waitForVisible(driver, findWorkshop);
        return driver.getTitle();
    }

    public FindWorkshopPage clickOnStudio() {
        WaitUtil.waitForClickable(driver, studio);
        studio.click();
        return this;
    }

    public FindWorkshopPage searchLocation(String text) {
        WaitUtil.waitForVisible(driver, locationSearch);
        locationSearch.sendKeys(text);
        locationSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public String getResultName(int resultNum) {
        WaitUtil.waitForVisible(driver, filter);
        return searchResultNames.get(resultNum - 1).getText();
    }

    public String getResultDistance(int resultNum) {
        WaitUtil.waitForVisible(driver, filter);
        return searchResultDistances.get(resultNum - 1).getText();
    }

    public SearchResultPage clickOnSearchResult(int resultNum) {
        String url = driver.getCurrentUrl();
        WebElement el = searchResultNames.get(resultNum - 1);
        WaitUtil.waitForClickable(driver, el);
        el.click();
        WaitUtil.waitUrlChanged(driver, url);
        return new SearchResultPage(driver);
    }
}

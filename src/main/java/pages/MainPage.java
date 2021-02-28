package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtil;

public class MainPage {

    private final WebDriver driver;

    @FindBy(xpath = "(//span[text() = 'Attend'])[1]")
    WebElement attendMenu;

    @FindBy(xpath = "(//span[@class='MenuItem_subtitle__3LoiE'])[1]")
    WebElement unlimitedWorkshopMenuItem;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public MainPage clickOnAttend() {
        WaitUtil.waitForClickable(driver, attendMenu);
        attendMenu.click();
        return this;
    }

    public FindWorkshopPage clickOnUnlimitedWorkshop() {
        String url = driver.getCurrentUrl();
        WaitUtil.waitForClickable(driver, unlimitedWorkshopMenuItem);
        unlimitedWorkshopMenuItem.click();
        WaitUtil.waitUrlChanged(driver, url);
        return new FindWorkshopPage(driver);
    }
}

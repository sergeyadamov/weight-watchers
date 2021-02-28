import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected static final long IMPLICIT_WAIT = 30;
    protected static final long PAGE_LOAD = 30;
    protected static final String BASE_URL = "https://www.weightwatchers.com/us/";
    protected WebDriver driver;

    @BeforeTest
    public void beforeMethod() {
        driver = Browser.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(BASE_URL);
    }

    @AfterTest
    public void afterMethod() {
        driver.quit();
    }
}

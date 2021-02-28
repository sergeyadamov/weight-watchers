import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Browser {

    public static WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setJavascriptEnabled(true);

            options.setCapability(ChromeOptions.CAPABILITY, capabilities);

            String os = System.getProperty("os.name");
            String driverDir = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "drivers";

            if (os.contains("Window")) {
                setChromeProperties(driverDir + File.separator + "windows" + File.separator + "chromedriver.exe");
            } else if (os.contains("Mac")) {
                setChromeProperties(driverDir + File.separator + "mac" + File.separator + "chromedriver");
            } else {
                throw new IllegalArgumentException("Unknown OS: " + os);
            }

            return new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Please run against Chrome browser!");
        }

    }

    private static void setChromeProperties(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TimeUtil;
import utils.WaitUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultPage {

    private final WebDriver driver;

    @FindBy(xpath = "//h1[contains(@class,'locationName-1jro_')]")
    WebElement locationName;

    @FindBy(xpath = "//div[@class='title-bEfSM'][contains(.,'Virtual Workshops schedule')]")
    WebElement workshopsSchedule;

    @FindBy(xpath = "//div[@class='title-3o8Pv'][contains(.,'Business hours')]")
    WebElement businessHours;

    @FindBy(xpath = "//div[contains(@class,'times-fms3v')]")
    List<WebElement> workHours;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLocationName() {
        WaitUtil.waitForVisible(driver, locationName);
        return locationName.getText();
    }

    public SearchResultPage scrollToWorkshopsSchedule() {
        WaitUtil.waitForVisible(driver, workshopsSchedule);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", workshopsSchedule);
        return this;
    }

    public void printTodayHoursOfOperation() {
        WaitUtil.waitForClickable(driver, businessHours);
        businessHours.click();

        int dayOfWeek = TimeUtil.getDayOfWeek();
        //to adopt to array
        dayOfWeek = dayOfWeek == 7 ? 0 : dayOfWeek;

        WebElement hours = workHours.get(dayOfWeek);
        WaitUtil.waitForVisible(driver, hours);
        String todayHours = hours.getText();
        System.out.println("TODAY’s hours of operation: " + todayHours);
    }

    public void printMeetings(String day) {
        scrollToWorkshopsSchedule();
        String xpath = "(//span[@class='dayName-1UpF5'][contains(.,'" + day + "')])[2]";
        WebElement scheduledDayEl = driver.findElement(By.xpath(xpath));
        WebElement parentEl = scheduledDayEl.findElement(By.xpath("./.."));
        List<WebElement> children = parentEl.findElements(By.xpath("./child::*"));

        if (children.size() < 2) {
            System.out.println("No meetings for " + day);
            return;
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 1; i < children.size(); i++) {
            String text = children.get(i).getText();
            String line = text.split("\n")[1];
            if (!line.contains(":")) {
                if (map.containsKey(line)) {
                    int num = map.get(line) + 1;
                    map.put(line, num);
                } else {
                    map.put(line, 1);
                }
            }
        }

        System.out.println("Number of meeting of the each person on " + day);

        map.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });

    }
}

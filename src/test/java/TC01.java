import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FindWorkshopPage;
import pages.MainPage;
import pages.SearchResultPage;

public class TC01 extends TestBase {

    private static final String MAIN_PAGE_TITLE = "WW (Weight Watchers): Weight Loss Program & Wellness Help | WW USA";
    private static final String WORKSHOP_PAGE_TITLE = "Find WW Studios & Meetings Near You | WW USA";
    private static final String SEARCH_LOCATION = "10011";

    @Test
    public void assertTitleMatchesWeightWatchers() {
        String title = new MainPage(driver).getTitle();
        Assert.assertEquals(title, MAIN_PAGE_TITLE, "Title doesn't match");
    }

    @Test(dependsOnMethods = {"assertTitleMatchesWeightWatchers"})
    public void assertTitleMatchesStudiosAndMeetings() {
        FindWorkshopPage workshopPage = new MainPage(driver)
                .clickOnAttend()
                .clickOnUnlimitedWorkshop();

        String title = workshopPage.getTitle();
        Assert.assertEquals(title, WORKSHOP_PAGE_TITLE, "Title doesn't match");
    }

    @Test(dependsOnMethods = {"assertTitleMatchesStudiosAndMeetings"})
    public void verifyNameAndPrintMeetings() {
        FindWorkshopPage workshopPage = new FindWorkshopPage(driver);
        workshopPage.clickOnStudio()
                .searchLocation(SEARCH_LOCATION);

        int searchResultNum = 1;
        String name = workshopPage.getResultName(searchResultNum);
        System.out.println("First result name: " + name);

        String distance = workshopPage.getResultDistance(searchResultNum);
        System.out.println("First result distance: " + distance);

        SearchResultPage resultPage = workshopPage.clickOnSearchResult(searchResultNum);

        String locationName = resultPage.getLocationName();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(name, locationName);

        resultPage.printTodayHoursOfOperation();
        resultPage.printMeetings("TUE");

        softAssert.assertAll();
    }
}
